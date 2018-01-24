<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>MyPage</h1>
	<img alt="" src="../resources/upload/${member.fname}" width="200px" height="200px">
	<h3>ID : ${member.id}</h3>
	<h3>PW : ${member.pw}</h3>
	<h3>NAME : ${member.name}</h3>
	<h3>EMAIL : ${member.email}</h3>
	<h3>PHONE : ${member.phone}</h3>
	<h3>AGE : ${member.age}</h3>
	<h3>JOB : ${member.job}</h3>
	
	<a href="memberUpdate">수정</a>
	<a href="memberDelete">탈퇴</a>
</body>
</html>