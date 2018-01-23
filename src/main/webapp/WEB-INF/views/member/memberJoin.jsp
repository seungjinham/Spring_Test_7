<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
/* 	$(function() {
		$("#id").keyup(function() {
			
		});
	});
	 */
</script>
</head>
<body>

	<form action="memberJoin" method="post" enctype="multipart/form-data">
		<p>ID : <input type="text" name="id" id="id"></p>
		<p>PW : <input type="password" name="pw"></p>
		<p>NAME : <input type="text" name="name"></p>
		<p>EMAIL : <input type="text" name="email"></p>
		<p>PHONE : <input type="text" name="phone"></p>
		<p>AGE : <input type="text" name="age"></p>
		<p>JOB : <input type="radio" name="job" value="S">S
				 <input type="radio" name="job" value="T">T
		</p>
		<p><input type="file" name="file"></p>
		<button>Join</button>
	</form>

</body>
</html>