
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
//2025.03.10  조승찬
// import {createCourseList} from "./course-list-layout-base.js";
// import {addCourseList, addPagination, initSearchForm} from "./course-list-layout-add.js";

// userListDiv 클릭시 course-list-event.js에서 호출
function fetchCourseTypeDetail(courseType, page, type, keyWord){
    return fetch(`/admin/course-type-detail/${courseType}`)
        .then(response => response.json())
        .then(data => {
            createCourseDetail(); // 코스 목록 기초 화면
            addCourseDetail(data.course, page, type, keyWord); // 코스목록 추가
        })
        .catch(error => {
            console.error("course list 데이타를 가져오는 중 오류", error);
            notFoundCourseType();
            throw error;
        });
};

    // 메인화면 코스 메뉴의 코스 클릭시 함수
// 메뉴가 클릭되었을 때 화면 로드. admin.js에 있는 함수
document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll(".MenuItems_submenu__YTg3N.courseType").forEach(type => {
        type.addEventListener("click", e => {
            console.log(" 타입별 상세 조회 클릭  "+e.target.textContent);
            // 삭제 후 첫 페이지부터 조회 될 수 있도록 page, type, keyWord 초기화 해서 보냄
            fetchCourseTypeDetail(e.target.textContent, 1, ``,``);
        });
    })
});

function notFoundCourseType(){
    const baseContainer = document.querySelector(".AppLayout_contents__YmI3N");
    baseContainer.innerHTML = ``;

    baseContainer.innerHTML = `
    <div class="notFoundCourseType">
        <h1> 공사 중 입니다 😊</h1>
    </div>
    `;
};
// // 페이지가 로드될 때 코스 목록을 자동으로 로드