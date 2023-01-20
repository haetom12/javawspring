package com.spring.javawspring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.spring.javawspring.vo.PdsVO;

public class JUnitTest {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	String url = "jdbc:mysql://localhost:3306/javaworks";
	String user = "root";
	String password = "1234";

	public JUnitTest() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	// 자료실 검색 \
	@Test
	public void pdsSearch() {
		String mid = "haetom";
		PdsVO vo = getpdsSearch(mid);
		System.out.println("vo : " + vo);
	}
	
	private PdsVO getpdsSearch(String mid) {
		PdsVO vo = new PdsVO();
		try {
			String sql = "select * from pds2 where mid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setMid(rs.getString("mid"));
				vo.setNickName(rs.getString("nickName"));
			}
			System.out.println("vo : " + vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
}
