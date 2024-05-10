package egovframework.dudu.calendar.dailywork.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class DuduWorkController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DuduWorkController.class);

	@Autowired
	private DefaultBeanValidator beanValidator;

	/** EgovMessageSource */
    @Resource(name="egovMessageSource")
    EgovMessageSource egovMessageSource;

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
	 * 일일 업무 등록 캘린더 조회
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@RequestMapping(value="/dudu/calendar/workMonthList.do")
	public String egovIndvdlSchdulManageMonthList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<String, String> commandMap,
			DuduWorkVO indvdlSchdulManageVO,
    		ModelMap model)
    throws Exception {
		
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		commandMap.put("id", loginVO.getId());
		System.out.println("EgovindvdlSchdulManageController 89 loginvo.getId : " + commandMap.put("id", loginVO.getId()));

		//일정구분 검색 유지
        model.addAttribute("searchKeyword", commandMap.get("searchKeyword") == null ? "" : (String)commandMap.get("searchKeyword"));
        model.addAttribute("searchCondition", commandMap.get("searchCondition") == null ? "" : (String)commandMap.get("searchCondition"));

		java.util.Calendar cal = java.util.Calendar.getInstance();

		String sYear = commandMap.get("year");
		String sMonth = commandMap.get("month");

		int iYear = cal.get(java.util.Calendar.YEAR);
		int iMonth = cal.get(java.util.Calendar.MONTH);
		int iDate = cal.get(java.util.Calendar.DATE);

                //검색 설정
                String sSearchDate = "";
                if(sYear == null || sMonth == null){
                        sSearchDate += Integer.toString(iYear);
                        sSearchDate += Integer.toString(iMonth+1).length() == 1 ? "0" + Integer.toString(iMonth+1) : Integer.toString(iMonth+1);
                }else{
                        iYear = Integer.parseInt(sYear);
                        iMonth = Integer.parseInt(sMonth);
                        sSearchDate += sYear;
                        sSearchDate += Integer.toString(iMonth+1).length() == 1 ? "0" + Integer.toString(iMonth+1) :Integer.toString(iMonth+1);
                }

		//공통코드 일정종류
		ComDefaultCodeVO voComCode = new ComDefaultCodeVO();
	   	voComCode = new ComDefaultCodeVO();
    	voComCode.setCodeId("COM030");
    	List listComCode = cmmUseService.selectCmmCodeDetail(voComCode);
    	model.addAttribute("schdulSe", listComCode);

    	commandMap.put("searchMonth", sSearchDate);
    	commandMap.put("searchMode", "MONTH");
    	
    	System.out.println(">>>>>id2 : " + commandMap.get("id"));

        List resultList = egovIndvdlSchdulManageService.selectIndvdlSchdulManageRetrieve(commandMap);
        model.addAttribute("DuduWorkController resultList", resultList);

		return "dudu/calendar/dailywork/duduWorkMonthList";
	}
	
	/**
	 * 일정 등록
	 */
	@RequestMapping(value="/dudu/calendar/workRegister.do")
	public String indvdlSchdulManageRegistActor(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("indvdlSchdulManageVO") DuduWorkVO indvdlSchdulManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {
		System.out.println("DuduWorkController 일정 등록 시작");
		
		try {
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	    	System.out.println("isAuthenticated : " + isAuthenticated);
			
			if(!isAuthenticated) { 
				System.out.println("isAuthenticated : " +isAuthenticated); model.addAttribute("message",egovMessageSource.getMessage("fail.common.login")); 
				return "dudu/calendar/login/duduLoginUsr"; 
			}
			 
			//로그인 객체 선언
			LoginVO loginVO = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			System.out.println("loginVO : " + loginVO);
			
			//서버  validate 체크
	        beanValidator.validate(indvdlSchdulManageVO, bindingResult);
			if(bindingResult.hasErrors()){
				System.out.println("bindingResult : " + bindingResult);
				return "dudu/calendar/dailywork/duduWorkMonthList";
			}

			indvdlSchdulManageVO.setSchdulChargerId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getId()));
			indvdlSchdulManageVO.setSchdulDeptId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getOrgnztNm()));
			indvdlSchdulManageVO.setSchdulChargerName(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getName()));
			
			System.out.println("DuduWorkController loginVO.getName() : " + loginVO.getName());
			System.out.println("DuduWorkController indvdlSchdulManageVO.getProjectId : " + indvdlSchdulManageVO.getProjectId());
			System.out.println("DuduWorkController indvdlSchdulManageVO.getSchdulChargerId : " + indvdlSchdulManageVO.getSchdulChargerId());
			System.out.println("DuduWorkController indvdlSchdulManageVO.getSchdulChargerName : " + indvdlSchdulManageVO.getSchdulChargerName());
			
	    	egovIndvdlSchdulManageService.insertIndvdlSchdulManage(indvdlSchdulManageVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    	System.out.println("일정 등록 성공 redirect:/dudu/calendar.do");
        return "redirect:/dudu/calendar.do";
	}
	
	/**
	 * 일정 수정 모달 띄우기
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/dudu/calendar/workUpdateView.do")
	public String indvdlSchdulManageModify(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			DuduWorkVO indvdlSchdulManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {
		System.out.println("DuduWorkController 일정 수정 폼 컨트롤러 시작");

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
	    	System.out.println("일정 수정 폼 컨트롤러 resultIndvdlSchdulmanageVOResult : " + resultIndvdlSchdulManageVOReuslt);

	    	model.addAttribute("indvdlSchdulManageVO", resultIndvdlSchdulManageVOReuslt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "dudu/calendar/dailywork/duduWorkModalView";
	}
	
	/**
	 * 일일 업무 수정 처리
	 */
	@RequestMapping(value="/dudu/calendar/workUpdateActor.do")
	public String indvdlSchdulManageModifyActor(
			ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("indvdlSchdulManageVO") DuduWorkVO indvdlSchdulManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {

		System.out.println("DuduWorkController 일정 수정 처리");
		
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
			  
			indvdlSchdulManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			indvdlSchdulManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));

			egovIndvdlSchdulManageService.updateIndvdlSchdulManage(indvdlSchdulManageVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("일정 수정 처리 getSchdulId : " + indvdlSchdulManageVO.getSchdulId());

		return "redirect:/dudu/calendar.do";
	}  
	
	/**
	 * 일정 삭제처리
	  */
	@RequestMapping(value="/dudu/calendar/workDeleteActor.do")
	public String projectDeleteActor(
			ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			@ModelAttribute("indvdlSchdulManageVO") DuduWorkVO indvdlSchdulManageVO,
			BindingResult bindingResult,
			@RequestParam("schdulId") String schdulId,
    		ModelMap model)
    throws Exception {
		
		System.out.println("DuduWorkController 일정 삭제 처리");
		
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
			  
			indvdlSchdulManageVO.setFrstRegisterId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			indvdlSchdulManageVO.setLastUpdusrId(loginVO == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
			
			egovIndvdlSchdulManageService.deleteIndvdlSchdulManage(indvdlSchdulManageVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("일정 삭제 처리 getSchdulId : " + indvdlSchdulManageVO.getSchdulId());

		return "redirect:/dudu/calendar.do";
		
	} 
	
	/**
	 * 일정 목록보기
	 */
	@IncludedInfo(name="팀원일정 목록보기", order = 320 ,gid = 40)
	@RequestMapping(value="/dudu/calendar/workList.do")
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
			searchVO.setSchdulChargerId(loginVO.getId());

			System.out.println("searchVO.getOrgnztNm : " + searchVO.getOrgnztNm());
			System.out.println("indevdlSchdulManageVO.getSchdulBgnde : " + indvdlSchdulManageVO.getSchdulBgnde());
			System.out.println("searchVO.getSchdulBgnde : " + searchVO.getSchdulBgnde());

	        List<?> workList = egovIndvdlSchdulManageService.selectUserWorkList(searchVO);
	        model.addAttribute("workList", workList);
	        System.out.println("DuduProjectController workList : " + workList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "dudu/calendar/dailywork/workListModalView";
	}
	
	// bar 모달 -> 일정 수정 삭제 모달 띄우기
	@SuppressWarnings("unused")
	@RequestMapping(value="/dudu/calendar/barWorkUpdateView.do")
	public String barWorkUpdateView(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			DuduWorkVO indvdlSchdulManageVO,
			BindingResult bindingResult,
    		ModelMap model)
    throws Exception {
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
	    	System.out.println("일정 수정 폼 컨트롤러 resultIndvdlSchdulmanageVOResult : " + resultIndvdlSchdulManageVOReuslt);

	    	model.addAttribute("indvdlSchdulManageVO", resultIndvdlSchdulManageVOReuslt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "dudu/calendar/dailywork/moWorkModalView";
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
	@SuppressWarnings("unused")
	private List<ComDefaultCodeVO> getTimeMM (){
    	ArrayList<ComDefaultCodeVO> listMM = new ArrayList<ComDefaultCodeVO>();
    	HashMap<?, ?> hmHHMM;
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


