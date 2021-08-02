<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<html>
<head>
    <title>Accident</title>
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th scope="col">First</th>
        <th scope="col">Last</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}" >
        <tr>
            <td><c:out value="${user.name}" /></td>
            <td><c:out value="${user.surname}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
