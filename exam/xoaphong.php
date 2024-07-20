<?php
include 'connect.php';
$maphong = $_POST['maphong'];
$mahd = $_POST['mahd'];

// Fetch the value of GIATHUE
// $str1 = "SELECT GIATHUE FROM THUE WHERE maphong='$maphong' AND mahd = '$mahd'";
// $rs1 = $conn->query($str1);
// if ($rs1->num_rows > 0) {
//     //Fetch the row from the result set
//     $row = $rs1->fetch_assoc();
//     //Xử lý tiền nếu đề bài yêu cầu
//     $giathue = (float)$row['GIATHUE'];

//     $str4 = "UPDATE HOADON SET TONGTIEN = TONGTIEN - $giathue WHERE mahd = '$mahd'";
//     $rs4 = $conn->query($str4);
// }
// Perform other operations using $giathue
$str2 = "DELETE FROM thue WHERE maphong='$maphong' AND mahd = '$mahd'";
$rs2 = $conn->query($str2);

$str3 = "UPDATE PHONG SET TINHTRANG = 'Trong' WHERE maphong='$maphong'";
$rs3 = $conn->query($str3);
