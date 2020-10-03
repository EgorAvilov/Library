<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 02.10.2020
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <script src="<c:url value="/resource/js/add-book.js"/>"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

    <title>Adding book</title>
</head>
<body style="background: url('resource/img/background.jpg') no-repeat center center fixed">
<header class="sticky-top">
    <u:nav/>
</header>
<main role="main">
    <hr>
    <div class="container bootstrap snippet" style="min-height: 900px;">
        <form class="form" action="controller" method="post" id="registrationForm" enctype="multipart/form-data">
        <div class="row">
            <div class="col-sm-10"><h1 class="text-white">New book</h1></div>
        </div>
        <div class="row">
            <div class="col-sm-3">
                <div class="text-center">
                    <img src="<c:url value="/resource/img/default-book-cover.jpg"/>" class="avatar img-circle img-thumbnail" alt="book">
                    <h6 class="text-white">Upload a training photo</h6>
                    <input type="file" name="cover" class="text-center center-block file-upload">
                </div></hr><br>
            </div>
            <div class="col-sm-9">
                <div class="tab-content">
                    <div class="tab-pane active" id="home">
                        <hr>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label class="text-white" for="title"><h4>Title</h4></label>
                                    <input type="text" class="form-control" name="title" id="title" placeholder="Title">
                                </div>
                            </div>
                            <div class="form-group">

                                <div class="col-xs-6">
                                    <label class="text-white" for="authors"><h4>Authors</h4></label>
                                    <input type="text" class="form-control" name="authors" id="authors" placeholder="Authors">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label class="text-white" for="publisher"><h4>Publisher</h4></label>
                                    <input type="text" class="form-control" name="publisher" id="publisher" placeholder="Publisher">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label class="text-white" for="publishDate"><h4>Publish date</h4></label>
                                    <input type="date" class="form-control" name="publishDate" id="publishDate">
                                </div>
                            </div>
                            <div class="form-group">

                                <div class="col-xs-6">
                                    <label class="text-white" for="genres"><h4>Genres</h4></label>
                                    <input type="text" class="form-control" name="genres" id="genres" placeholder="Genres">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label class="text-white" for="pageCount"><h4>Page count</h4></label>
                                    <input type="number" min="1" class="form-control" name="pageCount" id="pageCount" placeholder="Page count">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label class="text-white" for="ISBN"><h4>ISBN</h4></label>
                                    <input type="text" class="form-control" name="ISBN" id="ISBN" placeholder="ISBN">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label class="text-white" for="totalAmount"><h4>Total amount</h4></label>
                                    <input type="number" min="1" class="form-control" name="totalAmount" id="totalAmount" placeholder="Total amount">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="form-group">
                                    <label class="text-white" for="description">Description</label>
                                    <textarea class="form-control" name="description" id="description" rows="3" placeholder="Description"></textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <br>
                                    <button class="btn btn-lg btn-success" type="submit" name="command" value="add-book">Save</button>
                                    <a class="btn btn-lg btn-danger" href="${pageContext.request.contextPath}/controller?command=view-all-books">Cancel</a>
                                </div>
                            </div>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
        </form>
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
