<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 페이지</title>
</head>
<body>
<h1>회원가입 페이지</h1>

	
	<form action="${pageContext.request.contextPath}/LoginController/join.do"  method="post">
	<table border=0>
		<tr>
			<td>id</td>
			<td><input type="text" name="userID"></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="userName" placeholder="이름"></td>
		</tr>
		<tr>
			<td>password</td>
			<td><input type="text" name="userPassword" placeholder="비밀번호"></td>
		</tr>
		<tr>
			<td>분류</td>
			<td><input type="radio" name="category" value = "student" checked> 학생
			<input type="radio" name="category" value = "professor"> 교수 
			<input type="radio" name="category" value = "administrator"> 관리자</td>
		</tr>
		<tr>
		<td colspan=2><input type="submit" value="가입 신청하기"></td>
		</tr>
		
	</table>
	 <br />
	<a href="${pageContext.request.contextPath}/">돌아가기</a>
</form>
</body>
</html>