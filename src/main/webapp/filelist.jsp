<%--
  Created by IntelliJ IDEA.
  User: lenadekart
  Date: 12.05.2025
  Time: 02:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Список папок и файлов компьютера </title>
</head>
<body>
<p> ${currentDate} </p>
<h1> ${currentPath} </h1>
<a href="?path=${previousPath}">Upper</a>
<table>
  <tr>
    <th>File</th>
    <th>Size</th>
    <th>Date</th>
  </tr>
  <c:forEach items="${Items}" var="item" varStatus="status">
    <tr>
      <td>
        <c:choose>
          <c:when test="${item.size == 0}">
            <a href="?path=${currentPath}/${item.path}">${item.path}/</a>
          </c:when>
          <c:otherwise>
            <a href="download?path=${currentPath}/${item.path}">${item.path}</a>
          </c:otherwise>
        </c:choose>
      </td>
      <td>
        <c:choose>
          <c:when test="${item.size == 0}">
            ${""}
          </c:when>
          <c:otherwise>
            ${item.size} B
          </c:otherwise>
        </c:choose>
      </td>
      <td>${item.date}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>