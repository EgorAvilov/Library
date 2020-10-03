<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 03.10.2020
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value="/resource/css/main-page.css"/>"/>

    <title>Users</title>
</head>
<body style="background: url('resource/img/background.jpg') no-repeat center center fixed">
<header class="sticky-top">
    <u:nav/>
</header>
<main role="main">
    <div class="album py-5">
        <div class="container" style="min-height: 800px;">
            <div class="row">
                <table class="table table-dark">
                    <thead>
                    <tr>
                        <th style="text-align: center; vertical-align: middle" successcope="col">Id</th>
                        <th style="text-align: center; vertical-align: middle" scope="col">Username</th>
                        <th style="text-align: center; vertical-align: middle" scope="col">E-mail</th>
                        <th style="text-align: center; vertical-align: middle" scope="col">Role</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.users}" var="user">
                    <tr>
                        <th style="text-align: center; vertical-align: middle" scope="row">${user.id}</th>
                        <td style="text-align: center; vertical-align: middle">${user.username}</td>
                        <td style="text-align: center; vertical-align: middle">${user.email}</td>
                        <c:choose>
                            <c:when test="${user.role.roleId == 1}">
                                <td style="text-align: center; vertical-align: middle">Admin</td>
                            </c:when>
                            <c:otherwise>
                                <td style="text-align: center; vertical-align: middle">User</td>
                            </c:otherwise>
                        </c:choose>
                        <form action="controller" method="post">
                            <input type="hidden" name="userId" value="${user.id}">
                        <td style="text-align: center; vertical-align: middle">
                           <c:choose>
                               <c:when test="${user.role.roleId == 2}">
                                   <button class="btn btn-success" type="submit" name="command" value="set-admin">Set admin</button>
                               </c:when>
                           </c:choose>
                        </td>
                            <td style="text-align: center; vertical-align: middle">
                                <c:if test="${sessionScope.user.id != user.id}">
                                <button class="btn btn-danger" type="submit" name="command" value="change-user-deleted-status">Delete</button>
                                </c:if>
                            </td>
                        </form>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>
<footer class="text-muted bg-dark sticky-bottom">
    <div class="container">
        <p class="float-right">
            <a href="#">Back to top</a>
        </p>
        <p>&copy;Egor Avilov</p>
        <p>e-mail</p>
    </div>
</footer>
</body>
</html>
