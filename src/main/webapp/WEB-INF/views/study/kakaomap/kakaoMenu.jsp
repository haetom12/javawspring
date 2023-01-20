<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<div>
	<p>
		<a href="${ctp}/study/kakaoEx1" class="btn btn-success">마커표시/DB저장</a>
		<a href="${ctp}/study/kakaoEx2" class="btn btn-success">DB에 저장된 지명 검색/삭제</a>
		<a href="${ctp}/study/kakaoEx3" class="btn btn-success">검색한 자료 DB저장</a>
		<a href="${ctp}/study/kakaoEx4" class="btn btn-success">카테고리별 자료 가져오기</a>
		<a href="${ctp}/study/kakaoEx5" class="btn btn-success">위치들의 거리 구하기</a>
	</p>
</div>