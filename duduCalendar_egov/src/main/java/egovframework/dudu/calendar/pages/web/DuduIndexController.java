package egovframework.dudu.calendar.pages.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.quartz.impl.calendar.DailyCalendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ibm.icu.util.DangiCalendar;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uat.uia.service.EgovLoginService;
import egovframework.dudu.admin.main.service.AdminCalendarService;
import egovframework.dudu.admin.management.service.ManageService;
import egovframework.dudu.calendar.dailywork.service.DuduWorkService;
import egovframework.dudu.calendar.dailywork.service.DuduWorkVO;
import egovframework.dudu.calendar.project.service.DuduProjectService;
import egovframework.dudu.calendar.project.service.DuduProjectVO;

@Controller
public class DuduIndexController implements ApplicationContextAware, InitializingBean {

	private ApplicationContext applicationContext;

	private static final Logger LOGGER = LoggerFactory.getLogger(DuduIndexController.class);

	public void afterPropertiesSet() throws Exception {}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
		LOGGER.info("EgovComIndexController setApplicationContext method has called!");
	}
	
	/** EgovLoginService */
	@Resource(name = "loginService")
	private EgovLoginService loginService;

	/** duduprojectService */
	@Resource(name = "duduprojectService")
	private DuduProjectService egovDeptSchdulManageService;

	/** duduWorkService */
	@Resource(name = "duduWorkService")
	private DuduWorkService egovIndvdlSchdulManageService;
	
	/** adminCalendarService */
	@Resource(name = "adminCalendarService")
	private AdminCalendarService adminCalendarService;
	
	@RequestMapping(value = "/calendar.do")
	public String mode() throws Exception{
		return "/dudu/calendar/pages/mode";
	}
	
	@RequestMapping("/dudu/calendar.do")
	public String setContent(@ModelAttribute("searchVO") ComDefaultVO searchVO,
	        @RequestParam Map<?, ?> commandMap,
	        DuduProjectVO deptSchdulManageVO,
	        DuduWorkVO indvdlSchdulManageVO,
	        ModelMap model) {

	    System.out.println("DuduIndexController 시작");

	    try {
	        // 설정된 비밀번호 유효기간을 가져온다. ex) 180이면 비밀번호 변경후 만료일이 앞으로 180일
	        String propertyExpirePwdDay = EgovProperties.getProperty("Globals.ExpirePwdDay");
	        int expirePwdDay = Integer.parseInt(propertyExpirePwdDay);
	        model.addAttribute("expirePwdDay", expirePwdDay);
	        System.out.println("로그인 후 경로 지정 컨트롤러 expirePwdDay : " + expirePwdDay);

	        // 비밀번호 설정일로부터 몇일이 지났는지 확인한다. ex) 3이면 비빌번호 설정후 3일 경과
	        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	        model.addAttribute("loginVO", loginVO);
	        int passedDayChangePWD = 0;
	        if (loginVO != null) {
	            // 비밀번호 변경후 경과한 일수
	            passedDayChangePWD = loginService.selectPassedDayChangePWD(loginVO);
	            LOGGER.debug("===>>> passedDayChangePWD = " + passedDayChangePWD);
	            model.addAttribute("passedDay", passedDayChangePWD);
	        }

	        // 만료일자로부터 경과한 일수 => ex)1이면 만료일에서 1일 경과
	        model.addAttribute("elapsedTimeExpiration", passedDayChangePWD - expirePwdDay);

	        deptSchdulManageVO.setOrgnztNm(loginVO.getOrgnztNm());
	        searchVO.setOrgnztNm(loginVO.getOrgnztNm());
	        
	        if (loginVO.getOfcpsNm() != null && loginVO.getOfcpsNm().equals("1")) {
	        	// 팀장 페이지 
	        	searchVO.setMberId(loginVO.getId());
	        	
	        	// loginMessage 부분
	            HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	            HttpSession session = httpRequest.getSession();
	            String userId = loginVO.getId();
	            Boolean loginMessageDisplayed = (Boolean) session.getAttribute("loginMessageDisplayed_" + userId);
	            if (loginMessageDisplayed != null && loginMessageDisplayed) {
	            } else {
	                session.setAttribute("loginMessageDisplayed_" + userId, true);
	                String loginMessage = loginVO.getOrgnztNm() + "팀 " + loginVO.getName() + "님 환영합니다.";
	                model.addAttribute("loginMessage", loginMessage);
	            }

	            // 프로젝트 CRUD
	            List<?> resultList = egovDeptSchdulManageService.selectDeptSchdulManageList(searchVO);
	            model.addAttribute("resultList", resultList);
	            System.out.println("DuduIndexController resultList : " + resultList);

	            // 사원 목록
	            List<?> selectMberList = egovDeptSchdulManageService.selectMberList(searchVO);
	            model.addAttribute("mber", selectMberList);
	            System.out.println("DuduIndexController selectMberList : " + selectMberList);

	            // 달력에 팀원 업무 내용 표시
	            List<?> teamWorkList = egovDeptSchdulManageService.selectTeamWorkList(searchVO);
	            model.addAttribute("teamWorkList", teamWorkList);
	            System.out.println("DuduIndexController teamWorkList : " + teamWorkList);

	            System.out.println("------------> loginVo.getOfcpsNm : " + loginVO.getOfcpsNm());
	            return "dudu/calendar/project/duduProjectMonthList";
	        } else if (loginVO.getOfcpsNm() != null && loginVO.getOfcpsNm().equals("2")) {
	            // 연구원 페이지
	            searchVO.setMberId(loginVO.getId());
	            searchVO.setSchdulChargerName(loginVO.getName());
	            System.out.println("DuduIndexController mberId : " + searchVO.getMberId());
	            System.out.println("DuduIndexController searchVO.setSchdulChargerName : " + searchVO.getSchdulChargerName());

	            // loginMessage 부분
	            HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	            HttpSession session = httpRequest.getSession();
	            String userId = loginVO.getId();
	            Boolean loginMessageDisplayed = (Boolean) session.getAttribute("loginMessageDisplayed_" + userId);
	            if (loginMessageDisplayed != null && loginMessageDisplayed) {
	            } else {
	                session.setAttribute("loginMessageDisplayed_" + userId, true);
	                String loginMessage = loginVO.getOrgnztNm() + "팀 " + loginVO.getName() + "님 환영합니다.";
	                model.addAttribute("loginMessage", loginMessage);
	            }

	            // 사이드 바에 참여 프로젝트 표시
	            List<?> teamProjectList = egovIndvdlSchdulManageService.selectTeamProjectList(searchVO);
	            model.addAttribute("teamProjectList", teamProjectList);
	            System.out.println("teamProjectList : " + teamProjectList);

	            // 일일 업무 CRUD & 달력 표시
	            List<?> resultList = egovIndvdlSchdulManageService.selectIndvdlSchdulManageList(searchVO);
	            model.addAttribute("resultList", resultList);
	            System.out.println("DuduIndexController resultList : " + resultList);

	            System.out.println("------------> loginVo.getOfcpsNm : " + loginVO.getOfcpsNm());
	            return "dudu/calendar/dailywork/duduWorkMonthList";
	        } else if(loginVO.getOfcpsNm() != null && loginVO.getOfcpsNm().equals("0")) {
	        	// 관리자 페이지
	        	searchVO.setMberId(loginVO.getId());
	        	
	        	// loginMessage 부분
	            HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	            HttpSession session = httpRequest.getSession();
	            String userId = loginVO.getId();
	            Boolean loginMessageDisplayed = (Boolean) session.getAttribute("loginMessageDisplayed_" + userId);
	            if (loginMessageDisplayed != null && loginMessageDisplayed) {
	            } else {
	                session.setAttribute("loginMessageDisplayed_" + userId, true);
	                String loginMessage = "관리자 페이지입니다.";
	                model.addAttribute("loginMessage", loginMessage);
	            }

	            // 전체 프로젝트 목록
	            List<?> resultList = adminCalendarService.selectProjectList(searchVO);
	            model.addAttribute("resultList", resultList);
	            System.out.println("DuduIndexController resultList : " + resultList);

	            // 달력에 전체 사원 일정 표시
	            List<?> teamWorkList = adminCalendarService.selectDuduWorkList(searchVO);
	            model.addAttribute("teamWorkList", teamWorkList);
	            System.out.println("DuduIndexController teamWorkList : " + teamWorkList);

	            System.out.println("------------> loginVo.getOfcpsNm : " + loginVO.getOfcpsNm());
	            
	        	return "dudu/admin/adminMain";
	        }
	    } catch (Exception e) {
	        LOGGER.error("데이터 처리 중 오류 발생: " + e.getMessage());
	        e.printStackTrace();
	        return "error-page";
	    }

	    return "error-page";
	}

}
