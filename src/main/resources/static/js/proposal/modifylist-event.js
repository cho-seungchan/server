planService.getList(planLayout.showList);

const buttonContainer = document.querySelector(".button-container");
const listWrap = document.querySelector(".list-wrap");

// 추가버튼 이벤트
buttonContainer.addEventListener("click", (e) => {
        if(e.target.tagName === "BUTTON") {
            planService.getList(planLayout.showList, e.target.getAttribute("data-index"))
        }
});

// 수정 삭제 모달창 처리
listWrap.addEventListener("click", (e) => {
    if(e.target.classList.contains("FvtMb")) {
        document.querySelector("#modal-root").style.display = "flex";

        document.querySelector(".eCzJv").addEventListener("click", () => {
        document.querySelector("#modal-root").style.display = "none";
        });
    }
})


