<%--
  Created by IntelliJ IDEA.
  User: illyashulman
  Date: 15/05/2020
  Time: 20:59
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
    <title>Student</title>
</head>
<body bgcolor="white">

<div class="container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Surname</th>
            <th>Name</th>
            <th>Date of Birth</th>

        </tr>
        </thead>
        <tbody>

            <c:forEach items="${allsearched}" var="student">

                <tr>
                    <td>${student.surname}</td>
                    <td>${student.name}</td>
                    <td>${student.DOB}</td>
                    <td><a href="${pageContext.request.contextPath}/newStudent?id=${student.student_ID}">Edit</a><br></td>
                    <td><a href="${pageContext.request.contextPath}/deleteStudent?id=${student.student_ID}">Delete</a><br></td>




                    <td valign="top">



                    </td>
                </tr>
            </c:forEach>
        </form>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/newStudent" class="btn btn-success btn-block" role="button">Add Student</a>
    <a href="${pageContext.request.contextPath}/" class="btn btn-primary btn-sm btn-block" role="button">Main Menu</a>
</div>






</body>
</html>
