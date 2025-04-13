<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <title>Chi Tiết Sản Phẩm</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
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
            width: 100%;
            max-width: 250px;
            height: 200px;
            object-fit: cover;
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

    </style>
</head>
<body>

<!-- Header -->
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #007bff;">
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

        <!-- Thanh tìm kiếm -->
        <form class="d-flex flex-grow-1 mx-3" action="/phone/search" method="get">
            <input class="form-control me-2 rounded-pill" type="search" name="keyword"
                   placeholder="Nhập tên điện thoại, máy tính, phụ kiện..." aria-label="Search">
            <button class="btn btn-light rounded-circle" type="submit">
                <i class="fas fa-search text-primary"></i>
            </button>
        </form>

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

<!-- 🏷 Chi Tiết Sản Phẩm -->
<div class="container mt-5">
    <div class="product-card p-4">
        <h2 class="text-center text-primary mb-4">Chi Tiết Sản Phẩm</h2>
        <div class="row">
            <div class="col-md-5 text-center">
                <img src="${pageContext.request.contextPath}/images/${phone.hinhAnh != null ? phone.hinhAnh : 'default.jpg'}"
                     class="img-fluid product-img" alt="${phone.ten}">
            </div>
            <div class="col-md-7">
                <h3 class="text-info">${phone.ten}</h3>
                <p><strong>Thương hiệu:</strong> ${phone.thuongHieu}</p>
                <p><strong>Model:</strong> ${phone.model}</p>
                <p><strong>Năm sản xuất:</strong> ${phone.namSanXuat}</p>

                <!-- 🛠 Cập nhật định dạng giá -->
                <p class="price">
                    <fmt:formatNumber value="${phone.gia}" type="number" groupingUsed="true"/> VND
                </p>

                <p><strong>RAM:</strong> ${phone.ram} GB | <strong>Bộ nhớ trong:</strong> ${phone.boNhoTrong} GB</p>
                <p><strong>Dung lượng pin:</strong> ${phone.dungLuongPin} mAh</p>
                <p><strong>Hệ điều hành:</strong> ${phone.heDieuHanh}</p>
                <p><strong>Trạng thái:</strong>
                    <span class="badge bg-success">${phone.trangThai}</span>
                </p>
                <div class="d-grid gap-2">
                    <button class="btn btn-success btn-custom btn-buy">Mua Ngay</button>
                    <form action="${pageContext.request.contextPath}/cart/add" method="get">
                        <input type="hidden" name="idSanPham" value="${phone.id}">
                        <input type="number" name="soLuong" value="1" min="1">
                        <button type="submit" class="btn btn-warning btn-custom">Thêm vào giỏ hàng</button>
                    </form>
                </div>
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
