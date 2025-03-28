<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ include file="/views/layout/header.jsp" %>--%>

<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Danh Sách Sản Phẩm</title>

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

  <style>
    body {
      background-color: #f8f9fa;
    }
    .card {
      border-radius: 10px;
      transition: transform 0.3s ease-in-out;
    }
    .card:hover {
      transform: scale(1.05);
    }
    .price {
      font-size: 20px;
      font-weight: bold;
      color: #dc3545;
    }
    .btn-buy {
      background-color: #28a745;
      color: white;
      font-weight: bold;
    }
    .btn-buy:hover {
      background-color: #218838;
    }
    .btn-cart {
      background-color: #ffc107;
      color: black;
      font-weight: bold;
    }
    .btn-cart:hover {
      background-color: #e0a800;
    }
  </style>
</head>
<body>

<div class="container mt-4">
  <h1 class="text-center text-primary mb-4">📱 Danh Sách Điện Thoại</h1>
  <div class="row">
    <c:forEach var="phone" items="${phoneList}">
      <div class="col-md-4 mb-4">
        <div class="card h-100 shadow-sm">
          <img src="images/default.jpg" class="card-img-top" alt="${phone.ten}">
          <div class="card-body text-center">
            <h5 class="card-title">${phone.ten}</h5>
            <p class="card-text"><strong>Thương hiệu:</strong> ${phone.thuongHieu}</p>
            <p class="price">${phone.gia} VNĐ</p>

            <div class="d-grid gap-2">
              <a href="/phone/detail?id=${phone.id}" class="btn btn-primary">Xem chi tiết</a>
              <a href="/cart/add?id=${phone.id}" class="btn btn-cart">Thêm vào giỏ</a>
              <a href="/checkout/buy-now?id=${phone.id}" class="btn btn-buy">Mua ngay</a>
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
