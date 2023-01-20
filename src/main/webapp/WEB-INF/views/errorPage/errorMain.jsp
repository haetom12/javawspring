<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>errorMain</title>
  <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<div class="container">
	<p>
	  <pre>
	  	JSP파일 View에서의 에러가 발생시에는 JSP파일 상단에 @page지시자를 이용한 에러페이지로 이동할 수 있다.
	  	< % @ page errorPage = "에러발생시 수행할 jsp파일 경로와 파일명" % >
	  </pre>
	  <a href="${ctp}/errorPage/error1" class="btn btn-success">JSP 오류</a>
  </p>
  <hr />
	<p>
	  <pre>
	  	서블릿(servlet)에서 에러가 발생시를 대비하여 처리하는 방법?
	  	-web.xml에 error에 필요한 설정을 미리 해둔다.(아래 코드 참조)
	  	
	  	
	  </pre>
	  <a href="${ctp}/errorPage/error2" class="btn btn-success">servlet 오류(404)</a>
	  <a href="${ctp}/errorPage/error3" class="btn btn-success">servlet 오류-예외(404)</a>
  </p>
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>