<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Thêm Hàng hóa mới - Công ty ABC</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <style>
    .form-control:focus {
      border-color: #80bdff;
      box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
    }
    .card-header {
      font-weight: bold;
    }
    .required-field::after {
      content: " *";
      color: red;
    }
  </style>
</head>
<body>
<div class="container mt-5">
  <div class="row mb-4">
    <div class="col-md-8">
      <h2>Quản lý Hàng hóa - Công ty ABC</h2>
      <p class="text-muted">Hoa, rau, củ, quả cho thị trường miền Trung</p>
    </div>
    <div class="col-md-4 text-right">
      <img src="/images/logo.png" alt="Logo Công ty ABC" height="60">
    </div>
  </div>

  <div class="card">
    <div class="card-header bg-primary text-white">
      <h5 class="mb-0">Thêm hàng hóa mới</h5>
    </div>
    <div class="card-body">
      <c:if test="${not empty error}">
        <div class="alert alert-danger alert-dismissible fade show">
          <button type="button" class="close" data-dismiss="alert">&times;</button>
          <strong>${error}</strong>
        </div>
      </c:if>

      <form action="/products" method="post" id="productForm">
        <input type="hidden" name="action" value="create">

        <div class="form-group row">
          <label for="productId" class="col-sm-3 col-form-label required-field">Mã hàng hóa:</label>
          <div class="col-sm-9">
            <input type="text" class="form-control" id="productId" name="productId"
                   pattern="MHH-[A-Z0-9]{4}"
                   title="Mã hàng hóa phải có định dạng MHH-XXXX, với X là số hoặc chữ in HOA"
                   placeholder="MHH-XXXX" required>
            <small class="form-text text-muted">Định dạng: MHH-XXXX (X là số hoặc chữ in HOA)</small>
          </div>
        </div>

        <div class="form-group row">
          <label for="name" class="col-sm-3 col-form-label required-field">Tên hàng hóa:</label>
          <div class="col-sm-9">
            <input type="text" class="form-control" id="name" name="name" required>
          </div>
        </div>

        <div class="form-group row">
          <label for="unit" class="col-sm-3 col-form-label required-field">Đơn vị tính:</label>
          <div class="col-sm-9">
            <select class="form-control" id="unit" name="unit" required>
              <option value="">-- Chọn đơn vị tính --</option>
              <c:forEach items="${unitList}" var="unit">
                <option value="${unit}">${unit}</option>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="form-group row">
          <label for="price" class="col-sm-3 col-form-label required-field">Giá (VNĐ):</label>
          <div class="col-sm-9">
            <div class="input-group">
              <input type="number" class="form-control" id="price" name="price" min="1000" step="1" required>
              <div class="input-group-append">
                <span class="input-group-text">VNĐ</span>
              </div>
            </div>
            <small class="form-text text-muted">Giá phải là số nguyên dương và >= 1.000 VNĐ</small>
          </div>
        </div>

        <div class="form-group row">
          <label for="category" class="col-sm-3 col-form-label required-field">Loại hàng hóa:</label>
          <div class="col-sm-9">
            <select class="form-control" id="category" name="category" required>
              <option value="">-- Chọn loại hàng hóa --</option>
              <c:forEach items="${categoryList}" var="category">
                <option value="${category}">${category}</option>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="form-group row">
          <div class="col-sm-9 offset-sm-3">
            <button type="submit" class="btn btn-primary">Lưu</button>
            <a href="/products" class="btn btn-secondary">Hủy</a>
          </div>
        </div>
      </form>
    </div>
    <div class="card-footer">
      <p class="text-muted mb-0"><small>* Thông tin bắt buộc</small></p>
    </div>
  </div>
</div>

<script>
  // Client-side validation using JavaScript
  document.getElementById('productForm').addEventListener('submit', function(event) {
    let productId = document.getElementById('productId').value;
    let price = document.getElementById('price').value;
    let isValid = true;

    // Validate product ID
    if (!productId.match(/^MHH-[A-Z0-9]{4}$/)) {
      alert('Mã hàng hóa phải có định dạng MHH-XXXX, với X là số hoặc chữ in HOA');
      isValid = false;
    }

    // Validate price
    if (price < 1000) {
      alert('Giá phải là số nguyên dương và >= 1.000 VNĐ');
      isValid = false;
    }

    if (!isValid) {
      event.preventDefault();
    }
  });
</script>
</body>
</html>