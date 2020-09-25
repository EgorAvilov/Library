<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>Title</title>
</head>
<body>
<h1>HELLO!!!!!</h1>
<form action="controller" method="get">
    <input type="hidden" name="command" value="view-all-books">
    <input type="hidden" name="currentPage" value="1">
    <input type="hidden" name="recordsPerPage" value="20">
    <button type="submit">Show books</button>
</form>
</body>
</html>
