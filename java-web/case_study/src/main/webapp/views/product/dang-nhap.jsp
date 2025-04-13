<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Đăng nhập</title>

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

  <style>
    body {
      background-color: #f8f9fa;
    }
    .login-container {
      max-width: 400px;
      margin: 80px auto;
      padding: 20px;
      background: #fff;
      border-radius: 10px;
      box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
      text-align: center;
    }
    .login-container img {
      width: 100px; /* Kích thước logo */
      margin-bottom: 10px;
    }
    .btn-login {
      background-color: #007bff;
      color: white;
      font-weight: bold;
    }
    .btn-login:hover {
      background-color: #0056b3;
    }
    .form-control {
      border-radius: 8px;
    }
  </style>
</head>
<body>

<div class="login-container">
  <!-- 🏷 Logo -->
  <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" class="img-fluid">

  <h2 class="text-primary mb-3">🔑 Đăng nhập</h2>

  <form action="${pageContext.request.contextPath}/dang-nhap" method="post">
    <div class="mb-3">
      <label for="tenDangNhap" class="form-label">Tên đăng nhập</label>
      <input type="text" name="tenDangNhap" id="tenDangNhap" class="form-control" placeholder="Nhập tên đăng nhập" required>
    </div>

    <div class="mb-3">
      <label for="matKhau" class="form-label">Mật khẩu</label>
      <input type="password" name="matKhau" id="matKhau" class="form-control" placeholder="Nhập mật khẩu" required>
    </div>

    <button type="submit" class="btn btn-login w-100">Đăng nhập</button>

    <% if (request.getAttribute("errorMessage") != null) { %>
    <p class="text-danger text-center mt-3"><%= request.getAttribute("errorMessage") %></p>
    <% } %>
  </form>

  <div class="text-center mt-3">
    <a href="#" class="text-decoration-none">🔑 Quên mật khẩu?</a>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
