<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Title</title>
</head>
<body>
mvc 성공
<ul>
	<li>id=${member.id}</li> <!-- ((Member)request.getAttribute("member")).getId() 대신 이렇게 표현 가능 -->
	<li>username=${member.username}</li>
	<li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>