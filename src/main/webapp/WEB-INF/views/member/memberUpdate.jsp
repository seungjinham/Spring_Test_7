<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		$("#remove").click(function() {
			$(this).remove();
			$("#result").append('<p><input type="file" name="file"></p>');
		});			
	});
	
</script>
</head>
<body>
	<h1>MyPage Update</h1>
	<form action="memberUpdate" method="post" enctype="multipart/form-data">
		<c:if test="${empty member.fname}">
			<input type="file" name="file">
		</c:if>

		<c:if test="${not empty member.fname}">
			<img src="../resources/upload/${member.fname}" width="200px" height="200px">
			<input type="hidden" name="oname" value="${member.oname}">
			<input type="hidden" name="fname" value="${member.fname}">
			<div id="result">
				<span id="remove">${member.oname} X</span>
			</div>
			
		</c:if>

		<input type="hidden" name="id" value="${member.id}">
		<p>PW : <input type="password" name="pw" value="${member.pw}"></p>
		<p>NAME : <input type="text" name="name" value="${member.name}"></p>
		<p>EMAIL : <input type="email" name="email" value="${member.email}"></p>
		<p>PHONE : <input type="text" name="phone" value="${member.phone}"></p>
		<p>AGE : <input type="text" name="age" value="${member.age}"></p>
		<p>JOB : 
			<c:if test="${member.job == 'S'}">
				<input type="radio" name="job" value="S" checked="checked">S
				<input type="radio" name="job" value="T">T
			</c:if>		
			<c:if test="${member.job == 'T'}">
				<input type="radio" name="job" value="S">S
				<input type="radio" name="job" value="T" checked="checked">T
			</c:if>	
		</p>
		<button>수정</button>
	</form>
</body>
</html>