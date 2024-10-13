// Lấy tham chiếu đến các form
function navigateToLogin() {
    // Chuyển đến trang đăng nhập
    const loginForm = document.getElementById("loginForm");
    const signupForm = document.getElementById("signupForm");

    loginForm.classList.remove("inactive");
    loginForm.classList.add("active");

    signupForm.classList.remove("active");
    signupForm.classList.add("inactive");
}

function navigateToSignup() {
    // Chuyển đến trang đăng ký
    const loginForm = document.getElementById("loginForm");
    const signupForm = document.getElementById("signupForm");

    signupForm.classList.remove("inactive");
    signupForm.classList.add("active");

    loginForm.classList.remove("active");
    loginForm.classList.add("inactive");
}

function navigateToHome() {
    window.location.href = "/";
}
// Gán sự kiện click cho các form
loginForm.addEventListener("click", navigateToLogin);
signupForm.addEventListener("click", navigateToSignup);
// Đặt trạng thái ban đầu cho form
document.addEventListener("DOMContentLoaded", () => {
    navigateToLogin();
    document
        .getElementById("closeBtn")
        .addEventListener("click", navigateToHome);
});