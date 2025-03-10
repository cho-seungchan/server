//2025.03.08 조승찬
// 코스 목록 생성하는 함수
function addCourseList(course) {
    const courseListContainer = document.querySelector(".courseList-container");
    courseListContainer.innerHTML = ``;

    courses.forEach(course => {
        const courseRow = document.createElement("li");
        const courseType = course.courseType !== null ? course.courseType : '';
        courseRow.innerHTML =
            `<div class="userListDiv">
                <label class="">
                    <input type="radio" name="courseSelection" class="usersRadio"/>
                </label>
                <div class="courseIdDiv">${course.id}</div>
                <div class="courseadminNameDiv">${course.adminName}</div>
                <div class="courseNameDiv">${course.courseName}</div>
                <div class="courseThemeDiv">${course.courseTheme}</div>
                <div class="courseTypeDiv">${courseType}</div>
            </div>`;

        courseListContainer.appendChild(courseRow);
    });
};
