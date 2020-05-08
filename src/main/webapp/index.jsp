<%--
  Created by IntelliJ IDEA.
  User: illyashulman
  Date: 04/05/2020
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Main</title>
  </head>
  <body>

<h3>Please choose one option</h3>
<a href="${pageContext.request.contextPath}/createGroup">Create New Group</a>
<a href="${pageContext.request.contextPath}/group">View all groups</a>
<a href="${pageContext.request.contextPath}/student">View all students</a>

  </body>
</html>
