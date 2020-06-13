<%--
  Created by IntelliJ IDEA.
  User: illyashulman
  Date: 15/05/2020
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <h6>${sessionScope.surname} ${sessionScope.name}</h6>
    <title>Confirm Delete</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <a href="${pageContext.request.contextPath}/Logout">Log Out</a>
</head>
<body>

<div class="container">
    <h2>Do you want to delete this student?</h2><br>

    <a href="${pageContext.request.contextPath}/admin/deleteStudent?id=${id}" class="btn btn-danger btn-block" role="button">Delete</a>
    <a href="${pageContext.request.contextPath}/student" class="btn btn-primary btn-block" role="button">Cancel</a>

</div>
</body>
</html>
