<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Danh sách sản phẩm</title>
    <c:import url="../layout/labrary.jsp"/>
</head>
<body>
<c:import url="../layout/header.jsp"/>
<h1>Danh sách sản phẩm</h1>
<a href="/products?action=create" class="btn btn-success">Thêm mới</a>
<h3 style="color: red">${param.mess}</h3>

<!-- Form tìm kiếm -->
<form action="/products" method="get" class="d-flex justify-content-end mb-3">
    <input type="hidden" name="action" value="search">
    <input type="text" value="${param.name}" class="form-control form-control-sm w-25" name="name" placeholder="Nhập tên cần tìm kiếm">
    <button type="submit" class="btn btn-primary btn-sm ms-2">Tìm kiếm</button>
</form>

<!-- Bảng danh sách sản phẩm -->
<table class="table table-dark">
    <tr>
        <th>STT</th>
        <th>Tên sản phẩm</th>
        <th>Giá</th>
        <th>Mô tả</th>
        <th>Nhà sản xuất</th>
        <th>Sửa</th>
        <th>Xoá</th>
    </tr>
    <c:forEach items="${productList}" var="product" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td>${product.getName()}</td>
            <td>${product.getPrice()}</td>
            <td>${product.getDescription()}</td>
            <td>${product.getManufacturer()}</td>
            <td>
                <a href="/products?action=edit&id=${product.getId()}" class="btn btn-warning btn-sm">Sửa</a>
            </td>
            <td>
                <button onclick="thongTinXoa('${product.getId()}', '${product.getName()}')" type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    Xoá
                </button>
            </td>
        </tr>
    </c:forEach>
</table>

<!-- Hiển thị thông báo nếu không có kết quả tìm kiếm -->
<c:if test="${empty productList}">
    <p class="text-center text-danger">Không tìm thấy sản phẩm nào!</p>
</c:if>

<!-- Modal Xoá-->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/products" method="get">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Xác nhận xoá</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" id="deleteId" name="deleteId">
                    <span>Bạn có muốn xoá sản phẩm có tên </span><span id="deleteName"></span>?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Huỷ</button>
                    <button type="submit" class="btn btn-primary">Xoá</button>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    function thongTinXoa(id, name) {
        document.getElementById("deleteId").value = id;
        document.getElementById("deleteName").innerText = name;
    }
</script>
</body>
</html>
