<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Danh sách khách hàng</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" />
</head>
<body>
<div class="container mt-4">

  <h2 class="text-center text-primary mb-4">DANH SÁCH KHÁCH HÀNG</h2>

  <div class="d-flex justify-content-between mb-3">
    <a href="/customer?action=create" class="btn btn-success">➕ Thêm khách hàng</a>

    <form action="/customer" method="get" class="d-flex">
      <input type="text" name="search" value="${search}" class="form-control me-2" placeholder="Tìm theo tên..." />
      <input type="submit" class="btn btn-outline-primary" value="Tìm" />
      <input type="hidden" name="page" value="1" />
    </form>
  </div>

  <table class="table table-bordered table-striped table-hover">
    <thead class="table-light">
    <tr>
      <th>ID</th>
      <th>Loại KH</th>
      <th>Họ tên</th>
      <th>Ngày sinh</th>
      <th>Giới tính</th>
      <th>CMND</th>
      <th>SDT</th>
      <th>Email</th>
      <th>Địa chỉ</th>
      <th>Chức năng</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${customers}">
      <tr>
        <td>${c.id}</td>
        <td>${c.customerTypeId}</td>
        <td>${c.name}</td>
        <td>${c.birthday}</td>
        <td>
          <c:choose>
            <c:when test="${c.gender}">Nam</c:when>
            <c:otherwise>Nữ</c:otherwise>
          </c:choose>
        </td>
        <td>${c.idCard}</td>
        <td>${c.phone}</td>
        <td>${c.email}</td>
        <td>${c.address}</td>
        <td>
          <a href="/customer?action=edit&id=${c.id}" class="btn btn-sm btn-warning">Sửa</a>
          <a href="/customer?action=delete&id=${c.id}" class="btn btn-sm btn-danger" onclick="return confirm('Xác nhận xóa khách hàng này?');">Xóa</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <!-- Phân trang -->
  <c:if test="${totalPages > 1}">
    <nav aria-label="Page navigation">
      <ul class="pagination justify-content-center">
        <c:forEach var="i" begin="1" end="${totalPages}">
          <li class="page-item ${i == currentPage ? 'active' : ''}">
            <a class="page-link" href="/customer?page=${i}&search=${search}">${i}</a>
          </li>
        </c:forEach>
      </ul>
    </nav>
  </c:if>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
