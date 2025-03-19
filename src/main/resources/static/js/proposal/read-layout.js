const scheduleWrap = document.querySelector(".schedule-wrap")
const travelWrap = document.querySelector(".travel-wrap");
const includeWrap = document.querySelector(".include-wrap");
const excludeWrap = document.querySelector(".exclude-wrap");
const prepareWrap = document.querySelector(".prepare-wrap");
const startAddress = document.querySelector(".start-address");


const startDate = new Date(planDetail.plan.planStartDate);
const endDate = new Date(planDetail.plan.planEndDate);
const deadline = new Date(planDetail.plan.planDeadline);

const formatStartDate = `${startDate.getMonth()+1}월 ${startDate.getDate()}일`;
const formatEndDate = `${endDate.getMonth() + 1}월 ${endDate.getDate()}일`;
const formatDeadLine = `${deadline.getMonth() + 1}월 ${deadline.getDate()}일`;

const formatPrice = planDetail.plan.planPrice.toLocaleString();

let text = ``;

text = `
<header class="Article__Header-sc-1mmkltm-0 fCwkht">
    <hgroup>
        <h2 class="Article__Title-sc-1mmkltm-1 bZNoYF">여행 정보</h2>
    </hgroup>
</header>
<dl class="InfoSection__InfoContainer-yf02u4-0 dbbJUL">
    <div><dt>기간</dt><span>${formatStartDate} ~ ${formatEndDate}</span></div>
    <div><dt>모집 마감일</dt><dd>${formatDeadLine}</dd></div>
    <div><dt>최대 모집 인원</dt><dd>${planDetail.plan.planMaxPersonnel}명</dd></div>
    <div><dt>최소 출발 인원</dt><dd>${planDetail.plan.planMinPersonnel}명</dd></div>
    <div><dt>비용</dt><dd>${formatPrice}원</dd></div>
</dl>
`;
travelWrap.innerHTML = text;

text = ``;
const schedules = planDetail.plan.scheduleContents
schedules.forEach((schedule, i) => {
    text += `
    <article>
        <header class="Article__Header-sc-1mmkltm-0 fCwkht">
            <hgroup>
                <h2 class="Article__Title-sc-1mmkltm-1 bZNoYF">${i+1}일차 계획</h2>
            </hgroup>
        </header>
        <dl class="InfoSection__InfoContainer-yf02u4-0 dbbJUL">
            <div>상세내용</div>
            <div>${schedule.scheduleContent}</div>
        </dl>
    </article>
    `;
});
scheduleWrap.innerHTML = text;

text = ``;
const includes = planDetail.plan.includeContents;
includes.forEach((include)=>{
    text += `
    <span type="Round" class="Tag__RoundTag-sxb61j-1 jXxsiv">${include.includeContent}</span>
    `;
})
includeWrap.innerHTML = text;

text = ``;
const excludes = planDetail.plan.excludeContents;
excludes.forEach((exclude)=>{
    text += `
    <span type="Round" class="Tag__RoundTag-sxb61j-1 eMLPLA">${exclude.excludeContent}</span>
    `;
})
excludeWrap.innerHTML = text;

text = ``;
const prepares = planDetail.plan.prepareContents;
prepares.forEach((prepare) => {
    text += `
    <span type="Round" class="Tag__RoundTag-sxb61j-1 eISlhn">${prepare.prepareContent}</span>
    `;
})
prepareWrap.innerHTML = text;

text = `
<div id="mapMessage">${planDetail.plan.planStartAddress}</div>
`;

startAddress.innerHTML = text;

text = ``;

const buttonForm = document.querySelector(".form");

if(planDetail.member == null) {
    text = `
    <label class="blind" for="comment">로그인 후 글을 남겨주세요.</label>
    <span class="writeForm" style="height: 80px;">
        <textarea maxlength="1200" name="" rows="" class="comment" placeholder="로그인 후 글을 남겨주세요." cols="" onkeydown="commentresize(this);" readonly="readonly" style="height: 80px;"></textarea>
            <p class="Textarea__Count-sc-1b9phu6-2 jvAusQ">0 / 1200 (추천 글자수: 30자 이내)</p>
    </span>
    <div class="fileRegbtn_wrap">
        <span class="fileRegbtn">
            <input type="file" class="fileUp" "fileUp" multiple="" onchange="fileChange(this)" disabled="disabled">
            <label for="fileUp" class="btn_fileUp">파일찾기</label>
           <a href="/login/login" class="btn_apply ContentComment">로그인</a>
    </div>
    `;
    buttonForm.innerHTML = text;
}
else{
    document.querySelector(".replyWrap").className = "replyWrap subscription login";
    text = `
    <label class="blind" for="comment">로그인 후 글을 남겨주세요.</label>
    <span class="writeForm" style="height: 80px;">
        <textarea maxlength="1200" name="" rows="" class="comment" placeholder="글을 남겨주세요." cols="" onkeydown="commentresize(this);"></textarea>
            <p class="Textarea__Count-sc-1b9phu6-2 jvAusQ">0 / 1200 (추천 글자수: 30자 이내)</p>
    </span>
    <div class="fileRegbtn_wrap">
        <span class="fileRegbtn">
            <input type="file" class="fileUp" "fileUp" multiple="" onchange="fileChange(this)">
            <label for="fileUp" class="btn_fileUp">파일찾기</label>
            <button class="btn_apply ContentComment insertButton">등록</button>
    </div>
    `;
    buttonForm.innerHTML = text;
}


