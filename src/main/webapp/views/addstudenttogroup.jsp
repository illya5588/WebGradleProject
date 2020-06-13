<%--
  Created by IntelliJ IDEA.
  User: illyashulman
  Date: 18/05/2020
  Time: 15:22
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
    <title>Add Student To Group</title>
    <a href="${pageContext.request.contextPath}/Logout">Log Out</a>
</head>
<body>
<div class="container">
    <h3>Add Mark</h3>
    <form action="AddStudentToGroup" method="post">
        <div class="form-group">
            <select name="studentSelect">

                <c:forEach items="${students}" var="student" >
                    <option value="${student.student_ID}">${student}</option>

                </c:forEach>
            </select>
        </div>
        <div class="form-group">

            <button type="submit" class="btn btn-success">add Student</button>
        </div>
        <input type="hidden" name="groupId" value="${groupID}" hidden>
        </form>
        </div>

</body>
</html>
