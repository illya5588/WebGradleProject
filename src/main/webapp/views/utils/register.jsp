
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
    <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
    <script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
    <title>Welcome</title>
    <a href="${pageContext.request.contextPath}/Logout">Log Out</a>
</head>
<body>
<h3>Register</h3>
<div class="container">

    <form action="Register" method="post">
        <input type="text" class="form-control" name="surname" placeholder="surname">
        <input type="text" class="form-control" name="name" placeholder="name">
        <input type="text" class="form-control" name="date" placeholder="yyyy-mm-dd">
        <input type="text" class="form-control" name="login" placeholder="login (you can use name+surname)">
        <input type="password" class="form-control" name="password" placeholder="password">
        <select name="role">
            <option value="student">student</option>
            <option value="teacher">teacher</option>
        </select>
        <input type="submit" value="register">
    </form>
</div>
${message}
</body>
</html>
