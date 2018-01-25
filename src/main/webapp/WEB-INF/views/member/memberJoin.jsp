<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
 	$(function() {
 		var check=true;
		$("#id").keyup(function() {
			//선택자.함수		
			var data = $(this).val();
			
			$.post("memberIdCheck",{id:data},function(result){	
				if(result.trim()==0){
					$("#result").html("중복된 ID입니다");
				}else{
					$("#result").html("사용가능한 ID입니다");
				}
			});
			
		});
	});

</script>
</head>
<body>
	<h1>Member Join</h1>
	<form action="./memberJoin" method="post" enctype="multipart/form-data">
		<p>ID : <input type="text" name="id" id="id">
			<div id="result">
			
			</div>
		</p>
		<p>PW : <input type="password" name="pw"></p>
		<p>NAME : <input type="text" name="name"></p>
		<p>EMAIL : <input type="text" name="email"></p>
		<p>PHONE : <input type="text" name="phone"></p>
		<p>AGE : <input type="text" name="age"></p>
		<p>JOB : <input type="radio" name="job" value="S">S
				 <input type="radio" name="job" value="T">T
		</p>
		<p>PHOTO : <input type="file" name="file"></p>
		<button>Join</button>
	</form>

</body>
</html>