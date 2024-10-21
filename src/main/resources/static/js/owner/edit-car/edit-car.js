document.addEventListener('DOMContentLoaded', () => {
    let activeTab = 'basicinformation';
    const tabs = document.querySelectorAll('.edit-car-tabs button');

    const setActiveTab = (tab) => {
        activeTab = tab;

        // Cập nhật lớp active
        tabs.forEach(button => {
            button.classList.remove('active'); // Xóa lớp active khỏi tất cả các nút
        });
        const activeButton = Array.from(tabs).find(button => button.dataset.tab === activeTab);
        if (activeButton) {
            activeButton.classList.add('active'); // Thêm lớp active vào nút hiện tại
        }

        // Ẩn/hiện nội dung
        const contents = document.querySelectorAll('.tab-content');
        contents.forEach(content => {
            content.style.display = 'none'; // Ẩn tất cả các nội dung
        });
        document.querySelector(`.${activeTab}`).style.display = 'block'; // Hiện nội dung tương ứng
    };

    // Initial render

    // Gán sự kiện cho các nút
    tabs.forEach(button => {
        button.addEventListener('click', () => {
            setActiveTab(button.dataset.tab);
        });
    });

    // const car = {
    //     imageFront: /*[[${carDTO.imageFront}]]*/ '',
    //     imageBack: /*[[${carDTO.imageBack}]]*/ '',
    //     imageLeft: /*[[${carDTO.imageLeft}]]*/ '',
    //     imageRight: /*[[${carDTO.imageRight}]]*/ '',
    // };
    //
    // // Xử lý sự kiện xóa ảnh
    // document.querySelectorAll('.delete-button').forEach(button => {
    //     button.addEventListener('click', (event) => {
    //         const imageIndex = event.target.dataset.index;
    //         handleDelete(imageIndex);
    //     });
    // });
    //
    // // Xử lý sự kiện chọn file
    // document.querySelectorAll('input[type="file"]').forEach(input => {
    //     input.addEventListener('change', (event) => {
    //         const imageIndex = event.target.dataset.index;
    //         handleFileChange(event, imageIndex);
    //     });
    // });
    //
    // function handleDelete(imageIndex) {
    //     const imageFields = ['imageFront', 'imageBack', 'imageLeft', 'imageRight'];
    //     console.log(imageIndex);
    //
    //     car[imageFields[imageIndex]] = null;
    //     renderImages();
    // }
    //
    // function handleFileChange(event, imageIndex) {
    //     const file = event.target.files[0];
    //     const validExtensions = ['image/jpeg', 'image/jpg', 'image/png', 'image/gif'];
    //
    //     if (file && validExtensions.includes(file.type)) {
    //         const reader = new FileReader();
    //         reader.onloadend = () => {
    //             const imageFields = ['imageFront', 'imageBack', 'imageLeft', 'imageRight'];
    //             car[imageFields[imageIndex]] = reader.result;
    //             renderImages();
    //         };
    //         reader.readAsDataURL(file);
    //     } else {
    //         alert("Please select a valid image file (JPG, JPEG, PNG, GIF).");
    //     }
    // }
    //
    // function renderImages() {
    //     const imageFields = ['imageFront', 'imageBack', 'imageLeft', 'imageRight'];
    //     imageFields.forEach((field, index) => {
    //         const imgElement = document.querySelector(`.img-container-123:nth-child(${index + 1}) img`);
    //         const fileInput = document.querySelector(`input[type="file"][data-index="${index}"]`);
    //
    //         if (car[field]) {
    //             imgElement.src = car[field];
    //             imgElement.parentElement.previousElementSibling.style.display = 'block'; // Hiện phần tử <p>
    //         } else {
    //             imgElement.src = ''; // Xóa hình ảnh
    //             fileInput.value = ''; // Reset file input
    //         }
    //     });
    // }
});
