package egovframework.dudu.calendar.project.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.utl.fcc.service.EgovStringUtil;
import egovframework.dudu.calendar.dailywork.service.DuduWorkService;
import egovframework.dudu.calendar.dailywork.service.DuduWorkVO;
import egovframework.dudu.calendar.project.service.DuduProjectService;
import egovframework.dudu.calendar.project.service.DuduProjectVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class DuduProjectController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DuduProjectController.class);

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

	@Resource(name = "duduprojectService")
	private DuduProjectService egovDeptSchdulManageService;
	
	@Resource(name = "duduWorkService")
	private DuduWorkService egovIndvdlSchdulManageService;

	@Resource(name="EgovCmmUseService")
	private EgovCmmUseService cmmUseService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	// 첨부파일 관련
	@Resource(name="EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name="EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	
	/**
	 *  프로젝트를 등록한다. / 등록 처리 한다.
	 */
	@RequestMapping(value="/dudu/calendar/projectRegist.do")
	public String deptSchdulManageRegistActor(
	    @ModelAttribute("duduProjectVO") DuduProjectVO deptSchdulManageVO,
	    ModelMap model) throws Exception {
	    System.out.println("DuduProjectController deptSchdulManageRegistActor 실행");

	    // 0. Spring Security 사용자권한 처리
	    Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    if(!isAuthenticated) {
	        model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        return "mode";
	    }

	    // 로그인 객체 선언
	    LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	    
	    // 아이디 설정
	    deptSchdulManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
	    deptSchdulManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
	    deptSchdulManageVO.setProjectChargerId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getId()));
	    
	    deptSchdulManageVO.setProjectDeptName(loginVO.getOrgnztNm());

	    // 중복 제거된 값으로 설정
	    String projectPer = deptSchdulManageVO.getProjectPer();
	    String[] parts = projectPer.split(",");
	    Set<String> uniqueValues = new HashSet<>();
	    Collections.addAll(uniqueValues, parts); // 배열을 Set으로 변환
	    String uniqueProjectPer = String.join(",", uniqueValues);
	    deptSchdulManageVO.setProjectPer(uniqueProjectPer);

	    System.out.println("DuduProjectController uniqueProjectPer : " + uniqueProjectPer);
	    System.out.println("DuduProjectController 프로젝트 등록 deptSchdulManageVO.getProjectPer : " + deptSchdulManageVO.getProjectPer());
	    try {
	        egovDeptSchdulManageService.insertDeptSchdulManage(deptSchdulManageVO);
	        System.out.println("서비스로 넘어갔어요");
	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("errorMessage", "프로젝트 등록 중 오류가 발생했습니다.");
	        return "errorPage"; 
	    }
	    
	    return "redirect:/dudu/calendar.do";
	}
	
	/**
	 * 프로젝트 목록 조회
	 */
	@IncludedInfo(name="프로젝트 목록", order = 320 ,gid = 40)
	@RequestMapping(value="/dudu/calendar/projectList.do")
	public String egovDeptSchdulManageList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			DuduProjectVO deptSchdulManageVO,
    		ModelMap model)
    throws Exception {
		System.out.println("DuduProjectController 목록 조회");
		
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("loginVO", loginVO);

		searchVO.setOrgnztNm(loginVO.getOrgnztNm());

        List<?> resultList = egovDeptSchdulManageService.selectDeptSchdulManageList(searchVO);
        model.addAttribute("resultList", resultList);
        System.out.println("DuduProjectController resultList : " + resultList);
        
		return "dudu/calendar/project/duduProjectList";
	}
	
	/**
	 * 프로젝트 수정 폼
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/dudu/calendar/projectUpdateView.do")
	public String deptSchdulManageModify(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			DuduProjectVO deptSchdulManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {
		try {
			System.out.println("DuduProjectController 수정 폼 실행");
			
			LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	        model.addAttribute("loginVO", loginVO);
	        
	        deptSchdulManageVO.setOrgnztNm(loginVO.getOrgnztNm());
	        searchVO.setOrgnztNm(loginVO.getOrgnztNm());
	        
	        // 사원 목록
            List<?> selectMberList = egovDeptSchdulManageService.selectMberList(searchVO);
            model.addAttribute("mber", selectMberList);
            System.out.println("DuduIndexController selectMberList : " + selectMberList);
			
	    	//일정시작일자(시)
	    	model.addAttribute("schdulBgndeHH", (List<?>)getTimeHH());
	    	//일정시작일자(분)
	    	model.addAttribute("schdulBgndeMM", (List<?>)getTimeMM());
	    	//일정종료일자(시)
	    	model.addAttribute("schdulEnddeHH", (List<?>)getTimeHH());
	    	//일정정료일자(분)
	    	model.addAttribute("schdulEnddeMM", (List<?>)getTimeMM());

	    	EgovMap resultDeptSchdulManageVOReuslt = egovDeptSchdulManageService.selectDeptSchdulManageDetailVO(deptSchdulManageVO);
	    	System.out.println("ALERT resultDeptSchdulManageVOReuslt : " + resultDeptSchdulManageVOReuslt.toString());

	    	model.addAttribute("deptSchdulManageVO", resultDeptSchdulManageVOReuslt);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "dudu/calendar/project/duduProjectModalView";
	}
	
	/**
	 * 프로젝트 수정처리
	  */
	@RequestMapping(value="/dudu/calendar/projectUpdateActor.do")
	public String deptSchdulManageModifyActor(
			ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("deptSchdulManageVO") DuduProjectVO deptSchdulManageVO,
			BindingResult bindingResult,
			@RequestParam("projectId") String projectId,
    		ModelMap model)
    throws Exception {
		
		System.out.println("DuduProjectController 프로젝트 수정 처리");
		
		try {
	    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	if(!isAuthenticated) {
	    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        	return "egovframework/com/uat/uia/EgovLoginUsr";
	    	}

			LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			
			model.addAttribute("schdulBgndeHH", (List<?>)getTimeHH());
			model.addAttribute("schdulBgndeMM", (List<?>)getTimeMM());
			model.addAttribute("schdulEnddeHH", (List<?>)getTimeHH());
			model.addAttribute("schdulEnddeMM", (List<?>)getTimeMM());
	        
			deptSchdulManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			deptSchdulManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			
			// 중복 제거된 값으로 설정
		    String projectPer = deptSchdulManageVO.getProjectPer();
		    String[] parts = projectPer.split(",");
		    Set<String> uniqueValues = new HashSet<>();
		    Collections.addAll(uniqueValues, parts); // 배열을 Set으로 변환
		    String uniqueProjectPer = String.join(",", uniqueValues);
		    deptSchdulManageVO.setProjectPer(uniqueProjectPer);

	    	egovDeptSchdulManageService.updateDeptSchdulManage(deptSchdulManageVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("프로젝트 수정 처리 getProjectId : " + deptSchdulManageVO.getProjectId());

		return "redirect:/dudu/calendar.do";
		
	} 
	
	/**
	 * 프로젝트 삭제처리
	  */
	@RequestMapping(value="/dudu/calendar/projectDeleteActor.do")
	public String projectDeleteActor(
			ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("deptSchdulManageVO") DuduProjectVO deptSchdulManageVO,
			BindingResult bindingResult,
			@RequestParam("projectId") String projectId,
    		ModelMap model)
    throws Exception {
		
		System.out.println("DuduProjectController 프로젝트 삭제 처리");
		
		try {
	    	Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	if(!isAuthenticated) {
	    		model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
	        	return "egovframework/com/uat/uia/EgovLoginUsr";
	    	}

			LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			  
			model.addAttribute("schdulBgndeHH", (List<?>)getTimeHH());
			model.addAttribute("schdulBgndeMM", (List<?>)getTimeMM());
			model.addAttribute("schdulEnddeHH", (List<?>)getTimeHH());
			model.addAttribute("schdulEnddeMM", (List<?>)getTimeMM());
			  
			deptSchdulManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			deptSchdulManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			
	    	egovDeptSchdulManageService.deleteDeptSchdulManage(deptSchdulManageVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("프로젝트 삭제 처리 getProjectId : " + deptSchdulManageVO.getProjectId());

		return "redirect:/dudu/calendar.do";
		
	} 
	
	/**
	 * 팀원 일정 목록보기
	 */
	@IncludedInfo(name="팀원일정 목록보기", order = 320 ,gid = 40)
	@RequestMapping(value="/dudu/calendar/userWorkList.do")
	public String duduUserWorkList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
	        @RequestParam Map<?, ?> commandMap,
	        DuduProjectVO deptSchdulManageVO,
	        DuduWorkVO indvdlSchdulManageVO,
	        ModelMap model)
    throws Exception {
		System.out.println("DuduProjectController 팀원일정 목록 조회");
		try {
			LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	        model.addAttribute("loginVO", loginVO);
			
			searchVO.setOrgnztNm(loginVO.getOrgnztNm());
			searchVO.setSchdulBgnde(indvdlSchdulManageVO.getSchdulBgnde());

			System.out.println("searchVO.getOrgnztNm : " + searchVO.getOrgnztNm());
			System.out.println("indevdlSchdulManageVO.getSchdulBgnde : " + indvdlSchdulManageVO.getSchdulBgnde());
			System.out.println("searchVO.getSchdulBgnde : " + searchVO.getSchdulBgnde());

	        List<?> workList = egovDeptSchdulManageService.selectUserWorkList(searchVO);
	        model.addAttribute("workList", workList);
	        System.out.println("DuduProjectController workList : " + workList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "dudu/calendar/project/userWorkListModalView";
	}

	/**
	 * 팀원 일정 상세보기 
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/dudu/calendar/userWorkDetail.do")
	public String indvdlSchdulManageModify(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			DuduWorkVO indvdlSchdulManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {
		System.out.println("DuduWorkController 일정 상세보기 컨트롤러 시작");

		try {
			LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	        model.addAttribute("loginVO", loginVO);

	        System.out.println("DuduWorkController getOrgnztNm : " + loginVO.getOrgnztNm());
	        System.out.println("DuduWorkController searchVO getOrgnztNm : " + searchVO.getOrgnztNm());
	        
	        searchVO.setOrgnztNm(loginVO.getOrgnztNm());
			
	    	List<?> teamProjectList = egovIndvdlSchdulManageService.selectTeamProjectList(searchVO);
            model.addAttribute("teamProjectList", teamProjectList);
            System.out.println("teamProjectList : " + teamProjectList);

	    	EgovMap resultIndvdlSchdulManageVOReuslt = egovIndvdlSchdulManageService.selectIndvdlSchdulManageDetailVO(indvdlSchdulManageVO);
	    	System.out.println("일정 상세보기 컨트롤러 resultIndvdlSchdulmanageVOResult : " + resultIndvdlSchdulManageVOReuslt);

	    	model.addAttribute("indvdlSchdulManageVO", resultIndvdlSchdulManageVOReuslt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "dudu/calendar/project/duduUserWorkModalView";
	}
	
	/**
	 * 모달 -> 팀원 일정 상세보기 
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/dudu/calendar/moUserWorkDetail.do")
	public String moUserWorkDetail(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			DuduWorkVO indvdlSchdulManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {
		System.out.println("DuduWorkController 일정 상세보기 컨트롤러 시작");

		try {
			LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
	        model.addAttribute("loginVO", loginVO);

	        System.out.println("DuduWorkController getOrgnztNm : " + loginVO.getOrgnztNm());
	        System.out.println("DuduWorkController searchVO getOrgnztNm : " + searchVO.getOrgnztNm());
	        
	        searchVO.setOrgnztNm(loginVO.getOrgnztNm());
			
	    	List<?> teamProjectList = egovIndvdlSchdulManageService.selectTeamProjectList(searchVO);
            model.addAttribute("teamProjectList", teamProjectList);
            System.out.println("teamProjectList : " + teamProjectList);

	    	EgovMap resultIndvdlSchdulManageVOReuslt = egovIndvdlSchdulManageService.selectIndvdlSchdulManageDetailVO(indvdlSchdulManageVO);
	    	System.out.println("일정 상세보기 컨트롤러 resultIndvdlSchdulmanageVOResult : " + resultIndvdlSchdulManageVOReuslt);

	    	model.addAttribute("indvdlSchdulManageVO", resultIndvdlSchdulManageVOReuslt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "dudu/calendar/project/moUserWorkModalView";
	}
	
	/**
	 * 시간을 LIST를 반환한다.
	 * @return  List
	 * @throws
	 */
	@SuppressWarnings("unused")
	private List<ComDefaultCodeVO> getTimeHH (){
    	ArrayList<ComDefaultCodeVO> listHH = new ArrayList<ComDefaultCodeVO>();
    	HashMap<?, ?> hmHHMM;
    	for(int i=0;i <= 24; i++){
    		String sHH = "";
    		String strI = String.valueOf(i);
    		if(i<10){
    			sHH = "0" + strI;
    		}else{
    			sHH = strI;
    		}

    		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
    		codeVO.setCode(sHH);
    		codeVO.setCodeNm(sHH);

    		listHH.add(codeVO);
    	}

    	return listHH;
	}

	/**
	 * 분을 LIST를 반환한다.
	 * @return  List
	 * @throws
	 */
	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	private List getTimeMM (){
    	ArrayList listMM = new ArrayList();
    	HashMap hmHHMM;
    	for(int i=0;i <= 60; i++){

    		String sMM = "";
    		String strI = String.valueOf(i);
    		if(i<10){
    			sMM = "0" + strI;
    		}else{
    			sMM = strI;
    		}

    		ComDefaultCodeVO codeVO = new ComDefaultCodeVO();
    		codeVO.setCode(sMM);
    		codeVO.setCodeNm(sMM);

    		listMM.add(codeVO);
    	}
    	return listMM;
	}

	/**
	 * 0을 붙여 반환
	 * @return  String
	 * @throws
	 */
    public String dateTypeIntForString(int iInput){
		String sOutput = "";
		if(Integer.toString(iInput).length() == 1){
			sOutput = "0" + Integer.toString(iInput);
		}else{
			sOutput = Integer.toString(iInput);
		}

       return sOutput;
    }

}


