<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>guInput.jsp</title>
  <jsp:include page="/WEB-INF/views/include/bs4.jsp"></jsp:include>
  <style>
  	body{margin-top:20px;
		background:#eee;
		}
		.contact-area {
		    background: url(../img/bg/contact-bg.png) no-repeat;
		    background-attachment: fixed;
		    background-size: cover;
		    background-position: center;
		}
		
		@media only screen and (max-width:768px) {
		    .contact {
		        margin-bottom: 60px;
		    }
		}
		
		.contact input {
		    background: #fff;
		    border: 1px solid #fff;
		    border-radius: 3px;
		    -webkit-box-shadow: none;
		    box-shadow: none;
		    color: #232434;
		    font-size: 16px;
		    height: 60px;
		    padding: 10px;
		    width: 100%;
		    font-family: 'poppins', sans-serif;
		    padding-left: 30px;
		    -webkit-transition: all 0.3s ease 0s;
		    -o-transition: all 0.3s ease 0s;
		    transition: all 0.3s ease 0s;
		}
		
		.contact textarea {
		    background: #fff;
		    border: 1px solid #fff;
		    border-radius: 3px;
		    -webkit-box-shadow: none;
		    box-shadow: none;
		    color: #232434;
		    font-size: 16px;
		    padding: 10px;
		    width: 100%;
		    font-family: 'poppins', sans-serif;
		    padding-left: 30px;
		    -webkit-transition: all 0.3s ease 0s;
		    -o-transition: all 0.3s ease 0s;
		    transition: all 0.3s ease 0s;
		}
		
		.contact input:focus {
		    background: #fff;
		    border: 1px solid #fff;
		    color: #232434;
		    -webkit-box-shadow: none;
		    box-shadow: none;
		    outline: 0 none;
		}
		
		.contact textarea:focus {
		    background: #fff;
		    border: 1px solid #fff;
		    color: #232434;
		    -webkit-box-shadow: none;
		    box-shadow: none;
		    outline: 0 none;
		}
		
		.form-control::placeholder {
		    color: #232434;
		    opacity: 1;
		}
		
		.btn-contact-bg {
		    border-radius: 30px;
		    color: #fff;
		    outline: medium none !important;
		    padding: 15px 27px;
		    text-transform: capitalize;
		    -webkit-transition: all 0.3s ease 0s;
		    -o-transition: all 0.3s ease 0s;
		    transition: all 0.3s ease 0s;
		    background: #7564e5;
		    font-family: 'poppins', sans-serif;
		    cursor: pointer;
		    width: 100%;
		}
		
		.btn-contact-bg:hover,
		.btn-contact-bg:focus {
		    background: #232434;
		    color: #fff;
		}
		
		/*START ADDRESS*/
		
		.single_address {
		    overflow: hidden;
		    margin-bottom: 10px;
		    padding-left: 40px;
		}
		
		@media only screen and (max-width:768px) {
		    .single_address {
		        padding-left: 0px;
		    }
		}
		
		.single_address i {
		    background: #f6f6f6;
		    color: #7564e5;
		    border-radius: 30px;
		    width: 60px;
		    height: 60px;
		    line-height: 60px;
		    text-align: center;
		    float: left;
		    margin-right: 14px;
		    font-size: 22px;
		    -webkit-box-shadow: 0 5px 30px 0 rgba(0, 0, 0, 0.1);
		    box-shadow: 0 5px 30px 0 rgba(0, 0, 0, 0.1);
		    margin-bottom: 20px;
		    -webkit-transition: all 0.3s ease 0s;
		    -o-transition: all 0.3s ease 0s;
		    transition: all 0.3s ease 0s;
		}
		
		.single_address:hover i {
		    background: #7564e5;
		    color: #fff;
		}
		
		.single_address h4 {
		    font-size: 18px;
		    margin-bottom: 0px;
		    overflow: hidden;
		    font-weight: 600;
		}
		
		.single_address p {
		    overflow: hidden;
		    margin-top: 5px;
		}
		
		.section-title h1 {
		    font-size: 44px;
		    font-weight: 500;
		    margin-top: 0;
		    position: relative;
		    text-transform: capitalize;
		    margin-bottom: 15px;
		}
		.section-title p {
		    padding: 0 10px;
		    width: 70%;
		    margin: auto;
		    letter-spacing: 1px;
		}
		.section-title {
		    margin-bottom: 60px;
		}
		.text-center {
		    text-align: center!important;
		}
		.submitButton {
			margin-bottom: 5px;
		}
		
  </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/include/nav.jsp" />
<jsp:include page="/WEB-INF/views/include/slide2.jsp" />
<p><br/></p>
<div class="container">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />

<div id="contact" class="contact-area section-padding">
	<div class="container">										
		<div class="section-title text-center">
			<h1>방명록 등록</h1>
			<h3>비속어나 어긋나는 내용을 작성시 관리자에 의해 삭제될수 있습니다 ㅎ</h3>
		</div>					
		<div class="row">
			<div class="col-lg-7">	
				<div class="contact">
					<form class="form" name="enq" method="post">
						<div class="row">
							<div class="form-group col-md-6">
								<input type="text" name="name" id="name" class="form-control" placeholder="이름을 입력하세요." required="required">
							</div>
							<div class="form-group col-md-6">
								<input type="email" name="email" id="email" class="form-control" placeholder="이메일주소를 입력하세요." >
							</div>
							<div class="form-group col-md-12">
								<input type="text" name="homePage" id="homePage" class="form-control" placeholder="홈페이지 주소를 입력하세요." value="http://" >
							</div>
							<div class="form-group col-md-12">
								<textarea rows="6" name="content" id="content" class="form-control" placeholder="내용을 입력하세요" required="required"></textarea>
							</div>
							<div class="col-md-12 text-center">
								<button type="submit" value="방명록 등록" name="submit" class="btn btn-contact-bg submitButton">방명록 등록</button>
								<button type="reset" value="다시쓰기"  class="btn btn-contact-bg submitButton">다시 쓰기</button>
								<button type="button" value="뒤로가기" name="submit" onclick="location.href='${ctp}/guest/guestList';" class="btn btn-contact-bg submitButton">뒤로가기</button>
							</div>
						</div>
						<input type="hidden" name="hostIp" value="<%=request.getRemoteAddr()%>"/>
					</form>
				</div>
			</div><!--- END COL --> 
			<div class="col-lg-5">
				<div class="single_address">
					<i class="fa fa-map-marker"></i>
					<h4>그린컴퓨터학원</h4>
					<p>청주, 사장사거리 어딘가</p>
				</div>
				<div class="single_address">
					<i class="fa fa-envelope"></i>
					<h4>문의 문자는 이곳으로</h4>
					<p>haetom@naver.com</p>
				</div>
				<div class="single_address">
					<i class="fa fa-phone"></i>
					<h4>학원 연락처</h4>
					<p>(+82) 517 397 7100</p>
				</div>
				<div class="single_address">
					<i class="fa fa-clock-o"></i>
					<h4>강의 시간</h4>
					<p>Mon - Fri: 09.30 - 17.50</p>
				</div>					
			</div><!--- END COL --> 
		</div><!--- END ROW -->
	</div><!--- END CONTAINER -->	
</div>
</div>
<p><br/></p>
<jsp:include page="/WEB-INF/views/include/footer.jsp" />
</body>
</html>