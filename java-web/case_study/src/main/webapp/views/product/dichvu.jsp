<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codegym.case_study.model.DichVu" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Quản lý Dịch Vụ</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h2 class="mt-4">Danh Sách Dịch Vụ</h2>
  <a href="dichvu?action=add" class="btn btn-primary mb-3">Thêm Dịch Vụ</a>

  <table class="table table-bordered">
    <thead class="table-dark">
    <tr>
      <th>ID</th>
      <th>Tên Dịch Vụ</th>
      <th>Mô Tả</th>
      <th>Giá</th>
      <th>Trạng Thái</th>
      <th>Hành Động</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<DichVu> dichVuList = (List<DichVu>) request.getAttribute("dichVuList");
      if (dichVuList != null) {
        for (DichVu dv : dichVuList) {
    %>
    <tr>
      <td><%= dv.getIdDichVu() %></td>
      <td><%= dv.getTen() %></td>
      <td><%= dv.getMoTa() %></td>
      <td><%= dv.getGia() %></td>
      <td><%= dv.getTrangThai().equals("hoat_dong") ? "Hoạt Động" : "Tạm Ngưng" %></td>
      <td>
        <a href="dichvu?action=delete&id=<%= dv.getIdDichVu() %>" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
        <% if (dv.getTrangThai().equals("hoat_dong")) { %>
        <a href="dichvu?action=suspend&id=<%= dv.getIdDichVu() %>" class="btn btn-warning btn-sm">Tạm Ngưng</a>
        <% } %>
      </td>
    </tr>
    <%
        }
      }
    %>
    </tbody>
  </table>
</div>
</body>
</html>
