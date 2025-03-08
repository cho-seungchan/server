import {createCourseList, createPagination, initSearchForm} from "./course-list-layout";

function fetchCourseList(){
    return fetch('api/course-list?page=${page}&size=${size}&type=${type}&keyword=${keyword}')
        .then(response => response.json())
        .then(data => {
            createCourseList(data.course); // 코스목록 생성
            createPagination(data.pagination);  // 페이지 번호 생성
            initSearchForm(data.pagination, data.search); // 넘겨받은 페이지, 검색조건 유지
        })
        .catch(error => {
            console.error("course list 데이타를 가져오는 중 오류", error);
            throw error;
        });
};

// 페이지가 로드될 때 코스 목록을 자동으로 로드
document.addEventListener('DOMContentLoaded', fetchCourseList);
