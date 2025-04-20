<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.net.URLEncoder" %>
<html>
<head>
    <title>File Explorer</title>
    <link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <h3 class="crDate">${creationDate}</h3>
    <h1 class="currentDir">${currentDir}</h1>

    <c:if test="${not empty parentDir}">
        <a href="explorer?path=${URLEncoder.encode(parentDir, 'UTF-8')}" class="up-link">Наверх</a>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>Файл</th>
                <th>Размер</th>
                <th>Дата</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${files}" var="file">
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${file.isDirectory()}">
                            <img src="https://cdn-icons-png.flaticon.com/512/716/716784.png" alt="Папка" class="icon">
                            <a href="explorer?path=${URLEncoder.encode(file.getAbsolutePath(), 'UTF-8')}">${file.getName()}/</a>
                        </c:when>
                        <c:otherwise>
                            <img src="https://cdn-icons-png.flaticon.com/512/2965/2965300.png" alt="Файл" class="icon">
                            <a href="download?fileDownloadPath=${URLEncoder.encode(file.getAbsolutePath(), 'UTF-8')}">${file.getName()}</a>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="file-size">
                    <c:choose>
                        <c:when test="${file.isDirectory()}">-</c:when>
                        <c:otherwise>
                            <fmt:formatNumber value="${file.length()}" /> байт
                        </c:otherwise>
                    </c:choose>
                </td>
                <td class="file-date">
                    <jsp:useBean id="dateValue" class="java.util.Date"/>
                    <jsp:setProperty name="dateValue" property="time" value="${file.lastModified()}"/>
                    <fmt:formatDate value="${dateValue}" pattern="dd.MM.yyyy HH:mm"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>