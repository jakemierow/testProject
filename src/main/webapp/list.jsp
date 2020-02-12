<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pets</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <base href="${pageContext.request.contextPath}/"/>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Age</th>
        <th>Species</th>
    </tr>
    <c:forEach var="pet" items="${pets}">
        <tr>
            <td>${pet.id}</td>
            <td>${pet.name}</td>
            <td>${pet.age}</td>
            <td>${pet.species}</td>
        </tr>
    </c:forEach>
</table>
<!-- see base tag in head -->
<a href="index.jsp">Home</a>
</body>
</html>