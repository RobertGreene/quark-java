<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Information</title>
</head>
<body>
<a href="index.jsp">>>Back</a>
<pre>
<h1>User Information</h1>
<hr>
<div align="right">User ID: <b>${userInfo.userId}</b></div>
<div id="content">
<table>
	<tr>
	<th>Attribute</th>
	<th>Value</th>
	</tr>
	
	<tr>
	<td>Name</td>
	<td>${userInfo.name}</td>
	</tr>
	
	<tr>
	<td>Level</td>
	<td>${userInfo.level}</td>
	</tr>
</table>
</div>
</pre>
</body>
</html>