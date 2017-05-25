<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome TO My Zone</h1>
	<form action="/login" method="post">
		<div>
			<label> username: <input type="text" name="username" />
			</label>
		</div>
		<div>
			<label> password: <input type="password" name="password" />
			</label>
		</div>
		<div>
			<input type="submit" value="login" />
			<a href="regist">regist</a>
		</div>
		
	</form>
</body>
</html>