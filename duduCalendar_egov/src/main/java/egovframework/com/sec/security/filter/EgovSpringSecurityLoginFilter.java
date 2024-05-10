package egovframework.com.sec.security.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.ui.Model;
import org.springframework.web.context.support.WebApplicationContextUtils;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.config.EgovLoginConfig;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uat.uia.service.EgovLoginService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

public class EgovSpringSecurityLoginFilter implements Filter {

	private FilterConfig config;

	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSpringSecurityLoginFilter.class);

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		LOGGER.info("EgovSpringSecurityLoginFilter called...");

		// 로그인 URL
		 String loginURL = config.getInitParameter("loginURL"); 
		loginURL = loginURL.replaceAll("\r", "").replaceAll("\n", "");

		String loginProcessURL = config.getInitParameter("loginProcessURL");
		loginProcessURL = loginProcessURL.replaceAll("\r", "").replaceAll("\n", "");

		ApplicationContext act = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
		EgovLoginService loginService = (EgovLoginService) act.getBean("loginService");
		EgovLoginConfig egovLoginConfig = (EgovLoginConfig) act.getBean("egovLoginConfig");
		
		EgovMessageSource egovMessageSource = (EgovMessageSource) act.getBean("egovMessageSource");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		//String isLocallyAuthenticated = (String)session.getAttribute("isLocallyAuthenticated");
		String isRemotelyAuthenticated = (String) session.getAttribute("isRemotelyAuthenticated");

		String requestURL = ((HttpServletRequest) request).getRequestURI();

		//스프링 시큐리티 인증이 처리 되었는지 EgovUserDetailsHelper.getAuthenticatedUser() 메서드를 통해 확인한다.
		//context-common.xml 빈 설정에 egovUserDetailsSecurityService를 등록 해서 사용해야 정상적으로 동작한다.
		if (EgovUserDetailsHelper.getAuthenticatedUser() == null || requestURL.contains(loginProcessURL)) {

			if (isRemotelyAuthenticated != null && isRemotelyAuthenticated.equals("true")) {
				try {
					//세션 토큰 정보를 가지고 DB로부터 사용자 정보를 가져옴
					LoginVO loginVO = (LoginVO) session.getAttribute("loginVOForDBAuthentication");
					loginVO = loginService.actionLoginByEsntlId(loginVO);

					if (loginVO != null && loginVO.getId() != null && !loginVO.getId().equals("")) {
						//세션 로그인
						session.setAttribute("loginVO", loginVO);

						//로컬 인증결과 세션에 저장
						session.setAttribute("isLocallyAuthenticated", "true");

						//스프링 시큐리티 로그인
						//httpResponse.sendRedirect(httpRequest.getContextPath() + "/j_spring_security_check?j_username=" + loginVO.getUserSe() + loginVO.getId() + "&j_password=" + loginVO.getUniqId());

						UsernamePasswordAuthenticationFilter springSecurity = null;

						Map<String, UsernamePasswordAuthenticationFilter> beans = act.getBeansOfType(UsernamePasswordAuthenticationFilter.class);
						if (beans.size() > 0) {
							springSecurity = (UsernamePasswordAuthenticationFilter) beans.values().toArray()[0];
							springSecurity.setUsernameParameter("egov_security_username");
							springSecurity.setPasswordParameter("egov_security_password");
							springSecurity.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(request.getServletContext().getContextPath() +"/egov_security_login", "POST"));
						} else {
							LOGGER.error("No AuthenticationProcessingFilter");
							throw new IllegalStateException("No AuthenticationProcessingFilter");
						}
						//springSecurity.setContinueChainBeforeSuccessfulAuthentication(false);	// false 이면 chain 처리 되지 않음.. (filter가 아닌 경우 false로...)

						LOGGER.debug("before security filter call....");
						springSecurity.doFilter(new RequestWrapperForSecurity(httpRequest, loginVO.getUserSe() + loginVO.getId(), loginVO.getUniqId()), httpResponse, chain);
						LOGGER.debug("after security filter call....");

					}
				} catch(IllegalArgumentException e) {
					LOGGER.error("[IllegalArgumentException] Try/Catch...usingParameters Runing : "+ e.getMessage());
				} catch(Exception e) {
					LOGGER.error("["+e.getClass()+"] Try/Catch...Exception : " + e.getMessage());
				}

			} else if (isRemotelyAuthenticated == null) {
				if (requestURL.contains(loginProcessURL)) {

					String password = httpRequest.getParameter("password");
					String id = httpRequest.getParameter("id");
					
					// 보안점검 후속 조치(Password 검증)
					if ((id == null || "".equals(id)) && (password == null || "".equals(password))) {
						RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
						httpRequest.setAttribute("loginMessage", "");
						dispatcher.forward(httpRequest, httpResponse);
						//chain.doFilter(request, response);
						return;
					}
					else if (password == null || password.equals("") || password.length() < 8 || password.length() > 20) {
						httpRequest.setAttribute("loginMessage", egovMessageSource.getMessage("fail.common.login.password",request.getLocale()));
						RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
						
						dispatcher.forward(httpRequest, httpResponse);
						//chain.doFilter(request, response);
						return;
					}

					LoginVO loginVO = new LoginVO();

					loginVO.setId(httpRequest.getParameter("id"));
					loginVO.setPassword(password);
					loginVO.setUserSe(httpRequest.getParameter("userSe"));
					
					loginVO.getPermisId();
					System.out.println("permisId : " + loginVO.getPermisId());
					//------------------------------------------------------------------
				    // 사용자 로그인 처리
				    //------------------------------------------------------------------
					try {
						//사용자 입력 id, password로 DB 인증을 실행함
						loginVO = loginService.actionLogin(loginVO);
						//사용자 IP 기록
						loginVO.setIp(request.getRemoteAddr());
						
						/**
						 * permisId
						 * I : 비밀번호 초기화 상태
						 * T : 관리자 -> 임시 비밀번호 발급 상태
						 * O : 사용자 -> 임시 비밀번호 발급 후 비밀번호 변경 상태(로그인O)
						 */
						
						if (loginVO != null && loginVO.getId() != null && !loginVO.getId().equals("")) {
							System.out.println("156line permisId : " + loginVO.getPermisId());
							
							if (loginVO.getPermisId().equals("I")) {
								System.out.println("permisId : " + loginVO.getPermisId());
								
								httpRequest.setAttribute("loginMessage", "현재 비밀번호 초기화 상태입니다. 관리자에게 문의하세요.");
								RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/uat/uia/duduLoginUsr.do");
								dispatcher.forward(httpRequest, httpResponse);
								
								return;
							}
							
							if (loginVO.getPermisId().equals("T")) {
							    System.out.println("192line getPermisId : " + loginVO.getPermisId());
							    
							    httpRequest.setAttribute("loginMessage", "임시 비밀번호를 발급받은 경우 비밀번호를 변경해야 합니다. 비밀번호 변경 페이지로 이동하겠습니다.");
								RequestDispatcher dispatcher = httpRequest.getRequestDispatcher("/uat/uia/updatePasswordView.do");
								dispatcher.forward(httpRequest, httpResponse);

							    return;
							}
							//세션 로그인
							session.setAttribute("loginVO", loginVO);

							//로컬 인증결과 세션에 저장
							session.setAttribute("isLocallyAuthenticated", "true");

							//스프링 시큐리티 로그인
							// httpResponse.sendRedirect(httpRequest.getContextPath() + "/j_spring_security_check?j_username=" + loginVO.getUserSe() + loginVO.getId() + "&j_password=" + loginVO.getUniqId());

							UsernamePasswordAuthenticationFilter springSecurity = null;

							Map<String, UsernamePasswordAuthenticationFilter> beans = act.getBeansOfType(UsernamePasswordAuthenticationFilter.class);
							if (beans.size() > 0) {
								springSecurity = (UsernamePasswordAuthenticationFilter) beans.values().toArray()[0];
								springSecurity.setUsernameParameter("egov_security_username");
								springSecurity.setPasswordParameter("egov_security_password");
								springSecurity.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(request.getServletContext().getContextPath() +"/egov_security_login", "POST"));
							} else {
								LOGGER.error("No AuthenticationProcessingFilter");
								throw new IllegalStateException("No AuthenticationProcessingFilter");
							}
							//springSecurity.setContinueChainBeforeSuccessfulAuthentication(false);	// false 이면 chain 처리 되지 않음.. (filter가 아닌 경우 false로...)

							LOGGER.debug("before security filter call....");
							springSecurity.doFilter(new RequestWrapperForSecurity(httpRequest, loginVO.getUserSe() + loginVO.getId(), loginVO.getUniqId()), httpResponse, chain);
							LOGGER.debug("after security filter call....");
							httpRequest.setAttribute("loginMessage", loginVO.getOrgnztNm() + loginVO.getName() + "님 환영홥니다.");
						} else {
							System.out.println("id : " + loginVO.getId());
							System.out.println("permisId : " + loginVO.getPermisId());
							//사용자 정보가 없는 경우 로그인 화면으로 redirect 시킴
							httpRequest.setAttribute("loginMessage", "로그인에 실패하였습니다.");
							RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
							dispatcher.forward(httpRequest, httpResponse);
							
							return;
						}
			        } catch(IllegalArgumentException e) {
			            LOGGER.error("[IllegalArgumentException] : "+ e.getMessage());
					} catch (Exception ex) {
						//DB인증 예외가 발생할 경우 로그인 화면으로 redirect 시킴
						LOGGER.error("Login Exception : {}", ex.getCause(), ex);
						httpRequest.setAttribute("loginMessage", egovMessageSource.getMessage("fail.common.login",request.getLocale()));
						RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginURL);
						dispatcher.forward(httpRequest, httpResponse);
						//chain.doFilter(request, response);
						return;
					}
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}
}

class RequestWrapperForSecurity extends HttpServletRequestWrapper {
	private String username = null;
	private String password = null;

	public RequestWrapperForSecurity(HttpServletRequest request, String username, String password) {
		super(request);

		this.username = username;
		this.password = password;
	}
	
	@Override
	public String getServletPath() {
		return ((HttpServletRequest) super.getRequest()).getContextPath() + "/egov_security_login";
	}

	@Override
	public String getRequestURI() {
		return ((HttpServletRequest) super.getRequest()).getContextPath() + "/egov_security_login";
	}

	@Override
	public String getParameter(String name) {
		if (name.equals("egov_security_username")) {
			return username;
		}

		if (name.equals("egov_security_password")) {
			return password;
		}

		return super.getParameter(name);
	}
}
