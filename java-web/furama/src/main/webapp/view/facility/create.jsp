<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Thêm mới dịch vụ</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body>
<div class="container mt-5">
  <h2 class="text-center text-success mb-4">THÊM MỚI DỊCH VỤ</h2>

  <form action="/facility?action=create" method="post" class="row g-3">
    <div class="col-md-6">
      <label>Tên</label>
      <input type="text" name="name" class="form-control" required/>
    </div>
    <div class="col-md-6">
      <label>Diện tích</label>
      <input type="number" name="area" class="form-control" required/>
    </div>
    <div class="col-md-6">
      <label>Chi phí thuê</label>
      <input type="number" name="cost" class="form-control" required/>
    </div>
    <div class="col-md-6">
      <label>Số người tối đa</label>
      <input type="number" name="maxPeople" class="form-control" required/>
    </div>
    <div class="col-md-6">
      <label>Kiểu thuê</label>
      <input type="text" name="rentType" class="form-control" required/>
    </div>
    <div class="col-md-6">
      <label>Loại dịch vụ</label>
      <input type="text" name="facilityType" class="form-control" required/>
    </div>

    <div class="col-12 d-flex justify-content-between">
      <button type="submit" class="btn btn-primary">Lưu</button>
      <a href="/facility" class="btn btn-secondary">Quay lại</a>
    </div>
  </form>
</div>
</body>
</html>