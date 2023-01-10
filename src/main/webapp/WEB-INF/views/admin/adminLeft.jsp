<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>adLeft.jsp</title>
  <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
</head>
<body style="background-color: gray">
<p><br/></p>
<div class="container">
  <h5>관리자메뉴</h5>
  <hr/>
  <p>
    <a href="${ctp}/" target="_top">메인홈으로</a>
  </p>
  <hr/>
  <p>
    <a href="${ctp}/admin/adMain" target="_top">관리자홈으로</a>
  </p>
  <hr/>
  <p>
    <a href="#">방명록리스트</a>
  </p>
  <hr/>
  <p>
    <a href="${ctp}/admin/member/adminMemberList" target="adminContent">회원리스트</a>
  </p>
</div>
<p><br/></p>
</body>
</html>