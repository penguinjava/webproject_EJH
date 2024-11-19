<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="author" content="">
<meta name="keywords" content="">
<meta name="description" content="">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/vendor.css">
<link rel="stylesheet" type="text/css" href="style.css">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@400;700&family=Open+Sans:ital,wght@0,400;0,700;1,400;1,700&display=swap"
	rel="stylesheet">

</head>

<body>
	<div class="login-banner bg-primary-subtle block-2"
		style="background: url('images/login-bg.png') no-repeat; background-position: center; background-size: cover;">
		<div class="row login-content p-5">
			<div class="content-wrapper col-md-7 mx-auto text-white">
				<div class="login-banner bg-primary-subtle block-2"
					style="background: url('images/login-bg.png') no-repeat; background-position: center; background-size: cover;">
					<div class="row login-content p-5">
						<div class="content-wrapper col-md-7 mx-auto text-white">
							<h3 class="login-title text-center mb-4 text-dark">회원 가입</h3>
							<!-- 필수 표시 -->
							<div class="mb-3" style="color: red;">* 표시는 필수 항목 입니다.</div>
							<form action="../../create.do" method="post" class="p-4">
								<!-- 아이디 -->
								<div class="mb-3">
									<label for="user_id" class="form-label text-dark">
									<span style="color: red;">*</span> 회원 ID </label>
									<input type="text" id="user_id" name="user_id"
										class="form-control" placeholder="아이디" 
										oninput="check('user_id', this.value);">
									<div id="Message"></div>
								</div>
								
								<!-- 아이디 -->
								<div class="mb-3">
									<label for="user_name" class="form-label text-dark"> <span
										style="color: red;">*</span> 회원 이름
									</label> <input type="text" id="user_name" name="user_name"
										class="form-control" placeholder="본인 이름">
								</div>
								
								<!-- 비밀번호 -->
								<div class="mb-3">
									<label for="password" class="form-label text-dark">
									<span style="color: red;">*</span> 비밀번호
									</label> <input type="password" id="password" name="password"
										class="form-control" placeholder="비밀 번호">
								</div>

								<!-- 닉네임 -->
								<div class="mb-3">
									<label for="nickname" class="form-label text-dark">
									<span style="color: red;">*</span> 닉네임
									</label> <input type="text" id="nickname" name="nickname"
										class="form-control" placeholder="게시판 별명">
								</div>

								<!-- 이메일 -->
								<div class="mb-3">
									<label for="email" class="form-label text-dark">이메일</label>
									<input type="email" id="email" name="email" class="form-control"
										placeholder="xxx@xxx.com">
								</div>

								<!-- 전화 번호 -->
								<div class="mb-3">
									<span style="color: red;">*</span>
									<label for="phone_number" class="form-label text-dark">전화번호</label>
									<p style="color: black;">010 - 
									<input type="text" id="f_number" name="f_number" class="form-control" 
           									placeholder="앞" maxlength="4" oninput="앞자리" 
           									style="color: black; display: inline-block; width: 70px;"> - 
   						 			<input type="text" id="s_number" name="s_number" class="form-control" 
           									placeholder="뒷" maxlength="4" oninput="뒷자리" 
           									style="color: black; display: inline-block; width: 70px;">
								</div>

								<!-- 성별 -->
								<div class="mb-3">
								<span style="color: red;">*</span>
									<label for="gender" class="form-label text-dark">성별</label><br/>
									<input type="radio" id="male" name="gender" value="M"
										class="form-check-input">
										<label for="dender" class="form-check-label" style="color: black;">남자</label>
										<input type="radio" id="female" name="gender" value="W"
										class="form-check-input">
										<label for="dender" class="form-check-label" style="color: black;">여자</label>
								</div>


								<!-- 주소 -->
								<div class="mb-3">
									<span style="color: red;">*</span>
    								<label for="address" class="form-label text-dark">사는 지역</label>
    								<select id="address" name="address" class="form-control">
        								<option value="서울특별시">서울특별시</option>
        								<option value="인천광역시">인천광역시</option>
        								<option value="세종별자치시">세종특별자치시</option>
        								<option value="울산광역시">울산광역시</option>
        								<option value="대전광역시">대전광역시</option>
        								<option value="광주광역시">광주광역시</option>
        								<option value="부산광역시">부산광역시</option>
        								<option value="대구광역시">대구광역시</option>
        								<option value="경기도">경기도</option>
        								<option value="충청남도">충청남도</option>
        								<option value="충청북도">충청북도</option>
        								<option value="전라북도">전라북도</option>
        								<option value="전라남도">전라남도</option>
        								<option value="강원도">강원도</option>
        								<option value="경상북도">경상북도</option>
        								<option value="경상남도">경상남도</option>
        								<option value="제주">제주</option>
        								<!-- 필요에 따라 더 많은 지역 추가 -->
    								</select>
								</div>

								<!-- 회원 가입 -->
								<button type="submit" class="btn btn-primary w-100 mb-3">회원
									가입</button>

								<!-- 회원 재확인 -->
								<div class="text-center">
									<a href="../../index.jsp" class="nav-link text-dark">이미 계정이
										있으신가요? 로그인</a>
								</div>
							</form>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	</div>
	
	<script src="js/Check.js"></script>
</body>
</html>