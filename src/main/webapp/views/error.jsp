<%--
  Created by IntelliJ IDEA.
  User: illyashulman
  Date: 04/05/2020
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <h6>${sessionScope.surname} ${sessionScope.name}</h6>
    <title>Title</title>
    <a href="${pageContext.request.contextPath}/Logout">Log Out</a>
</head>
<body>

${error}

</body>
</html>
