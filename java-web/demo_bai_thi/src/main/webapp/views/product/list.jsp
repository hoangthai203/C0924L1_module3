<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>Quản lý Hàng hóa - Công ty ABC</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <style>
    .table th, .table td {
      vertical-align: middle;
    }
    .category-badge {
      font-size: 0.9em;
      padding: 5px 10px;
    }
    .category-Rau {
      background-color: #28a745;
      color: white;
    }
    .category-Củ {
      background-color: #fd7e14;
      color: white;
    }
    .category-Quả {
      background-color: #dc3545;
      color: white;
    }
    .category-Hoa {
      background-color: #6f42c1;
      color: white;
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

  <c:if test="${not empty mess}">
    <div class="alert alert-success alert-dismissible fade show">
      <button type="button" class="close" data-dismiss="alert">&times;</button>
      <strong>${mess}</strong>
    </div>
  </c:if>

  <div class="card mb-4">
    <div class="card-header bg-primary text-white">
      <div class="row">
        <div class="col-md-8">
          <h5 class="mb-0">Danh sách hàng hóa</h5>
        </div>
        <div class="col-md-4 text-right">
          <a href="/products?action=create" class="btn btn-light">
            <i class="fa fa-plus"></i> Thêm hàng hóa mới
          </a>
        </div>
      </div>
    </div>
    <div class="card-body">
      <form action="/products" method="get" class="form-row mb-4">
        <input type="hidden" name="action" value="search">
        <div class="col-md-5">
          <input type="text" name="name" class="form-control" placeholder="Tìm theo tên hàng hóa" value="${searchName}">
        </div>
        <div class="col-md-5">
          <select name="category" class="form-control">
            <option value="all">Tất cả loại hàng hóa</option>
            <c:forEach items="${categoryList}" var="category">
              <option value="${category}" ${category eq searchCategory ? 'selected' : ''}>${category}</option>
            </c:forEach>
          </select>
        </div>
        <div class="col-md-2">
          <button type="submit" class="btn btn-primary btn-block">Tìm kiếm</button>
        </div>
      </form>

      <div class="table-responsive">
        <table class="table table-bordered table-hover">
          <thead class="thead-dark">
          <tr>
            <th width="15%">Mã hàng hóa</th>
            <th width="25%">Tên hàng hóa</th>
            <th width="15%">Đơn vị tính</th>
            <th width="15%">Giá (VNĐ)</th>
            <th width="15%">Loại hàng hóa</th>
            <th width="15%">Thao tác</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="product" items="${productList}">
            <tr>
              <td>${product.productId}</td>
              <td>${product.name}</td>
              <td>${product.unit}</td>
              <td class="text-right">
                <fmt:formatNumber value="${product.price}" pattern="#,###" /> VNĐ
              </td>
              <td>
                <span class="badge category-badge category-${product.category}">${product.category}</span>
              </td>
              <td class="text-center">
                <button type="button" class="btn btn-danger btn-sm"
                        data-toggle="modal" data-target="#deleteModal"
                        data-id="${product.productId}"
                        data-name="${product.name}">
                  Xóa
                </button>
              </td>
            </tr>
          </c:forEach>
          <c:if test="${empty productList}">
            <tr>
              <td colspan="6" class="text-center">Không tìm thấy hàng hóa nào.</td>
            </tr>
          </c:if>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>

<!-- Delete Modal -->
<div class="modal fade" id="deleteModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <h4 class="modal-title">Xác nhận xóa</h4>
        <button type="button" class="close text-white" data-dismiss="modal">&times;</button>
      </div>
      <div class="modal-body">
        <p>Bạn có chắc chắn muốn xóa hàng hóa <strong><span id="productName"></span></strong>?</p>
        <p class="text-danger">Lưu ý: Hành động này không thể hoàn tác!</p>
      </div>
      <div class="modal-footer">
        <form action="/products" method="post">
          <input type="hidden" name="action" value="delete">
          <input type="hidden" name="deleteId" id="deleteId">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
          <button type="submit" class="btn btn-danger">Xóa</button>
        </form>
      </div>
    </div>
  </div>
</div>

<script>
  $('#deleteModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget);
    var productId = button.data('id');
    var productName = button.data('name');

    $('#deleteId').val(productId);
    $('#productName').text(productName);
  });
</script>
</body>
</html>