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
    <title>Student</title>
    <a href="${pageContext.request.contextPath}/Logout">Log Out</a>
</head>
<body bgcolor="white">

<div class="container">
    <h2>Students</h2>
    <div class="topNav">

        <form class="form-inline" action="SearchStudents" method="post">

            <input type="text" class="form-control" name="search" placeholder="please type..."><br>

            <button type="submit" class="btn btn-outline-success form-control btn-sm">Search</button>

        </form>

    </div>


    <form action="" method="post" class="row">

            <div class="btn-group" role="group" aria-label="Basic example">
                <div class="form-check">
                    <input type="checkbox"
                           class="form-check-input" ${pageable.success eq "unclassified" ? 'checked' : ''}
                           id="unclassified" value="unclassified" name="success">
                    <label class="form-check-label" for="unclassified">Unclassified</label>
                </div>
            </div>
            <div class="btn-group" role="group" aria-label="Basic example">
                <select name="criteria">
                    <option id="1"
                            value="By surname"  ${pageable.criteria eq "By surname" ? 'selected="selected"' : ''}>By
                        student
                    </option>
                    <option id="2" value="By group" ${pageable.criteria eq "By group" ? 'selected="selected"' : ''}>By
                        group
                    </option>
                    <option id="3" value="Default" ${pageable.criteria eq "Default" ? 'selected="selected"' : ''}>
                        Default
                    </option>
                </select>
            </div>
            <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="radio" name="order" class="btn btn-primary ${pageable.order eq "ASC" ? 'active' : ''}"
                            value="ASC">ASC
                    </button>
                    <button type="radio" name="order"
                            class="btn btn-secondary ${pageable.order eq "DESC" ? 'active' : ''}"
                            value="DESC">DESC
                    </button>
                </div>


    </form>


</div>
<div class="container">

    <div class="row">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Surname</th>
                <th>Name</th>
                <th>Date of Birth</th>
                <th>Group</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${allstudents}" var="student">

                <tr>
                    <td>${student.surname}</td>
                    <td>${student.name}</td>
                    <td>${student.DOB}</td>
                    <td>${student.group.name}</td>
                    <c:choose>
                        <c:when test="${'admin' eq sessionScope.role}">
                            <td><a href="${pageContext.request.contextPath}/admin/newStudent?id=${student.student_ID}"
                                   class="btn btn-outline-primary" role="button">Edit</a><br></td>

                            <form action="GetMarks" method="post">
                                <input type="hidden" name="id" value="${student.student_ID}" hidden>
                                <td>
                                    <button type="submit" class="btn btn-outline-success">Marks</button>
                                </td>
                                <br>
                            </form>
                            <form action="ConfirmStudentDelete" method="post">
                                <input type="hidden" name="id" value="${student.student_ID}" hidden>
                                <td>
                                    <button type="submit" class="btn btn-outline-danger">Delete</button>
                                </td>
                                <br>
                            </form>

                            <td valign="top">
                            </td>
                        </c:when>
                        <c:when test="${'teacher' eq sessionScope.role}">
                            <td><a href="${pageContext.request.contextPath}/admin/newStudent?id=${student.student_ID}"
                                   class="btn btn-outline-primary" role="button">Edit</a><br></td>

                            <form action="GetMarks" method="post">
                                <input type="hidden" name="id" value="${student.student_ID}" hidden>
                                <td>
                                    <button type="submit" class="btn btn-outline-success">Marks</button>
                                </td>
                                <br>
                            </form>
                            <form action="ConfirmStudentDelete" method="post">
                                <input type="hidden" name="id" value="${student.student_ID}" hidden>
                                <td>
                                    <button type="submit" class="btn btn-outline-danger">Delete</button>
                                </td>
                                <br>
                            </form>

                            <td valign="top">
                            </td>
                        </c:when>
                    </c:choose>

                </tr>
            </c:forEach>
            </tbody>

        </table>

        <nav aria-label="Page navigation example">
            <ul class="pagination">

                <a href="${pageContext.request.contextPath}/student?page=${currentpage-1}&criteria=${pageable.criteria}&order=${pageable.order}&success=${pageable.success}"
                   class="btn btn-outline-primary ${currentpage == 1  ? 'disabled' : ''}" role="button" disabled>Previous</a>
                <c:forEach var="page" begin="1" end="${pageable.pages}">
                    <a href="${pageContext.request.contextPath}/student?page=${page}&criteria=${pageable.criteria}&order=${pageable.order}&success=${pageable.success}"
                       class="btn btn-outline-primary ${page == currentpage  ? 'active' : ''}" role="button">${page}</a>

                </c:forEach>

                <a href="${pageContext.request.contextPath}/student?page=${currentpage+1}&criteria=${pageable.criteria}&order=${pageable.order}&success=${pageable.success}"
                   class="btn btn-outline-primary ${currentpage == pageable.pages  ? 'disabled' : ''}" role="button">Next</a>
            </ul>

        </nav>
    </div>


    <div class="bottomNav">
        <c:choose>
            <c:when test="${'admin' eq sessionScope.role}">
                <a href="${pageContext.request.contextPath}/admin/newStudent" class="btn btn-success btn-sm"
                   role="button">Add Student</a>
                <a href="${pageContext.request.contextPath}/admin/AddMark" class="btn btn-outline-primary btn-sm"
                   role="button">Add Mark</a><br>
            </c:when>
        </c:choose>

        <a href="${pageContext.request.contextPath}/" class="btn btn-primary btn-sm" role="button">Main Menu</a>
        <div class="btn-group">

        </div>
    </div>

</div>


${pageable.success}


</body>
</html>
