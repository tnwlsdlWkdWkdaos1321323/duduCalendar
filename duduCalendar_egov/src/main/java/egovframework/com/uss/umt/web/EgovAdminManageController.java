package egovframework.com.uss.umt.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.uss.umt.service.AdminManageVO;
import egovframework.com.uss.umt.service.EgovAdminManageService;
import egovframework.com.uss.umt.service.UserDefaultVO;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.com.utl.sim.service.EgovFileScrty;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * 기업회원관련 요청을  비지니스 클래스로 전달하고 처리된결과를  해당   웹 화면으로 전달하는  Controller를 정의한다
 * @author 공통서비스 개발팀 조재영
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10 조재영				최초 생성
 *   2011.08.26	정진오			IncludedInfo annotation 추가
 *   2014.12.08	이기하			암호화방식 변경(EgovFileScrty.encryptPassword)
 *   2015.06.16	조정국			수정시 유효성체크 후 에러발생 시 목록으로 이동하여 에러메시지 표시
 *   2015.06.19	조정국			미인증 사용자에 대한 보안처리 기준 수정 (!isAuthenticated)
 *   2017.07.21  장동한 			로그인인증제한 작업
 *   2020.07.18  윤주호 			암호 설정 규칙 강화 및 버그 수정
 * </pre>
 */

@Controller
public class EgovAdminManageController {

	/** entrprsManageService */
	@Resource(name = "adminManageService")
	private EgovAdminManageService adminManageService;

	/** cmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** DefaultBeanValidator beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/** 비밀번호 힌트 조회 목록*/
	@ModelAttribute("passwordHint_result")
	private List<?> getPasswordHintResult(ComDefaultCodeVO vo) throws Exception{
		vo.setCodeId("COM022");
		return cmmUseService.selectCmmCodeDetail(vo);
	}
	
	/** 성별 조회 목록 */
	@ModelAttribute("sexdstnCode_result")
	private List<?> getSexdstnCode_result(ComDefaultCodeVO vo) throws Exception{
		vo.setCodeId("COM014");
		return cmmUseService.selectCmmCodeDetail(vo);
	}
	
	/** 사용자 상태 조회 목록 */
	@ModelAttribute("adminMberSttus_result")
	private List<?> getAdminMberSttus_result(ComDefaultCodeVO vo) throws Exception{
		vo.setCodeId("COM013");
		return cmmUseService.selectCmmCodeDetail(vo);
	}
	
	/** 그룹 정보 조회 목록 */
	@ModelAttribute("groupId_result")
	private List<?> getGroupId_result(ComDefaultCodeVO vo) throws Exception{
		vo.setTableNm("COMTNORGNZTINFO");
		return cmmUseService.selectGroupIdDetail(vo);
	}
	
	/** 기업 구분 조회 목록 */
	@ModelAttribute("adminSeCode_result")
	private List<?> getAdminSeCode_result(ComDefaultCodeVO vo) throws Exception{
		vo.setCodeId("COM026");
		return cmmUseService.selectCmmCodeDetail(vo);
	}
	
