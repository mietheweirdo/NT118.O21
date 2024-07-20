<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách khách hàng</title>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>

<body>
    Số lượng khách hàng:
    <input type="number" id="quantityInput" name="quantityInput">

    <table border="1">
        <thead>
            <tr>
                <th>STT</th>
                <th>Mã Khách Hàng</th>
                <th>Tên Khách Hàng</th>
                <th>Tổng Tiền Thuê</th>
            </tr>
        </thead>
        <tbody id="customerList">
            <!-- Danh sách khách hàng sẽ được hiển thị ở đây -->
        </tbody>
    </table>
</body>
</html>

<script>
        $(document).ready(function() {
            // Bắt sự kiện khi người dùng nhấn phím "Enter" trong textfield
            $("#quantityInput").on("keydown", function(event) {
                if (event.key === "Enter") {
                    var quantity = $(this).val();

                    // Gửi yêu cầu AJAX để lấy danh sách khách hàng
                    $.post("xulycau4.php", {
                        quantity: quantity
                    }, function(data) {
                        // Cập nhật nội dung của bảng khách hàng
                        $("#customerList").html(data);
                    });
                }
            });
        });
    </script>