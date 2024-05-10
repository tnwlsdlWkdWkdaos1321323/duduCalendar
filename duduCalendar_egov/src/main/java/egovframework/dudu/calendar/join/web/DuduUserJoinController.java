package egovframework.dudu.calendar.join.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.dudu.calendar.join.service.DuduUserJoinService;
import egovframework.dudu.calendar.join.service.DuduUserJoinVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;


@Controller
public class DuduUserJoinController {

	/** duduUserJoinService */
	@Resource(name = "duduUserJoinService")
	private DuduUserJoinService duduUserJoinService;

	/** cmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** propertiesService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** DefaultBeanValidator beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** 비밀번호 힌트 조회 목록 */
	@ModelAttribute("passwordHint_result")
	private List<?> getPasswordHintResult(ComDefaultCodeVO vo) throws Exception {
		vo.setCodeId("COM022");
		return cmmUseService.selectCmmCodeDetail(vo);
	}

	/** 성별 조회 목록 */
	@ModelAttribute("sexdstnCode_result")
	private List<?> getSexdstnCode_result(ComDefaultCodeVO vo) throws Exception {
		vo.setCodeId("COM014");
		return cmmUseService.selectCmmCodeDetail(vo);
	}

	/** 사용자 상태 조회 목록 */
	@ModelAttribute("entrprsMberSttus_result")
	private List<?> getEntrprsMberSttus_result(ComDefaultCodeVO vo) throws Exception {
		vo.setCodeId("COM013");
		return cmmUseService.selectCmmCodeDetail(vo);
	}

	/** 그룹 정보 조회 목록 */
	@ModelAttribute("groupId_result")
	private List<?> getGroupId_result(ComDefaultCodeVO vo) throws Exception {
		vo.setTableNm("COMTNORGNZTINFO");
		return cmmUseService.selectGroupIdDetail(vo);
	}

	/** 기업 구분 조회 목록 */
	@ModelAttribute("entrprsSeCode_result")
	private List<?> getEntrprsSeCode_result(ComDefaultCodeVO vo) throws Exception {
		vo.setCodeId("COM026");
		return cmmUseService.selectCmmCodeDetail(vo);
	}

	/** 업종 구분 조회 목록 */
	@ModelAttribute("indutyCode_result")
	private List<?> getIndutyCode_result(ComDefaultCodeVO vo) throws Exception {
		vo.setCodeId("COM027");
		return cmmUseService.selectCmmCodeDetail(vo);
	}	
	
	/**
     * 부서 목록 조회
     */
	@RequestMapping(value="/uss/umt/join/duduDeptPopup.do")
	public String egovMeetingManageLisAuthorGroupPopupPost (
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
    		ModelMap model)
    throws Exception {

    	 List<?> resultList = duduUserJoinService.selectDeptList(searchVO);
         model.addAttribute("resultList", resultList);

    	return "dudu/calendar/join/duduDeptPopup";
    }

	/**
	 * 회원 가입 화면으로 이동
	 */
	@RequestMapping("/uss/umt/joinView.do")
	public String sbscrbEntrprsMberView(
			@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, 
			@ModelAttribute("duduUserJoinVO") DuduUserJoinVO duduUserJoinVO,
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<String, Object> commandMap, 
			Model model) 
	throws Exception {

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		if (!"".equals(commandMap.get("realname"))) {
			model.addAttribute("applcntNm", commandMap.get("realname"));
			model.addAttribute("applcntIhidnum", commandMap.get("ihidnum"));
		}
		if (!"".equals(commandMap.get("realName"))) {
			model.addAttribute("applcntNm", commandMap.get("realName"));
		}
		duduUserJoinVO.setEntrprsMberSttus("DEFAULT");
		List<?> selectDeptList = duduUserJoinService.selectDeptList(searchVO);
		model.addAttribute("selectDeptList", selectDeptList);

		return "dudu/calendar/join/duduJoin";
	}

