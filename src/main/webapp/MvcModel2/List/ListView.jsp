<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유 게시판</title>
</head>
<body>
<table border="1" style="width: 100%; text-align: center;">
	<tr>
    	<td>번호</td> <td>${dto.board_id }</td>
        <td>작성자</td> <td>${dto.nickname }</td>
    </tr>
    <tr>
    	<c:choose>
    		<c:when test="${ymd eq today}">
       			<td>작성일</td> <td>${ymd }</td>
       		</c:when>
       		<c:otherwise>
       			<td>작성일</td> <td>${time }</td>
       		</c:otherwise>
       	</c:choose>
        <td>조회수</td> <td>${dto.visitcount }</td>
    </tr>
    <tr>
    	<td>제목</td>
    	<td colspan="3">${dto.title }</td>
    </tr>
    <tbody>
    	<tr>
        	<td colspan="5" style="text-align: center; color: gray;">
        		${dto.content }
        	</td>
        </tr>
    </tbody>
        
        <!-- 게시글 수정 삭제 -->
	<tfoot>
		<tr>
			<td colspan="4" align="center">
				<button type="button"
				onclick="location.href='../../edit.do?';">
				수정 하기</button>
				
				<button type="button"
				onclick="location.href='../../delete.do?';">
				삭제 하기</button>
				
				<button type="button"
				onclick="location.href='../../boardlist.do';">
				목록 바로가기</button>
			</td>
		</tr>
	</tfoot>
</table>

</body>
</html>