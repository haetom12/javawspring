package com.spring.javawspring.service;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.spring.javawspring.vo.MemberVO;

public interface MemberService {

	public MemberVO getMemberIdCheck(String mid);

	public MemberVO getMemberNickNameCheck(String nickName);

	public int setMemberJoinOk(MultipartFile fName, MemberVO vo);

	public void setMemberVisitProcess(MemberVO vo);

	public int totRecCnt();

	public ArrayList<MemberVO> getMemberList(int startIndexNo, int pageSize);

	public int totSearchRecCnt(String search, String searchString);

	public ArrayList<MemberVO> getMemberSearch(int startIndexNo, int pageSize, String search, String searchString);

	public MemberVO getMemberInfo(String mid);

	public MemberVO getFindMid(String name, String nickName);

	public void setMemberDelete(String mid);

	public int totTermRecCnt(String search, String searchString);

	public ArrayList<MemberVO> getTermMemberList(int startIndexNo, int pageSize, String search, String searchString);

	public void setMemberPwdUpdate(String mid, String pwd);

	public int setMemberUpdateOk(MultipartFile fName, MemberVO vo, String pastPhoto);

}
