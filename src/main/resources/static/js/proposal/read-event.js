const buttonWrap = document.querySelector(".form")
const answerButton = document.querySelector("#question-wrap");





buttonWrap.addEventListener("keyup", (e)=>{
    if(e.target.classList.contains("comment"))
        e.target.nextElementSibling.textContent = `${e.target.value.length} / 1200 (추천 글자수: 30자 이내)`;
});

buttonWrap.addEventListener("click", async (e) => {
    if(e.target.classList.contains("insertButton")) {
        findPlanId = planDetail.plan.id;
        const questionContent = document.querySelector(".comment");
        await readService.writeQuestion({
            planId: findPlanId,
            memberId: loginMember.id,
            questionContent: questionContent.value
        });
        questionContent.value = "";
        readService.getList(planDetail.plan.id, readLayOut.showList);
    }

});



const moreQuestion = document.querySelector(".btn_more");
const questionWrap = document.querySelector("#question-wrap");

moreQuestion.addEventListener("click", (e) => {
    readService.getList(planDetail.plan.id, readLayOut.showList);
})

questionWrap.addEventListener("click", async (e) => {
    if (e.target.classList.contains("btn2")) {
        const questionItem = e.target.closest("li");
        const questionId = questionItem.id; // <li id="questionId"> 구조이므로 id 속성 사용
        const replyBox = questionItem.querySelector(".replyBox");

        // 이미 열려 있다면 닫기
        if (replyBox.style.display === "block") {
            replyBox.style.display = "none";
            return;
        }
        const targetId = e.target.getAttribute("data-index");

        replyBox.style.display = "block";

        // 답변 목록 가져오기
        await readService.getAnswerList(targetId, (answerListData) => {

            let answerHTML = "";
                answerHTML += `
                    <li>
                        <div class="txt_reply">
                            <p>${answerListData.answerContent}</p>
                            <div class="date">
                                <em class="name">${answerListData.memberNickname}</em>
                            </div>
                        </div>
                    </li>
                `;

            // 답변 목록 업데이트
            replyBox.querySelector("ul").innerHTML = answerHTML;
        });
    }
});


questionWrap.addEventListener("keyup", (e) => {
    if(e.target.classList.contains("coment")){
        e.target.nextElementSibling.textContent = `${e.target.value.length} / 1200 (추천 글자수: 30자 이내)`;
    }
})

answerButton.addEventListener("click", async (e) => {
    if(e.target.classList.contains("insertAnswerButton")){
        findPlanId = planDetail.plan.id;
        answerId = planDetail.plan.memberId;
        targetQuestionId = e.target.getAttribute("data-index")

        const answerContent = e.target.parentElement.previousElementSibling.querySelector(".answer-comment");
        readService.writeAnswer({
            memberId: answerId,
            planId: findPlanId,
            questionId: targetQuestionId,
            answerContent: answerContent.value});
        answerContent.value = "";

        readService.getAnswerList((answerListData) => {
            let text = "";
            text += `
                    <li>
                        <div class="txt_reply">
                            <p>${answerListData.answerContent}</p>
                            <div class="date">
                                <em class="name">${answerListData.memberNickname}</em>
                            </div>
                        </div>
                    </li>
                `;

            // 답변 목록 업데이트
        });
            replyBox.querySelector("ul").innerHTML = text;
            return;
        }
})

joinWrap.addEventListener("click", (e) => {
    console.log(e.target.tagName);
    if(e.target.classList.contains("join-button")){
        if(loginMember.id == planDetail.plan.memberId){
        alert("작성자는 참여할 수 없습니다.")
        return;
        }
    }
})

