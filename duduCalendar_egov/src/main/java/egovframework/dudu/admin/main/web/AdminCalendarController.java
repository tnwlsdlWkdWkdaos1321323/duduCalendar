package egovframework.dudu.admin.main.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.com.cmm.ComDefaultVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.dudu.admin.main.service.AdminCalendarService;
import egovframework.dudu.admin.main.service.AdminCalendarVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class AdminCalendarController {
	
	/** adminCalendarService */
	@Resource(name = "adminCalendarService")
	private AdminCalendarService adminCalendarService;
	
	/**
	 * 사원 일정 상세보기
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/dudu/admin/schdulDetail.do")
	public String schdulDetail(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?,?> commnadMap,
			AdminCalendarVO adminCalendarVO,
			BindingResult bindingResult,
    		ModelMap model)
	throws Exception{
		System.out.println("AdminCalendarController 사원 일정 상세보기 시작");
		
		try {
			EgovMap schdulList =  adminCalendarService.selectSchdulDetail(adminCalendarVO);
			System.out.println("사원 일정 상세보기 컨트롤러 schdulList : " + schdulList);
			model.addAttribute("schdulList", schdulList);
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
		
		return "dudu/admin/schdul/schdulDetailModalView";
	}

	/**
	 * 사원 일정 목록보기
	 */
	@IncludedInfo(name="사원일정 목록보기", order = 320 ,gid = 40)
	@RequestMapping(value="/dudu/admin/schdulList.do")
	public String schdulList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
	        @RequestParam Map<?, ?> commandMap,
	        AdminCalendarVO adminCalendarVO,
	        ModelMap model)
	throws Exception{
		System.out.println("AdminCaledarController 사원 일정 상세보기");
		try {
			searchVO.setSchdulBgnde(adminCalendarVO.getSchdulBgnde());
			
			List<?> workList = adminCalendarService.selectSchdulList(searchVO);
			model.addAttribute("workList", workList);
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
		return "dudu/admin/schdul/schdulListModalView";
	}

	/**
	 * bar 모달 -> 사원 일정 목록보기
	 */
	@IncludedInfo(name="사원일정 목록보기", order = 320 ,gid = 40)
	@RequestMapping(value="/dudu/admin/barSchdulDetail.do")
	public String barSchdulDetail(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?,?> commnadMap,
			AdminCalendarVO adminCalendarVO,
			BindingResult bindingResult,
    		ModelMap model)
	throws Exception{
		System.out.println("AdminCalendarController 사원 일정 상세보기 시작");
		try {
			EgovMap schdulList =  adminCalendarService.selectSchdulDetail(adminCalendarVO);
			System.out.println("사원 일정 상세보기 컨트롤러 schdulList : " + schdulList);
			model.addAttribute("schdulList", schdulList);
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
		return "dudu/admin/schdul/barSchdulDetailModalView";
	}

	/**
	 * 전체 프로젝트 목록 조회
	 */
	@IncludedInfo(name="프로젝트 목록", order = 320 ,gid = 40)
	@RequestMapping(value="/dudu/admin/projectList.do")
	public String projectList(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			AdminCalendarVO adminCalendarVO,
			ModelMap model)
	throws Exception{
		System.out.println("AdminCalendarController 전체 프로젝트 목록 조회");
		try {
			List<?> resultList = adminCalendarService.selectProjectList(searchVO);
			model.addAttribute("resultList", resultList);
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
		return "dudu/admin/project/projectList";
	}
	
	/**
	 * 프로젝트 상세보기
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="/dudu/admin/projectView.do")
	public String projectDetailView(
			@ModelAttribute("searchVO") ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			AdminCalendarVO adminCalendarVO,
			BindingResult bindingResult,
    		ModelMap model)
	throws Exception{
		System.out.println("AdminCalnedarController 프로젝트 상세보기");
		try {
			EgovMap projectDetailView = adminCalendarService.selectProjectDetail(adminCalendarVO);
			model.addAttribute("projectDetailView", projectDetailView);
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
		return "dudu/admin/project/projectModalView";
	}

	/**
	 * 프로젝트 삭제 함수
	 */
	@RequestMapping(value = "/dudu/admin/projectDeleteActor.do")
	public String projectDeleteActor(
			ComDefaultVO searchVO,
			@RequestParam Map<?, ?> commandMap,
			AdminCalendarVO adminCalnedarVO,
			BindingResult bindingResult,
			@RequestParam("projectId") String projectId,
    		ModelMap model)
	throws Exception{
		System.out.println("AdminCalendarController 프로젝트 삭제");
		try {
			adminCalendarService.deleteProjectAct(adminCalnedarVO);
		} catch (Exception e) {
			e.printStackTrace();
			return "error-page";
		}
		return "redirect:/dudu/calendar.do";
	}
	
}
