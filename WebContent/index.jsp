<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<html>
		<head>
		<meta charset="UTF-8">
		<title>Корисничка апликација</title>
	</head>
	<body>
		<h3>Корисничка апликација - YI Web Design 001</h3>
		<dl>
			<dt>Странице изгледа</dt>
			<dd><a href='${pageContext.request.contextPath}/example.jsp' target='_blank'>Шаблон изгледа страница</a></dd>
			<dd><a href='${pageContext.request.contextPath}/home.jsp' target='_blank'>Општи кориснички интерфејс</a></dd>
		</dl>
		<dl>
			<dt>Тестирање</dt>
			<dd><a href='${pageContext.request.contextPath}/TEST/test-error.jsp' target='_blank'>Тестирање грешке</a></dd>
			<dd><a href='${pageContext.request.contextPath}/TEST/test-exception.jsp' target='_blank'>Тестирање изузетка</a></dd>
			<dd><a href='${pageContext.request.contextPath}/TEST/test-not-found.jsp' target='_blank'>Тестирање непостојање</a></dd>
		</dl>
		<dl>
			<dt>Апликације</dt>
			<dd><a href='${pageContext.request.contextPath}/home-users.jsp' target='_blank'>Корисници и пријавни систем</a></dd>
		</dl>
		<dl>
			<dt>Сервиси</dt>
			<dd><a href='${pageContext.request.contextPath}/yatospace/rss/fly/today'  target='_blank'>Данашњи летови</a></dd>
		</dl>
		<dl>
			<dt>Странице</dt>
			<dd><a href='${pageContext.request.contextPath}/fly-today.jsp'  target='_blank'>Страница са летовима</a></dd>
		</dl>
		<dl>
			<dt>Примјери</dt>
			<dd><a href='${pageContext.request.contextPath}/rss' target='_blank'>RSS Example</a></dd>
		</dl>
	</body>
</html>