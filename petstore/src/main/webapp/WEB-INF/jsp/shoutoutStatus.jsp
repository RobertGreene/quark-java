<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shoutout</title>
</head>
<body>

<!-- Common Header -->
<div id="header" class="header">
	<a href="./?session_key=${sessionDetails.sessionKey }">Return to Home Page</a>
	<h1>Post Shoutout</h1>
	<hr>
</div>

<div id="shoutoutStatus" class="statusDisplay">
	
	<table>
		<tr>
		<th>Shoutout Message:</th>
		<td>${shoutout }</td>
		</tr>
		<tr>
		<th>Post Status:</th>
		<td>${shoutoutStatus }</td>
		</tr>
	</table>
	
</div>

</body>
</html>