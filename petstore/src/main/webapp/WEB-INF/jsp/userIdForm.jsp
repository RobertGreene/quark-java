<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>User Information</title>
</head>
<body>
<h2>User Information</h2>
<form:form method="post" action="getUserInformation.html">
<a href="index.jsp">>>Back</a> 
    <table>
    <tr>
        <td><form:label path="userId">User Id:</form:label></td>
        <td><form:input path="userId" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Get User Information"/>
        </td>
    </tr>
</table>  
 
</form:form>
</body>
</html>