<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.example.LMS.model.*"%>

<%
	ArrayList<Lecture> allLecture = (ArrayList<Lecture>) request.getAttribute("allLecture");
	String category = session.getAttribute("category").toString();
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${pageContext.request.contextPath}/resources/stylesheets/registForCourse.css" rel="stylesheets">

<title>명지대학교 학과관리시스템</title>
</head>
<body>
<h1>성적 열람 화면</h1>
	<table border = 0>
		<tr>
			<td>강좌번호</td>
			<td>과목명</td>
			<td>담당교수</td>
			<td>개설년도</td>
			<td>학년</td>	
			<td>학점</td>
			<td>정원</td>
			<td>평점</td>
			<td></td>
		</tr>
		
				<%for (Lecture c : allLecture) {%>
		<tr>
			<td><%=c.getCid()%></td>
			<td><%=c.getGwamokname()%></td>
			<td><%=c.getProfname()%></td>
			<td><%=c.getYear()%></td>
			<td><%=c.getGrade()%></td>
			<td><%=c.getHackjum()%></td>
			<td><%=c.getMaxpeople() %></td>
			<td><%=c.getCredit() %></td>
			
		</tr>
		<%} %>
	</table> <br/>

	<form action="${pageContext.request.contextPath}/LoginController/logout.do" method="post">
		<button class="btn_logout">로그아웃</button>
	</form>
	<form action="${pageContext.request.contextPath}/LoginController/login.do" method="post">
		<input type = "hidden" name ="user_id" value = "${userID}">
		<input type = "hidden" name ="user_password" value = "${userPass}">
		<button class="btn_historyback">뒤로</button>
	</form>
	
			
</body>
</html>
<script type="text/javascript">

</script>