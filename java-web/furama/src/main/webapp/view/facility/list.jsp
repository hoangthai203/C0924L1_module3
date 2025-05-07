<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Danh sách dịch vụ</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body>
<div class="container mt-4">
  <h2 class="text-center text-primary mb-4">DANH SÁCH DỊCH VỤ</h2>
  <!-- Dòng chức năng: Thêm + Tìm kiếm -->
  <div class="d-flex justify-content-between align-items-center mb-3">
    <a href="/facility?action=create" class="btn btn-success">➕ Thêm dịch vụ</a>
    <!-- Tìm kiếm -->
    <form action="/facility" method="get" class="d-flex">
      <input type="text" name="search" value="${search}" class="form-control me-2" placeholder="Tìm theo tên..."/>
      <button type="submit" class="btn btn-outline-primary">Tìm</button>
      <input type="hidden" name="page" value="1"/>
    </form>
  </div>
  <!-- Bảng dữ liệu -->
  <table class="table table-bordered table-striped table-hover">
    <thead class="table-light">
    <tr>
      <th>ID</th>
      <th>Tên</th>
      <th>Diện tích</th>
      <th>Chi phí thuê</th>
      <th>Số người tối đa</th>
      <th>Kiểu thuê</th>
      <th>Loại dịch vụ</th>
      <th>Chức năng</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="facility" items="${facilityList}">
      <tr>
        <td>${facility.id}</td>
        <td>${facility.name}</td>
        <td>${facility.area}</td>
        <td>${facility.cost}</td>
        <td>${facility.maxPeople}</td>
        <td>${facility.rentType}</td>
        <td>${facility.facilityType}</td>
        <td>
          <a href="/facility?action=edit&id=${facility.id}" class="btn btn-sm btn-warning">Sửa</a>
          <a href="/facility?action=delete&id=${facility.id}" class="btn btn-sm btn-danger"
             onclick="return confirm('Bạn có chắc muốn xóa dịch vụ này?');">Xóa</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <!-- Phân trang -->
  <nav>
    <ul class="pagination justify-content-center">
      <c:forEach var="i" begin="1" end="${totalPages}">
        <li class="page-item ${i == currentPage ? 'active' : ''}">
          <a class="page-link" href="/facility?page=${i}&search=${search}">${i}</a>
        </li>
      </c:forEach>
    </ul>
  </nav>
</div>
</body>
</html>