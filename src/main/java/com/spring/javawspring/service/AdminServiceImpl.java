package com.spring.javawspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.javawspring.dao.AdminDAO;
import com.spring.javawspring.dao.BoardDAO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDAO adminDAO;
	@Autowired
	BoardDAO boardDAO;

	@Override
	public int setMemberLevelCheck(int idx, int level) {
		return adminDAO.setMemberLevelCheck(idx, level);
	}

	@Override
	public int setMemberDelete(int idx) {
		return adminDAO.setMemberDelete(idx);
	}

	@Override
	public int setAdminBoardDelete(String delItems) {
		String[] delfile = delItems.split("/");
		for(int i=0; i<delfile.length; i++) {
			boardDAO.setAdminBoardDelete(Integer.parseInt(delfile[i]));			
		}
		int res = 1;
		return res;
	}
}
