<%--
  Created by IntelliJ IDEA.
  User: illyashulman
  Date: 08/05/2020
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student</title>
</head>
<body>
<form action="newStudent" method="post">
    <h4>Please enter details</h4><br>
    Please enter surname: <input type="text" name="surname" placeholder="surname"><br>
    Please enter name: <input type="text" name="name" placeholder="name"><br>
    Please enter date of birth: <input type="text" name="date" placeholder="dd-mm-yyyy">
    <input type="submit" name="add Student">

</form>

</body>
</html>
