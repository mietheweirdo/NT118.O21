<?php
include 'connect.php';
$mahd = $_GET['mahd'];
$sqlPhongTrong = "SELECT * FROM PHONG WHERE TINHTRANG = \"Trong\"";
$resultPhongTrong = $conn->query($sqlPhongTrong);

$phongTrongHTML = '';
while ($row = $resultPhongTrong->fetch_assoc()) {
    $phongTrongHTML .= "<tr>
    <td>{$row['MAPHONG']}</td>
    <td>{$row['TENPHONG']}</td>
    <td>{$row['LOAIPHONG']}</td>
    <td><button class='thembtn' value={$row['MAPHONG']}>Thêm</button></td>
    </tr>";
}

// Trả về dữ liệu dưới dạng HTML
echo $phongTrongHTML;
