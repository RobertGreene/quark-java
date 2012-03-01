<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Top Score Listing</title>
</head>
<body>
<pre>
<h1>Top Scores for Friendster</h1>

    <table>
    <tr>
    	<th>ID</th>
        <th>Avatar ID</th>
        <th>Score</th>
        <th>Posted At</th>
    </tr>
    <c:forEach items="${userInfo.scores}" var="score" varStatus="status">
        <tr>
            <td align="center">${status.count}</td>
            <td>${score.score}</td>
            <td>${score.avatarId}</td>
            <td>${score.postedAt }</td>
        </tr>
    </c:forEach>
</table>
<br/>
</pre>
</body>
</html>