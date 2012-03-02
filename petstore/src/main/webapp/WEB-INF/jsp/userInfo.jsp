<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Information</title>
</head>
<body>

<!-- Common Header -->
<div id="header" class="header">
	<a href="./?session_key=${sessionDetails.sessionKey }">Return to Home Page</a>
	<h1>Post Shoutout</h1>
	<hr>
</div>

<!-- User Information -->
<div id="userInfo" class="statusDisplay">
<h1>User Information</h1>

<div align="center">
	<table>
		<tr>
		<th>User ID</th>
		<td>${userInfo.uid }</td>
		</tr>
		<tr>
		<th>First Name</th>
		<td>${userInfo.firstName }</td>
		</tr>
		<tr>
		<th>Last Name</th>
		<td>${userInfo.lastName }</td>
		</tr>
		<tr>
		<th>URL</th>
		<td>${userInfo.url }</td>
		</tr>
		<tr>
		<th>Primary Photo URL</th>
		<td>${userInfo.primaryPhotoUrl }</td>
		</tr>
		<tr>
		<th>Level</th>
		<td>${userInfo.level }</td>
		</tr>
		<tr>
		<th>Hometown</th>
		<td>${userInfo.hometown }</td>
		</tr>
		<tr>
		<th>User Type</th>
		<td>${userInfo.userType }</td>
		</tr>
		<tr>
		<th>Fan Profile Type</th>
		<td>${userInfo.fanProfileType }</td>
		</tr>
		<tr>
		<th>Fan Profile Category</th>
		<td>${userInfo.fanProfileCategory }</td>
		</tr>
		<tr>
		<th>Relationship Status</th>
		<td>${userInfo.relationshipStatus }</td>
		</tr>
		<tr>
		<th>Gender</th>
		<td>${userInfo.gender }</td>
		</tr>
		<tr>
		<th>Occupation</th>
		<td>${userInfo.occupation }</td>
		</tr>
		<tr>
		<th>Companies</th>
		<td>${userInfo.companies }</td>
		</tr>
		<tr>
		<th>Hobbies and Interests</th>
		<td>${userInfo.hobbiesAndInterests }</td>
		</tr>
		<tr>
		<th>Affiliations</th>
		<td>${userInfo.affiliations }</td>
		</tr>
		<tr>
		<th>School Other</th>
		<td>${userInfo.schoolOther }</td>
		</tr>
		<tr>
		<th>Favorites</th>
		<td>${userInfo.favorites }</td>
		</tr>
		<tr>
		<th>About Me</th>
		<td>${userInfo.aboutMe }</td>
		</tr>
		<tr>
		<th>Want To Meet</th>
		<td>${userInfo.wantToMeet }</td>
		</tr>
		<tr>
		<th>Birthday</th>
		<td>${userInfo.birthday }</td>
		</tr>
		<tr>
		<th>FB ID</th>
		<td>${userInfo.fbId }</td>
		</tr>
		<tr>
		<th>Admin</th>
		<td>${userInfo.admin }</td>
		</tr>
	</table>
</div>
</div>

</body>
</html>