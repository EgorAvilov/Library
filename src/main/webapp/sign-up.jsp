<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 19.09.2020
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/sign-in.css"/>">

    <title>Sign up</title>
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
                    </div>
                    <input type="text" name="firstName" id="firstName" class="form-control"
                           placeholder="First name" aria-label="First name"
                           aria-describedby="addon-wrapping">
                </div>
                <br>
                <div class="input-group flex-nowrap">
                    <div class="input-group-prepend">
                    </div>
                    <input type="text" name="lastName" id="LastName" class="form-control"
                           placeholder="Last name" aria-label="Last name"
                           aria-describedby="addon-wrapping">
                </div>
                <br>
                <div class="input-group flex-nowrap">
                    <div class="input-group-prepend">
                    </div>
                    <input type="text" name="email" id="email" class="form-control"
                           placeholder="E-mail" aria-label="Email"
                           aria-describedby="addon-wrapping">
                </div>
                <br>
                <div class="input-group flex-nowrap">
                    <select class="custom-select">
                        <option selected value="true">Male</option>
                        <option value="false">Female</option>
                    </select>
                </div>
                <br>
                <div class="input-group flex-nowrap">
                    <div class="input-group-prepend">
                    </div>
                    <input type="text" name="phoneNumber" id="phoneNumber" class="form-control"
                           placeholder="Phone number" aria-label="Phone number"
                           aria-describedby="addon-wrapping">
                </div>
                <br>
                <div class="input-group flex-nowrap">
                    <div class="input-group-prepend">
                    </div>
                    <input type="text" name="username" id="username" class="form-control"
                           placeholder="Username" aria-label="Username"
                           aria-describedby="addon-wrapping">
                </div>
                <br>
                <div class="input-group flex-nowrap">
                    <div class="input-group-prepend">
                    </div>
                    <input type="password" name="password" id="password" class="form-control"
                           placeholder="Password" aria-label="Password"
                           aria-describedby="addon-wrapping">
                </div>
                <br>
                <input type="hidden" name="command" value="sign-up">
                <button type="submit" class="btn btn-success">
                    Sign up
                </button>
            </form>
        </div>
        <div class="col-12 forgot">
            <p class="add">
                Have an account? <a href="sign-in">Sign in!</a>
            </p>
        </div>
    </div>
</div>
</body>
</html>
