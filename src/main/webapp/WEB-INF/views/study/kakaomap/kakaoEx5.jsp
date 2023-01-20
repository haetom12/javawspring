<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>title</title>
  <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<div class="container">
  <div id="map" style="width:100%;height:500px;"></div>
  <jsp:include page="kakaoMenu.jsp"/>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ac23d1dc8d935cead13bb8f2c930e90f"></script>
	<script>
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: new kakao.maps.LatLng(36.519487777536845, 127.25814811650591), // 지도의 중심좌표
	        level: 6 // 지도의 확대 레벨
	    };
	
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
		<c:forEach var="vo" items="${vos}">
			// 마커가 표시될 위치입니다 
			var markerPosition  = new kakao.maps.LatLng(${vo.latitude}, ${vo.longitude}); 
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
			    position: markerPosition
			});
			// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);
		</c:forEach>
	// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
	// marker.setMap(null);    
	</script>
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />

</body>
</html>