<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<html>
<head>
    <title>Accident</title>
</head>
<body>
<div class="container">
    <div>
        Login as : ${user.username}
    </div>
    <a class="btn btn-primary" href="<c:url value='/create'/>" role="button">Добавить инцидент</a>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Type</th>
            <th scope="col">Rule</th>
            <th scope="col">Edit</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="accident" items="${accidents}">
            <tr>
                <td><c:out value="${accident.name}"/></td>
                <td><c:out value="${accident.type.name}"/></td>
                <th>
                    <c:forEach items="${accident.rules}" var="rule">
                        <c:out value="${rule.name}"/>
                    </c:forEach>
                </th>
                <td><a href="<c:url value='/edit?id=${accident.id}'/>">Редактировать инцидент</a></td>
                <td><a href="<c:url value='/delete?id=${accident.id}'/>">Удалить</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
