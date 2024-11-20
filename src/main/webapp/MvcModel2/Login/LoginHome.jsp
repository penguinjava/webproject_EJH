<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>로그인 화면</title>
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
                <c:choose>
                    <c:when test="${empty id}">
                        <h3 class="text-center mb-4">환영합니다</h3>
                        <form action="./login.do" method="post" class="p-4">
                            <div class="mb-3">
                                <label for="user_id" class="form-label">회원 ID</label>
                                <input type="text" id="user_id" name="user_id" class="form-control" placeholder="ID"
                                       required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">회원 비밀번호</label>
                                <input type="password" id="password" name="password" class="form-control"
                                       placeholder="PW" required>
                            </div>
                            <button type="submit" class="btn btn-primary w-100 mb-3">로그인</button>
                        </form>
                        <div class="text-center">
                            <a href="#" class="nav-link text-dark">아이디 찾기</a>
                            <a href="#" class="nav-link text-dark">비밀번호 찾기</a>
                        </div>
                        <div class="text-center mt-4">
                            <a href="./create.do" class="btn btn-secondary">회원 가입</a>
                        </div>
                    </c:when>
                    <c:otherwise>
    						<!-- 로그아웃 버튼 -->
                        <h3 class="text-center mb-4">환영합니다, ${nick}님!</h3>
                        
    						<div class="text-center" style="display: flex; justify-content: center; gap: 10px;">
    							<form action="./logout.do" method="post">
        							<button type="submit" class="btn btn-danger" name="indexOut">로그아웃</button>
    							</form>
    								<button type="button" class="btn btn-danger" onclick="history.back();">돌아가기</button>
							</div>
                    </c:otherwise>
                </c:choose>
				</div>
            </div>
        </div>
</section>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>
