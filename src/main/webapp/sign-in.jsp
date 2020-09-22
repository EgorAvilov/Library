<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 18.09.2020
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
    <link rel="stylesheet" type="text/css" href="css/sign-in.css">

    <title>Sign in</title>
</head>
<body>
    <div class="col-sm-9 main-section">
        <div class="modal-content">

            <div class="col-12 user-img">
                <img src="face.png" alt="">
            </div>

            <div class="col-12 form-input">
                <form action="controller" method="post" oninput="return validateSignIn()">
                    <div class="input-group flex-nowrap">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="addon-wrapping">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                            </span>
                        </div>
                        <input type="text" name="username" id="username" class="form-control"
                               placeholder="Username" aria-label="Username"
                               aria-describedby="addon-wrapping">
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
                               aria-describedby="addon-wrapping">
                    </div>
                    <br>
                    <c:if test="${sign_in_message != null}">
                        <p style="color: #FF0000;"><fmt:message key="message.invalid-sign-in-values"/><p>
                    </c:if>
                    <c:if test="${user_banned_message != null}">
                        <p style="color: #FF0000;"><fmt:message key="message.user-banned"/></p>
                    </c:if>
                    <c:if test="${success_sign_up != null}">
                        <p style="color: #00FF00;"><fmt:message key="message.success-sign-up"/></p>
                    </c:if>
                    <input type="hidden" name="command" value="sign-in">
                    <button type="submit" class="btn btn-success">
                        Sign in
                    </button>
                </form>
            </div>
            <div class="col-12 forgot">
                <p class="add">
                    <a href="restore-password">Forgot password?</a>
                </p>
                <p class="add">
                    <a href="sign-up">Don't have an account? Sign up!</a>
                </p>
            </div>
        </div>
    </div>
</body>
</html>
