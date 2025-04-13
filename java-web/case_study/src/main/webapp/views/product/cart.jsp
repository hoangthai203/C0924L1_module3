<%@ page import="com.codegym.case_study.model.Cart, com.codegym.case_study.model.CartItem" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  NumberFormat currencyVN = NumberFormat.getCurrencyInstance(new java.util.Locale("vi", "VN"));
%>
<!DOCTYPE html>
<html>
<head>
  <title>Giỏ Hàng</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
  <style>
    .btn-custom {
      min-width: 80px;
    }
    .table th, .table td {
      vertical-align: middle;
    }
  </style>
</head>
<body class="container my-5">
<h2 class="mb-4">🛒 Giỏ Hàng Của Bạn</h2>

<%
  Cart gioHang = (Cart) request.getAttribute("gioHang");
  if (gioHang != null && !gioHang.getDanhSachSanPham().isEmpty()) {
%>

<table class="table table-bordered table-hover text-center">
  <thead class="table-dark">
  <tr>
    <th>Tên Sản Phẩm</th>
    <th>Giá</th>
    <th style="width: 150px;">Số Lượng</th>
    <th>Thành Tiền</th>
    <th>Hành Động</th>
  </tr>
  </thead>
  <tbody>
  <%
    for (CartItem item : gioHang.getDanhSachSanPham()) {
  %>
  <tr>
    <td><%= item.getPhone().getTen() %></td>
    <td><%= currencyVN.format(item.getPhone().getGia()) %></td>
    <td>
      <form action="<%=request.getContextPath()%>/cart/update" method="get" class="d-flex justify-content-center gap-1">
        <input type="hidden" name="idSanPham" value="<%= item.getIdSanPham() %>">
        <input type="number" name="soLuong" value="<%= item.getSoLuong() %>" min="1" class="form-control" style="width: 60px;">
        <button type="submit" class="btn btn-sm btn-primary btn-custom">Cập nhật</button>
      </form>
    </td>
    <td><%= currencyVN.format(item.getThanhTien()) %></td>
    <td>
      <a href="<%=request.getContextPath()%>/cart/remove?idSanPham=<%= item.getIdSanPham() %>" class="btn btn-sm btn-danger">Xóa</a>
    </td>
  </tr>
  <%
    }
  %>
  <tr class="fw-bold">
    <td colspan="3" class="text-end">Tổng cộng:</td>
    <td><%= currencyVN.format(gioHang.tinhTongTien()) %></td>
    <td>
      <a href="<%=request.getContextPath()%>/cart/clear" class="btn btn-sm btn-outline-danger">Xóa tất cả</a>
    </td>
  </tr>
  </tbody>
</table>

<%
} else {
%>
<div class="alert alert-info">Giỏ hàng trống.</div>
<%
  }
%>

<a href="<%=request.getContextPath()%>/phone" class="btn btn-outline-secondary mt-3">⬅ Tiếp tục mua sắm</a>
</body>
</html>
