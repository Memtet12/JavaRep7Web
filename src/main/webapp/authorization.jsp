<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <title>Авторизация</title>
</head>
<body>
<div class="auth-container">
    <h1>Авторизуйтесь на сайте</h1>
    <c:if test="${not empty param.error}">
        <div class="alert alert-danger">Неверный логин или пароль!</div>
    </c:if>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="form-group">
            <label for="username">Логин:</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Логин" required>
        </div>
        <div class="form-group">
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Пароль" required>
        </div>
        <button type="submit" class="btn btn-primary">Войти</button>
    </form>
    <a href="${pageContext.request.contextPath}/register" class="register-link">Зарегистрироваться</a>
</div>
</body>
</html>
