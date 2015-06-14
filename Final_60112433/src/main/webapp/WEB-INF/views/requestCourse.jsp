<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.example.LMS.model.*"%>

<%
	ArrayList<Course> allCourses = (ArrayList<Course>) request.getAttribute("allCourses");
	String category = session.getAttribute("category").toString();
	String userID = session.getAttribute("userID").toString();
	String userPass = session.getAttribute("userPass").toString();
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; chsarset=UTF-8">


<title>수강 신청 화면</title>
</head>
<body>
<h1>수강 신청 화면 </h1>

	<table border = 0>
		<tr>
			<td> 강좌번호 </td>
			<td> 과목명 </td>
			<td> 담당교수 </td>
			<td> 연도 </td>
			<td> 학년 </td>
			<td> 학점 </td>
			<td> 정원 </td>
			<td> 신청하기 </td>
		</tr>
		<%for (Course c : allCourses) {%>
		<tr>
			<td><%=c.getCid()%></td>
			<td><%=c.getGwamokname()%></td>
			<td><%=c.getPname()%></td>
			<td><%=c.getYear()%></td>
			<td><%=c.getGrade()%></td>
			<td><%=c.getHackjum()%></td>
			<td><%=c.getMaxpeople() %></td>
			<td>
				<form action="${pageContext.request.contextPath}/CourseController/requestCourse" method="post">
				<input type="hidden" name="cid" value=<%=c.getCid()%>>
				<button class="btn_registForCourse">신청</button>
				</form>
			</td>
		</tr>
		<%} %>
	</table>	<br/><br/>
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
