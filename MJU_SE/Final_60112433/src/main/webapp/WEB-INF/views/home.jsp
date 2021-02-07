<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>학사관리 메인</title>
</head>
<body>
<h1>
	학사관리 시스템
</h1>

<P>  The time on the server is ${serverTime}. </P> <br /> 
	1. 로그인 <br />
	<form action="${pageContext.request.contextPath}/LoginController/login.do" method="POST">
		<input type="text" name="user_id">	<input type="password" name="user_password"> <br/>
		<button>로그인</button> <br /><br />
	</form>
	2. <a href="${pageContext.request.contextPath}/LoginController/joinPage.do">회원가입</a>

</body>
</html>