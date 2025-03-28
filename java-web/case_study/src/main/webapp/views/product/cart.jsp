<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.codegym.case_study.model.Cart" %>
<%@ page import="com.codegym.case_study.model.CartItem" %>
<%@ page import="java.util.List" %>

<%
  // Lấy giỏ hàng từ session
  Cart gioHang = (Cart) session.getAttribute("gioHang");
  if (gioHang == null) {
    gioHang = new Cart();
  }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Giỏ hàng</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
  <h2 class="mb-4">🛒 Giỏ hàng của bạn</h2>

  <% if (!gioHang.getDanhSachSanPham().isEmpty()) { %>
  <table class="table table-bordered">
    <thead class="table-dark">
    <tr>
      <th>#</th>
      <th>Sản phẩm</th>
      <th>Giá</th>
      <th>Số lượng</th>
      <th>Tổng tiền</th>
      <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <%
      int index = 1;
      for (CartItem item : gioHang.getDanhSachSanPham()) {
    %>
    <tr>
      <td><%= index++ %></td>
      <td><%= item.getPhone().getTen() %></td>
      <td><%= item.getPhone().getGia() %> VND</td>
      <td>
        <form action="cart?action=update" method="post" class="d-flex">
          <input type="hidden" name="id" value="<%= item.getPhone().getId() %>">
          <input type="number" name="quantity" value="<%= item.getSoLuong() %>" class="form-control w-50" min="1">
          <button type="submit" class="btn btn-sm btn-primary ms-2">Cập nhật</button>
        </form>
      </td>
      <td><%= item.getThanhTien() %> VND</td>
      <td>
        <form action="cart?action=remove" method="post">
          <input type="hidden" name="id" value="<%= item.getPhone().getId() %>">
          <button type="submit" class="btn btn-sm btn-danger">Xóa</button>
        </form>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>

  <h4 class="text-end">🛍 Tổng cộng: <strong><%= gioHang.tinhTongTien() %> VND</strong></h4>

  <div class="d-flex justify-content-between">
    <a href="index.jsp" class="btn btn-secondary">⬅ Quay lại mua hàng</a>

    <form action="cart?action=clear" method="post">
      <button type="submit" class="btn btn-warning">🗑 Xóa toàn bộ giỏ hàng</button>
    </form>

    <a href="checkout.jsp" class="btn btn-success">Thanh toán</a>
  </div>

  <% } else { %>
  <p class="alert alert-warning">Giỏ hàng của bạn đang trống!</p>
  <a href="index.jsp" class="btn btn-secondary">⬅ Quay lại mua hàng</a>
  <% } %>

</div>
</body>
</html>
