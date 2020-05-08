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
    <title>Create Group</title>
</head>
<body>
<form action="createGroup" method="post">
    Please enter group name: <input type = "text" name="name" placeholder="name"> <br>
    Please enter date of creation: <input type= "date" name="date" placeholder="dd-mm-yyyy"> <br>
    <input type="submit" name="Create table">

</form>
</body>
</html>