	/** 업종 구분 조회 목록 */
	@ModelAttribute("indutyCode_result")
	private List<?> getIndutyCode_result(ComDefaultCodeVO vo) throws Exception{
		vo.setCodeId("COM027");
		return cmmUseService.selectCmmCodeDetail(vo);
	}
	
	
	/**
	 * 관리자 등록화면으로 이동한다.
	 * @param userSearchVO 검색조건정보
	 * @param entrprsManageVO 기업회원 초기화정보
	 * @param model 화면모델
	 * @return uss/umt/EgovEntrprsMberInsert
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovAdminMberInsertView.do")
	public String insertAdminMberView(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, @ModelAttribute("adminManageVO") AdminManageVO adminManageVO, Model model)
			throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

		//패스워드힌트목록을 코드정보로부터 조회
//		vo.setCodeId("COM022");
//		List<?> passwordHint_result = cmmUseService.selectCmmCodeDetail(vo);
		//성별구분코드를 코드정보로부터 조회
//		vo.setCodeId("COM014");
//		List<?> sexdstnCode_result = cmmUseService.selectCmmCodeDetail(vo);
		//사용자상태코드를 코드정보로부터 조회
//		vo.setCodeId("COM013");
//		List<?> entrprsMberSttus_result = cmmUseService.selectCmmCodeDetail(vo);
		//그룹정보를 조회 - GROUP_ID정보
//		vo.setTableNm("COMTNORGNZTINFO");
//		List<?> groupId_result = cmmUseService.selectGroupIdDetail(vo);
		//기업구분코드를 코드정보로부터 조회 - COM026
//		vo.setCodeId("COM026");
//		List<?> entrprsSeCode_result = cmmUseService.selectCmmCodeDetail(vo);
		//업종코드를 코드정보로부터 조회 - COM027
//		vo.setCodeId("COM027");
//		List<?> indutyCode_result = cmmUseService.selectCmmCodeDetail(vo);

//		model.addAttribute("passwordHint_result", passwordHint_result); //패스워트힌트목록
//		model.addAttribute("sexdstnCode_result", sexdstnCode_result); //성별구분코드목록
//		model.addAttribute("entrprsMberSttus_result", entrprsMberSttus_result);//사용자상태코드목록
//		model.addAttribute("groupId_result", groupId_result); //그룹정보 목록
//		model.addAttribute("entrprsSeCode_result", entrprsSeCode_result); //기업구분코드 목록
//		model.addAttribute("indutyCode_result", indutyCode_result); //업종코드목록

		return "egovframework/com/uss/umt/EgovAdminMberInsert";
	}

	/**
	 * 관리자등록처리후 목록화면으로 이동한다.
	 * @param entrprsManageVO 신규기업회원정보
	 * @param bindingResult   입력값검증용  bindingResult
	 * @param model           화면모델
	 * @return forward:/uss/umt/EgovEntrprsMberManage.do
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovAdminMberInsert.do")
	public String insertAdminMber(@ModelAttribute("adminManageVO") AdminManageVO adminManageVO, BindingResult bindingResult, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		beanValidator.validate(adminManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			return "egovframework/com/uss/umt/EgovAdminMberInsert";
		} else {
			if ((adminManageVO == null ? "" : EgovStringUtil.isNullToString(adminManageVO.getGroupId())).equals("")) {
				adminManageVO.setGroupId(null);
			}
			adminManageService.insertAdminmber(adminManageVO);
			//Exception 없이 진행시 등록성공메시지
			model.addAttribute("resultMsg", "success.common.insert");
		}
		return "forward:/uss/umt/EgovAdminMberManage.do";

	}

	/**
	 * 기업회원정보 수정을 위해기업회원정보를 상세조회한다.
	 * @param entrprsmberId 상세조회 대상 기업회원아이디
	 * @param userSearchVO 조회조건정보
	 * @param model 화면모델
	 * @return uss/umt/EgovEntrprsMberSelectUpdt
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovAdminMberSelectUpdtView.do")
	public String updateAdminMberView(@RequestParam("selectedId") String adminmberId, @ModelAttribute("searchVO") UserDefaultVO userSearchVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		AdminManageVO adminManageVO = new AdminManageVO();
		adminManageVO = adminManageService.selectAdminmber(adminmberId);
		model.addAttribute("adminManageVO", adminManageVO);
		model.addAttribute("userSearchVO", userSearchVO);

		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		//패스워드힌트목록을 코드정보로부터 조회
//		vo.setCodeId("COM022");
//		List<?> passwordHint_result = cmmUseService.selectCmmCodeDetail(vo);
//		//성별구분코드를 코드정보로부터 조회
//		vo.setCodeId("COM014");
//		List<?> sexdstnCode_result = cmmUseService.selectCmmCodeDetail(vo);
//		//사용자상태코드를 코드정보로부터 조회
//		vo.setCodeId("COM013");
//		List<?> entrprsMberSttus_result = cmmUseService.selectCmmCodeDetail(vo);
//		//그룹정보를 조회 - GROUP_ID정보
//		vo.setTableNm("COMTNORGNZTINFO");
//		List<?> groupId_result = cmmUseService.selectGroupIdDetail(vo);
//		//기업구분코드를 코드정보로부터 조회 - COM026
//		vo.setCodeId("COM026");
//		List<?> entrprsSeCode_result = cmmUseService.selectCmmCodeDetail(vo);
//		//업종코드를 코드정보로부터 조회 - COM027
//		vo.setCodeId("COM027");
//		List<?> indutyCode_result = cmmUseService.selectCmmCodeDetail(vo);

//		model.addAttribute("passwordHint_result", passwordHint_result); //패스워트힌트목록
//		model.addAttribute("sexdstnCode_result", sexdstnCode_result); //성별구분코드목록
//		model.addAttribute("entrprsMberSttus_result", entrprsMberSttus_result);//사용자상태코드목록
//		model.addAttribute("groupId_result", groupId_result); //그룹정보 목록
//		model.addAttribute("entrprsSeCode_result", entrprsSeCode_result); //기업구분코드 목록
//		model.addAttribute("indutyCode_result", indutyCode_result); //업종코드목록

		return "egovframework/com/uss/umt/EgovAdminMberSelectUpdt";
	}
	
	/**
	 * 로그인인증제한 해제 
	 * @param entrprsManageVO 기업회원정보
	 * @param model 화면모델
	 * @return uss/umt/EgovEntrprsMberSelectUpdtView.do
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovAdminMberLockIncorrect.do")
	public String updateLockIncorrect(AdminManageVO adminManageVO, Model model) throws Exception {

	    
	    // 미인증 사용자에 대한 보안처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if (!isAuthenticated) {
	        return "index";
	    }
	    
	    adminManageService.updateLockIncorrect(adminManageVO);
	    
	    return "forward:/uss/umt/EgovAdminMberSelectUpdtView.do";
	}

	/**
	 * 기업회원정보 수정후 목록조회 화면으로 이동한다.
	 * @param entrprsManageVO 수정할 기업회원정보
	 * @param bindingResult 입력값 검증용 bindingResult
	 * @param model 화면모델
	 * @return forward:/uss/umt/EgovEntrprsMberManage.do
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovAdminMberSelectUpdt.do")
	public String updateAdminMber(@ModelAttribute("adminManageVO") AdminManageVO adminManageVO, BindingResult bindingResult, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		beanValidator.validate(adminManageVO, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("resultMsg", bindingResult.getAllErrors().get(0).getDefaultMessage());
			return "forward:/uss/umt/EgovAdminMberSelectUpdtView.do";
		} else {
			if ("".equals(adminManageVO.getGroupId())) {
				adminManageVO.setGroupId(null);
			}
			adminManageService.updateAdminmber(adminManageVO);
			//Exception 없이 진행시 수정성공메시지
			model.addAttribute("resultMsg", "success.common.update");
			return "forward:/uss/umt/EgovAdminMberManage.do";
		}
	}

	/**
	 * 기업회원정보삭제후 목록조회 화면으로 이동한다.
	 * @param checkedIdForDel 삭제대상아이디 정보
	 * @param userSearchVO 조회조건정보
	 * @param model 화면모델
	 * @return "forward:/uss/umt/EgovUserManage.do"
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovAdminMberDelete.do")
	public String deleteAdminMber(@RequestParam("checkedIdForDel") String checkedIdForDel, @ModelAttribute("searchVO") UserDefaultVO userSearchVO, Model model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		adminManageService.deleteAdminmber(checkedIdForDel);
		//Exception 없이 진행시 삭제성공메시지
		model.addAttribute("resultMsg", "success.common.delete");
		return "forward:/uss/umt/EgovAdminMberManage.do";
	}

	/**
	 * 기업회원목록을 조회한다. (pageing)
	 * @param userSearchVO 검색조건정보
	 * @param model 화면모델
	 * @return uss/umt/EgovEntrprsMberManage
	 * @throws Exception
	 */
	@IncludedInfo(name = "기업회원관리", order = 450, gid = 50)
	@RequestMapping(value = "/uss/umt/EgovAdminMberManage.do")
	public String selectAdminMberList(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, ModelMap model) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		/** EgovPropertyService.sample */
		userSearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		userSearchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(userSearchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(userSearchVO.getPageUnit());
		paginationInfo.setPageSize(userSearchVO.getPageSize());

		userSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		userSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		userSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

		List<?> adminList = adminManageService.selectAdminMberList(userSearchVO);
		model.addAttribute("resultList", adminList);

		int totCnt = adminManageService.selectAdminMberListTotCnt(userSearchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);

		//사용자상태코드를 코드정보로부터 조회
//		ComDefaultCodeVO vo = new ComDefaultCodeVO();
//		vo.setCodeId("COM013");
//		List<?> entrprsMberSttus_result = cmmUseService.selectCmmCodeDetail(vo);
//		model.addAttribute("entrprsMberSttus_result", entrprsMberSttus_result);//기업회원상태코드목록

		return "egovframework/com/uss/umt/EgovAdminMberManage";
	}

