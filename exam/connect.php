<?php
// Thông tin kết nối MySQL
$servername = "localhost"; // Tên máy chủ MySQL
$username = "root"; // Tên đăng nhập MySQL
$password = ""; // Mật khẩu MySQL
$database = "exam"; // Tên cơ sở dữ liệu MySQL

// Tạo kết nối
$conn = new mysqli($servername, $username, $password, $database);

// Kiểm tra kết nối
if ($conn->connect_error) {
    die("Kết nối không thành công: " . $conn->connect_error);
}

// Thiết lập bảng ký tự cho kết nối
$conn->set_charset("utf8");

// Các lệnh SQL và xử lý dữ liệu sẽ được thực hiện sau đây

// Đóng kết nối sau khi sử dụng
// $conn->close();
?>