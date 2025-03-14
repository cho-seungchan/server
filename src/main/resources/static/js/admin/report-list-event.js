// 2025.03.14 조승찬

document.addEventListener("DOMContentLoaded", function () {

    document.body.addEventListener("click", e => {

        // 페이지 번호 클릭시 해당 페이지 조회 요청
        const pageLink = e.target.closest(".report-change-page");
        if (pageLink) {   // 페이지 이동 처리
            console.log("페이지 이동요청 들어옴");
            e.preventDefault(); // 기본 이벤트 막기

            // const pageValue = pageLink.getAttribute("href");
            const pageValue = pageLink.dataset.page;
            const typeValue = document.querySelector('select[name="type"]').value;
            const keyWordValue = document.querySelector('input[name="keyWord"]').value;
            const isAct = document.querySelector('input[name="isAct"]:checked').parentElement.textContent.trim();
            fetchReportList(pageValue, '', keyWordValue, isAct); // type 값은 오류방지를 위해 빈문자열로 보냄
        }


        // 검색 클릭시 검색 요청
        if (e.target.classList.contains("fa-reportSearch")) {

            e.preventDefault(); // 기본 이벤트 막기
            const pageValue = 1;   // 검색 조회시는 1페이지부터 조회
            const typeValue = document.querySelector('select[name="type"]').value;
            const keyWordValue = document.querySelector('input[name="keyWord"]').value;
            const isAct = document.querySelector('input[name="isAct"]:checked').parentElement.textContent.trim(); // 전체, 피드, 댓글
            console.log("검색 전송시 isAct  "+isAct);
            fetchReportList(pageValue, ``,keyWordValue, isAct);  // type 값은 오류방지를 위해 빈문자열로 보냄
        }

    });

    document.body.addEventListener("change", e => {
    });
});
