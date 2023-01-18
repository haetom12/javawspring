<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>qrCode.jsp</title>
  <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
  
  <script>
  	'use strict';
  	
  	function qrCreate(no) {
			let name = '';
			let price = '';
			let ingredient = '';
			let query = '';
			let food = '';
			
  		if(no == 1) {
  			name = myform.name.value;
  			price = myform.price.value;
  			ingredient = myform.ingredient.value;
  			query = {
  					name : name,
  					price : price,
  					ingredient : ingredient
  			}
  		}
  		
  		$.ajax({
  			type : "post",
  			url  : "${ctp}/study/qrCode2",
				data : query,  	
				success : function(res) {
					/* alert("QR코드가 생성되었습니다. 이름은? " + res); */
					food = res.substr(-8);
					$("#qrCodeView").show();
					$("#qrView").html(res);
					let qrImage = '<img src="${ctp}/data/qrCode/'+res+'.png">';
					$("#qrImage").html(qrImage);
					$("#foodStr").val(food);
					/* location.href='${ctp}/study/foodSearch2?foodStr='+food; */
					
				},
				error : function() {
					alert("전송오류!");
				}
  		});
  	}
  	// 음식 검색
  	function foodCheck() {
			let name = '';

  		if(no == 1) {
  			name = myform.name.value;
  			query = {
  					name : name
  			}
  		}
  		
  		$.ajax({
  			type : "post",
  			url  : "${ctp}/study/qrCode",
				data : query,  	
				success : function(res) {
					alert("QR코드가 생성되었습니다. 이름은? " + res);
					$("#qrCodeView").show();
					$("#qrView").html(res);
					let qrImage = '<img src="${ctp}/data/qrCode/'+res+'.png">';
					$("#qrImage").html(qrImage);
				},
				error : function() {
					alert("전송오류!");
				}
  		});
  	}
  </script>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<div class="container">
	<form name="myform">
	  <h2>QR Code 생성연습</h2>
	  <p>
	  	상품명 : <input type="text" name="name" style="width: 50%" class="form-control"/><br />
	  	가격 : <input type="number" name="price" style="width: 50%" class="form-control"/><br />
	  	재료 : <input type="text" name="ingredient" style="width: 50%" class="form-control"/><br />
	  	<input type="button" value="신상정보QR생성" onclick="qrCreate(1)" class="btn btn-success" />
	  </p>
	  <hr />
	  <div id="qrCodeView" style="display: none;">
	  	<h3>생성된 QR코드 확인하기</h3>
	  	<div>
	  		- 생성돤 qr코드명 : <span id="qrView"></span><br />
	  		<span id="qrImage"></span>
	  	</div>
	  </div>
  </form>
	<form name="myform2" method="post" action="${ctp}/study/foodSearch">
	<div>
		검색할 고유번호 : <input type="text" name="foodStr" id="foodStr" class="form-control" style="width: 50%" />
		<input type="submit" value="검색" class="btn btn-primary"/>
	</div>
	</form>
	<c:if test="${vo != null}">
		<h2>검색한 상품 정보입니다</h2>
	
	  상품번호 : <input type="text" name="idx" value="${vo.idx}" style="width: 50%" class="form-control"/><br />
	  상품명 : <input type="text" name="name" value="${vo.name}" style="width: 50%" class="form-control"/><br />
  	가격 : <input type="number" name="price" value="${vo.price}" style="width: 50%" class="form-control"/><br />
  	재료 : <input type="text" name="ingredient" value="${vo.ingredient}" style="width: 50%" class="form-control"/><br />
	</c:if>
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>