<%--
  Created by IntelliJ IDEA.
  User: illyashulman
  Date: 04/05/2020
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<html>
<head>
    <title>Index page</title>
</head>
<body>
${groups}<br>
<a href="${pageContext.request.contextPath}/createGroup">Create New Group</a>

</body>
</html>
