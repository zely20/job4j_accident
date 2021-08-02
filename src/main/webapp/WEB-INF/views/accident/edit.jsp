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
            <td>Текст:</td>
            <td><input type='text' name='text' value="${accident.text}"></td>
            <td>Адрес:</td>
            <td><input type='text' name='address' value="${accident.address}"></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>
