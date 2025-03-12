<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Product Form</title></head>
<body>
<h2>${product == null ? "Add Product" : "Edit Product"}</h2>
<form action="/products" method="post">
    <input type="hidden" name="id" value="${product.id}" />
    Name: <input type="text" name="name" value="${product.name}" /><br/>
    Price: <input type="text" name="price" value="${product.price}" /><br/>
    Description: <input type="text" name="description" value="${product.description}" /><br/>
    Manufacturer: <input type="text" name="manufacturer" value="${product.manufacturer}" /><br/>
    <input type="submit" value="${product == null ? "Add" : "Update"}" />
</form>
</body>
</html>