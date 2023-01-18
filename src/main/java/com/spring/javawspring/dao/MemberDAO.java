package com.spring.javawspring.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.javawspring.vo.MemberVO;

public interface MemberDAO {

	public MemberVO getMemberIdCheck(@Param("mid") String mid);

	public MemberVO getMembernickNameCheck(@Param("nickName") String nickName);

	public int setMemberJoinOk(@Param("vo") MemberVO vo);

	//오늘 재방문이라면 '총방문수','오늘방문수','포인트' 누적처리
	public void setMemTotalUpdate(@Param("mid") String mid, @Param("nowTodayPoint") int nowTodayPoint, @Param("todayCnt") int todayCnt);

	public int totRecCnt();

	public ArrayList<MemberVO> getMemberList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize);

	public int totSearchCnt(@Param("search") String search, @Param("searchString") String searchString);

	public ArrayList<MemberVO> getMemberSearch(@Param("startIndexNo") int startIndexNo,@Param("pageSize") int pageSize, @Param("search") String search, @Param("searchString") String searchString);

	public MemberVO getMemberMid(@Param("name") String name, @Param("nickName") String nickName);

	public void getMemberDelete(@Param("mid") String mid);

	public int totTermRecCnt(@Param("search") String search, @Param("searchString") String searchString);

	public ArrayList<MemberVO> getTermMemberList(@Param("startIndexNo") int startIndexNo,@Param("pageSize") int pageSize, @Param("search") String search, @Param("searchString") String searchString);

	public void setMemberPwdUpdate(@Param("mid") String mid, @Param("pwd") String pwd);

	public void setMemberUpdate(@Param("vo") MemberVO vo);

	public MemberVO getMemberNicknameEmailCheck(@Param("nickName") String nickName, @Param("email")  String email);

	public void setKakaoMemberInputOk(@Param("mid") String mid, @Param("pwd") String pwd, @Param("nickName") String nickName, @Param("email") String email);

	public void setMemberUserDelCheck(@Param("mid") String mid);

}
