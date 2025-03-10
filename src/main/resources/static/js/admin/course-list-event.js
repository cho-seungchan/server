// 2025.03.08 조승찬

document.addEventListener("DOMContentLoaded", function () {

    let courseType = null;
    let courseId = null;
    document.body.addEventListener("click", e => {
        console.log(e.className+" "+e.target.className);

        const pageLink = e.target.closest(".change-page");
        if (pageLink) {   // 페이지 이동 처리
            e.preventDefault(); // 기본 이벤트 막기

            // const pageValue = pageLink.getAttribute("href");
            const pageValue = pageLink.dataset.page;
            const typeValue = document.querySelector('select[name="type"]').value;
            const keyWordValue = document.querySelector('input[name="keyWord"]').value;
            fetchCourseList(pageValue, typeValue, keyWordValue);
        }

        if (e.target.className == "selectCourseBtn"){
            const pageValue = document.querySelector('input[name="page"]').value;
            const typeValue = document.querySelector('select[name="type"]').value;
            const keyWordValue = document.querySelector('input[name="keyWord"]').value;
            // 전송할 데이타 json 형태로 변경
            const sendData = {
                courseId: courseId,
                courseType: courseType
            };
            sendCourseOptionAndFetchCourseList(sendData, pageValue, typeValue, keyWordValue);
        }

        if (e.target.classList.contains("fa-search")){
            const pageValue = 1;   // 검색 조회시는 1페이지부터 조회
            const typeValue = document.querySelector('select[name="type"]').value;
            const keyWordValue = document.querySelector('input[name="keyWord"]').value;
            fetchCourseList(pageValue, typeValue, keyWordValue);
        }

    });

    document.body.addEventListener("change", e => {
        if (e.target.classList.contains("selectCourseOpt")) { // 코스 선택 드롭다운 메뉴에서 선택한 값 유지
            courseType = e.target.value.trim();
            console.log("선택된 값 변경됨:", courseType);
        }

        if (e.target.classList.contains("usersRadio")) { // 체크된 리스트의 코스 아이디 저장
            const radio = e.target;

            // 선택된 라디오 버튼의 부모 요소 탐색
            const userListDiv = radio.closest(".userListDiv");
            if (userListDiv) {
                courseId = userListDiv.querySelector(".courseIdDiv").textContent.trim();
                console.log("선택된 course.id:", courseId);
            }
        }
    });
});
