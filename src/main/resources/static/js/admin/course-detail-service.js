//2025.03.10  조승찬
// import {createCourseList} from "./course-list-layout-base.js";
// import {addCourseList, addPagination, initSearchForm} from "./course-list-layout-add.js";

// userListDiv 클릭시 course-list-event.js에서 호출
function fetchCourseDetail(id){
    return fetch(`/admin/course-detail/${id}`)
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

function updateCourseDetail(sendData) {
    return fetch(`/admin/course-detail`, {
        method: "PUT",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify(sendData)
    })
    .then(response => {
        if (!response.ok){
            console.log("course 데이타를 수정하는 중 오류", error);
            return;
        }
        fetchCourseDetail(sendData.id)  // 수정 완료시 조회해서 다시 업데이트 화면으로
    })
        .catch(error => {
            console.error("course 데이타 수정을 요청하는 중 오류", error);
            throw error;
        });
}

function deleteCourseDetail(id, page, type, keyWord) {
    return fetch(`/admin/course-detail/${id}`, {
        method: "DELETE",
        headers: {"Content-type": "application/json"},
        body: JSON.stringify({ id: id })
    })
        .then(response => {
            if (!response.ok){
                console.error("course 데이타를 삭제하는 중 오류", error);
                throw error;
            }
            fetchCourseList(page, type, keyWord)  // 삭제 완료시 리스트 화면으로 .....
        })
        .catch(error => {
            console.error("course 데이타 삭제를 요청하는 중 오류", error);
            throw error;
        });
}