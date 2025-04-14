<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.codegym.furama.model.Employee" %>
<jsp:useBean id="employee" type="com.codegym.furama.model.Employee" scope="request" />
<html>
<head>
  <title>Sửa nhân viên</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"/>
</head>
<body>
<div class="container mt-5">
  <h2 class="text-center text-warning mb-4">CHỈNH SỬA NHÂN VIÊN</h2>

  <form action="/employee?action=edit" method="post" class="row g-3">
    <div class="col-md-6">
      <label>Mã NV</label>
      <input type="text" name="id" value="${employee.id}" class="form-control" readonly/>
    </div>
    <div class="col-md-6">
      <label>Họ tên</label>
      <input type="text" name="name" value="${employee.name}" class="form-control" required/>
    </div>
    <div class="col-md-6">
      <label>Ngày sinh</label>
      <input type="date" name="birthday" value="${employee.birthday}" class="form-control" required/>
    </div>
    <div class="col-md-6">
      <label>CMND</label>
      <input type="text" name="idCard" value="${employee.idCard}" class="form-control" required/>
    </div>
    <div class="col-md-6">
      <label>Lương</label>
      <input type="number" step="0.1" name="salary" value="${employee.salary}" class="form-control" required/>
    </div>
    <div class="col-md-6">
      <label>SĐT</label>
      <input type="text" name="phone" value="${employee.phone}" class="form-control" required/>
    </div>
    <div class="col-md-6">
      <label>Email</label>
      <input type="email" name="email" value="${employee.email}" class="form-control" required/>
    </div>
    <div class="col-md-6">
      <label>Địa chỉ</label>
      <input type="text" name="address" value="${employee.address}" class="form-control" required/>
    </div>
    <div class="col-md-4">
      <label>Chức vụ</label>
      <input type="number" name="positionId" value="${employee.positionId}" class="form-control" required/>
    </div>
    <div class="col-md-4">
      <label>Bằng cấp</label>
      <input type="number" name="educationId" value="${employee.educationDegreeId}" class="form-control" required/>
    </div>
    <div class="col-md-4">
      <label>Phòng ban</label>
      <input type="number" name="divisionId" value="${employee.divisionId}" class="form-control" required/>
    </div>

    <div class="col-12 d-flex justify-content-between">
      <button type="submit" class="btn btn-primary">Cập nhật</button>
      <a href="/employee" class="btn btn-secondary">Quay lại</a>
    </div>
  </form>
</div>
</body>
</html>
