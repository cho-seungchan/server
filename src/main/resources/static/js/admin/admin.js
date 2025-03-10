
//document.addEventListener("DOMContentLoaded", function () {
    let menuBtn = document.querySelector(".AppLayout_expandNavButton__NTEwM");
    let nav = document.querySelector(".AppNavbarLayout_container__NmY5O");
    let div = document.querySelector(".AppLayout_contents__YmI3N");

    menuBtn.addEventListener("click", function () {
        nav.classList.toggle("active");
    });

    menuBtn.addEventListener("click", function () {
        div.classList.toggle("active");
    });

    menuBtn.addEventListener("click", function () {
        menuBtn.classList.toggle("active");
    });

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

//2025.03.09 조승찬
// 관리자 메인 화면에서 메뉴 선택 시, 해당 메뉴의 기초 화면을 로딩하는 함수
// element가 단일 요소가 아니라 HTMLCollection이므로
// HTMLCollection을 배열로 변환한 후, 각 요소에 addEventListener를 적용
function generateClickedScreens(elementClass, callback) {
    const elements = document.getElementsByClassName(elementClass);

    if (elements.length > 0) {
        Array.from(elements).forEach(element => {
            element.addEventListener('click', callback);
        });
    } else {
        console.warn(`No elements found with class: ${elementClass}`);
    }
}
//});