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
  <script>
    'use strict';
		function checkpwd() {
    	let name = FindMidform.name.value;
    	let nickName = FindMidform.nickName.value;
    	
    	if(name.trim() == "") {
    		alert("아이디를 입력하세요!");
    		FindMidform.name.focus();
    	}
    	if(nickName.trim() == "") {
    		alert("이메일을 입력하세요!");
    		FindMidform.nickName.focus();
    	}
    	else {
    		FindMidform.submit();
    	}
    }
		
  </script>

</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<!-- !PAGE CONTENT! -->
<div class="w3-container">
	<form name="FindMidform" method="post">
	  <div class="w3-card-4" style="width:30%">
	    <header class="w3-container w3-blue">
	      <h3>아이디 찾기</h3>
	    </header>
	    <div class="w3-container">
	      <h3>찾으려시는 비밀번호의 아이디와 <br/> 이메일을 입력하세요</h3>
	      <hr>
	      <input class="w3-input w3-border w3-margin-bottom" id="mid" name="mid" type="text" placeholder="아이디 입력하세요" autofocus required>
	      <input class="w3-input w3-border w3-margin-bottom" id="toMail" name="toMail" type="text" placeholder="이메일을 입력하세요" required>
	    </div>
	    <input type="submit" class="w3-button w3-block w3-green " value="임시비밀번호 발급"/>
	    <input type="reset" value="다시입력" class="w3-button w3-block w3-yellow" />
	    <button class="w3-button w3-block w3-dark-grey" onclick="location.href='${ctp}/member/memberLogin';">뒤로 가기</button>
	  </div>
	</form>
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>