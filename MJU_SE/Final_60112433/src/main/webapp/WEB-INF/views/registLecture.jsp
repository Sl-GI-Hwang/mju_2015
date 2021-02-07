<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.example.LMS.model.*"%>

<%
	ArrayList<Lecture> allLectures = (ArrayList<Lecture>) request.getAttribute("allLectures");
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="${pageContext.request.contextPath}/resources/stylesheets/registForCourse.css" rel="stylesheets">

<title>강좌 개설 화면</title>
</head>
<body>
<h1>강좌 개설 화면</h1>
	<table border = 0>
		<tr>
			<td>강좌번호</td>
			<td>과목명</td>
			<td>연도</td>
			<td>학년</td>
			<td>정원</td>
			<td>학점</td>
			<td></td>
		</tr>
		<%for (Lecture l : allLectures) {%>
		<form action="${pageContext.request.contextPath}/CourseController/deleteLecture" method="post">
		<input type="hidden" name="cid" value=<%=l.getCid()%>>
		<tr>
			<td><%=l.getCid()%></td>
			<td><%=l.getGwamokname()%></td>
			<td><%=l.getYear()%></td>
			<td><%=l.getGrade()%></td>
			<td><%=l.getMaxpeople() %></td>
			<td><%=l.getHackjum()%></td>
			<td>
				<button class="btn_registForLecture">삭제</button>
			</td>
		</tr>
		</form>
		<%}%>
		<form action="${pageContext.request.contextPath}/CourseController/registLecture" method="post">
			<tr>
			
				<td><input type="text" name="cid" ></td>
				<td><input type="text" name="gwamokname"></td>
				<td><input type="text" name="year"></td>
				<td><input type="text" name="grade"></td>
				<td><input type="text" name="maxpeople" ></td>
				<td><input type="text" name="hackjum"></td>
				<td><button class="btn_registForLecture"> 과목 개설</button></td>
			<input type = "hidden" name ="uid" value = "${uid}">
		</form>	
	</table>	<br/>
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
