<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
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
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">    <link href="<c:url value="/resource/css/book-page.css"/>" rel="stylesheet" type="text/css"/>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="<c:url value="/resource/css/calendar.css"/>">
    <script src="<c:url value="/resource/js/calendar.js"/>"></script>

    <title>${requestScope.book.title}</title>
</head>
<body style="background: url('resource/img/background.jpg') no-repeat center center fixed">
<header class="sticky-top">
    <u:nav/>
</header>
<div style="min-height: 800px;" class="container emp-profile bg-dark">
        <div class="row">
            <div class="col-md-4">
                <div class="profile-img">
                    <c:choose>
                        <c:when test="${requestScope.book.cover == null || requestScope.book.cover eq ''}">
                            <img style="height: 225px; width: 225px;" class="card-img-top" src="<c:url value="/resource/img/default-book-cover.jpg"/>" data-src="holder.js/100px225?theme=thumb&bg=55595c&fg=eceeef&text=Thumbnail" alt="Card image cap">
                        </c:when>
                        <c:otherwise>
                            <img src="data:image/*;base64, ${requestScope.book.cover}" alt=""/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="col-md-6">
                <div class="profile-head">
                    <h5 class="text-white">
                        ${requestScope.book.title}
                    </h5>
                    <h6 class="text-white">
                        ${requestScope.book.authors}
                    </h6>
                    <p class="proile-rating text-white">Publisher: <span class="text-white">${requestScope.book.publisher}</span></p>
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Additional</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                    <div class="profile-work">
                        <br>
                            <c:choose>
                                <c:when test="${sessionScope.user.role.roleId == 2}">
                                    <button onclick="document.getElementById('calendar').style.display='block'" class="btn btn-success">Borrow book</button>
                                    <div id="calendar" class="modal">

                                        <form action="controller" class="modal-content animate bg-dark" method="post">
                                            <input type="hidden" name="bookId" value="${requestScope.book.id}">
                                            <div class="imgcontainer bg-dark">
                                                <h4 class="text-white">Due date</h4>
                                            </div>

                                            <div class="container bg-dark">
                                                <input type="date" name="dueDate">
                                            </div>

                                            <div class="container bg-dark">
                                                <button type="submit" class="btn btn-success" name="command" value="add-borrow-record">Submit</button>
                                                <button type="button" onclick="document.getElementById('calendar').style.display='none'" class="btn btn-danger cancelbtn">Cancel</button>
                                            </div>
                                        </form>
                                    </div>
                                    </c:when>
                                <c:otherwise>
                                        <form action="controller" method="post">
                                            <input type="hidden" name="bookId" value="${requestScope.book.id}">
                                            <button type="submit" name="command" value="change-book-deleted-status" class="btn btn-danger">Delete</button>
                                        </form>
                                        <h5></h5>
                                        <form action="controller" method="get">
                                            <input type="hidden" name="bookId" value="${requestScope.book.id}">
                                            <button type="submit" name="command" value="forward-edit-book" class="btn btn-warning">Edit</button>
                                        </form>
                                </c:otherwise>
                            </c:choose>
                        </form>
                    </div>
            </div>
            <div class="col-md-8">
                <div class="tab-content profile-tab" id="myTabContent">
                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <div class="row">
                            <div class="col-md-6">
                                <label class="text-white">Publish date</label>
                            </div>
                            <div class="col-md-6">
                                <p class="text-white">${requestScope.book.publishDate}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label class="text-white">Genres</label>
                            </div>
                            <div class="col-md-6">
                                <p class="text-white">${requestScope.book.genres}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label class="text-white">Amount of pages</label>
                            </div>
                            <div class="col-md-6">
                                <p class="text-white">${requestScope.book.pageCount}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label class="text-white">Description</label>
                            </div>
                            <div class="col-md-6">
                                <p class="text-white">${requestScope.book.description}</p>
                            </div>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                        <div class="row">
                            <div class="col-md-6">
                                <label class="text-white">Status</label>
                            </div>
                            <div class="col-md-6">
                                <c:choose>
                                    <c:when test="${requestScope.book.status == false}">
                                        <p class="text-white">Unavailable</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="text-white">Available (${requestScope.book.availableAmount} out of ${requestScope.book.totalAmount})</p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label class="text-white">ISBN</label>
                            </div>
                            <div class="col-md-6">
                                <p class="text-white">${requestScope.book.ISBN}</p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label class="text-white">Total amount</label>
                            </div>
                            <div class="col-md-6">
                                <p class="text-white">${requestScope.book.totalAmount}</p>
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
