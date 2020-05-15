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
    <title>Welcome</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  </head>
  <body bgcolor="#fffaf0">
  <div class="container">
    <h2>Welcome</h2>
    <div class="list-group">
      <a href="${pageContext.request.contextPath}/createGroup" class="list-group-item list-group-item-action">Create New Group</a>
      <a href="${pageContext.request.contextPath}/group" class="list-group-item list-group-item-action">View all groups</a>
      <a href="${pageContext.request.contextPath}/student" class="list-group-item list-group-item-action">View all students</a>
      <a href="${pageContext.request.contextPath}/newStudent" class="list-group-item list-group-item-action">Create new Student</a>
    </div>
  </div>



  </body>
</html>
