// 코스 목록 생성하는 함수
export function createCourseList(courses) {
    const courseListContainser = document.querySelector("courseList-container");
    courseListContainer.innerHTML = ``;

    courses.forEach(course => {
        const courseRow = document.createElement("li");
        courseRow.innerHTML =
            `<div className="userListDiv">
                <label className="">
                    <input type="checkbox" className="usersCheckbox"/>
                </label>
                <div className="courseId">${course.id}</div>
                <div className="adminName">${course.adminName}</div>
                <div className="courseName">${course.courseName}</div>
                <div className="courseTheme">${couse.courseTheme}</div>
                <div className="courseType">${course.courseType}</div>
            </div>`;
        courseListContainer.appendChild(courseRow);
    });
};

// 페이지 번호 생성하는 함수
export function createPagination(pagination) {
    const paginationContainer = document.querySelector(".pagination-container");
    paginationContainer.innerHTML = ""; // 기존 페이지네이션 초기화

    // 이전 페이지 버튼 추가
    if (pagination.prev) {
        const prevLink = document.createElement("a");
        prevLink.classList.add("change-page");
        prevLink.href = `${pagination.startPage - 1}`;
        prevLink.innerHTML = `
            <button class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 iItkLq">
                <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E" 
                     class="PaginationButtonGroup__Icon-x0iffd-2 jVxRns" />
            </button>
        `;
        paginationContainer.appendChild(prevLink);
    }

    // 페이지 번호 버튼 추가
    for (let i = pagination.startPage; i <= pagination.endPage; i++) {
        if (pagination.page !== i) {
            const pageLink = document.createElement("a");
            pageLink.classList.add("change-page");
            pageLink.href = `${i}`;
            pageLink.innerHTML = `<code>${i}</code>`;
            paginationContainer.appendChild(pageLink);
        } else {
            const currentPage = document.createElement("code");
            currentPage.innerText = i;
            paginationContainer.appendChild(currentPage);
        }
    }

    // 다음 페이지 버튼 추가
    if (pagination.next) {
        const nextLink = document.createElement("a");
        nextLink.classList.add("change-page");
        nextLink.href = `${pagination.endPage + 1}`;
        nextLink.innerHTML = `
            <button class="Button-bqxlp0-0 ButtonPage__StyledButton-k07u44-0 iItkLq">
                <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='40' height='40' viewBox='0 0 40 40'%3E %3Cg fill='none' fill-rule='evenodd'%3E %3Cpath fill='none' d='M0 0h40v40H0z'/%3E %3Cpath stroke='%23DDD' stroke-width='1.5' d='M18 16l4 4-4 4'/%3E %3C/g%3E %3C/svg%3E" 
                     class="PaginationButtonGroup__Icon-x0iffd-2 dQqQMu" />
            </button>
        `;
        paginationContainer.appendChild(nextLink);
    }
}

// 검색 폼을 초기화하는 함수
export function initSearchForm(pagination, search) {
    document.querySelector('input[name="page"]').value = pagination.page;
    document.querySelector('select[name="type"]').value = search.type;
    document.querySelector('input[name="keyWord"]').value = search.keyWord;
}