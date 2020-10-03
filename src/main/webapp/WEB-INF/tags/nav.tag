<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag isELIgnored="false" %>
<nav>
    <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light bg-dark">
                <form action="controller" method="get">
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item active">
                                <a class="nav-link text-white" href="${pageContext.request.contextPath}/controller?command=view-all-books">Main page</a>
                            </li>
                            <c:choose>
                                <c:when test="${sessionScope.user.role.roleId == 1}">
                                    <li class="nav-item">
                                        <a class="nav-link text-white" href="${pageContext.request.contextPath}/controller?command=view-all-users">Users</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link text-white" href="${pageContext.request.contextPath}/add-book">Add book</a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="nav-item">
                                        <a class="nav-link text-white" href="#">Borrow records</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Profile
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item text-dark" href="#">View</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item text-dark" href="#">Edit</a>
                                </div>
                            </li>
                            <input type="hidden" name="command" value="sign-out">
                            <li class="nav-item">
                                <button type="submit" class="btn btn-link text-white">Sign out</button>
                            </li>
                        </ul>
                    </div>
                </form>
            </nav>
        </div>
    </div>
    <div class="navbar navbar-dark bg-dark box-shadow">
        <div class="container d-flex justify-content-between">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarHeader" aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
    </div>
</nav>