const oldPassword = document.querySelector("input[name='oldPassword']");
const newPassword = document.querySelector(".newPassword");
const checkPassword = document.querySelector(".checkPassword");
const passwordText = document.querySelector(".Form__Description-sc-1quypp7-2");
const button = document.querySelector("form>button");
let errorMsg = document.createElement("p");
errorMsg.classList.add("changeComment");

const passwordRegex = /^(?=.*[A-Za-z])(?=.*[\d\W_])[A-Za-z\d\W_]{10,}$/;

newPassword.addEventListener("keyup", () => {
    console.log("새 비밀번호 입력됨:", newPassword.value);

    const passwordText = document.querySelector(".Form__Description-sc-1quypp7-2");



    if (newPassword.value === oldPassword.value && newPassword.value !== "") {
        newPassword.classList.add("errorInput");
        newPassword.classList.remove("iRBMai");
        passwordText.innerText = "기존 비밀번호와 동일한 비밀번호는 사용할 수 없습니다.";
        passwordText.classList.add("bViOzS");
        passwordText.classList.remove("cIOZzg");
        return;
    }

    if (!passwordRegex.test(newPassword.value) && newPassword.value !== "") {
        newPassword.classList.add("errorInput");
        newPassword.classList.remove("iRBMai");
        passwordText.classList.add("bViOzS");
        passwordText.classList.remove("cIOZzg");
        return;
    }

    newPassword.classList.add("iRBMai");
    newPassword.classList.remove("errorInput");
    passwordText.classList.add("cIOZzg");
    passwordText.classList.remove("bViOzS");

    checkPassword.dispatchEvent(new Event("keyup"));
});

checkPassword.addEventListener("keyup", () => {

    if (checkPassword.nextElementSibling && checkPassword.nextElementSibling.classList.contains("changeComment")) {
        checkPassword.nextElementSibling.remove();
    }

    if (checkPassword.value === newPassword.value && checkPassword.value !== "") {
        button.classList.add("changeButton");
        button.classList.remove("chSrfn");
        button.removeAttribute("disabled");
        checkPassword.classList.remove("errorInput");
        checkPassword.classList.add("iRBMai");
        return;
    }

    checkPassword.classList.remove("iRBMai");
    checkPassword.classList.add("errorInput");
    button.classList.add("chSrfn");
    button.classList.remove("changeButton");
    button.setAttribute("disabled", "");

    errorMsg.innerText = "먼저 입력하신 비밀번호와 일치하지 않습니다.";
    checkPassword.after(errorMsg);
});
