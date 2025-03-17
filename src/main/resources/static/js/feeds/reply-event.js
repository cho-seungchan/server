


// 동적 생성때문에 최상위 요소로 위임하도록 모든 이벤트들 수정  25.03.16 조승찬 추가
document.addEventListener("DOMContentLoaded", function () {

    // input 창에 글자를 넣으면 등록 버튼 활성화
    document.body.addEventListener("input", e => {
        if (e.target.classList.contains("oflaA")) {
            if (e.target.value.trim().length > 0) {
                document
                    .querySelector(".CurrentTextAreaSection__SubmitButton-sc-1l5sqeb-5")
                    .classList.add("hMkcjB");
                document
                    .querySelector(".CurrentTextAreaSection__SubmitButton-sc-1l5sqeb-5")
                    .classList.remove("FEhZA");
                document.querySelector(
                    ".CurrentTextAreaSection__SubmitButton-sc-1l5sqeb-5"
                ).style.cursor = "pointer";
            } else {
                document
                    .querySelector(".CurrentTextAreaSection__SubmitButton-sc-1l5sqeb-5")
                    .classList.remove("hMkcjB");
                document
                    .querySelector(".CurrentTextAreaSection__SubmitButton-sc-1l5sqeb-5")
                    .classList.add("FEhZA");
                document.querySelector(
                    ".CurrentTextAreaSection__SubmitButton-sc-1l5sqeb-5"
                ).style.cursor = "not-allowed";
            }
        }
    })


    document.body.addEventListener("click", e => {

        // 댓글 삭제 버튼 클릭시 이벤트
        if (e.target.classList.contains("kosXvh")){
            if (e.target.textContent.trim() == "삭제"){
                const userConfirmed = confirm("정말 삭제 하시겠습니까?");
                if (userConfirmed) {
                    const  id = e.target.closest(".lcexfU").querySelector(".replyIdDiv").textContent.trim();
                    const feedId = document.querySelector(".replyFeedId").value;
                    // alert("삭제합니다 "+id+" "+feedId)
                    deleteReplyList(id, feedId);  // 삭제하고 처음부터 조회 레스트컨트롤러 함수
                }
            } else if (e.target.textContent.trim() == "신고") {
                const  id = e.target.closest(".lcexfU").querySelector(".replyIdDiv").textContent.trim();
                reportModalCreate(id)
            }
        }


        // 더보기 버튼 클릭시 json 형태로 처리 25.03.16 조승찬 추가 시작
        if (e.target.className == "moreReplyList") {
            const feedId = document.querySelector(".replyFeedId").value;
            const page = e.target.value; // 다음 조회할 페이지 설정
            console.log(" 더보기 클릭 "+feedId+" "+page);
            fetchReplyListApi(feedId, page);
        }

        // 모달창 x 버튼 클릭시 모달 삭제
        if (e.target.className == "closeReplyReportModal") {
            // e.target.classList.remove("clicked");
            document.querySelector(".reply-report-modal-body").innerHTML = ``;
            document.querySelector(".reply-report-modal-body").style.display = "none";
        }
        // 25.03.16 조승찬 추가 끝

        // 신고 확인 버튼 클릭 시
        if (e.target.className == "reply-reportConfirmBtn") {
            // 신고 json data 생성
            const sendData = {
                // 모달창 생성될 때 div로 만들어 놓은 곳에서 id 가져오기
                id : document.querySelector(".reply-report-modal-body").querySelector(".replyIdDiv").textContent.trim(), // 댓글 아이디
                reportedReason : document.querySelector(".reply-report-modal-body").querySelector(".reply-reportModal-ContentInput").value.trim(), // 신고 내용
                feedId : document.querySelector(".replyFeedId").value
            }
            reportReplyList(sendData);  // 신고처리만 하면 종료
            // 모달창 클리어
            document.querySelector(".reply-report-modal-body").innerHTML = ``;
            document.querySelector(".reply-report-modal-body").style.display = "none";
            alert("신고 되었습니다. ")
        }


    })

});

