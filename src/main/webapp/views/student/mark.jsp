<%--
  Created by IntelliJ IDEA.
  User: illyashulman
  Date: 17/05/2020
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <h6>${sessionScope.surname} ${sessionScope.name}</h6>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <title>Marks</title>
    <a href="${pageContext.request.contextPath}/Logout">Log Out</a>
</head>

<body>
<div class="container">
    <h2>${student.surname} ${student.name} Marks</h2>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>Subject</th>
            <th>Mark</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${marks}" var="mark">
            <tr>
                <td>${mark.key}</td>
                <td>${mark.value}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


</body>
</html>

