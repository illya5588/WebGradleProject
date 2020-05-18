<%--
  Created by IntelliJ IDEA.
  User: illyashulman
  Date: 17/05/2020
  Time: 20:25
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
    <title>Add Mark</title>
</head>
<body>
<div class="container">
    <h3>Add Mark</h3>
    <form action="AddMark" method="post">
        <div class="form-group">
            <select name="studentSelect">

                <c:forEach items="${students}" var="student" >
                    <option value="${student.student_ID}">${student}</option>

                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <select name="subjectSelect">

                <c:forEach items="${subjects}" var="subject" >
                    <option value="${subject.ID}">${subject}</option>

                </c:forEach>
            </select>
        </div>

        <div class="form-group">
            <input type="text" name="mark" placeholder="digit mark"><br>
        </div>
        <div class="form-group">
            <input type="hidden" name="id" value="${id}" hidden>
            <button type="submit" class="btn btn-success">add Mark</button>
        </div>
    </form>
</div>
</body>
</html>
