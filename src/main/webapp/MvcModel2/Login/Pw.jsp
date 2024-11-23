<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>비밀번호 찾기</title>
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
            	<h3 class="text-center mb-4">비밀번호를 기억하고 로그인 해주세요</h3><br/>
            	<h5 class="text-center mb-4">비밀번호는 개인정보 수정해서 변경 가능합니다</h5>
					<div class="container">
						<h1 class="text-center mb-4">변경된 비밀번호는 ${pw } 입니다.</h1><br/>
					</div>
                            <button type="submit" class="btn btn-primary w-100 mb-3">비밀번호 찾기</button>
				</div>
            </div>
        </div>
</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>