	/**
	 * 회원가입 신청 후 로그인 화면으로 이동
	 */
	@RequestMapping("/uss/umt/join.do")
	public String sbscrbEntrprsMber(@ModelAttribute("duduUserJoinVO") DuduUserJoinVO duduUserJoinVO) throws Exception {

		try {
			duduUserJoinVO.setEntrprsMberSttus("A");
			duduUserJoinService.insertEntrprsmber(duduUserJoinVO);
			return "forward:/calendar.do";
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
	}

	/**
	 * 모달조회
	 */
    @RequestMapping(value="/joinModal.do")
    public String selectUtlJsonInquire()  throws Exception {
        return "dudu/calendar/join/duduJoinModal";
    }
    
    /**
	 * 비밀번호 초기화 화면
	 */
	@RequestMapping(value = "/uat/uia/initPasswordView.do")
	public String intiPasswordView(@ModelAttribute("searchVO") ComDefaultVO searchVO, ModelMap model) throws Exception {
		
		// 부서 선택
		List<?> resultList = duduUserJoinService.selectDeptList(searchVO);
        model.addAttribute("resultList", resultList);
        
		return "dudu/calendar/join/duduPasswordSearch";
	}
    
    /**
     * 비밀번호 초기화
     */
    @RequestMapping(value = "/uat/uia/initPassword.do")
    public String InitPassword(@ModelAttribute("loginVO") LoginVO loginVO, ModelMap model) throws Exception{
    	System.out.println("비밀번호 초기화 컨트롤로");
    	try {
    		duduUserJoinService.initPassword(loginVO);
    		model.addAttribute("initMessage", "비밀번호 초기화가 완료되었습니다. 관리자에게 문의하세요");
    		return "dudu/calendar/login/duduLoginUsr";
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
    }
    
    /**
	 * 비밀번호 찾기 화면
	 */
	@RequestMapping(value = "/uat/uia/passwordSearchView.do")
	public String passwordSearchView(@ModelAttribute("searchVO") ComDefaultVO searchVO, ModelMap model) throws Exception {
		
		// 부서 선택
		List<?> resultList = duduUserJoinService.selectDeptList(searchVO);
        model.addAttribute("resultList", resultList);
        
		return "dudu/calendar/join/duduPasswordSearch";
	}
	
	/**
	 * 비밀번호를 찾는다.
	 */
	@RequestMapping(value = "/uat/uia/searchPassword.do")
	public String searchPassword(@ModelAttribute("loginVO") LoginVO loginVO, ModelMap model) throws Exception {
		System.out.println("DuduLoginController 비밀번호 찾기");
		System.out.println("loginVO.getName : " + loginVO.getName());
		System.out.println("loginVO.getId : " + loginVO.getId());
		System.out.println("loginVO.getOrgnztNm : " + loginVO.getOrgnztNm());
		try {
			if (loginVO == null || loginVO.getId() == null || loginVO.getId().equals("") && loginVO.getName() == null || "".equals(loginVO.getName()) && loginVO.getOrgnztNm() == null || loginVO.getOrgnztNm().equals("")) {
				return "egovframwork/com/cmm/error/egovError";
			}

			// 1. 비밀번호 찾기
			String newpassword = duduUserJoinService.searchPassword(loginVO);

			// 2. 결과 리턴
			if (newpassword != null & !newpassword.isEmpty()) {
				model.addAttribute("resultInfo", "새로 발급된 임시 비밀번호<br> " + newpassword);
				return "dudu/calendar/join/duduPasswordResult";
			} else {
				model.addAttribute("resultInfo", "입력하신 정보가 일치하지 않아 비밀번호 찾기에 실패하였습니다.");
				return "dudu/calendar/join/duduPasswordResult";
			}
		} catch (Exception e) {
			model.addAttribute("resultInfo", "입력하신 정보가 일치하지 않아<br>비밀번호 찾기에 실패하였습니다.");
			return "dudu/calendar/join/duduPasswordResult";
		}
	} 
	
	/**
	 * 비밀번호 변경 화면
	 */
	@RequestMapping(value = "/uat/uia/updatePasswordView.do")
	public String updatePasswordView() throws Exception{
		return "dudu/calendar/join/duduPasswordUpdate";
	}
	
	/**
	 * 비밀번호 변경 처리
	 */
	@RequestMapping(value = "/uat/uia/updatePassword.do")
	public String updatePassword(@ModelAttribute("loginVO") LoginVO loginVO, ModelMap model, HttpServletRequest request) throws Exception {
	    System.out.println("DuduUserJoinController 비밀번호 변경 시작");
	    String newPassword = request.getParameter("password");
	    try {
	        loginVO.setPassword(newPassword);
	        duduUserJoinService.updateUserPassword(loginVO);
	        model.addAttribute("password", "비밀번호 변경이 완료되었습니다. 다시 로그인해주세요.");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "dudu/calendar/login/duduLoginUsr";
	}

}