const readLayOut = (() => {
    const showList = (questionListData) => {
        const questionWrap = document.querySelector("#question-wrap");
        let text = ``;
        questionListData.questionList.forEach((question, i)=>{
            if(planDetail.member == null) {
                text += `
           <li id="${question.id}">
    <div class="profile">
        <div class="photo" icid="b940dab6-e56b-4103-b120-a1f4c83c5e25" style="background-image:url(https://phinf.pstatic.net/contact/20210105_226/1609820759733fLo89_PNG/avatar_profile.png)">
        </div>
        <span class="ico"><img src="https://korean.visitkorea.or.kr/resources/images/sub/ico_naver.png" alt="네이버"></span>
    </div>
    <div class="txt_reply">
        <p>${question.questionContent}</p>
        <div class="date">
            <em class="name">${question.memberNickname}</em>
            <span>${question.createDate}</span>
        </div>
    </div>
    <span class="replyBtn active">
        <button data-index="${question.id}" type="button" class="btn2" listener="true" title="선택됨">
            <em class="blind">댓글</em>
        </button>
    </span>
    <div class="replyBox" style="display: none;">
        <ul>
            <li class="inputcomment">
                <div class="mLine">
                    <div class="replyForm">
                        <form name="form">
                            <label class="blind" for="replyForm">글을 입력하세요.</label>
                            <span class="writeForm" style="height: 80px;">
                                <textarea id="replyForm" rows="" placeholder="로그인 후 소중한 글을 남겨주세요." cols="" readonly="readonly" listener="true"></textarea>
                                <p class="Textarea__Count-sc-1b9phu6-2 jvAusQ">0 / 1200 (추천 글자수: 30자 이내)</p>
                            </span>
                            <div class="btn">
                                <span class="fileRegbtn">
                                    <input type="file" class="fileUp" id="fileUpb940dab6-e56b-4103-b120-a1f4c83c5e25" name="fileUpb940dab6-e56b-4103-b120-a1f4c83c5e25" onchange="fileChange(this)" disabled="disabled">
                                        <label for="fileUpb940dab6-e56b-4103-b120-a1f4c83c5e25" class="btn_fileUp">파일찾기</label>
                                </span>
                                <a href="/login/login" class="btn_apply ContentComment" listener="true">로그인</a>
                            </div>
                        </form>
                    </div>
                </div>
            </li>
        </ul>
    </div></li>
    `;
            }else {
                text += `
           <li id="${question.id}">
    <div class="profile">
        <div class="photo" icid="b940dab6-e56b-4103-b120-a1f4c83c5e25" style="background-image:url(https://phinf.pstatic.net/contact/20210105_226/1609820759733fLo89_PNG/avatar_profile.png)">
        </div>
        <span class="ico"><img src="https://korean.visitkorea.or.kr/resources/images/sub/ico_naver.png" alt="네이버"></span>
    </div>
    <div class="txt_reply">
        <p>${question.questionContent}</p>
        <div class="date">
            <em class="name">${question.memberNickname}</em>
            <span>${question.createDate}</span>
        </div>
    </div>
    <span class="replyBtn active">
        <button data-index="${question.id}" type="button" class="btn2" listener="true" title="선택됨">
            <em class="blind">댓글</em>
        </button>
    </span>
    <div class="replyBox" style="display: none;">
        <ul>
            <li class="inputcomment">
                <div class="mLine">
                    <div class="replyForm">
                        <form name="form">
                            <label class="blind" for="replyForm">글을 입력하세요.</label>
                            <span class="writeForm" style="height: 80px;">
                                <textarea class="answer-comment" id="replyForm" rows="" placeholder="글을 남겨주세요." cols=""  listener="true"></textarea>
                                <p class="Textarea__Count-sc-1b9phu6-2 jvAusQ">0 / 1200 (추천 글자수: 30자 이내)</p>
                            </span>
                            <div class="btn">
                                <span class="fileRegbtn">
                                    <input type="file" class="fileUp" id="fileUpb940dab6-e56b-4103-b120-a1f4c83c5e25" name="fileUpb940dab6-e56b-4103-b120-a1f4c83c5e25" onchange="fileChange(this)" disabled="disabled">
                                        <label for="fileUpb940dab6-e56b-4103-b120-a1f4c83c5e25" class="btn_fileUp">파일찾기</label>
                                </span>
                                                <button class="btn_apply ContentComment insertAnswerButton" data-index="${question.id}">등록</button>

                            </div>
                        </form>
                    </div>
                </div>
            </li>
        </ul>
    </div></li>
    `;
            }
        })
        questionWrap.innerHTML = text;
    }

    return {showList: showList}
})();



