<?php
include 'connect.php'; // Kết nối đến MySQL

    $selectedMAHD = $_POST['mahd'];
    // Xử lý yêu cầu AJAX dựa vào giá trị của action
    // Lấy danh sách phòng đã thêm từ CSDL dựa vào MAHD
    $sqlPhongDaThem = "SELECT * FROM PHONG WHERE MAPHONG IN (SELECT MAPHONG FROM THUE WHERE MAHD = '$selectedMAHD')";
    $resultPhongDaThem = $conn->query($sqlPhongDaThem);

    $phongDaThemHTML = '';
    while ($row = $resultPhongDaThem->fetch_assoc()) {
        $phongDaThemHTML .= "<tr>
        <td>{$row['MAPHONG']}</td>
        <td>{$row['TENPHONG']}</td>
        <td>{$row['LOAIPHONG']}</td>
        <td><button value={$row['MAPHONG']} class='xoabtn')'>Xóa</button></td>
        </tr>";
    }

    // Trả về dữ liệu dưới dạng HTML
    echo $phongDaThemHTML;
