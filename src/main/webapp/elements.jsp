<%--
  Created by IntelliJ IDEA.
  User: Jake
  Date: 2/4/20
  Time: 6:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>JSP Elements: <%= new java.util.Date() %></title>
</head>
<body>
<jsp:include page="header.jsp" />

<%!
    public String createTableDetail(int num) {
        return "<td>" + num + "</td>";
    }
%>

<table>
    <%
        for (int row = 1; row < 5; row++) {
            out.print("<tr>");
            for (int col = 1; col < 5; col++) {
                out.print(createTableDetail(row * col));
            }
            out.print("</tr>");
        }
    %>
</table>

<%@include file="footer.jsp"%>
</body>
</html>