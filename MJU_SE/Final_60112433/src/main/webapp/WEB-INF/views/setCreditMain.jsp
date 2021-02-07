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
<script src="http://code.jquery.com/jquery-latest.min.js"
	type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/resources/stylesheets/registForCourse.css" rel="stylesheets">

<title>명지대학교 학과관리시스템</title>
</head>
<body>
<h1>성적 부여 화면 메인</h1>
	<table border = 0>
		<tr>
			<td>강좌번호</td>
			<td>과목명</td>
			<td>개설년도</td>
			<td>학년</td>
			<td>학점</td>
			<td>정원</td>
			<td></td>
		</tr>
		
		<%for (Lecture c : allLecture) {%>
		<tr>
			<td><%=c.getCid()%></td>
			<td><%=c.getGwamokname()%></td>
			<td><%=c.getYear()%></td>
			<td><%=c.getGrade()%></td>
			<td><%=c.getHackjum()%></td>
			<td><%=c.getMaxpeople() %></td>
			
		</tr>
		<%} %>
	</table> <br/>
	<form action="${pageContext.request.contextPath}/CourseController/setCreditPage" method="post">
	성적을 부여할 과목의 강좌번호를 입력해 주세요  <br/>
	 <input type="text" name="cid">
				<button class="btn_setCreditPage">성적 부여하기</button>
				</form>
		<br/>
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