<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ include file="/views/layout/header.jsp" %>--%>

<html>
<head>
    <title>Chi Tiết Sản Phẩm</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .product-card {
            max-width: 800px;
            margin: auto;
            background: #fff;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        .product-img {
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }
        .price {
            font-size: 24px;
            font-weight: bold;
            color: #dc3545;
        }
        .btn-custom {
            width: 100%;
            font-size: 18px;
            font-weight: bold;
        }
        .btn-buy:hover {
            background: #28a745 !important;
            color: white !important;
        }
        .btn-cart:hover {
            background: #ffc107 !important;
            color: black !important;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <div class="product-card p-4">
        <h2 class="text-center text-primary mb-4">Chi Tiết Sản Phẩm</h2>
        <div class="row">
            <div class="col-md-5 text-center">
                <img src="images/default.jpg" class="img-fluid product-img" alt="${phone.ten}">
            </div>
            <div class="col-md-7">
                <h3 class="text-info">${phone.ten}</h3>
                <p><strong>Thương hiệu:</strong> ${phone.thuongHieu}</p>
                <p><strong>Model:</strong> ${phone.model}</p>
                <p><strong>Năm sản xuất:</strong> ${phone.namSanXuat}</p>
                <p class="price">${phone.gia} VND</p>
                <p><strong>RAM:</strong> ${phone.ram} GB | <strong>Bộ nhớ trong:</strong> ${phone.boNhoTrong} GB</p>
                <p><strong>Dung lượng pin:</strong> ${phone.dungLuongPin} mAh</p>
                <p><strong>Hệ điều hành:</strong> ${phone.heDieuHanh}</p>
                <p><strong>Trạng thái:</strong>
                    <span class="badge bg-success">${phone.trangThai}</span>
                </p>

                <!-- Button -->
                <div class="d-grid gap-2">
                    <button class="btn btn-success btn-custom btn-buy">Mua Ngay</button>
                    <button class="btn btn-warning btn-custom btn-cart">Thêm vào giỏ hàng</button>
                </div>

                <!-- Link Quay Lại -->
                <div class="mt-3 text-center">
                    <a href="/phone" class="btn btn-secondary">Quay lại danh sách</a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
