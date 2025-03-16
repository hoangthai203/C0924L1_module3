<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quản lý sản phẩm</title>
    <c:import url="../layout/labrary.jsp"/>
    <style>
        input, button, select {
            margin-top: 10px;
        }

        .form {
            margin-top: 20px;
            width: 300px;
            background-color: lightgrey;
            margin-left: 500px;
            display: flex;
            justify-content: center;
            padding-bottom: 20px;
        }

        h1 {
            text-align: center;
        }

        button {
            background-color: green;
            margin-left: 50px;
        }

        .error {
            color: red;
            text-align: center;
        }
    </style>
</head>
<body>
<c:import url="../layout/header.jsp"/>

<form class="form" action="/products?action=create" method="post">
    <div>
        <h3>${product == null ? "Thêm mới sản phẩm" : "Chỉnh sửa sản phẩm"}</h3>

<%--        <input type="number" name="id" value="${product.id}" placeholder="Nhập ID" required /><br>--%>
        <input type="text" name="name" value="${product.name}" placeholder="Nhập tên sản phẩm" required /><br>
        <input type="number" step="0.01" name="price" value="${product.price}" placeholder="Nhập giá"><br>
        <input type="text" name="description" value="${product.description}" placeholder="Nhập mô tả"><br>
        <input type="text" name="manufacturer" value="${product.manufacturer}" placeholder="Nhập nhà sản xuất"><br>

        <button type="submit">${product == null ? "Thêm mới" : "Cập nhật"}</button>

        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>
    </div>
</form>

</body>
</html>
