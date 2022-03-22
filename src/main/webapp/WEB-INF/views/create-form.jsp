<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 상대 경로 사용, 현재 URL이 속한 계층 결로 + /save // 보통은 절대 경로로 하는게 더 좋다 -->
	<form action="save" method="post">
		username: <input type="text" name="username">
		age: <input type="text" name="age">
		<button type="submit">전송</button>
	</form>

</body>
</html>

<!-- WEB-INF 에 있는 view 템플릿은 webapp 처럼 바로 호출되지 않는다. 컨트롤러를 통해서만 매핑이 된다. -->