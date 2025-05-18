<%--
  Created by IntelliJ IDEA.
  User: lenadekart
  Date: 12.05.2025
  Time: 02:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <style>
    body {
      font-family: Arial, sans-serif;
      max-width: 400px;
      margin: 50px auto;
      padding: 20px;
      text-align: center
    }
    .container {
      background: #f8f9fa;
      padding: 30px;
      border-radius: 10px;
      box-shadow: 0 4px 6px rgba(0,0,0,0.1)
    }
    a {
      display: inline-block;
      margin-top: 20px;
      padding: 12px 25px;
      background: #007bff;
      color: white;
      text-decoration: none;
      border-radius: 5px
    }
    h1 {
      color: #2c3e50;
      margin-bottom: 30px
    }
  </style>
  <title>Главная страница</title>
</head>
<body>
<div class="container">
  <h1>Привет, ${login}!</h1>
  <a href="filelist">Перейти к файлам</a>
</div>
</body>
</html>
