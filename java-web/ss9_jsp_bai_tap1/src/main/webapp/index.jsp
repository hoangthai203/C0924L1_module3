<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Discount Calculator</title>
</head>
<body>
<h2>Product Discount Calculator</h2>
<form action="discount" method="post">
    <label>Product Description:</label>
    <input type="text" name="description" required><br><br>

    <label>List Price:</label>
    <input type="number" name="listPrice" required step="0.01"><br><br>

    <label>Discount Percent (%):</label>
    <input type="number" name="discountPercent" required step="0.01"><br><br>

    <input type="submit" value="Calculate Discount">
</form>
</body>
</html>
