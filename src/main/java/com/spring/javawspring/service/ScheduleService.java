package com.spring.javawspring.service;

import java.util.List;

import com.spring.javawspring.vo.ScheduleVO;

public interface ScheduleService {

	public void getSchedule();

	public List<ScheduleVO> getScheduleMenu(String mid, String ymd);

	public void scheduleInputOkPost(ScheduleVO vo);

	public int scheduleDeleteOk(int idx);

	public int scheduleUpdateOk(ScheduleVO vo);

}
