<html>

<head>
</head>

<body>

	<h1>Friendster Test Application</h1>
	<hr>
	
	<div align="right">
	<pre>
		<table width="75%">
			<tr>
				<td><b>Session Key:</b></td>
				<td>${appDetails.sessionKey }</td>
			</tr>
			<tr>
				<td><b>API Key</b></td>
				<td>${appDetails.apiKey }</td>
			</tr>
			<tr>
				<td><b>API Secret</b></td>
				<td>${appDetails.apiSecret }</td>
			</tr>
			<tr>
				<td><b>Session Expiry</b></td>
				<td>${appDetails.expiryDate}</td>
			</tr>
		</table>
	</pre>
	</div>

	<form method="GET" action="./shoutout">
		<input type="hidden" name="api_key" value="${appDetails.apiKey}" />
		<input type="hidden" name="api_secret" value="${appDetails.apiSecret}" />
		<input type="hidden" name="session_key" value="${appDetails.sessionKey}" />
		<input type="submit" value="Post Shoutout">
	</form>
	<a href="postShoutoutForm?api_key=">Post Shoutout</a>
	<br>
	<a href="getMessages.html">Get Messages</a>
	<br>
	<a href="getUserInfoForm.html">Get User Information</a>
	<br>
	<a href="hello.html">Get Friends</a>
	<br>
	<a href="score.html">Get Topscores</a>
	<br>
	<a href="hello.html">Post Topscore</a>
	<br>
	<a href="hello.html">Get Message</a>
	<br>
	<a href="hello.html">Post Message</a>
	<br>
	<a href="hello.html">Post Notification</a>
	<br>
	<a href="hello.html">Get Application Friends</a>
	<br>
	<a href="hello.html">Get Shoutout</a>
	<br>
	<a href="wallet.html">Get Wallet Balance</a>
	<br>
	<a href="hello.html">Get Payment Reqest</a>
	<br>
	<a href="hello.html">Commit Payment Reqest</a>
	<br>

</body>


</html>