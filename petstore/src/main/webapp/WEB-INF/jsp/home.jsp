<html>

<head>
</head>

<body>

	<h1>Friendster Test Application</h1>
	<hr>

	<div id="sessionDetails" class="sessionDetails">
		<table width="75%">
			<tr>
				<td><b>Session Key:</b></td>
				<td>${sessionDetails.sessionKey }</td>
			</tr>
		</table>

	</div>

	<!-- Shoutouts -->
	<div id="shoutout" class="formInput">
	<h3>Post Shoutout</h3>
	<form method="POST" action="./shoutout">
		<input type="hidden" name="session_key" value="${sessionDetails.sessionKey}" /> 

		<!-- Shoutout Text Field -->
		<table>
			<tr>
				<td><b>Shoutout:</b></td>
				<td><textarea rows="3" cols="50" name="shoutout_text"></textarea></td>
			<tr>
			<tr>
				<td/>
				<td><input type="submit" value="Post Shoutout"></td>
			</tr>
		</table>
	</form>
	</div>
	
	<!-- User Information -->
	<div id="user_info" class="formInput">
	<h3>User Information</h3>
	<form method="POST" action="./user">
		<input type="hidden" name="session_key" value="${sessionDetails.sessionKey}" /> 

		<table>
			<tr>
				<td><b>User ID:</b></td>
				<td><input type="text" name="user_id"/></td>
			<tr>
			<tr>
				<td/>
				<td><input type="submit" value="Get User Information"></td>
			</tr>
		</table>
	</form>
	</div>
	
	<!-- Wallet Balance -->
	<div id="user_info" class="formInput">
	<h3>Wallet Balance</h3>
	<form method="POST" action="./wallet">
		<input type="hidden" name="session_key" value="${sessionDetails.sessionKey}" /> 

		<table>
			<tr>
				<td><b>User ID:</b></td>
				<td><input type="text" name="user_id"/></td>
			<tr>
			<tr>
				<td/>
				<td><input type="submit" value="Get Wallet Balance"></td>
			</tr>
		</table>
	</form>
	</div>
	
</body>


</html>