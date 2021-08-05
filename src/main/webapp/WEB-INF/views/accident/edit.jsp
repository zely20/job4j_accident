<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<html>
<body>
<div class="container">
    <form action="<c:url value='/update'/>" method='POST'>
        <table>
            <tr>
                <td>Название:</td>
                <td><input type='text' name='id' value="${accident.id}" readonly></td>
                <td>Название:</td>
                <td><input type='text' name='name' value="${accident.name}"></td>
            </tr>
            <tr>
                <td>Тип нарушения:</td>
                <td>
                    <select name="type.id">
                        <c:forEach var="type" items="${types}">
                                <option value=<c:out value="${type.id}"/>><c:out value="${type.name}"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Статья КоАП:</td>
                <td>
                    <select name="rIds" multiple>
                        <c:forEach var="rule" items="${rules}">
                                    <option value=<c:out value="${rule.id}"/>><c:out value="${rule.name}"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit" value="Сохранить"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
