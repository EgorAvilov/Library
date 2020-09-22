<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 19.09.2020
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<div>
    <form action="controller" method="get">
        <input type="text" name="firstName" placeholder="firstName">
        <input type="text" name="lastName" placeholder="lastName">
        <input type="text" name="email" placeholder="email">
        <input type="text" name="gender" placeholder="gender">
        <input type="text" name="phoneNumber" placeholder="phoneNumber">
        <input type="text" name="username" placeholder="username">
        <input type="text" name="password" placeholder="password">
        <input type="hidden" name="command" value="sign-up">
        <button type="submit">Sign up</button>
    </form>
</div>
</body>
</html>
