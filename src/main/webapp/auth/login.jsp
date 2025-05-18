<%--
  Created by IntelliJ IDEA.
  User: lenadekart
  Date: 18.05.2025
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
  <style>
    body {
      font-family: Arial, sans-serif;
      max-width: 300px;
      margin: 50px auto;
      padding: 20px
    }

    .container {
      background: #f5f5f5;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1)
    }

    input {
      width: 100%;
      padding: 8px;
      margin: 5px 0 15px;
      border: 1px solid #ddd;
      border-radius: 4px;
      box-sizing: border-box
    }

    button {
      background: #007bff;
      color: white;
      padding: 10px 15px;
      border: none;
      border-radius: 4px;
      width: 100%;
      cursor: pointer
    }

    .error {
      color: red;
      margin-bottom: 10px
    }

    a {
      display: block;
      text-align: center;
      margin-top: 15px;
      color: #007bff;
      text-decoration: none
    }
  </style>
</head>

<body>
<div class="container">
  <% if (request.getAttribute("error") != null) { %>
  <div class="error">
    <%= request.getAttribute("error") %>
  </div>
  <% } %>
  <h2 style="margin-top: 0">Вход</h2>
  <form action="login" method="post">
    <input type="text" name="login" placeholder="Логин" required>
    <input type="password" name="password" placeholder="Пароль" required>
    <button type="submit">Войти</button>
  </form> <a href="register">Зарегистрироваться</a> </div>
</body>

</html>