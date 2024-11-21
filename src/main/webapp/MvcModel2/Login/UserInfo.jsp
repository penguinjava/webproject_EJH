<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>유저 정보</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/vendor.css">
    <link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
<header class="bg-light py-3 border-bottom">
    <div class="container text-center">
        <h1>로그인</h1>
    </div>
</header>

<section class="login-banner bg-primary-subtle vh-100 d-flex justify-content-center align-items-center"
         style="background: url('images/login-bg.png') no-repeat center center; background-size: cover;">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 bg-white p-5 rounded-3 shadow">
   				<!-- 사용자 정보 -->
                <div class="container mt-5">
    				<h3 class="login-title text-center mb-4">환영합니다, ${nick }님!</h3>
    					<div class="mb-4 text-center">
        					<p style="color: black;"><strong>회원님 이름 : </strong>
        						${dto.user_name}</p>
        					<p style="color: black;"><strong>회원 가입일 : </strong>
        						${dto.join_date}</p>
        					<p style="color: black;"><strong>이  메  일 : </strong>
        						${dto.email}</p>
        					<p style="color: black;"><strong>전 화 번 호 : </strong>
        						${dto.phone_number}</p>
    					</div>
    			</div>
    			<!-- 개인정보 / 로그아웃 / 돌아가기 -->
    			<div class="text-center" style="display: flex; justify-content: center; gap: 10px;">
    				<!-- 개인정보 수정 -->
 	   					<button type="button" class="btn btn-danger" onclick="location.href='./userEdit.do'">개인 정보 수정</button>
 	   					<!-- 로그아웃 -->
    				<form action="./logout.do" method="post">
        				<button type="submit" class="btn btn-danger">로그아웃</button>
    				</form>
    				<button type="button" class="btn btn-danger" onclick="history.back();">돌아가기</button>    								
				</div>
			</div>
    	</div>
	</div>
</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>
