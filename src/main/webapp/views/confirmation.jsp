
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
    <title>Confirmation</title>
    <a href="${pageContext.request.contextPath}/Logout">Log Out</a>
</head>
<body>
<div class="container">

    <div class="row">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Surname</th>
                <th>Name</th>
                <th>Date of Birth</th>

            </tr>
            </thead>
            <tbody>

            <c:forEach items="${allusers}" var="user">

                <tr>
                    <form name="ConfirmUsers" method="post">
                    <td><input type="text" hidden name="surname" value="${user.surname}">${user.surname}</td>
                    <td><input type="text" hidden name="name" value="${user.name}">${user.name}</td>
                    <td><input type="text" hidden name="date" value="${user.DOB}">${user.DOB}</td>
                        <input type="text" hidden name="id" value="${user.ID}">

                    <td><select name="role">
                        <option value="student">student</option>
                        <option value="teacher">teacher</option>
                    </select></td>
                    <td><input type="submit" value="Confirm"></td>
                    </form>



                    <td valign="top">
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