	/**
	 * 기업회원가입신청 등록화면으로 이동한다.
	 * @param userSearchVO 검색조건정보
	 * @param entrprsManageVO 기업회원초기화정보
	 * @param commandMap 파라메터전송 commandMap
	 * @param model 화면모델
	 * @return uss/umt/EgovEntrprsMberSbscrb
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovAdminMberSbscrbView.do")
	public String sbscrbAdminMberView(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, @ModelAttribute("adminManageVO") AdminManageVO adminManageVO,
			@RequestParam Map<String, Object> commandMap, Model model) throws Exception {

		ComDefaultCodeVO vo = new ComDefaultCodeVO();

//		//패스워드힌트목록을 코드정보로부터 조회
//		vo.setCodeId("COM022");
//		List<?> passwordHint_result = cmmUseService.selectCmmCodeDetail(vo);
//		//성별구분코드를 코드정보로부터 조회
//		vo.setCodeId("COM014");
//		List<?> sexdstnCode_result = cmmUseService.selectCmmCodeDetail(vo);
//		//기업구분코드를 코드정보로부터 조회 - COM026
//		vo.setCodeId("COM026");
//		List<?> entrprsSeCode_result = cmmUseService.selectCmmCodeDetail(vo);
//		//업종코드를 코드정보로부터 조회 - COM027
//		vo.setCodeId("COM027");
//		List<?> indutyCode_result = cmmUseService.selectCmmCodeDetail(vo);

//		model.addAttribute("passwordHint_result", passwordHint_result); //패스워트힌트목록
//		model.addAttribute("sexdstnCode_result", sexdstnCode_result); //성별구분코드목록
//		model.addAttribute("entrprsSeCode_result", entrprsSeCode_result); //기업구분코드 목록
//		model.addAttribute("indutyCode_result", indutyCode_result); //업종코드목록
		if (!"".equals(commandMap.get("realname"))) {
			model.addAttribute("applcntNm", commandMap.get("realname")); //실명인증된 이름 - 주민번호인증
			model.addAttribute("applcntIhidnum", commandMap.get("ihidnum")); //실명인증된 주민등록번호 - 주민번호 인증
		}
		if (!"".equals(commandMap.get("realName"))) {
			model.addAttribute("applcntNm", commandMap.get("realName")); //실명인증된 이름 - ipin인증
		}
		adminManageVO.setAdminMberSttus("DEFAULT");

		return "egovframework/com/uss/umt/EgovAdminMberSbscrb";
	}

	/**
	 * 기업회원가입신청 등록처리후 로그인화면으로 이동한다.
	 * @param entrprsManageVO 기업회원가입신청정보
	 * @return forward:/uat/uia/egovLoginUsr.do
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovAdminMberSbscrb.do")
	public String sbscrbAdminMber(@ModelAttribute("adminManageVO") AdminManageVO adminManageVO) throws Exception {

		//가입상태 초기화
		adminManageVO.setAdminMberSttus("A");
		//그룹정보 초기화
		//entrprsManageVO.setGroupId("1");
		//기업회원가입신청 등록시 기업회원등록기능을 사용하여 등록한다.
		adminManageService.insertAdminmber(adminManageVO);
		return "forward:/uat/uia/egovLoginUsr.do";
	}

	/**
	 * 기업회원 약관확인 화면을 조회한다.
	 * @param model 화면모델
	 * @return uss/umt/EgovStplatCnfirm
	 * @throws Exception
	 */
	@RequestMapping("/uss/umt/EgovStplatCnfirmAdmin.do")
	public String sbscrbAdminMber(Model model) throws Exception {

		//기업회원용 약관 아이디 설정
		String stplatId = "STPLAT_0000000000002";
		//회원가입유형 설정-기업회원
		String sbscrbTy = "USR02";
		//약관정보 조회
		List<?> stplatList = adminManageService.selectStplat(stplatId);
		model.addAttribute("stplatList", stplatList); //약관정보포함
		model.addAttribute("sbscrbTy", sbscrbTy); //회원가입유형포함

		return "egovframework/com/uss/umt/EgovStplatCnfirm";
	}

