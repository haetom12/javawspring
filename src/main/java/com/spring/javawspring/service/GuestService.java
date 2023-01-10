package com.spring.javawspring.service;

import java.util.ArrayList;

import com.spring.javawspring.vo.GuestVO;

public interface GuestService {

	public ArrayList<GuestVO> getGuestList(int startIndexNo, int pageSize);

	public void setGuestInput(GuestVO vo);

	public int totRecCnt();

	public void guestDelete(int idx);

	public int totSearchCnt(String search, String searchString);

	public ArrayList<GuestVO> setguestSearch(int startIndexNo, int pageSize, String search, String searchString);

	public GuestVO guestUpdate(int idx);

	public void guestUpdateOk(GuestVO vo);

	public ArrayList<GuestVO> getTermGuestList(int startIndexNo, int pageSize, String search, String searchString);


}
