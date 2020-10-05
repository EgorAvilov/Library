<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 03.10.2020
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/resource/css/no-access.css"/>"/>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <title>No access</title>
</head>
<body style="background: url('resource/img/background.jpg') no-repeat center center fixed">
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1 class="text-white">
                    Oops!</h1>
                <h2 class="text-white">
                    You don't have an access</h2>
                <c:choose>
                    <c:when test="${sessionScope.user.role.roleId == 2 || sessionScope.user.role.roleId == 1}">
                        <div class="error-details text-white">
                            Please, go to the main page!
                        </div>
                        <div class="error-actions">
                            <a href="${pageContext.request.contextPath}/controller?command=view-all-books" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
                                Main page </a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="error-details text-white">
                            Please, go to the sign in page!
                        </div>
                        <div class="error-actions">
                            <a href="${pageContext.request.contextPath}/sign-in" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
                                Sign in </a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
</body>
</html>
