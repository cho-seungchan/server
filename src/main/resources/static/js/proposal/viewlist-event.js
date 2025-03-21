const button = document.querySelector(".pay-button")

button.addEventListener("click", () =>{
    if(member != null) {
    document['viewlist-form'].submit();
    } else {
        alert("로그인 후 작성해주세요")
        return "/login/login"
    }
})