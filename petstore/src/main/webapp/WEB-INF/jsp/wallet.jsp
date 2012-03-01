<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wallet Balance</title>
</head>
<body bgcolor="gray">
<pre>
<div id="title">
<h1>Wallet Balance</h1>
</div>
<hr/>

<div id="content">
	Wallet Balance for User ID <b>${userInfo.userId}</b> :
</div>

<div id="balance">
	Friendster Coins: <b>${userInfo.coins}</b>
</div>
</pre>
</body>
</html>