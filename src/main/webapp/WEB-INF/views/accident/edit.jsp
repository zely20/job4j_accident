<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form  action="<c:url value='/save'/>" method='POST'>
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
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>
