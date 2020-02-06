<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Attributes</title>
</head>
<body>
<p><%= request.getAttribute("myCity") %>
</p>

<%
    String[] zipCodes = (String[]) request.getAttribute("zips");
    for (int i = 0; i < zipCodes.length; i++) {
        String zipCode = zipCodes[i];
        out.write("<p>" + zipCode + "</p>");
    }
%>

<p>${myCity}</p>

<c:forEach var="zipCode" items="${zips}">
    <p>${zipCode}</p>
</c:forEach>

<c:if test="${zips.length > 1}">
    <p>This city has multiple zip codes</p>
</c:if>
</body>
</html>