<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="appRoot" value="${appRoot.request.contextPath}"/>
<html>
<head>
    <title>Pets</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
</head>
<body>
<table>
    <tr>
        <th>Name</th>
        <th>Age</th>
        <th>Favorite Toy</th>
        <th>Weight</th>
        <th>Nickname</th>
    </tr>
    <c:forEach var="pet" items="${pets}">
        <tr>
            <td>${pet.name}</td>
            <td>${pet.age}</td>
            <td>${pet.detail.favoriteToy}</td>
            <td>${pet.detail.weight}</td>
            <td>${pet.detail.nickname}</td>
        </tr>
    </c:forEach>
</table>
<a href="${appRoot}/index.jsp">Home</a>
</body>
</html>