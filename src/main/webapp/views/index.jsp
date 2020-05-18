<%--
  Created by IntelliJ IDEA.
  User: illyashulman
  Date: 04/05/2020
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <title>Groups</title>
</head>
<body>
<div class="container">
    <h2>Groups</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Name</th>
            <th>Date of Creation</th>
            <th>Number of students</th>
            <th>Group's students</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${groups}" var="group">
        <tr>
            <td>${group.name}</td>
            <td>${group.dateOfCreation}</td>
            <td>${group.groupList.size()}</td>
            <c:choose>
                <c:when test="${group.groupList.size()!=0}">
                    <td><a href="${pageContext.request.contextPath}/groupstudents?id=${group.id}">View Students</a><br></td>
                </c:when>
                <c:otherwise>
                    <td><a href="${pageContext.request.contextPath}/AddStudentToGroup?id=${group.id}">Add Student</a><br></td>
                </c:otherwise>

            </c:choose>
        </tr>


            </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/createGroup" class="btn btn-success btn-block" role="button">Create Group</a>
    <a href="${pageContext.request.contextPath}/" class="btn btn-primary btn-sm btn-block" role="button">Main Menu</a>

</div>






</body>
</html>
