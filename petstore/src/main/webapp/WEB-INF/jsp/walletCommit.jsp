<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thank you for your payment</title>
</head>

<body bgcolor="gray">
<!-- Common Header -->
<div id="header" class="header">
	<a href="../?session_key=${sessionKey }">Return to Home Page</a>
	<h1>Thank you for your purchase!</h1>
	<hr>
</div>

<div id="header" class="header">
	<p>Thank you for your purchase. Here are the details of your purchase:</p>
	<hr>
</div>

<div id="walletPayment" class="statusDisplay">

	<table>
		<tr>
		<th>Authorized Amount</th>
		<td>${requestAmount }</td>
		</tr>
		<tr>
		<th>Debited Amount</th>
		<td>${debitAmount }</td>
		</tr>
		
		<tr>
		<th>Transaction ID</th>
		<td>${transId }</td>
		</tr>
		
		<tr>
		<th>Transaction Time</th>
		<td>${transTime }</td>
		</tr>
		
		<tr>
		<th>Balance After Transaction</th>
		<td>${balance }</td>
		</tr>
	</table>	
</div>

</body>
</html>