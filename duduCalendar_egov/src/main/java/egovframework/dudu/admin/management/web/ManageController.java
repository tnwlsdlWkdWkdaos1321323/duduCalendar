package egovframework.dudu.admin.management.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.dudu.calendar.join.service.DuduUserJoinService;
import egovframework.dudu.calendar.join.service.DuduUserJoinVO;
import egovframework.dudu.calendar.management.service.UserDefaultVO;
import egovframework.dudu.admin.management.service.ManageService;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class ManageController {

	/** manageService */
	@Resource(name = "manageService")
	private ManageService manageService;
	
	/** cmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** duduUserJoinService */
	@Resource(name = "duduUserJoinService")
	private DuduUserJoinService duduUserJoinService;

	/** DefaultBeanValidator beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;
	
	/**
	 * 팀원 목록 조회
	 */
	@IncludedInfo(name = "팀원 관리", order = 470, gid = 50)
	 @RequestMapping(value = "/dudu/admin/mbManagement.do")
    public String selectMbManagement(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, ModelMap model) throws Exception {
        LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        model.addAttribute("loginVO", loginVO);
        userSearchVO.setOrgnztNm(loginVO.getOrgnztNm());

        try {
            Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
            if (!isAuthenticated) {
                return "index";
            }

            userSearchVO.setPageUnit(10); // 페이지 당 표시할 항목 수
            userSearchVO.setPageSize(10); // 페이지 수

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(userSearchVO.getPageIndex());
            paginationInfo.setRecordCountPerPage(userSearchVO.getPageUnit());
            paginationInfo.setPageSize(userSearchVO.getPageSize());

            userSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
            userSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
            userSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            model.addAttribute("resultList", manageService.selectMbManagement(userSearchVO));

            int totCnt = manageService.selectMbManagementCnt(userSearchVO);
            paginationInfo.setTotalRecordCount(totCnt);
            model.addAttribute("paginationInfo", paginationInfo);

        } catch (Exception e) {
            e.printStackTrace();
            return "error-page";
        }

        return "dudu/admin/management/mbManagement";
    }
	
	/**
	 * 팀원 상태 변경 후 /dudu/admin/mbManagement.do로 forward 시킴
	 */
	@RequestMapping("/dudu/admin/mbManagementUpt.do")
    public String updateMber(HttpServletRequest request, Model model) throws Exception {
		
		try {
			String updateSttus = request.getParameter("updateSttus");
	        String selectedUserIds = request.getParameter("selectedUserIds");
	        System.out.println("updateSttus에서 가져온 userId : " + updateSttus);
	        System.out.println("selectedUserIds에서 가져온 userIds : " + selectedUserIds);

	        String[] checks = updateSttus.split(",");
	        String[] userIds = selectedUserIds.split(",");

	        for (String userId : userIds) {
	        	manageService.updateMber(userId);
	        	System.out.println("manageController userId : " + userId);
	        }

	        model.addAttribute("resultMsg", "팀원 상태 변경에 성공하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
        
		return "forward:/dudu/admin/mbManagement.do"; 

    }
	
	/**
	 * 팀원 삭제 후 forward:/dudu/calendar/management.do
	 */
	@RequestMapping("/dudu/admin/mbManageDelete.do")
	public String deleteMber(HttpServletRequest request, Model model) throws Exception {

	    System.out.println("duduMberManageController 팀원 삭제 컨트롤러");
	    try {
	    	String deleteUser = request.getParameter("deleteUser");
		    String selectUserIds = request.getParameter("selectedUserIds");

		    if (selectUserIds == null || selectUserIds.isEmpty()) {
		        System.out.println("selectedUserIds에서 가져온 userIds가 null 또는 비어있습니다.");
		        model.addAttribute("errorMsg", "유저 ID가 선택되지 않았습니다.");
		        return "errorPage";
		    }

		    System.out.println("deleteUser에서 가져온 userId : " + deleteUser);
		    System.out.println("selectedUserIds에서 가져온 userIds : " + selectUserIds);

		    String[] userIds = selectUserIds.split(",");
		    
		    manageService.deleteMber(userIds);

		    model.addAttribute("resultMsg", "팀원 상태 변경에 성공하였습니다.");
		    System.out.println("duduMbermanageController 팀원 삭제에 성공했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}

	    return "forward:/dudu/admin/mbManagement.do";
	}
	
	/**
	 * 임시 비밀번호 발급
	 */
	@RequestMapping("/dudu/admin/tempPassword.do")
	public String tempPassword(HttpServletRequest request, Model model) throws Exception{
		System.out.println("duduMberManageController 임시 비밀번호 발급");
		try {
			String updateSttus = request.getParameter("updateSttus");
	        String selectedUserIds = request.getParameter("selectedUserIds");
	        System.out.println("updateSttus에서 가져온 userId : " + updateSttus);
	        System.out.println("selectedUserIds에서 가져온 userIds : " + selectedUserIds);

	        String[] checks = updateSttus.split(",");
	        String[] userIds = selectedUserIds.split(",");

	        String tempPassword = "";
	        for (String userId : userIds) {
	        	tempPassword = manageService.tempPassword(userId);
	        	System.out.println("duduMberManageController userId : " + userId);
	        }
	        
	        model.addAttribute("tempMassage", "새로 발급된 비밀번호 : " + tempPassword);
	        return "forward:/dudu/admin/mbManagement.do";
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
	}

	/**
	 * 팀장 목록 관리
	 */
	@IncludedInfo(name = "팀장관리", order = 470, gid = 50)
	@RequestMapping(value = "/dudu/admin/leaderManagement.do")
	public String selectLeaderManagement(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, ModelMap model) throws Exception{
		
		System.out.println("팀장 목록 조회 컨트롤러 시작");
		
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("loginVO", loginVO);
		userSearchVO.setOrgnztNm(loginVO.getOrgnztNm());
		
		try {
			
			Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
			if (!isAuthenticated) {
				return "index";
			}

			/** EgovPropertyService */
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

			List<?> mberList = manageService.selectLeaderManagement(userSearchVO);
			model.addAttribute("resultList", mberList);

			int totCnt = manageService.selectLeaderManagementCnt(userSearchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);

			ComDefaultCodeVO vo = new ComDefaultCodeVO();
			vo.setCodeId("COM013");
			List<?> mberSttus_result = cmmUseService.selectCmmCodeDetail(vo);
			model.addAttribute("entrprsMberSttus_result", mberSttus_result);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}

		return "dudu/admin/management/leaderManagement";
	}
	
	/**
	 * 팀장 삭제 후 forward:/dudu/admin/leaderManagement.do
	 */
	@RequestMapping("/dudu/admin/leaderManageDelete.do")
	public String deleteLeader(HttpServletRequest request, Model model) throws Exception {

	    System.out.println("duduMberManageController 팀장 삭제 컨트롤러");
	    try {
	    	String deleteUser = request.getParameter("deleteUser");
		    String selectUserIds = request.getParameter("selectedUserIds");

		    if (selectUserIds == null || selectUserIds.isEmpty()) {
		        System.out.println("selectedUserIds에서 가져온 userIds가 null 또는 비어있습니다.");
		        model.addAttribute("errorMsg", "유저 ID가 선택되지 않았습니다.");
		        return "errorPage";
		    }

		    System.out.println("deleteUser에서 가져온 userId : " + deleteUser);
		    System.out.println("selectedUserIds에서 가져온 userIds : " + selectUserIds);

		    String[] userIds = selectUserIds.split(",");
		    
		    manageService.deleteLeader(userIds);

		    model.addAttribute("resultMsg", "팀장 삭제에 성공하였습니다.");
		    System.out.println("duduMbermanageController 팀장 삭제에 성공했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}

	    return "forward:/dudu/admin/leaderManagement.do";
	}

	/**
	 * 팀장 등록 화면으로 이동
	 */
	@RequestMapping("/uss/umt/leaderInsertView.do")
	public String insertMberView(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, ModelMap model) throws Exception{
		
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
        model.addAttribute("loginVO", loginVO);
        userSearchVO.setOrgnztNm(loginVO.getOrgnztNm());

        try {
            Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
            if (!isAuthenticated) {
                return "index";
            }

            userSearchVO.setPageUnit(6); // 페이지 당 표시할 항목 수
            userSearchVO.setPageSize(5); // 페이지 수

            PaginationInfo paginationInfo = new PaginationInfo();
            paginationInfo.setCurrentPageNo(userSearchVO.getPageIndex());
            paginationInfo.setRecordCountPerPage(userSearchVO.getPageUnit());
            paginationInfo.setPageSize(userSearchVO.getPageSize());

            userSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
            userSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
            userSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());

            model.addAttribute("resultList", manageService.selectMbManagement(userSearchVO));

            int totCnt = manageService.selectMbManagementCnt(userSearchVO);
            paginationInfo.setTotalRecordCount(totCnt);
            model.addAttribute("page", paginationInfo);

        } catch (Exception e) {
            e.printStackTrace();
            return "error-page";
        }
		
		return "dudu/admin/management/leaderInsertModalView";
	}

	/**
	 * 팀장 등록 후 화면 이동
	 */
	@RequestMapping("/uss/umt/leaderInsert.do")
	public String sbscrbEntrprsMber(HttpServletRequest request, Model model) throws Exception {

		System.out.println("duduMberManageController 팀장 등록 컨트롤러");
	    try {
	    	String deleteUser = request.getParameter("deleteUser");
		    String selectUserIds = request.getParameter("selectedUserIds");

		    if (selectUserIds == null || selectUserIds.isEmpty()) {
		        System.out.println("selectedUserIds에서 가져온 userIds가 null 또는 비어있습니다.");
		        model.addAttribute("errorMsg", "유저 ID가 선택되지 않았습니다.");
		        return "errorPage";
		    }

		    System.out.println("deleteUser에서 가져온 userId : " + deleteUser);
		    System.out.println("selectedUserIds에서 가져온 userIds : " + selectUserIds);

		    String[] userIds = selectUserIds.split(",");
		    
		    manageService.insertLeader(userIds);

		    model.addAttribute("resultMsg", "팀장 등록에 성공하였습니다.");
		    System.out.println("duduMbermanageController 팀장 등록에 성공했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
		return "forward:/dudu/admin/leaderManagement.do";
	}

}
