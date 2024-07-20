<?php
include 'connect.php'; // Kết nối đến MySQL

if (isset($_POST['quantity'])) {
    $quantity = $_POST['quantity'];

    // Lấy danh sách khách hàng có số tiền thuê cao nhất (sắp xếp giảm dần)
    $sql = "SELECT KHACHHANG.MAKH, KHACHHANG.TENKH, SUM(HOADON.TONGTIEN) AS TONGTIEN
    FROM KHACHHANG
    LEFT JOIN HOADON ON KHACHHANG.MAKH = HOADON.MAKH
    GROUP BY KHACHHANG.MAKH, KHACHHANG.TENKH
    ORDER BY TONGTIEN DESC LIMIT $quantity";
    $result = $conn->query($sql);

    $stt = 1;

    // Hiển thị danh sách khách hàng trong bảng
    while ($row = $result->fetch_assoc()) {
        echo "<tr>
                <td>{$stt}</td>
                <td>{$row['MAKH']}</td>
                <td>{$row['TENKH']}</td>
                <td>{$row['TONGTIEN']}</td>
              </tr>";
        $stt++;
    }
}
?>
