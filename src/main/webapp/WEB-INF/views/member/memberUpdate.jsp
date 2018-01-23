<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="memberUpdate" method="post" enctype="multipart/form-data">
		<p>ID : <input type="text" name="id" id="id" value="${member.id}"></p>
		<p>PW : <input type="password" name="pw" value="${member.pw}"></p>
		<p>NAME : <input type="text" name="name" value="${member.name}"></p>
		<p>EMAIL : <input type="email" name="email" value="${member.email}"></p>
		<p>PHONE : <input type="text" name="phone" value="${member.phone}"></p>
		<p>AGE : <input type="text" name="age" value="${member.id}"></p>
		<p>JOB : <input type="radio" name="job" value="S">S
				 <input type="radio" name="job" value="T">T
		</p>
		<p><input type="file" name="file"></p>
		<button>Update</button>
	</form>
</body>
</html>