<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Hóa Đơn</title>
</head>

<body>
    <form method="post">
        Tên Khách Hàng:
        <select name="tenkh" required>
            <?php
            include 'connect.php'; // Kết nối đến MySQL

            // Lấy danh sách tên khách hàng từ CSDL
            $sql = "SELECT MAKH, TENKH FROM KHACHHANG";
            $result = $conn->query($sql);

            // Hiển thị danh sách tên khách hàng trong dropdown
            while ($row = $result->fetch_assoc()) {
                echo "<option value='{$row['MAKH']}'>{$row['TENKH']}</option>";
            }
            ?>
        </select><br>
        Mã Hóa Đơn:
        <input type="text" name="mahd" required><br>
        Tên Hóa Đơn:
        <input type="text" name="tenhd" required><br>
        Tổng Tiền:
        <input type="text" name="tongtien" required><br>

        <input type="submit" value="Thêm" name="thembtn">
    </form>
</body>

</html>

<?php
include 'connect.php'; // Kết nối đến MySQL

if (isset($_POST['thembtn'])) {
    // Lấy thông tin từ form
    $mahd = $_POST['mahd'];
    $tenhd = $_POST['tenhd'];
    $makh = $_POST['tenkh'];
    $tongtien = $_POST['tongtien'];

    // SQL để thêm hóa đơn mới vào bảng HOADON
    $sql = "INSERT INTO HOADON (MAHD, TENHD, MAKH, TONGTIEN) VALUES ('$mahd','$tenhd', '$makh', '$tongtien')";

    if ($conn->query($sql) === TRUE) {
        echo "Thêm hóa đơn thành công!";
    } else {
        echo "Lỗi: " . $sql . "<br>" . $conn->error;
    }
};
?>