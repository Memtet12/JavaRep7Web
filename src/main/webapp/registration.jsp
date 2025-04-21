<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="style.css">
    <title>Регистрация</title>
</head>
<body>
<div class="container mt-5">
    <div class="card">
        <div class="card-body">
            <h1 class="card-title text-center">Регистрация на сайте</h1>
            <h5 class="card-subtitle mb-2 text-center text-muted">Введите данные</h5>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>
            <form action="register" method="post">
                <div class="form-group">
                    <input name="username" type="text" class="form-control" placeholder="Логин" required>
                </div>
                <div class="form-group">
                    <input name="password" type="password" class="form-control" placeholder="Пароль" required>
                </div>
                <div class="form-group">
                    <input name="email" type="email" class="form-control" placeholder="Email" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Зарегистрироваться</button>
            </form>
            <a href="login" class="login-link">Войти</a>
        </div>
    </div>
</div>
</body>
</html>
