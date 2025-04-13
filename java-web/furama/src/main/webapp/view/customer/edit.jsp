<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.codegym.furama.model.Customer" %>
<jsp:useBean id="customer" type="com.codegym.furama.model.Customer" scope="request" />
<html>
<head>
  <title>Sửa khách hàng</title>
</head>
<body>
<h2 style="text-align: center;">CHỈNH SỬA KHÁCH HÀNG</h2>

<form action="/customer?action=edit" method="post" style="width: 500px; margin: auto;">
  Mã khách hàng: <input type="text" name="id" value="${customer.id}" readonly /><br/><br/>
  Loại khách hàng: <input type="number" name="typeId" value="${customer.customerTypeId}" required /><br/><br/>
  Họ tên: <input type="text" name="name" value="${customer.name}" required /><br/><br/>
  Ngày sinh: <input type="date" name="birthday" value="${customer.birthday}" required /><br/><br/>
  Giới tính:
  <input type="radio" name="gender" value="true" ${customer.gender ? "checked" : ""} /> Nam
  <input type="radio" name="gender" value="false" ${!customer.gender ? "checked" : ""} /> Nữ
  <br/><br/>
  CMND: <input type="text" name="idCard" value="${customer.idCard}" required /><br/><br/>
  Số điện thoại: <input type="text" name="phone" value="${customer.phone}" required /><br/><br/>
  Email: <input type="email" name="email" value="${customer.email}" required /><br/><br/>
  Địa chỉ: <input type="text" name="address" value="${customer.address}" required /><br/><br/>

  <button type="submit">Cập nhật</button>
  <a href="/customer">Quay lại danh sách</a>
</form>

</body>
</html>
