function navigateToStep(stepNumber) {
    switch(stepNumber) {
        case 1:
            window.location.href = "/addcar1"; // URL tương ứng cho Step 1
            break;
        case 2:
            window.location.href = "/addCar2"; // URL tương ứng cho Step 2
            break;
        case 3:
            window.location.href = "/addCar3"; // URL tương ứng cho Step 3
            break;
        case 4:
            window.location.href = "/addCar4"; // URL tương ứng cho Step 4
            break;
        default:
            console.error("Invalid step number");
    }


    // button js

    document.getElementById('cancelBtn').addEventListener('click', function() {
        // Hành động khi nhấn nút Cancel
        // Ví dụ: Quay lại trang trước
        window.history.back();
    });
    
    document.getElementById('nextBtn').addEventListener('click', function() {
        // Hành động khi nhấn nút Next
        // Ví dụ: Chuyển hướng đến trang tiếp theo
        window.location.href = 'addCar2.html'; // Thay 'nextPage.html' bằng trang bạn muốn chuyển đến
    });
    
}
