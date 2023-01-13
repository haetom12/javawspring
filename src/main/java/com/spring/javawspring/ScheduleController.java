package com.spring.javawspring;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.javawspring.service.ScheduleService;
import com.spring.javawspring.vo.ScheduleVO;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	ScheduleService scheduleService;

	
	// 어드민 메인
	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public String scheduleGet(Model model) {
		scheduleService.getSchedule();
		
		return "schedule/schedule";
	}
	
	// 어드민 메인
	@RequestMapping(value = "/scheduleMenu", method = RequestMethod.GET)
	public String scheduleMenuGet(Model model, HttpSession session, String ymd) {
		scheduleService.getSchedule();
		String mid = (String) session.getAttribute("sMid");
//		System.out.println(mid);
		List<ScheduleVO> vos = scheduleService.getScheduleMenu(mid,ymd);
		
		model.addAttribute("vos",vos);
		model.addAttribute("ymd",ymd);
		
		return "schedule/scheduleMenu";
	}
	
	// 일정 입력
	@ResponseBody
	@RequestMapping(value = "/scheduleInputOk", method = RequestMethod.POST)
	public String scheduleInputOkPost(ScheduleVO vo) {
		scheduleService.scheduleInputOkPost(vo);
		
		return "";
	}
	
	// 일정 삭제
	@ResponseBody
	@RequestMapping(value = "/scheduleDeleteOk", method = RequestMethod.POST)
	public String scheduleDeleteOkPost(int idx) {
		int res =0;
		res = scheduleService.scheduleDeleteOk(idx);
		
		return res+"";
	}
	// 일정 수정
	@ResponseBody
	@RequestMapping(value = "/scheduleUpdateOk", method = RequestMethod.POST)
	public String scheduleUpdateOkPost(ScheduleVO vo) {
		int res =0;
		res = scheduleService.scheduleUpdateOk(vo);
		
		return res+"";
	}
	
	
	
}
