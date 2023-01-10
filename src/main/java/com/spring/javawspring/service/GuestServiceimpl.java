package com.spring.javawspring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.javawspring.dao.GuestDAO;
import com.spring.javawspring.vo.GuestVO;

@Service
public class GuestServiceimpl implements GuestService {
	@Autowired
	GuestDAO guestDAO;

	@Override
	public ArrayList<GuestVO> getGuestList(int startIndexNo, int pageSize) {
		return guestDAO.getGuestList(startIndexNo, pageSize);
	}

	@Override
	public void setGuestInput(GuestVO vo) {
		guestDAO.setGuestInput(vo);
	}

	@Override
	public int totRecCnt() {
		return guestDAO.totRecCnt();
	}

	@Override
	public void guestDelete(int idx) {
		guestDAO.guestDelete(idx);
	}

	@Override
	public int totSearchCnt(String search, String searchString) {
		return guestDAO.totSearchCnt(search, searchString);
	}

	@Override
	public ArrayList<GuestVO> setguestSearch(int startIndexNo, int pageSize, String search, String searchString) {
		return guestDAO.getGuestSearch(startIndexNo, pageSize, search, searchString);
	}

	@Override
	public GuestVO guestUpdate(int idx) {
		return guestDAO.guestUpdate(idx);
	}

	@Override
	public void guestUpdateOk(GuestVO vo) {
		guestDAO.guestUpdateOk(vo);
	}

	@Override
	public ArrayList<GuestVO> getTermGuestList(int startIndexNo, int pageSize, String search, String searchString) {
		return guestDAO.getTermGuestList(startIndexNo,pageSize, search, searchString);
	}

}
