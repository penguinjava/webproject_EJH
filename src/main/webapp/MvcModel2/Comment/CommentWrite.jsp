<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>게시판 작성</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
          crossorigin="anonymous">
    <link rel="stylesheet" href="css/vendor.css">
    <link rel="stylesheet" href="style.css">
</head>
<body>  
    <div class="container mt-5">
        <h2 class="text-center mb-4">게시글 작성</h2>
        <form action="./boardWrite.do" method="post">
            <div class="mb-3">
                <label for="title" class="form-label">제목</label>
                <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요" required>
            </div>
            <div class="mb-3">
                <label for="content" class="form-label">내용</label>
                <textarea class="form-control" id="content" name="content" rows="10" placeholder="내용을 입력하세요" required></textarea>
            </div>
            <!-- 게시글 종류 구분 -->
            <input type="hidden" value="comment" name="category" />
            <div class="text-center">
                <button type="submit" class="btn btn-primary">글 작성</button>
                <a href="./boardPage.do" class="btn btn-secondary">취소</a>
            </div>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
            crossorigin="anonymous"></script>
</body>
</html>