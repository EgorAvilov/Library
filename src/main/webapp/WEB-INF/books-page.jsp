<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 23.09.2020
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Books</title>
</head>
<body>
<tr>
    <th>id</th>
    <th>title</th>
</tr>
<c:forEach items="${requestScope.books}" var="book">
    <tr>
        <td>${book.id}</td>
    </tr>
    <tr>
        <td>${book.title}</td>
    </tr>
</c:forEach>
</body>
</html>
