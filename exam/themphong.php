<?php 
include 'connect.php';
$maphong = $_POST['maphong'];
$mahd = $_POST['mahd'];
$sql1 = "INSERT INTO THUE (mahd, maphong) VALUES ('$mahd','$maphong')";
$rs1 = $conn->query($sql1);
$str3 = "UPDATE PHONG SET TINHTRANG = 'Co Khach' WHERE maphong='$maphong'";
$rs3 = $conn->query($str3);
?>