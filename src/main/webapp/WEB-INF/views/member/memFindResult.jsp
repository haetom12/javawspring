<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>title</title>
  <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
  <script src="${ctp}/js/woo.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&family=Noto+Sans+KR:wght@700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<style>
	body {
  font-family: 'IBM Plex Sans KR', sans-serif;
  text-align: center;
}

/* Center website */
.w3-container .w3-card-4 {
	text-align: center;
  margin: 0px auto;
  margin-top: 1%;
}

</style>

</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<!-- !PAGE CONTENT! -->
<div class="w3-container">
	  <div class="w3-card-4" style="width:30%">
	    <header class="w3-container w3-blue">
	      <h3>아이디/비밀번호 찾기</h3>
	    </header>
	    <div class="w3-container">
	      <h3>검 색 결 과</h3>
	     	<div>
	     		<h2>검색한 정보는 <font color="blue">${res}</font> 입니다!</h2> 
	     	</div>
	    </div>
	    <button class="w3-button w3-block w3-dark-grey" onclick="location.href='${ctp}/member/memberLogin';">로그인으로 돌아가기</button>
	  </div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>