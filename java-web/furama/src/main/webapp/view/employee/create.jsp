<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm nhân viên</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center text-success mb-4">THÊM NHÂN VIÊN</h2>

    <form action="/employee?action=create" method="post" class="row g-3">
        <div class="col-md-6">
            <label>Mã NV</label>
            <input type="text" name="id" class="form-control" required/>
        </div>
        <div class="col-md-6">
            <label>Họ tên</label>
            <input type="text" name="name" class="form-control" required/>
        </div>
        <div class="col-md-6">
            <label>Ngày sinh</label>
            <input type="date" name="birthday" class="form-control" required/>
        </div>
        <div class="col-md-6">
            <label>CMND</label>
            <input type="text" name="idCard" class="form-control" required/>
        </div>
        <div class="col-md-6">
            <label>Lương</label>
            <input type="number" step="0.1" name="salary" class="form-control" required/>
        </div>
        <div class="col-md-6">
            <label>SĐT</label>
            <input type="text" name="phone" class="form-control" required/>
        </div>
        <div class="col-md-6">
            <label>Email</label>
            <input type="email" name="email" class="form-control" required/>
        </div>
        <div class="col-md-6">
            <label>Địa chỉ</label>
            <input type="text" name="address" class="form-control" required/>
        </div>
        <div class="col-md-4">
            <label>Chức vụ</label>
            <input type="number" name="positionId" class="form-control" required/>
        </div>
        <div class="col-md-4">
            <label>Bằng cấp</label>
            <input type="number" name="educationId" class="form-control" required/>
        </div>
        <div class="col-md-4">
            <label>Phòng ban</label>
            <input type="number" name="divisionId" class="form-control" required/>
        </div>

        <div class="col-12 d-flex justify-content-between">
            <button type="submit" class="btn btn-primary">Lưu</button>
            <a href="/employee" class="btn btn-secondary">Quay lại</a>
        </div>
    </form>
</div>
</body>
</html>
