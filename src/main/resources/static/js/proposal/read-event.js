const buttonWrap = document.querySelector(".form")
console.log(loginMember);

buttonWrap.addEventListener("keyup", (e)=>{
    if(e.target.classList.contains("comment"))
        e.target.nextElementSibling.textContent = `${e.target.value.length} / 1200 (추천 글자수: 30자 이내)`;
});

buttonWrap.addEventListener("click", async (e) => {
    if(e.target.classList.contains("insertButton")) {
        const questionContent = document.querySelector(".comment");
        await readService.writeQuestion({planId: findPlanID, memberId: findMemberId, content:questionContent.value});
        console.log(planDetail.plan.id);
        console.log(member.id);
        console.log(questionContent.value);
        questionContent.value = "";

    }
})
