package com.spring.javawspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.javawspring.dao.AdminDAO;
import com.spring.javawspring.dao.BoardDAO;
import com.spring.javawspring.vo.BoardVO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDAO adminDAO;
	@Autowired
	BoardDAO boardDAO;
	@Autowired
	BoardService boardService;

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
			BoardVO vo = boardDAO.getBoardContent(Integer.parseInt(delfile[i]));
			if(vo.getContent().indexOf("src=\"/") != -1) boardService.imgDelete(vo.getContent()); 
			boardDAO.setAdminBoardDelete(Integer.parseInt(delfile[i]));			
		}
		int res = 1;
		return res;
	}
}
