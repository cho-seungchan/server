let menuBtn = document.querySelector(".AppLayout_expandNavButton__NTEwM");
let nav = document.querySelector(".AppNavbarLayout_container__NmY5O");

menuBtn.addEventListener("click", function () {
    nav.classList.toggle("active");
});

let div = document.querySelector(".AppLayout_contents__YmI3N");
menuBtn.addEventListener("click", function () {
    div.classList.toggle("active");
});

menuBtn.addEventListener("click", function () {
    menuBtn.classList.toggle("active");
});


document.addEventListener("DOMContentLoaded", function () {
    // 2025.03.03 조승찬 추가
    // 클릭시 글자 크기 농도 변하도록 설정
    document.querySelectorAll(".MenuItems_submenu__YTg3N").forEach((menus) => {
        menus.addEventListener("click", menu => {
            document.querySelectorAll(".MenuItems_submenu__YTg3N").forEach(e=>{
                e.classList.remove("checked");
            })
            menu.target.classList.add("checked");
        })
    })
    // 2025.03.03 조승찬 추가

});