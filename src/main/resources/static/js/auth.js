document.getElementById("registration").addEventListener("submit", (event) => {
    const password = document.getElementById("password");
    const confirmPassword = document.getElementById("confirm_password");
    const message = document.getElementById("message");
    let timeoutID;
    if (password.value !== confirm_password.value) {
        event.preventDefault();
        message.style.display = "block";

        new Promise((resolve, reject) => {
            timeoutId = window.setTimeout(() => {
                message.style.display = "none";
                resolve();
            }, 4000)
        }).then(data => window.clearTimeout(timeoutID))

    }
})