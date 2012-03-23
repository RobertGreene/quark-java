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
	<h1>Wallet Payment</h1>
	<hr>
</div>

<div id="walletPayment" class="statusDisplay">

	<table>
		<tr>
		<th>Amount</th>
		<td>${amount }</td>
		</tr>
		<tr>
		<th>Callback URL</th>
		<td>${callback_url }</td>
		</tr>
		
		<tr>
		<th>Request Token</th>
		<td>${request_token }</td>
		</tr>
	</table>	
</div>

</body>
</html>