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
<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    <defs>
	<symbol xmlns="http://www.w3.org/2000/svg" id="user" viewBox="0 0 24 24">
          <path fill="currentColor" d="M15.71 12.71a6 6 0 1 0-7.42 0a10 10 0 0 0-6.22 8.18a1 1 0 0 0 2 .22a8 8 0 0 1 15.9 0a1 1 0 0 0 1 .89h.11a1 1 0 0 0 .88-1.1a10 10 0 0 0-6.25-8.19ZM12 12a4 4 0 1 1 4-4a4 4 0 0 1-4 4Z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="heart" viewBox="0 0 24 24">
          <path fill="currentColor" d="M20.16 4.61A6.27 6.27 0 0 0 12 4a6.27 6.27 0 0 0-8.16 9.48l7.45 7.45a1 1 0 0 0 1.42 0l7.45-7.45a6.27 6.27 0 0 0 0-8.87Zm-1.41 7.46L12 18.81l-6.75-6.74a4.28 4.28 0 0 1 3-7.3a4.25 4.25 0 0 1 3 1.25a1 1 0 0 0 1.42 0a4.27 4.27 0 0 1 6 6.05Z" />
    </symbol>
	
	<svg xmlns="http://www.w3.org/2000/svg" style="display: none;">
    	<defs>
        <symbol xmlns="http://www.w3.org/2000/svg" id="user"
			viewBox="0 0 24 24">
         <path fill="currentColor"
			d="M15.71 12.71a6 6 0 1 0-7.42 0a10 10 0 0 0-6.22 8.18a1 1 0 0 0 2 .22a8 8 0 0 1 15.9 0a1 1 0 0 0 1 .89h.11a1 1 0 0 0 .88-1.1a10 10 0 0 0-6.25-8.19ZM12 12a4 4 0 1 1 4-4a4 4 0 0 1-4 4Z" />
        </symbol>
    	</defs>
</svg>
	<header>
		<div class="container-fluid">
			<div class="row py-3 border-bottom">

				<div class="col-sm-4 col-lg-3 text-center text-sm-start">
					<div class="main-logo">
						<a href="./home.do"> <img src="images/logo.png" alt="logo"
							class="img-fluid">
						</a>
					</div>
				</div>

				<div
					class="col-sm-6 offset-sm-2 offset-md-0 col-lg-5 d-none d-lg-block">
					<div class="search-bar row bg-light p-2 my-2 rounded-4">
						<!-- 검색 기능 -->
						<form action="./boardPage.do" method="GET" class="mb-4" style="text-align: center;">
							<div class="input-group" style="max-width: 600px; margin: 0 auto;">
								<select name="searchFild" class="form-select border-0 bg-transparent">
									<option value="board">자유 게시판</option>
									<option value="file">자료실 게시판</option>
									<option value="comment">Q&A 게시판</option>
								</select>
								<input type="text" name="search" class="form-control"
										placeholder="검색하기" name="searchWord"/>
								<button type="submit" class="btn btn-outline-primary">검색하기</button>
							</div>
						</form>
						<!------------>
					</div>
				</div>

				<div
					class="col-sm-8 col-lg-4 d-flex justify-content-end gap-5 align-items-center mt-4 mt-sm-0 justify-content-center justify-content-sm-end">

					<ul class="d-flex justify-content-end list-unstyled m-0">
						<li><a href="./memberInfo.do"
							class="rounded-circle bg-light p-2 mx-1">
							<svg width="24" height="24" viewBox="0 0 24 24">
									<use xlink:href="#user"></use></svg>
						</a></li>
						<li><a href="#" class="rounded-circle bg-light p-2 mx-1">
								<svg width="24" height="24" viewBox="0 0 24 24">
									<use xlink:href="#heart"></use></svg>
						</a></li>
					</ul>
				</div>

			</div>
		</div>
		<div class="container-fluid">
			<div class="row py-3">
				<div
					class="d-flex  justify-content-center justify-content-sm-between align-items-center">
					<nav class="main-menu d-flex navbar navbar-expand-lg">

						<button class="navbar-toggler" type="button"
							data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
							aria-controls="offcanvasNavbar">
							<span class="navbar-toggler-icon"></span>
						</button>

						<div class="offcanvas offcanvas-end" tabindex="-1"
							id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">

							<div class="offcanvas-header justify-content-center">
								<button type="button" class="btn-close"
									data-bs-dismiss="offcanvas" aria-label="Close"></button>
							</div>

							<div class="offcanvas-body">

								<ul
									class="navbar-nav justify-content-end menu-list list-unstyled d-flex gap-md-3 mb-0">
									<li class="nav-item active"><a href="./boardPage.do"
										class="nav-link">자유게시판</a></li>
									<li class="nav-item dropdown"><a href="./filePage.do"
										class="nav-link">자료실</a></li>
									<li class="nav-item"><a href="./commentPage.do"
										class="nav-link">Q&A게시판</a></li>
								</ul>
							</div>
						</div>
					</nav>
				</div>
			</div>
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
