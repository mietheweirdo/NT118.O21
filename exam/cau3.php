<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đặt Thuê Phòng</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script> <!-- Sử dụng thư viện jQuery -->
</head>

<body>
    Chọn Mã Hóa Đơn:
    <select id="mahd" name="mahd">
        <?php
        include 'connect.php'; // Kết nối đến MySQL

        // Lấy danh sách tên khách hàng từ CSDL
        $sql = "SELECT MAHD FROM HOADON";
        $result = $conn->query($sql);

        // Hiển thị danh sách tên khách hàng trong dropdown
        while ($row = $result->fetch_assoc()) {
            echo "<option value='{$row['MAHD']}'>{$row['MAHD']}</option>";
        }
        ?>
    </select>
    <br>

    Danh sách các phòng còn trống <br>
    <table border="1">
            <tr>
                <th>Mã Phòng</th>
                <th>Tên Phòng</th>
                <th>Loại Phòng</th>
                <th>Thêm</th>
            </tr>
        <tbody id="phongtrong">
            <!-- Danh sách phòng còn trống sẽ được liệt kê ở đây -->
        </tbody>
    </table>
    <br>

    Danh Sách Các Phòng Đã Thêm <br>
    <table border="1">
        <thead>
            <tr>
                <th>Mã Phòng</th>
                <th>Tên Phòng</th>
                <th>Loại Phòng</th>
                <th>Xóa</th>
            </tr>
        </thead>
        <tbody id="phongdathem">
            <!-- Danh sách phòng đã thêm sẽ được liệt kê ở đây -->
        </tbody>
    </table>

</body>

</html>

<script>
    $(document).ready(function() {
        // Bắt sự kiện khi giá trị của selectbox mahd thay đổi
        $("#mahd").on("change", function() {
            var mahd = $("#mahd").val();
            $.get("bangphongcontrong.php", {
                mahd:mahd,
            }, function(data) {
                $("#phongtrong").html(data);
            });
            $.post("bangphongdathem.php", {
                mahd: mahd
            }, function(data) {
                $("#phongdathem").html(data);
            });
        });
    });

    $(document).on("click", ".xoabtn", function() {
        var mahd = $("#mahd").val();
        var maphong = $(this).val();
        $.post("xoaphong.php", {
            mahd:mahd,
            maphong:maphong
        }, function(data) {});
        $(this).parent().parent().remove();
        $.get("bangphongcontrong.php", {
                mahd:mahd,
            }, function(data) {
                $("#phongtrong").html(data);
            });
    });

    // Bắt sự kiện click trên nút Thêm trong bảng "Danh sách các phòng còn trống"
    $(document).on("click", ".thembtn", function() {
        var mahd = $("#mahd").val();
        var maphong = $(this).val();
        $.post("themphong.php", {
            mahd:mahd,
            maphong:maphong
        }, function(data) {});
        $(this).parent().parent().remove();
        $.post("bangphongdathem.php", {
                mahd: mahd
            }, function(data) {
                $("#phongdathem").html(data);
            });
    });
</script>