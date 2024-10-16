// Định nghĩa các biến
const steps = document.querySelectorAll(".step");
const stepContents = document.querySelectorAll(".step-content");
let currentIndex = 0;

// Cập nhật trạng thái bước
function updateSteps() {
    // Cập nhật bước hiển thị
    steps.forEach((step, index) => {
        step.classList.remove("active");
        if (index === currentIndex) {
            step.classList.add("active");
        }
    });

    // Cập nhật nội dung bước hiển thị
    stepContents.forEach((content, index) => {
        content.classList.remove("active");
        if (index === currentIndex) {
            content.classList.add("active");
        }
    });
}

// Xử lý nút Next
document.getElementById("nextBtn").addEventListener("click", function() {
    // Chuyển đến bước tiếp theo nếu chưa ở bước cuối
    if (currentIndex < steps.length - 1) {
        currentIndex++;
        updateSteps();
    } else {
        alert("Bạn đã hoàn thành tất cả các bước!");
    }
});

// Xử lý nút Cancel
document.getElementById("cancelBtn").addEventListener("click", function() {
    // Quay lại bước đầu tiên
    currentIndex = 0;
    updateSteps();
});

// Khởi tạo trạng thái ban đầu
updateSteps();
