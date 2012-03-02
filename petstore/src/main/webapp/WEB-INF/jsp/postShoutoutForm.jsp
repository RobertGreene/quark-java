<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Shoutout</title>
</head>
<body>
	<h2>Shoutout</h2>
		<a href="/home.html">>>Back</a>
		
		<form method="POST" action="./shoutout"/>
			<table>
				<tr>
					<td><b>Shoutout:</b></td>
					<td><input type="text" name="shoutout"/></td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="text" name="api" value="${appDetails.apiKey }"/>
					<input type="hidden" name="api_key"
						value="${appDetails.apiKey }" /> 
					<input type="hidden"
						name="api_secret" value="${appDetails.apiSecret }" /> 
					<input type="hidden" 
					name="session_key" value="${appDetails.sessionKey }" />
					<input type="submit" value="Post Shoutout" /></td>
				</tr>
			</table>
		</form>

</body>
</html>