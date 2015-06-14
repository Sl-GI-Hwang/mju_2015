<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="com.example.LMS.model.*"%>

<%
	ArrayList<Takes> allTakes = (ArrayList<Takes>) request.getAttribute("allTakes");
	String category = session.getAttribute("category").toString();
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>명지대학교 학과관리시스템</title>
</head>
<body>
<h1>성적 부여 화면</h1>
	<table border = 0>
		<tr>
			<td>학생</td>
			<td>성적</td>
			<td>성적 부여</td>
			
			<td></td>
		</tr>
		
		<%for (Takes c : allTakes) {%>
		<tr>
			<td><%=c.getUcode()%></td>
			<td><%=c.getGrade()%></td>

			<td><form action="${pageContext.request.contextPath}/CourseController/setCredits" method="post">
			<input type = "text" name="grade">
				<input type="hidden" name="cid" value=<%=c.getCid()%>>
				<input type="hidden" name="uid" value=<%=c.getUid()%>>
				<button class="btn_setCreditPage">성적 부여</button>
			</form>
			</td>
			
		</tr>
		<%} %>
	</table>	<br/>
				<form action="${pageContext.request.contextPath}/LoginController/logout.do" method="post">
		<button class="btn_logout">로그아웃</button>
	</form>
				
	<form action="${pageContext.request.contextPath}/CourseController/setCreditMain"">
		<button class="btn_historyback">뒤로</button>
	</form>

			
</body>
</html>
<script type="text/javascript">

</script>