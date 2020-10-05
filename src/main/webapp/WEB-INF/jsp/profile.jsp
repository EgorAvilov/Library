<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 28.09.2020
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link href="<c:url value="/resource/css/book-page.css"/>" rel="stylesheet" type="text/css"/>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


    <title>${requestScope.book.title}</title>
</head>
<body style="background: url('resource/img/background.jpg') no-repeat center center fixed">
<header class="sticky-top">
    <u:nav/>
</header>
<div style="min-height: 800px;" class="container emp-profile bg-dark">
    <div class="row">
        <div class="col-md-4">

        </div>
        <div class="col-md-6">
            <div class="profile-head">
                <h5 class="text-white">
                    ${sessionScope.user.firstName}
                </h5>
                <h6 class="text-white">
                    ${sessionScope.user.lastName}
                </h6>
                <p class="proile-rating text-white">Username: <span
                        class="text-white">${sessionScope.user.username}</span></p>
                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                           aria-controls="home" aria-selected="true">About</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <div class="profile-work">
                <br>
                <h5></h5>
                <form action="controller" method="get">
                    <input type="hidden" name="userId" value="${sessionScope.user.id}">
                    <button style="width: 100%;" type="submit" name="command" value="forward-edit-profile"
                            class="btn btn-info">Edit
                    </button>
                </form>
                </form>
            </div>
        </div>
        <div class="col-md-8">
            <div class="tab-content profile-tab" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    <div class="row">
                        <div class="col-md-6">
                            <label class="text-white">Email</label>
                        </div>
                        <div class="col-md-6">
                            <p class="text-white">${sessionScope.user.email}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label class="text-white">Gender</label>
                        </div>
                        <div class="col-md-6">
                            <c:choose>
                                <c:when test="${sessionScope.user.gender==false}">
                                    Male
                                </c:when>
                                <c:otherwise>
                                    Female
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label class="text-white">Date of registration</label>
                        </div>
                        <div class="col-md-6">
                            <p class="text-white">${sessionScope.user.dateOfRegistration}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label class="text-white">Phone nuber</label>
                        </div>
                        <div class="col-md-6">
                            <p class="text-white">${sessionScope.user.phoneNumber}</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <label class="text-white">Password</label>
                        </div>
                        <div class="col-md-6">
                            <p class="text-white">${sessionScope.user.password}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
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
