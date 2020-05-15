<%--
  Created by IntelliJ IDEA.
  User: illyashulman
  Date: 08/05/2020
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Student</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h3>Add new Student or edit existing</h3>

    <form action="newStudent" method="post">
        <div class="form-group">
            <label>surname</label>
            <input type="text" class="form-control" name="surname" placeholder="surname" value="${student.surname}">
        </div>
        <div class="form-group">
            <label>name</label>
            <input type="text" class="form-control"  name="name" placeholder="name" value="${student.name}">
        </div>
        <div class="form-group">
            <label>date of birth</label>
            <input type="text" class="form-control"  name="date" placeholder="yyyy-mm-dd" value="${student.DOB.toString()}">
        </div>
        <div class="form-group">
            <label>group</label>
            <select name="groupsSelect">

                <c:forEach items="${groups}" var="item" varStatus="loop">
                    <option value="${item.id}" ${item.id == group.id ? 'selected="selected"' : ''}>${item.name}</option>

                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">add Student</button>
        <a href="${pageContext.request.contextPath}/" class="btn btn-outline-primary" role="button">Main Menu</a>

        <input type="hidden" name="id" value="${student.student_ID}">
    </form>
</div>

</body>
</html>
