<%--
  Created by IntelliJ IDEA.
  User: illyashulman
  Date: 05/05/2020
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <title>Create Group</title>
</head>
<body>
<div class="container">
    <h2>Create new Group</h2>

    <form action="createGroup" method="post">
        <div class="form-group">
            <label>Please enter group name</label>
            <input type="text" class="form-control" name="name" placeholder="name">
        </div>
        <div class="form-group">
            <label>Please enter date of group start</label>
            <input type="date" class="form-control" name="date" placeholder="dd-mm-yyyy">
        </div>
        <button type="submit" class="btn btn-primary">Create group</button>
        <a href="${pageContext.request.contextPath}/" class="btn btn-outline-primary" role="button">Main Menu</a>
    </form>
</div>



</body>
</html>
