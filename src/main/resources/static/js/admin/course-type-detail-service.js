//2025.03.10  조승찬
// import {createCourseList} from "./course-list-layout-base.js";
// import {addCourseList, addPagination, initSearchForm} from "./course-list-layout-add.js";

// userListDiv 클릭시 course-list-event.js에서 호출
function fetchCourseTypeDetail(courseType){
    return fetch(`/admin/course-type-detail/${courseType}`)
        .then(response => response.json())
        .then(data => {
            createCourseDetail(); // 코스 목록 기초 화면
            addCourseDetail(data.course); // 코스목록 추가
        })
        .catch(error => {
            console.error("course list 데이타를 가져오는 중 오류", error);
            notFoundCourseType();
            throw error;
        });
};

// 메뉴가 클릭되었을 때 화면 로드. admin.js에 있는 함수
document.addEventListener("DOMContentLoaded", function() {
    document.querySelectorAll(".MenuItems_submenu__YTg3N.courseType").forEach(type => {
        type.addEventListener("click", e => {
            console.log(" 타입별 상세 조회 클릭  "+e.target.textContent);
            fetchCourseTypeDetail(e.target.textContent);
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