<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Product List</title></head>
<body>
<h2>Product List</h2>
<a href="/products?action=create">Add New Product</a>
<table border="1">
    <tr><th>ID</th><th>Name</th><th>Price</th><th>Description</th><th>Manufacturer</th><th>Actions</th></tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.description}</td>
            <td>${product.manufacturer}</td>
            <td>
                <a href="/products?action=edit&id=${product.id}">Edit</a>
                <a href="/products?action=delete&id=${product.id}" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>