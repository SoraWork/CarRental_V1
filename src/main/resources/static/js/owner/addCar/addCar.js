document.addEventListener('DOMContentLoaded', function() {
    // Lấy các nút
    const cancelButtons = document.querySelectorAll('#cancelBtn'); // Lấy tất cả các nút Cancel
    const nextButtons = document.querySelectorAll('#nextBtn'); // Lấy tất cả các nút Next
    const messageDiv = document.getElementById('message');

    // Nút Cancel quay về bước đầu tiên
    cancelButtons.forEach((button) => {
        button.addEventListener('click', function() {
            // Ẩn tất cả các bước
            const allSteps = document.querySelectorAll('.tab-pane');
            allSteps.forEach(step => {
                step.classList.remove('show', 'active', 'active-step', 'inactive-step');
            });

            // Hiện bước đầu tiên
            const firstStep = document.getElementById('step1');
            firstStep.classList.add('show', 'active', 'active-step');

            // Cập nhật thông điệp
            messageDiv.textContent = 'Bạn đã quay lại Bước 1!';

            // Reset màu cho tất cả nav-link
            resetNavLinks();
        });
    });

    // Nút Next chuyển đến bước tiếp theo
    nextButtons.forEach((button) => {
        button.addEventListener('click', function() {
            const currentStep = document.querySelector('.tab-pane.active');
            const nextStep = currentStep.nextElementSibling; // Lấy bước tiếp theo

            if (nextStep) { // Kiểm tra xem có bước tiếp theo không
                currentStep.classList.remove('show', 'active', 'active-step'); // Ẩn bước hiện tại
                nextStep.classList.add('show', 'active', 'active-step'); // Hiện bước tiếp theo

                // Cập nhật thông điệp
                const stepNumber = nextStep.id.replace('step', ''); // Lấy số bước từ ID
                messageDiv.textContent = `Bạn đã chuyển đến Bước ${stepNumber}!`;

                // Cập nhật màu cho nav-link
                updateNavLinks(currentStep.id, nextStep.id);
            }

            // Thêm lớp inactive-step cho các bước không hoạt động
            const allSteps = document.querySelectorAll('.tab-pane');
            allSteps.forEach(step => {
                if (!step.classList.contains('active')) {
                    step.classList.add('inactive-step');
                }
            });
        });
    });

    // Cập nhật màu cho các nav-link
    function updateNavLinks(currentStepId, nextStepId) {
        const currentNavLink = document.querySelector(`a[href="#${currentStepId}"]`);
        const nextNavLink = document.querySelector(`a[href="#${nextStepId}"]`);
        
        // Đánh dấu bước hiện tại là đã hoàn thành
        if (currentNavLink) {
            currentNavLink.classList.remove('active'); // Xóa active khỏi bước hiện tại
            currentNavLink.classList.add('completed-step'); // Thêm lớp hoàn thành
        }

        // Đánh dấu bước tiếp theo là bước hiện tại
        if (nextNavLink) {
            nextNavLink.classList.add('active'); // Thêm active cho bước tiếp theo
        }

        // Đặt lại màu cho các nav-link không phải là bước hiện tại hoặc đã hoàn thành
        const allNavLinks = document.querySelectorAll('.nav-link');
        allNavLinks.forEach(navLink => {
            if (navLink !== nextNavLink && navLink !== currentNavLink) {
                navLink.classList.remove('active', 'completed-step'); // Xóa lớp active và completed
            }
        });
    }

    // Reset màu cho tất cả nav-link khi quay lại bước đầu tiên
    function resetNavLinks() {
        const allNavLinks = document.querySelectorAll('.nav-link');
        allNavLinks.forEach(navLink => {
            navLink.classList.remove('active', 'completed-step'); // Xóa lớp active và completed
        });

        // Đánh dấu bước 1 là bước hiện tại
        const firstNavLink = document.querySelector('a[href="#step1"]');
        if (firstNavLink) {
            firstNavLink.classList.add('active');
        }
    }
});
