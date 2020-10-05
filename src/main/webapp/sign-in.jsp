<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 18.09.2020
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
    <link rel="stylesheet" type="text/css" href="resource/css/sign-in.css">
    <fmt:setBundle basename="message"/>

    <title>Sign in</title>
</head>
<body style="background: url('resource/img/background.jpg') no-repeat center center fixed">
    <div class="col-sm-9 main-section">
        <div class="modal-content">

            <div class="col-12 user-img">
                <img src="resource/img/face.png" alt="">
            </div>

            <div class="col-12 form-input">
                <form action="controller" method="post">
                    <div class="input-group flex-nowrap">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="addon-wrapping">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                            </span>
                        </div>
                        <input type="text" name="username" id="username" class="form-control"
                               placeholder="Username" aria-label="Username"
                               aria-describedby="addon-wrapping" required>
                    </div>
                    <br>
                    <div class="input-group flex-nowrap">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="addon-wrapping">
                                <i class="fa fa-lock" aria-hidden="true"></i>
                            </span>
                        </div>
                        <input type="password" name="password" id="password" class="form-control"
                               placeholder="Password" aria-label="Password"
                               aria-describedby="addon-wrapping" required>
                    </div>
                    <c:if test="${sessionScope.message_sign_in != null}">
                    <br>
                    <p style="color: #FF0000;"><fmt:message key="${sessionScope.message_sign_in}"/><p>
                    </c:if>
                    <br>
                    <input type="hidden" name="command" value="sign-in">
                    <button type="submit" class="btn btn-success">
                        Sign in
                    </button>
                </form>
            </div>
            <div class="col-12 forgot">
                <p class="add">
                    Don't have an account? <a href="sign-up">Sign up!</a>
                </p>
            </div>
        </div>
    </div>
</body>
</html>
