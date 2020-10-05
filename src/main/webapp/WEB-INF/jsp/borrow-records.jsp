<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04.10.2020
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<c:url value="/resource/css/main-page.css"/>"/>
    <script src="<c:url value="/resource/js/calendar.js"/>"></script>
    <link rel="stylesheet" href="<c:url value="/resource/css/calendar.css"/>"/>


    <title>Borrow records</title>
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
                    <c:choose>
                    <c:when test="${sessionScope.user.role.roleId == 2}">
                    <tr>
                        <th style="text-align: center; vertical-align: middle" successcope="col">№</th>
                        <th style="text-align: center; vertical-align: middle" scope="col">Book title</th>
                        <th style="text-align: center; vertical-align: middle" scope="col">Borrow date</th>
                        <th style="text-align: center; vertical-align: middle" scope="col">Due date</th>
                        <th style="text-align: center; vertical-align: middle" scope="col">Comment</th>
                        <th style="text-align: center; vertical-align: middle" scope="col">Status</th>
                        <th style="text-align: center; vertical-align: middle" scope="col">Return date</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.borrowRecords}" var="borrowRecord">
                        <tr>
                            <th style="text-align: center; vertical-align: middle" scope="row">Record
                                №${borrowRecord.id}</th>
                            <td style="text-align: center; vertical-align: middle">${borrowRecord.bookTitle}</td>
                            <td style="text-align: center; vertical-align: middle">${borrowRecord.borrowDate}</td>
                            <td style="text-align: center; vertical-align: middle">${borrowRecord.dueDate}</td>
                            <td style="text-align: center; vertical-align: middle">
                                <c:choose>
                                    <c:when test="${borrowRecord.comment != null}">
                                        ${borrowRecord.comment}
                                    </c:when>
                                    <c:otherwise>
                                        -
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td style="text-align: center; vertical-align: middle">
                                <c:choose>
                                    <c:when test="${borrowRecord.statusId == 1}">
                                        Returned
                                    </c:when>
                                    <c:when test="${borrowRecord.statusId == 2}">
                                        Returned and damaged
                                    </c:when><c:when test="${borrowRecord.statusId == 3}">
                                    Lost
                                </c:when>
                                    <c:otherwise>
                                        -
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <form action="controller" method="post">
                                <c:choose>
                                    <c:when test="${borrowRecord.returnDate == null}">
                                        <td style="text-align: center; vertical-align: middle">
                                            <input type="date" name="returnDate" style="width: 100%;" required> </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td style="text-align: center; vertical-align: middle">${borrowRecord.returnDate}</td>
                                    </c:otherwise>
                                </c:choose>
                                <td style="text-align: center; vertical-align: middle">
                                    <c:choose>
                                        <c:when test="${borrowRecord.returnDate == null}">
                                            <input type="hidden" name="borrowRecordId" value="${borrowRecord.id}">
                                            <input type="hidden" name="bookId" value="${borrowRecord.bookId}">
                                            <button type="submit" name="command" value="edit-borrow-record-by-user"
                                                    class="btn btn-info">Submit
                                            </button>
                                        </c:when>
                                    </c:choose>
                                </td>
                            </form>
                        </tr>
                    </c:forEach>
                    </tbody>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <th style="text-align: center; vertical-align: middle" successcope="col">№</th>
                            <th style="text-align: center; vertical-align: middle" scope="col">Username</th>
                            <th style="text-align: center; vertical-align: middle" scope="col">Book title</th>
                            <th style="text-align: center; vertical-align: middle" scope="col">Borrow date</th>
                            <th style="text-align: center; vertical-align: middle" scope="col">Due date</th>
                            <th style="text-align: center; vertical-align: middle" scope="col">Return date</th>
                            <th style="text-align: center; vertical-align: middle" scope="col">Status</th>
                            <th style="text-align: center; vertical-align: middle" scope="col">Comment</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.borrowRecords}" var="borrowRecord">
                            <tr>
                                <th style="text-align: center; vertical-align: middle" scope="row">Record
                                    №${borrowRecord.id}</th>
                                <td style="text-align: center; vertical-align: middle">${borrowRecord.username}</td>
                                <td style="text-align: center; vertical-align: middle">${borrowRecord.bookTitle}</td>
                                <td style="text-align: center; vertical-align: middle">${borrowRecord.borrowDate}</td>
                                <td style="text-align: center; vertical-align: middle">${borrowRecord.dueDate}</td>
                                <td style="text-align: center; vertical-align: middle">
                                    <c:choose>
                                        <c:when test="${borrowRecord.returnDate != null}">
                                            ${borrowRecord.returnDate}
                                        </c:when>
                                        <c:otherwise>
                                            -
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <form action="controller" method="post">
                                    <td style="text-align: center; vertical-align: middle">
                                        <c:choose>
                                            <c:when test="${borrowRecord.statusId == 1}">
                                                Returned
                                            </c:when>
                                            <c:when test="${borrowRecord.statusId == 2}">
                                                Returned and damaged
                                            </c:when><c:when test="${borrowRecord.statusId == 3}">
                                            Lost
                                        </c:when>
                                            <c:otherwise>
                                                <select class="custom-select" name="statusId">
                                                    <option selected value="1">Returned</option>
                                                    <option value="2">Returned and damaged</option>
                                                    <option value="3">Lost</option>
                                                </select>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td style="text-align: center; vertical-align: middle">

                                        <c:choose>
                                            <c:when test="${borrowRecord.comment != null}">
                                                ${borrowRecord.comment}
                                            </c:when>
                                            <c:otherwise>
                                                <input type="text" name="comment">
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td style="text-align: center; vertical-align: middle">
                                        <c:choose>
                                            <c:when test="${borrowRecord.statusId== 0}">
                                                <input type="hidden" name="borrowRecordId" value="${borrowRecord.id}">
                                                <input type="hidden" name="bookId" value="${borrowRecord.bookId}">
                                                <button type="submit" name="command" value="edit-borrow-record-by-admin"
                                                        class="btn btn-info">Submit
                                                </button>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </form>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </c:otherwise>
                    </c:choose>
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
