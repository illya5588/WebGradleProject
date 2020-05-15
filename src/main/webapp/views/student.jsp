
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
    <title>Student</title>
</head>
<body bgcolor="white">

<div class="container">
    <h2>Students</h2>
    <div class="container">
    <form action="SearchStudents" method="post">

        <input type="text" class="form-control" name="search" placeholder="please type..."><br>
        <button type="submit" class="btn btn-outline-success">Search</button>

</form>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Surname</th>
            <th>Name</th>
            <th>Date of Birth</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${allstudents}" var="student">

            <tr>
                <td>${student.surname}</td>
                <td>${student.name}</td>
                <td>${student.DOB}</td>
                <td><a href="${pageContext.request.contextPath}/newStudent?id=${student.student_ID}">Edit</a><br></td>
                <form action="ConfirmStudentDelete" method="post">
                    <input type="hidden" name="id" value="${student.student_ID}" hidden>
                    <td><button type="submit" class="btn btn-outline-danger">Delete</button></td><br>
                </form>

                <td valign="top">
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/" class="btn btn-danger btn-sm btn-block" role="button">Unclassified Students</a>
    <a href="${pageContext.request.contextPath}/newStudent" class="btn btn-success btn-block" role="button">Add Student</a>
    <a href="${pageContext.request.contextPath}/" class="btn btn-primary btn-sm btn-block" role="button">Main Menu</a>
</div>






</body>
</html>
