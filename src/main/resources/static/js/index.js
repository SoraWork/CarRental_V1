document.addEventListener('DOMContentLoaded', function () {
    const menuToggle = document.getElementById("show-menu");
    const header = document.querySelector('header');
    const menu = document.querySelector('.mini-action-123'); // Thêm menu vào đây
    if (menuToggle && header && menu) {
        menuToggle.addEventListener('click', function (event) {
            // event.stopPropagation();
            menu.classList.toggle('show-menu');
            document.body.classList.toggle('overlay-active');
        });

        document.addEventListener('click', function (event) {
            if (!menu.contains(event.target) && !menuToggle.contains(event.target)) {
                menu.classList.remove('show-menu');
                document.body.classList.remove('overlay-active');
            }
        });

    }
});