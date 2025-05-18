<%--
  Created by IntelliJ IDEA.
  User: lenadekart
  Date: 18.05.2025
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    body {
      font-family: Arial, sans-serif;
      background: #f8f9fa;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }
    .error-box {
      background: #fff;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0 2px 12px rgba(0,0,0,0.1);
      text-align: center;
      max-width: 400px;
    }
    .error-box h2 {
      margin-bottom: 10px;
      color: #c00;
    }
    .error-box p {
      margin: 0 0 20px;
      color: #555;
    }
    .error-box a {
      display: inline-block;
      padding: 8px 16px;
      background: #eee;
      border-radius: 4px;
      text-decoration: none;
      color: #333;
    }
  </style>
  <title>Ошибка</title>
</head>
<body>
<div class="error-box">
  <h2>⚠ Ошибка</h2>
  <p>${error}</p>
  <a href="?path=${previousPath}">← Назад</a>
</div>
</body>
</html>