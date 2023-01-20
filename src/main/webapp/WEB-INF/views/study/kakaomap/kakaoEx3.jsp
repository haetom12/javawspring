<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>kakaoEx2.jsp</title>
  <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
  <script>
		
		
  </script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<div class="container">
  <h2>DB에 저장된 지명으로 검색하기</h2>
  <hr/>
  <div>
    <form name="myform" method="post">
      <div>
        <font size="4"><b>저장된 지명으로 검색하기</b></font>
        <input type="text" name="address" placeholder="지역검색" />
        <input type="button" value="검색" onclick="addressSearch()"/>
      </div>
    </form>
  </div>
  <hr/>
  <div id="map" style="width:100%;height:500px;"></div>
  
  
  <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=158c673636c9a17a27b67c95f2c6be5c&libraries=services"></script>
  <hr/>
  <jsp:include page="kakaoMenu.jsp"/>
  <div id="clickPoint"></div>
  <script>
		// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
		var infowindow = new kakao.maps.InfoWindow({zIndex:1});
		
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 장소 검색 객체를 생성합니다
		var ps = new kakao.maps.services.Places(); 
		
		// 키워드로 장소를 검색합니다
		
		function addressSearch() {
			var address = myform.address.value;
			ps.keywordSearch(address, placesSearchCB); 
		}
		
		// 키워드 검색 완료 시 호출되는 콜백함수 입니다
		function placesSearchCB (data, status, pagination) {
		    if (status === kakao.maps.services.Status.OK) {
		
		        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
		        // LatLngBounds 객체에 좌표를 추가합니다
		        var bounds = new kakao.maps.LatLngBounds();
		
		        for (var i=0; i<data.length; i++) {
		            displayMarker(data[i]);    
		            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
		        }       
		
		        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
		        map.setBounds(bounds);
		    } 
		}
		
		// 지도에 마커를 표시하는 함수입니다
		function displayMarker(place) {
		    
		    // 마커를 생성하고 지도에 표시합니다
		    var marker = new kakao.maps.Marker({
		        map: map,
		        position: new kakao.maps.LatLng(place.y, place.x) 
		    });
		
		    // 마커에 클릭이벤트를 등록합니다
		    kakao.maps.event.addListener(marker, 'click', function() {
		        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
		        infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>' + place.y + "/" + place.x);
		        infowindow.open(map, marker);
		        
		        var message = '클릭	한 위치의 위도는 <font color="red"> ' + place.y + '</font> 이고, ';
				    message += '경도는 <font color="red"> ' + place.x + '</font> 입니다. &nbsp;';
				    message += '<input type="button" value="처음위치로 복귀" onclick="location.reload();"/><br/>';
				    message += '<p>선택한 지점의 장소명 : '+place.place_name+' &nbsp;';
				    message += '<input type="button" value="장소저장" onclick="addressSave(\''+place.place_name+'\',\''+place.y+'\',\''+place.x+'\')" /></p>';
				    
				    document.getElementById("clickPoint").innerHTML = message;
		    });
		}
		
		function addressSave(address,latitude,longitude) {
			if(address.trim()==""){
				alert("지점을 선택하세요");
				myform.address.focus();
				return false;
			}
			
			var query = {
					address : address,
					latitude : latitude,
					longitude : longitude	
			}
			
			$.ajax({
				type : "post",
				url  : "${ctp}/study/kakaoEx3Save",
				data : query,
				success : function(res) {
					if(res == "1") alert("선택한 지점이 등록되었습니다.");
					else alert("이미 같은지점이 있습니다. 이름을 변경해서 다시 등록하세요.");
				},
				error : function() {
					alert("전송오류!");
				}
			});
		}
		
	</script>
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>