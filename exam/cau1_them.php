<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <form method="post">
        Mã khách hàng <input type="text" name="makh" placeholder="Mã khách hàng"> <br>
        Tên khách hàng <input type="text" name="tenkh" placeholder="Tên khách hàng"><br>
        Số điện thoại <input type="text" name="sdt" placeholder="Số điện thoại"><br>
        Căn cước công dân <input type="text" name="cccn" placeholder="Căn cước công dân"><br>
        <input type="submit" value="Thêm" name="thembtn">
    </form>
</body>

</html>

<?php
include "connect.php";
if (isset($_POST['thembtn'])) {
    $makh = $_POST['makh'];
    $tenkh = $_POST['tenkh'];
    $sdt = $_POST['sdt'];
    $cccd = $_POST['cccn'];
    $sql = "insert into khachhang(makh,tenkh,cccn,sdt) values('$makh','$tenkh','$cccd','$sdt')";
    $conn->query($sql);
};
?>