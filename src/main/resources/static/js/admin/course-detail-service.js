//2025.03.10  조승찬
// import {createCourseList} from "./course-list-layout-base.js";
// import {addCourseList, addPagination, initSearchForm} from "./course-list-layout-add.js";

// userListDiv 클릭시 course-list-event.js에서 호출
function fetchCourDetail(id){
    return fetch(`/admin/course-detail`)
        .then(response => response.json())
        .then(data => {
            createCourseDetail(); // 코스 목록 기초 화면
            addCourseDetail(data.course); // 코스목록 추가
        })
        .catch(error => {
            console.error("course list 데이타를 가져오는 중 오류", error);
            throw error;
        });
};
