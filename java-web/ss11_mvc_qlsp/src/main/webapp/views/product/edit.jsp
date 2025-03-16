<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Chỉnh sửa sản phẩm</title>
  <c:import url="../layout/labrary.jsp"/>
</head>
<body>
<c:import url="../layout/header.jsp"/>
<h1>Chỉnh sửa sản phẩm</h1>
<form action="/products?action=update" method="post">
  <input type="hidden" name="id" value="${product.id}">
  <label for="name">Tên sản phẩm:</label>
  <input type="text" id="name" name="name" value="${product.name}" required><br>

  <label for="price">Giá:</label>
  <input type="text" id="price" name="price" value="${product.price}" required><br>

  <label for="description">Mô tả:</label>
  <input type="text" id="description" name="description" value="${product.description}"><br>

  <label for="manufacturer">Nhà sản xuất:</label>
  <input type="text" id="manufacturer" name="manufacturer" value="${product.manufacturer}"><br>

  <button type="submit">Cập nhật</button>
</form>

</body>
</html>
