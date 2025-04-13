<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Thêm Dịch Vụ</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <div class="card shadow-lg">
        <div class="card-header bg-primary text-white text-center">
            <h2>Thêm Dịch Vụ</h2>
        </div>
        <div class="card-body">
            <form action="dichvu?action=add" method="post">
                <div class="mb-3">
                    <label for="ten" class="form-label">Tên dịch vụ:</label>
                    <input type="text" id="ten" name="ten" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label for="gia" class="form-label">Giá:</label>
                    <input type="number" id="gia" name="gia" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label for="moTa" class="form-label">Mô tả:</label>
                    <textarea id="moTa" name="mo_ta" class="form-control" rows="3" required></textarea>
                </div>

                <div class="mb-3">
                    <label for="trangThai" class="form-label">Trạng thái:</label>
                    <select id="trangThai" name="trangThai" class="form-select">
                        <option value="Hoạt động">Hoạt động</option>
                        <option value="Tạm ngưng">Tạm ngưng</option>
                    </select>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-success">Thêm</button>
                    <a href="dichvu" class="btn btn-secondary">Quay lại danh sách</a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
