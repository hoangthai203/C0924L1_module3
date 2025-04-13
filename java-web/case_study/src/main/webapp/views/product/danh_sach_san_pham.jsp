<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Danh Sách Sản Phẩm</title>

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>

  <style>
    body {
      background-color: #f8f9fa;
    }

    .card {
      max-width: 90%; /* Giảm kích thước card */
      border-radius: 8px;
      transition: transform 0.3s ease-in-out;
      overflow: hidden;
      margin: auto;
    }

    .card:hover {
      transform: scale(1.05);
    }

    .card img {
      width: 100%;
      height: 180px; /* Giảm chiều cao ảnh */
      object-fit: contain;
      padding: 10px;
    }

    .card-title {
      font-size: 16px; /* Giảm kích thước tên sản phẩm */
      font-weight: bold;
    }

    .card-text {
      font-size: 14px;
    }

    .price {
      font-size: 18px;
      font-weight: bold;
      color: #dc3545;
    }

    .btn {
      font-size: 14px; /* Nhỏ hơn để phù hợp card */
      padding: 5px 10px;
    }

    .navbar {
      background-color: #007bff;
    }
  </style>
</head>
<body>

<!-- Header -->
<nav class="navbar navbar-expand-lg navbar-dark">
  <div class="container">
    <!-- Logo -->
    <a class="navbar-brand d-flex align-items-center" href="/">
      <img src="${pageContext.request.contextPath}/images/logo.png"
           alt="Logo" height="40" class="me-2">
    </a>

    <!-- Danh mục -->
    <button class="btn btn-outline-light ms-2">
      <i class="fas fa-bars"></i> Danh mục
    </button>

    <!-- Dịch vụ -->
    <a href="${pageContext.request.contextPath}/dichvu" class="btn btn-outline-light ms-2">
      <i class="fas fa-concierge-bell"></i> Dịch vụ
    </a>

    <!-- Thanh tìm kiếm -->
    <form class="d-flex flex-grow-1 mx-3" action="/phone/search" method="get">
      <input class="form-control me-2 rounded-pill" type="search" name="keyword"
             placeholder="Nhập tên điện thoại, máy tính, phụ kiện..." aria-label="Search">
      <button class="btn btn-light rounded-circle" type="submit">
        <i class="fas fa-search text-primary"></i>
      </button>
    </form>

    <%-- Hiển thị thông báo --%>
    <c:if test="${not empty sessionScope.successMessage}">
      <div class="alert alert-success alert-dismissible fade show text-center" role="alert">
          ${sessionScope.successMessage}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      </div>
      <% session.removeAttribute("successMessage"); %> <%-- Xóa sau khi hiển thị --%>
    </c:if>

    <!-- Tài khoản & Giỏ hàng -->
    <div class="d-flex align-items-center">
      <c:choose>
        <c:when test="${not empty sessionScope.loggedUser}">
          <span class="text-white me-3">👤 ${sessionScope.loggedUser.tenDangNhap}</span>
          <a href="/logout" class="btn btn-danger">Đăng xuất</a>
        </c:when>
        <c:otherwise>
          <a href="${pageContext.request.contextPath}/dang-nhap" class="btn btn-outline-light me-3">
            <i class="fas fa-user"></i> Đăng nhập
          </a>
        </c:otherwise>
      </c:choose>

      <a href="/cart" class="btn btn-dark position-relative">
        <i class="fas fa-shopping-cart"></i> Giỏ hàng
        <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-warning">
          ${sessionScope.cart.size()}
        </span>
      </a>
    </div>
  </div>
</nav>


<!-- 🛍 Danh Sách Sản Phẩm -->
<div class="container mt-4">
  <h1 class="text-center text-primary mb-4">📱 Danh Sách Điện Thoại</h1>
  <div class="row">
    <c:forEach var="phone" items="${phoneList}">
      <div class="col-12 col-sm-6 col-md-4 col-lg-3 mb-3">
        <div class="card h-100 shadow-sm">
          <img src="${pageContext.request.contextPath}/images/${phone.hinhAnh != null ? phone.hinhAnh : 'default.jpg'}"
               alt="${phone.ten}" class="img-fluid">

          <div class="card-body text-center">
            <h5 class="card-title">${phone.ten}</h5>
            <p class="card-text"><strong>Thương hiệu:</strong> ${phone.thuongHieu}</p>

            <!-- Định dạng giá tiền -->
            <p class="price">
              <fmt:formatNumber value="${phone.gia}" type="currency" currencySymbol="VNĐ" />
            </p>

            <div class="d-grid gap-2">
              <a href="${pageContext.request.contextPath}/phone/detail?id=${phone.id}" class="btn btn-primary">Xem chi tiết</a>
              <a href="${pageContext.request.contextPath}/cart/add?idSanPham=${phone.id}&soLuong=1" class="btn btn-cart">Thêm vào giỏ</a>
              <a href="${pageContext.request.contextPath}/checkout/buy-now?id=${phone.id}" class="btn btn-buy">Mua ngay</a>
            </div>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
