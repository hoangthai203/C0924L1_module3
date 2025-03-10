<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Danh sách khách hàng</title>
  <c:import url="../layout/labrary.jsp"/>
</head>
<body>
<c:import url="../layout/header.jsp"/>
<h1>Danh sách khách hàng</h1>
<table>
  <tr>
    <th>STT</th>
    <th>Tên</th>
    <th>Ngày sinh</th>
    <th>Địa chỉ</th>
    <th>Ảnh</th>
  </tr>
  <c:forEach items="${customerList}" var="customer" varStatus="status">
    <tr>
      <td>${status.count}</td>
      <td>${customer.getName()}</td>
      <td>${customer.getBirthday()}</td>
      <td>${customer.getAddress()}</td>
      <td><img src="${customer.getImage()}" alt="Ảnh khách hàng" width="100"></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>