<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wallet Balance</title>
</head>

<body bgcolor="gray">
<!-- Common Header -->
<div id="header" class="header">
	<a href="./?session_key=${sessionDetails.sessionKey }">Return to Home Page</a>
	<h1>Wallet Balance</h1>
	<hr>
</div>

<div id="walletBalance" class="statusDisplay">

	<table>
		<tr>
		<th>User ID</th>
		<td>${userId }</td>
		</tr>
		<tr>
		<th>Wallet Balance</th>
		<td>${walletBalance }</td>
		</tr>
	</table>	
</div>

</body>
</html>