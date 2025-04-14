<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Danh sách nhân viên</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body>
<div class="container mt-4">
  <h2 class="text-center text-primary mb-4">DANH SÁCH NHÂN VIÊN</h2>
  <!-- Dòng chức năng: Thêm + Tìm kiếm -->
  <div class="d-flex justify-content-between align-items-center mb-3">
    <a href="/employee?action=create" class="btn btn-success">➕ Thêm nhân viên</a>
    <!-- Tìm kiếm -->
    <form action="/employee" method="get" class="d-flex">
      <input type="text" name="search" value="${search}" class="form-control me-2" placeholder="Tìm theo tên..."/>
      <button type="submit" class="btn btn-outline-primary">Tìm</button>
      <input type="hidden" name="page" value="1"/>
    </form>
  </div>
  <!-- Bảng dữ liệu -->
  <table class="table table-bordered table-striped table-hover">
    <thead class="table-light">
    <tr>
      <th>Mã NV</th>
      <th>Họ tên</th>
      <th>Ngày sinh</th>
      <th>CMND</th>
      <th>Lương</th>
      <th>SDT</th>
      <th>Email</th>
      <th>Địa chỉ</th>
      <th>Chức vụ</th>
      <th>Bằng cấp</th>
      <th>Phòng ban</th>
      <th>Chức năng</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="e" items="${employees}">
      <tr>
        <td>${e.id}</td>
        <td>${e.name}</td>
        <td>${e.birthday}</td>
        <td>${e.idCard}</td>
        <td>${e.salary}</td>
        <td>${e.phone}</td>
        <td>${e.email}</td>
        <td>${e.address}</td>
        <td>
          <c:forEach var="position" items="${positions}">
            <c:if test="${position.id eq e.positionId}">${position.name}</c:if>
          </c:forEach>
        </td>
        <td>
          <c:forEach var="edu" items="${educationDegrees}">
            <c:if test="${edu.id eq e.educationDegreeId}">${edu.name}</c:if>
          </c:forEach>
        </td>
        <td>
          <c:forEach var="division" items="${divisions}">
            <c:if test="${division.id eq e.divisionId}">${division.name}</c:if>
          </c:forEach>
        </td>
        <td>
          <a href="/employee?action=edit&id=${e.id}" class="btn btn-sm btn-warning">Sửa</a>
          <a href="/employee?action=delete&id=${e.id}" class="btn btn-sm btn-danger"
             onclick="return confirm('Bạn có chắc muốn xóa nhân viên này?');">Xóa</a>
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
          <a class="page-link" href="/employee?page=${i}&search=${search}">${i}</a>
        </li>
      </c:forEach>
    </ul>
  </nav>
</div>
</body>
</html>