	/**
	 * 기업회원 암호 수정처리 후 화면 이동한다.
	 * @param model 화면모델
	 * @param commandMap 파라메터전달용 commandMap
	 * @param userSearchVO 검색조건정보
	 * @param entrprsManageVO 기업회원수정정보
	 * @return uss/umt/EgovEntrprsPasswordUpdt
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovAdminPasswordUpdt.do")
	public String updatePassword(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("adminManageVO") AdminManageVO adminManageVO) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		String oldPassword = (String) commandMap.get("oldPassword");
		String newPassword = (String) commandMap.get("newPassword");
		String newPassword2 = (String) commandMap.get("newPassword2");
		String uniqId = (String) commandMap.get("uniqId");

		boolean isCorrectPassword = false;
		AdminManageVO resultVO = new AdminManageVO();
		adminManageVO.setAdminMberPassword(newPassword);
		adminManageVO.setOldPassword(oldPassword);
		adminManageVO.setUniqId(uniqId);

		String resultMsg = "";
		resultVO = adminManageService.selectPassword(adminManageVO);
		//패스워드 암호화
		String encryptPass = EgovFileScrty.encryptPassword(oldPassword, adminManageVO.getAdminmberId());
		if (encryptPass.equals(resultVO.getAdminMberPassword())) {
			if (newPassword.equals(newPassword2)) {
				isCorrectPassword = true;
			} else {
				isCorrectPassword = false;
				resultMsg = "fail.user.passwordUpdate2";
			}
		} else {
			isCorrectPassword = false;
			resultMsg = "fail.user.passwordUpdate1";
		}

		if (isCorrectPassword) {
			adminManageVO.setAdminMberPassword(EgovFileScrty.encryptPassword(newPassword, adminManageVO.getAdminmberId()));
			adminManageService.updatePassword(adminManageVO);
			model.addAttribute("adminManageVO", adminManageVO);
			resultMsg = "success.common.update";
		} else {
			model.addAttribute("adminManageVO", adminManageVO);
		}
		model.addAttribute("userSearchVO", userSearchVO);
		model.addAttribute("resultMsg", resultMsg);

		return "egovframework/com/uss/umt/EgovAdminPasswordUpdt";
	}

	/**
	 * 기업회원암호 수정 화면 이동
	 * @param model 화면모델
	 * @param commandMap 파라메터전송용 commandMap
	 * @param userSearchVO 검색조건정보
	 * @param entrprsManageVO 기업회원수정정보
	 * @return uss/umt/EgovEntrprsPasswordUpdt
	 * @throws Exception
	 */
	@RequestMapping(value = "/uss/umt/EgovAdminPasswordUpdtView.do")
	public String updatePasswordView(ModelMap model, @RequestParam Map<String, Object> commandMap, @ModelAttribute("searchVO") UserDefaultVO userSearchVO,
			@ModelAttribute("adminManageVO") AdminManageVO adminManageVO) throws Exception {

		// 미인증 사용자에 대한 보안처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			return "index";
		}

		String userTyForPassword = (String) commandMap.get("userTyForPassword");
		adminManageVO.setUserTy(userTyForPassword);

		model.addAttribute("userSearchVO", userSearchVO);
		model.addAttribute("adminManageVO", adminManageVO);
		return "egovframework/com/uss/umt/EgovAdminPasswordUpdt";
	}

}