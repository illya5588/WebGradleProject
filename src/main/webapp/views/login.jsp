
<%@ page contentType="text/html;charset=UTF-8" language="java" %>




<html>
<head>
    <h6>${sessionScope.surname} ${sessionScope.name}</h6>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Login Page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <a href="${pageContext.request.contextPath}/Logout">Log Out</a>
</head>
<body>
<div class="Container">
<form class="text-center border border-light p-5" action="Login" method="post">

    <p class="h4 mb-4">Sign in</p>


    <input type="text" name="login" class="form-control mb-4" placeholder="Login">
    <input type="password" name="password"  class="form-control mb-4" placeholder="Password">
    <button type="submit" class="btn btn-info btn-block my-4">Sign in</button>






</form>
<p>Not a member?
    <a href="${pageContext.request.contextPath}/Register">Register</a>
</p>
</div>
</body>
</html>
