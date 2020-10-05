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
    <link rel="stylesheet" href="<c:url value="/resource/css/calendar.css"/>">
    <script src="<c:url value="/resource/js/calendar.js"/>"></script>

    <title>Adding book</title>
</head>
<body style="background: url('resource/img/background.jpg') no-repeat center center fixed">
<header class="sticky-top">
    <u:nav/>
</header>
<main role="main">
    <hr>
    <div class="container bootstrap snippet" style="min-height: 900px;">
        <form class="form" action="controller" method="post" id="registrationForm">
            <input type="hidden" name="userId" value="${sessionScope.user.id}">
            <div class="row">
                <c:choose>
                    <c:when test="${sessionScope.user != null}">
                        <div class="col-sm-10"><h1 class="text-white">${sessionScope.user.firstName}</h1></div>
                    </c:when>
                </c:choose>
            </div>
            <div class="row">
                <div class="col-sm-3">
                    <div class="text-center">
                        <c:choose>
                            <c:when test="${sessionScope.user != null}">
                                <img src="<c:url value="/resource/img/default-book-cover.jpg"/>" class="avatar img-circle img-thumbnail" alt="book">
                            </c:when>
                        </c:choose>

                    </div></hr><br>
                </div>
                <div class="col-sm-9">
                    <div class="tab-content">
                        <div class="tab-pane active" id="home">
                            <hr>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label class="text-white" for="firstName"><h4>First name</h4></label>
                                    <c:choose>
                                        <c:when test="${sessionScope.user.firstName != null}">
                                            <input type="text" class="form-control" value="${sessionScope.user.firstName}" name="firstName" id="firstName" placeholder="First name" required>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" class="form-control" name="firstName" id="firstName" placeholder="First name" required>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group">

                                <div class="col-xs-6">
                                    <label class="text-white" for="lastName"><h4>Last name</h4></label>
                                    <c:choose>
                                        <c:when test="${sessionScope.user.lastName != null}">
                                            <input type="text" class="form-control" value="${sessionScope.user.lastName}" name="lastName" id="lastName" placeholder="Last name" required>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Last name" required>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label class="text-white" for="gender"><h4>Gender</h4></label>
                                    <c:choose>
                                        <c:when test="${sessionScope.user.gender != null}">
                                            <select class="custom-select" name="gender" id="gender">
                                                <option selected value="true">Male</option>
                                                <option value="false">Female</option>
                                            </select>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                    <label class="text-white" for="phoneNumber"><h4>Phone number</h4></label>
                                    <c:choose>
                                        <c:when test="${sessionScope.user.phoneNumber != null}">
                                            <input type="text" class="form-control" value="${sessionScope.user.phoneNumber}" name="phoneNumber" id="phoneNumber" placeholder="Phone number" required>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="Phone number" required>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group">

                                <div class="col-xs-6">
                                    <label class="text-white" for="password"><h4>Password</h4></label>
                                    <c:choose>
                                        <c:when test="${sessionScope.user.password!= null}">
                                            <input type="text" class="form-control" value="${sessionScope.user.password}" name="password" id="password" placeholder="Password" required>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" class="form-control" name="password" id="password" placeholder="Password" required>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                   <%-- <label class="text-white" for="pageCount"><h4>Page count</h4></label>--%>

                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                   <%-- <label class="text-white" for="ISBN"><h4>ISBN</h4></label>--%>
                                   <%-- <c:choose>
                                        <c:when test="${requestScope.book.ISBN != null}">
                                            <input type="text" class="form-control" value="${requestScope.book.ISBN}" name="ISBN" id="ISBN" placeholder="ISBN" required>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" class="form-control" name="ISBN" id="ISBN" placeholder="ISBN" required>
                                        </c:otherwise>
                                    </c:choose>--%>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-6">
                                   <%-- <label class="text-white" for="totalAmount"><h4>Total amount</h4></label>--%>
                                    <%--<c:choose>
                                        <c:when test="${requestScope.book.totalAmount != 0}">
                                            <input type="number" min="1" class="form-control" value="${requestScope.book.totalAmount}" name="totalAmount" id="totalAmount" placeholder="Total amount" required>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="number" min="1" class="form-control" name="totalAmount" id="totalAmount" placeholder="Total amount" required>
                                        </c:otherwise>
                                    </c:choose>--%>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="form-group">
                                    <%--<label class="text-white" for="description">Description</label>--%>
                                   <%-- <c:choose>
                                        <c:when test="${requestScope.book.description != null}">
                                            <textarea class="form-control" name="description" id="description" rows="3" placeholder="Description">
                                                    ${requestScope.book.description}
                                            </textarea>
                                        </c:when>
                                        <c:otherwise>
                                            <textarea class="form-control" name="description" id="description" rows="3" placeholder="Description"></textarea>
                                        </c:otherwise>
                                    </c:choose>--%>
                                </div>
                                <c:if test="${requestScope.message_ != null}">
                                    <p style="color: #FF0000;"><fmt:message key="${requestScope.message_add_book}"/></p>
                                </c:if>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <br>


                                            <button class="btn btn-lg btn-success" type="submit" name="command" value="edit-profile">Save</button>

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
