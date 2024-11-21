<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="../memberInfo.do">정보 보기</a>
<h1>정보</h1>${dto.user_name }
<p>아이디</p>${dto.user_id }
<p>닉네임</p>${dto.nick_name }
<p>날짜</p>${join_date }
</body>
</html>