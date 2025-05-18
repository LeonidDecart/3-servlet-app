<%--
  Created by IntelliJ IDEA.
  User: lenadekart
  Date: 12.05.2025
  Time: 02:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    * { box-sizing: border-box; }
    body {
      font-family: Arial, sans-serif;
      margin: 0; padding: 20px;
      background: #f5f5f5;
    }
    .header {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      align-items: center;
      margin-bottom: 20px;
    }
    .header h2 { margin: 0; }
    .header .meta {
      color: #666;
    }
    .container {
      display: flex;
      flex-wrap: wrap;
      gap: 20px;
    }
    .table-container {
      background: #fff;
      padding: 15px;
      border-radius: 8px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
      flex: 1 1 100%;
    }
    @media (min-width: 768px) {
      .table-container {
        flex: 1 1 48%;
      }
    }
    table {
      width: 100%;
      border-collapse: collapse;
    }
    th, td {
      padding: 10px;
      border-bottom: 1px solid #eee;
      text-align: left;
    }
    th {
      background: #f8f9fa;
    }
    a { color: #007bff; text-decoration: none; }
    .up-btn {
      display: inline-block;
      margin-bottom: 15px;
      padding: 6px 12px;
      background: #f8f9fa;
      border-radius: 4px;
      color: #333;
    }
  </style>
  <title>Файловый менеджер</title>
</head>
<body>
<div class="header">
  <h2>${userPathPoint.relativePath}</h2>
  <div class="meta">${currentDate} | <a href="logout">Выход</a></div>
</div>
<a class="up-btn" href="?path=${userPathPoint.parentPath}">↑ Наверх</a>

<div class="container">
  <!-- Папки -->
  <div class="table-container">
    <h3>Папки</h3>
    <table>
      <tr>
        <th>Имя</th>
        <th>Путь</th>
        <th>Кол-во</th>
        <th>Размер</th>
        <th>Изменён</th>
      </tr>
      <c:forEach items="${dirs}" var="dir">
        <tr>
          <td><a href="?path=${dir.path}">📁 ${dir.name}</a></td>
          <td>${dir.path}</td>
          <td>${dir.itemCount}</td>
          <td>${dir.size} B</td>
          <td>${dir.date}</td>
        </tr>
      </c:forEach>
    </table>
  </div>

  <!-- Файлы -->
  <div class="table-container">
    <h3>Файлы</h3>
    <table>
      <tr>
        <th>Имя</th>
        <th>Путь</th>
        <th>Размер</th>
        <th>Изменён</th>
        <th>⬇</th>
      </tr>
      <c:forEach items="${files}" var="file">
        <tr>
          <td>${file.name}</td>
          <td>${file.path}</td>
          <td>${file.size} B</td>
          <td>${file.date}</td>
          <td><a href="download?path=${file.path}">Скачать</a></td>
        </tr>
      </c:forEach>
    </table>
  </div>
</div>
</body>
</html>