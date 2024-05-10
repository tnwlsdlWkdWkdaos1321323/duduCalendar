package egovframework.dudu.calendar.management.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.com.cmm.ComDefaultCodeVO;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.service.EgovCmmUseService;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.dudu.calendar.management.service.DuduMberManageService;
import egovframework.dudu.calendar.management.service.UserDefaultVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class DuduMberManageController {

	/** mberManageService */
	@Resource(name = "duduMberManageService")
	private DuduMberManageService mberManageService;

	/** cmmUseService */
	@Resource(name = "EgovCmmUseService")
	private EgovCmmUseService cmmUseService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** DefaultBeanValidator beanValidator */
	@Autowired
	private DefaultBeanValidator beanValidator;

	/**
	 * 팀원목록을 조회한다. (pageing)
	 */
	@IncludedInfo(name = "팀원관리", order = 470, gid = 50)
	@RequestMapping(value = "/dudu/calendar/management.do")
	public String selectMberList(@ModelAttribute("userSearchVO") UserDefaultVO userSearchVO, ModelMap model)
			throws Exception {
		System.out.println("팀원 목록 조회 컨트롤러 시작");
		
		LoginVO loginVO = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		model.addAttribute("loginVO", loginVO);
		userSearchVO.setOrgnztNm(loginVO.getOrgnztNm());
		System.out.println("팀원 목록 조회 컨트롤러 userSearchVO.getOrgnztNm() : " + userSearchVO.getOrgnztNm());
		
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

			List<?> mberList = mberManageService.selectMberList(userSearchVO);
			model.addAttribute("resultList", mberList);

			int totCnt = mberManageService.selectMberListTotCnt(userSearchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			model.addAttribute("paginationInfo", paginationInfo);

			ComDefaultCodeVO vo = new ComDefaultCodeVO();
			vo.setCodeId("COM013");
			List<?> mberSttus_result = cmmUseService.selectCmmCodeDetail(vo);
			model.addAttribute("entrprsMberSttus_result", mberSttus_result);// 기업회원상태코드목록
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "dudu/calendar/management/duduMberManage";
	}

	/**
	 * 팀원 상태 변경 후 /dudu/calendar/management.do로 forward 시킴
	 */
	@RequestMapping("/dudu/calendar/managementUpt.do")
    public String updateMber(HttpServletRequest request, Model model) throws Exception {
        
        String updateSttus = request.getParameter("updateSttus");
        String selectedUserIds = request.getParameter("selectedUserIds");
        System.out.println("updateSttus에서 가져온 userId : " + updateSttus);
        System.out.println("selectedUserIds에서 가져온 userIds : " + selectedUserIds);

        String[] checks = updateSttus.split(",");
        String[] userIds = selectedUserIds.split(",");

        for (String userId : userIds) {
            mberManageService.updateMber(userId);
        }

        model.addAttribute("resultMsg", "팀원 상태 변경에 성공하였습니다.");

        return "forward:/dudu/calendar/management.do";

    }

	/**
	 * 팀원 삭제 후 forward:/dudu/calendar/management.do
	 */
	@RequestMapping("/dudu/calendar/manageDelete.do")
	public String deleteMber(HttpServletRequest request, Model model) throws Exception {

	    System.out.println("duduMberManageController 팀원 삭제 컨트롤러");

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

		/**
	    for (String userId : userIds) {
	        mberManageService.deleteMber(userId);
	    } */
	    mberManageService.deleteMber(userIds);

	    model.addAttribute("resultMsg", "팀원 상태 변경에 성공하였습니다.");
	    System.out.println("duduMbermanageController 팀원 삭제에 성공했습니다.");

	    return "forward:/dudu/calendar/management.do";
	}

}
