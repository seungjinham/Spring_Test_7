<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${board}View</h1>
	
	<h3>NUM : ${view.num}</h3>
	<h3>TITLE : ${view.title}</h3>
	<h3>WRITER : ${view.writer}</h3>
	<h3>CONTENTS : ${view.contents}</h3>
	<h3>DATE : ${view.reg_date}</h3>
	<h3>HIT : ${view.hit}</h3>	
	
	<!-- 첨부파일 a 태그 사용 -->
	<c:forEach items="${view.files}" var="file" >
		<a href="../file/fileDown?fname=${file.fname}&oname=${file.oname}">${file.oname}</a>
	</c:forEach>
	
	<br>
	<a href="${board}Update?num=${view.num}">Update</a>
	<a href="${board}Delete?num=${view.num}">Delete</a>
	
</body>
</html>