<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>교수 페이지</title>
</head>
<body>
		<h1>교수 페이지 <br/></h1>
		<a href="${pageContext.request.contextPath}/CourseController/registLecturePage">
		<input type = "hidden" name ="User" value = "${User}"> 1. 강좌 개설</a><br/>
		<a href="${pageContext.request.contextPath}/CourseController/setCreditMain">
		<input type = "hidden" name ="User" value = "${User}"> 2. 성적 부여</a><br/><br/>
		
		<a href="${pageContext.request.contextPath}/LoginController/logout.do">로그아웃</a>
</body>
</html>