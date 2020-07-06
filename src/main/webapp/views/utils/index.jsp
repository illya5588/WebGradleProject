
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Welcome</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <c:choose>
      <c:when test="${sessionScope.role eq null}">
        <a href="${pageContext.request.contextPath}/Login">Log In</a>
      </c:when>
    </c:choose>

  </head>
  <body bgcolor="#fffaf0">
  <div class="container">
    <h2>Welcome back, ${sessionScope.name}</h2>
${sessionScope.role}
    <div class="list-group">
      <c:choose>
      <c:when test="${'admin' eq sessionScope.role}" >
        <a href="${pageContext.request.contextPath}/admin/createGroup" class="list-group-item list-group-item-action">Create New Group</a>
        <a href="${pageContext.request.contextPath}/admin/newStudent" class="list-group-item list-group-item-action">Create new Student</a>
        <c:choose>
          <c:when test="${confirmed!=0}">
            <a href="${pageContext.request.contextPath}/admin/ConfirmUsers" class="list-group-item list-group-item-action">Users to confirm</a>
          </c:when>
        </c:choose>

      </c:when>
      </c:choose>
      <a href="${pageContext.request.contextPath}/group" class="list-group-item list-group-item-action">View all groups</a>
      <a href="${pageContext.request.contextPath}/student" class="list-group-item list-group-item-action">View all students</a>

      <a href="${pageContext.request.contextPath}/Register" class="list-group-item list-group-item-action">Register</a>
    </div>
  </div>



  </body>
</html>
