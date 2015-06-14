<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String category = session.getAttribute("category").toString();
  String logincode = session.getAttribute("logincode").toString();
  String courseErr = session.getAttribute("courseErr").toString();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>명지대학교 학과관리시스템</title>
</head>
<body>

	<%if(logincode.equals("0")){%>
		<script language="javascript"> alert("해당하는 유저가 없습니다.");
		history.back();
		</script>
	<%}else if(logincode.equals("1")){%>
		<script language="javascript"> alert("비밀번호가 틀렸습니다.");
		history.back();
		</script>
	<%} if(courseErr.equals("1")){%>
		<script language="javascript"> alert("이미 신청하셨습니다.");
		history.back();
		</script>
	<%}else if(courseErr.equals("2")){%>
		<script language="javascript"> alert("정원 초과입니다");
		history.back();
		</script>
	<%}%>
		

</body>
</html>