<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag isELIgnored="false" %>
<nav>
    <nav class="navbar navbar-expand-lg navbar-light bg-dark" style="max-height: 60px;">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link text-white" href="${pageContext.request.contextPath}/controller?command=view-all-books">Main page</a>
                </li>
                <li class="nav-item active">
                    <c:choose>
                    <c:when test="${sessionScope.user.role.roleId==1}">
                        <a class="nav-link text-white" href="${pageContext.request.contextPath}/controller?command=view-all-borrow-records-by-admin">Borrow records</a>
                    </c:when>
                        <c:otherwise>
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/controller?command=view-all-borrow-records-by-user">Borrow records</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <c:if test="${sessionScope.user.role.roleId == 1}">
                <li class="nav-item active">
                    <a class="nav-link text-white" href="${pageContext.request.contextPath}/controller?command=view-all-users">Users</a>
                </li>
                </c:if>

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Profile
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item text-dark" href="user-page">View</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item text-dark" href="edit-profile">Edit</a>
                    </div>
                </li>
                <li class="nav-item active">
                    <form action="controller" method="get">
                        <button type="submit" name="command" value="sign-out" class="btn btn-link text-white">Sign out</button>
                    </form>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0 " action="controller" method="get">
                <input class="form-control mr-sm-2" name="searchParameter"   type="text" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0"  name="command" value="search-book" type="submit">Search</button>
            </form>
        </div>
    </nav>
</nav>