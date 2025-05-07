<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Chỉnh sửa dịch vụ</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body>
<div class="container mt-5">
  <h2 class="text-center text-warning mb-4">CHỈNH SỬA DỊCH VỤ</h2>

  <form action="/facility?action=edit" method="post" class="row g-3">
    <div class="col-md-6">
      <label>ID</label>
      <input type="text" name="id" value="${facility.id}" class="form-control" readonly/>
    </div>
    <div class="col-md-6">
      <label>Tên</label>
      <input type="text" name="name" value="${facility.name}" class="form-control" required/>
    </div>
    <div class="col-md-6">
      <label>Diện tích</label>
      <input type="number" name="area" value="${facility.area}" class="form-control" required/>
    </div>
    <div class="col-md-6">
      <label>Chi phí thuê</label>
      <input type="number" name="cost" value="${facility.cost}" class="form-control" required/>
    </div>
    <div class="col-md-6">
      <label>Số người tối đa</label>
      <input type="number" name="maxPeople" value="${facility.maxPeople}" class="form-control" required/>
    </div>
    <div class="col-md-6">
      <label>Kiểu thuê</label>
      <input type="text" name="rentType" value="${facility.rentType}" class="form-control" required/>
    </div>
    <div class="col-md-12">
      <label>Loại dịch vụ</label>
      <input type="text" name="facilityType" value="${facility.facilityType}" class="form-control" required/>
    </div>

    <div class="col-12 d-flex justify-content-between">
      <button type="submit" class="btn btn-primary">Cập nhật</button>
      <a href="/facility" class="btn btn-secondary">Quay lại</a>
    </div>
  </form>
</div>
</body>
</html>