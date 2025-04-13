<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Thêm khách hàng</title>
</head>
<body>
<h2 style="text-align: center;">THÊM KHÁCH HÀNG</h2>

<form action="/customer?action=create" method="post" style="width: 500px; margin: auto;">
  Mã khách hàng: <input type="text" name="id" required /><br/><br/>
  Loại khách hàng (1-5): <input type="number" name="typeId" min="1" max="5" required /><br/><br/>
  Họ tên: <input type="text" name="name" required /><br/><br/>
  Ngày sinh: <input type="date" name="birthday" required /><br/><br/>
  Giới tính:
  <input type="radio" name="gender" value="true" checked /> Nam
  <input type="radio" name="gender" value="false" /> Nữ
  <br/><br/>
  CMND: <input type="text" name="idCard" required /><br/><br/>
  Số điện thoại: <input type="text" name="phone" required /><br/><br/>
  Email: <input type="email" name="email" required /><br/><br/>
  Địa chỉ: <input type="text" name="address" required /><br/><br/>

  <button type="submit">Thêm mới</button>
  <a href="/customer">Quay lại danh sách</a>
</form>

</body>
</html>
