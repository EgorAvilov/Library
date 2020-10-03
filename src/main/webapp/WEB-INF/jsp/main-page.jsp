<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 18.09.2020
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value="/resource/css/main-page.css"/>"/>
    <title>Title</title>
</head>
<body style="background: url('resource/img/background.jpg') no-repeat center center fixed">
<header class="sticky-top">
    <u:nav/>
</header>
<main role="main">
    <div class="album py-5">
        <div class="container" style="min-height: 800px;">
            <div class="row">
                <c:forEach items="${requestScope.books}" var="book">
                    <div class="col-md-4">
                        <div class="card mb-4 box-shadow bg-dark">
                            <c:choose>
                                <c:when test="${book.cover == null || book.cover eq ''}">
                                    <img style="height: 225px; width: 225px;" class="card-img-top" src="<c:url value="/resource/img/default-book-cover.jpg"/>" data-src="holder.js/100px225?theme=thumb&bg=55595c&fg=eceeef&text=Thumbnail" alt="Card image cap">
                                </c:when>
                                <c:otherwise>
                                    <img style="height: 225px; width: 225px;" class="card-img-top" src="data:image/*;base64, ${book.cover}" data-src="holder.js/100px225?theme=thumb&bg=55595c&fg=eceeef&text=Thumbnail" alt="Card image cap">
                                </c:otherwise>
                            </c:choose>
                            <div class="card-body">
                                <div>
                                    <h7 class="card-text text-white">Title: ${book.title}</h7>
                                </div>
                                <div>
                                    <h7 class="card-text text-white">Authors: ${book.authors}</h7>
                                </div>
                                <div>
                                    <h7 class="card-text text-white">Publishing date: ${book.publishDate}</h7>
                                </div>
                                <p></p>
                                    <div class="d-flex justify-content-between align-items-center">
                                        <form action="controller" method="get">
                                            <input type="hidden" name="bookId" value="${book.id}">
                                        <div class="btn-group">
                                            <button type="submit" name="command" value="view-book" class="btn btn-sm btn-outline-secondary">View</button>
                                            <button type="button" name="command" value="edit-book" class="btn btn-sm btn-outline-secondary">Edit</button>
                                        </div>
                                        </form>
                                    <small class="text-muted text-white">Available amount: ${book.availableAmount}</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
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
