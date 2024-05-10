package egovframework.dudu.calendar.login.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.EgovComponentChecker;
import egovframework.com.cmm.EgovMessageSource;
/*import egovframework.dudu.calendar.login.service.LoginVO;*/
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.config.EgovLoginConfig;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.uat.uia.service.EgovLoginService;

@Controller
public class DuduLoginController {

	/** DuduLoginService 
	@Resource(name = "duduLoginService")
	private DuduLoginService loginService;*/
	
	/** EgovLoginService */
	@Resource(name = "loginService")
	private EgovLoginService loginService;

	/** EgovCmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	@Resource(name = "egovLoginConfig")
	EgovLoginConfig egovLoginConfig;

	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(DuduLoginController.class);

	/**
	 * 팀원 로그인 화면
	 */
	@IncludedInfo(name = "로그인", listUrl = "/uat/uia/duduLoginUsr.do", order = 10, gid = 10)
	@RequestMapping(value = "/uat/uia/duduLoginUsr.do")
	public String loginUsrView(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		if (EgovComponentChecker.hasComponent("mberManageService")) {
			model.addAttribute("useMemberManage", "true");
		}

		String auth_error =  request.getParameter("auth_error") == null ? "" : (String)request.getParameter("auth_error");
		if(auth_error != null && auth_error.equals("1")){
			return "egovframework/com/cmm/error/accessDenied";
		}
		
		String message = (String)request.getParameter("loginMessage");
		if (message!=null) model.addAttribute("loginMessage", message);
		
		return "dudu/calendar/login/duduLoginUsr";
	}
	
	/**
	 * 팀장 로그인 화면
	 */
	@IncludedInfo(name = "로그인", listUrl = "/uat/uia/duduLoginLeader.do", order = 10, gid = 10)
	@RequestMapping(value = "/uat/uia/duduLoginLeader.do")
	public String loginLeaderView(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		if (EgovComponentChecker.hasComponent("mberManageService")) {
			model.addAttribute("useMemberManage", "true");
		}

		String auth_error =  request.getParameter("auth_error") == null ? "" : (String)request.getParameter("auth_error");
		if(auth_error != null && auth_error.equals("1")){
			return "egovframework/com/cmm/error/accessDenied";
		}
		
		String message = (String)request.getParameter("loginMessage");
		if (message!=null) model.addAttribute("loginMessage", message);
		
		return "dudu/calendar/login/duduLoginLeader";
	}
	
	/**
	 * 관리자 로그인 화면
	 */
	@IncludedInfo(name = "로그인", listUrl = "/uat/uia/adminLogin.do", order = 10, gid = 10)
	@RequestMapping(value = "/uat/uia/adminLogin.do")
	public String adminLoginView(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		if (EgovComponentChecker.hasComponent("mberManageService")) {
			model.addAttribute("useMemberManage", "true");
		}

		String auth_error =  request.getParameter("auth_error") == null ? "" : (String)request.getParameter("auth_error");
		if(auth_error != null && auth_error.equals("1")){
			return "egovframework/com/cmm/error/accessDenied";
		}
		
		String message = (String)request.getParameter("loginMessage");
		if (message!=null) model.addAttribute("loginMessage", message);
		
		return "dudu/admin/login";
	}
	
	/**
	 * 로그아웃한다.
	*/
	@RequestMapping(value = "/uat/uia/actionLogout.do")
	public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception {
		request.getSession().setAttribute("loginVO", null);
		request.getSession().setAttribute("accessUser", null);
		
		// 로그아웃 컨트롤러나 서비스에서 세션 초기화
		HttpSession session = request.getSession(false); // 기존 세션이 없으면 새로 생성하지 않음
		if (session != null) {
		    String userId = (String) session.getAttribute("userId");
		    session.removeAttribute("loginMessageDisplayed_" + userId);
		    session.invalidate(); // 세션 무효화 (선택 사항)
		}

		/* model.addAttribute("logout", "로그아웃 되었습니다."); */

		return "redirect:/";
	}
	
}
