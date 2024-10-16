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
}
