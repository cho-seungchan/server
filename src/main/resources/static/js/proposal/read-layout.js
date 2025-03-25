const scheduleWrap = document.querySelector(".schedule-wrap")
const travelWrap = document.querySelector(".travel-wrap");
const includeWrap = document.querySelector(".include-wrap");
const excludeWrap = document.querySelector(".exclude-wrap");
const prepareWrap = document.querySelector(".prepare-wrap");
const startAddress = document.querySelector(".start-address");
const slickTrack = document.querySelector(".slick-track");
const moreFeeds = document.querySelector(".more-feeds");


const startDate = new Date(planDetail.plan.planStartDate);
const endDate = new Date(planDetail.plan.planEndDate);
const deadline = new Date(planDetail.plan.planDeadline);

const formatStartDate = `${startDate.getMonth() + 1}월 ${startDate.getDate()}일`;
const formatEndDate = `${endDate.getMonth() + 1}월 ${endDate.getDate()}일`;
const formatDeadLine = `${deadline.getMonth() + 1}월 ${deadline.getDate()}일`;

const formatPrice = planDetail.plan.planPrice.toLocaleString();

let text = ``;
const arFilePaths = new Array();
const arMemberPath = new Array();

planDetail.feedList.slice(0, 6).forEach((feed, i) => {
    console.log(feed);
    const encodedFilePath = feed.files[0]?.filePath && feed.files[0]?.fileName
        ? encodeURIComponent(`${feed.files[0].filePath}/${feed.files[0].fileName}`)
        : null;
    const encodedMemberPath = feed.memberFilePath && feed.memberFileName
        ? encodeURIComponent(`${feed.memberFilePath}/${feed.memberFileName}`)
        : null;

    const defaultImage = "/images/proposal/noImage.png";
    const defaultProfileImage = "/images/proposal/noImage.png"
    text += `
<div data-index="${i}" class="slick-slide" tabIndex="-1" aria-hidden="true" style="outline: none; width: 244px">
    <div>
        <div class="CoverReviewCard__Wrapper-sc-1kgiguh-0 ihDCaS">
            <div class="CoverReviewCard__ImageSection-sc-1kgiguh-1 eSCFvY">
                <a href="/products/174811/reviews">
                    <div class="Image__Wrapper-v97gyx-0 gDuKGF">
                        <div class="Fade__Wrapper-sc-1s0ipfq-0 koasSX" style="
                                                                                        opacity: 1;
                                                                                        display: block;
                                                                                    ">
                            <div class="Ratio" style="display: block">
                                <div class="Ratio-ratio" style="
                                                                                                height: 0px;
                                                                                                position: relative;
                                                                                                width: 100%;
                                                                                                padding-top: 100%;
                                                                                            ">
                                    <div class="Ratio-content thumnail-wrap" style="
                                                                                                    height: 100%;
                                                                                                    left: 0px;
                                                                                                    position: absolute;
                                                                                                    top: 0px;
                                                                                                    width: 100%;
                                                                                                ">
                                        <img alt="review-thumbnail" class="Image__StyledImage-v97gyx-1 VUNpA"
                                             width="220"
                                             height="220"
                                             src="${encodedFilePath ? `/files/display?path=${encodedFilePath}` : defaultImage}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
            </div>
            <div class="CoverReviewCard__InfoSection-sc-1kgiguh-2 ggRfTm">
                <div class="CoverReviewCard__UserProfileSection-sc-1kgiguh-3 gvMYvX">
                    <div class="CoverReviewCard__ProfileImage-sc-1kgiguh-4 hbfRVr">
                        <div class="Image__Wrapper-v97gyx-0 img-wrap">
                             <img class="Image__StyledImageLoader-v97gyx-2 bUFcfh" width="40" height="40"
                                  src=""/>
                            <div class="Fade__Wrapper-sc-1s0ipfq-0 koasSX" style="
                                                                                            opacity: 1;
                                                                                            display: block;
                                                                                        ">
                                <div class="Ratio" style="display: block">
                                    <div class="Ratio-ratio" style="
                                                                                                    height: 0px;
                                                                                                    position: relative;
                                                                                                    width: 100%;
                                                                                                    padding-top: 100%;
                                                                                                ">
                                        <div class="Ratio-content profile-wrap" style="
                                            height: 100%;
                                            left: 0px;
                                            position: absolute;
                                            top: 0px;
                                            width: 100%;
                                        ">
                                             <img alt="user-1225608-profile"
                                                  class="Image__StyledImage-v97gyx-1 hPRDSh" width="40" height="40"
                                                 src="${encodedMemberPath ? `/files/display?path=${encodedMemberPath}` : defaultProfileImage}"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="CoverReviewCard__UserInfo-sc-1kgiguh-5 fsJcFt">
                        <span class="CoverReviewCard__UserName-sc-1kgiguh-6 biuXID">${feed.memberNickname}</span>
                    </div>
                </div>
                <p class="CoverReviewCard__ProductTitle-sc-1kgiguh-7 jtBQUX"></p>
                <span class="SpanLineClamp-my36n9-0 CoverReviewCard__ReviewContent-sc-1kgiguh-8 gsjveC">${feed.feedContent}</span>
            </div>
        </div>
    </div>
</div>
`;


});

slickTrack.innerHTML = text;

text = `
<a class="ReviewSection__StyledLink-h5kv09-2 PIawM" href="/proposal/reviewlist?planId=${planDetail.plan.id}">${planDetail.feedList.length-6}개 후기 더보기<img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='12' height='12' fill='none' viewBox='0 0 12 12'%3E %3Cpath fill='%230075EF' fill-rule='evenodd' d='M3.97 1.72c.293-.293.767-.293 1.06 0L9.31 6l-4.28 4.28c-.293.293-.767.293-1.06 0-.293-.293-.293-.767 0-1.06L7.19 6 3.97 2.78c-.293-.293-.293-.767 0-1.06z' clip-rule='evenodd'/%3E %3C/svg%3E" alt="arrow-blue"></a>
`;
moreFeeds.innerHTML = text;

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
                <h2 class="Article__Title-sc-1mmkltm-1 bZNoYF">${i + 1}일차 계획</h2>
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
includes.forEach((include) => {
    text += `
    <span type="Round" class="Tag__RoundTag-sxb61j-1 jXxsiv">${include.includeContent}</span>
    `;
})
includeWrap.innerHTML = text;

text = ``;
const excludes = planDetail.plan.excludeContents;
excludes.forEach((exclude) => {
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

if (planDetail.member == null) {
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
} else {
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

const joinWrap = document.querySelector(".join-wrap");

if (loginMember == null) {
    text = `
    <div class="FloatingActionBar__FloatingButtonWrapper-a3gyda-0 dIeHJQ">
        <a href="/login/login" class="Button-bqxlp0-0 BUemN Button__StyledSubmitButton-sc-1dkzbac-0 eIJDxV join-button" width="100%" height="56px" color="white" font-size="16px">
            <div class="DefaultButton__ActionLabel-sc-4mlqfg-0 eaCxDu join-button">참여하기</div>
        </a>
        <!-- 찜 버튼 -->
        <button class="Button-bqxlp0-0 ButtonWish__StyledButton-sc-7k8l60-0 eZGjgL enp_mobon_cart" width="32px" height="32px">
            <img src="data:image/svg+xml,%3Csvg width='40' height='40' viewBox='0 0 32 32' fill='none' xmlns='http://www.w3.org/2000/svg'%3E %3Cg clip-path='url(%23clip0_2519_490)'%3E %3Cmask id='mask0_2519_490' style='mask-type:luminance' maskUnits='userSpaceOnUse' x='0' y='0' width='32' height='32'%3E %3Cpath d='M32 0H0V32H32V0Z' fill='white'/%3E %3C/mask%3E %3Cg mask='url(%23mask0_2519_490)'%3E %3Cmask id='mask1_2519_490' style='mask-type:luminance' maskUnits='userSpaceOnUse' x='0' y='0' width='32' height='32'%3E %3Cpath d='M32 0H0V32H32V0Z' fill='white'/%3E %3C/mask%3E %3Cg mask='url(%23mask1_2519_490)'%3E %3Cpath d='M28 12C28 16.1889 24.9781 19.7916 22.063 22.3047C20.2579 23.8469 18.2982 25.1986 16.2154 26.3381C16.2027 26.3441 16.1898 26.3505 16.1774 26.357C16.1226 26.3861 16.0613 26.4013 15.999 26.4015C15.9401 26.4015 15.882 26.3881 15.8293 26.362L15.8094 26.3511L15.784 26.3378C15.1694 26.0033 14.5659 25.649 13.9743 25.2754C12.5586 24.3847 11.2086 23.3914 9.93704 22.3049C7.02334 19.7914 4.00003 16.1887 4 12C4.00014 10.6766 4.41054 9.38568 5.17469 8.30517C5.93885 7.22466 7.01918 6.4076 8.26696 5.96651C9.51472 5.52542 10.8685 5.482 12.142 5.84222C13.4155 6.20245 14.546 6.94859 15.3778 7.97794C15.5297 8.16589 15.7584 8.2751 16 8.2751C16.2416 8.2751 16.4704 8.16589 16.6222 7.97794C17.4541 6.94859 18.5845 6.20245 19.8579 5.84222C21.1315 5.482 22.4853 5.52542 23.7331 5.96651C24.9808 6.4076 26.0611 7.22466 26.8253 8.30517C27.5894 9.38568 27.9998 10.6766 28 12Z' stroke='%23333333' stroke-width='1.5' stroke-linejoin='round'/%3E %3C/g%3E %3C/g%3E %3C/g%3E %3Cdefs%3E %3CclipPath id='clip0_2519_490'%3E %3Crect width='32' height='32' fill='white'/%3E %3C/clipPath%3E %3C/defs%3E %3C/svg%3E" alt="찜하기">
            <span class="SaveActionButton__Count-sc-18l2t54-1 hxJYoP wish-count">${wishCount}</span>
        </button>
    </div>
    `;
    joinWrap.innerHTML = text;
} else if (loginMember.id == planDetail.plan.memberId) {
    text = `
    <div class="FloatingActionBar__FloatingButtonWrapper-a3gyda-0 dIeHJQ">
        <button type="button" class="Button-bqxlp0-0 BUemN Button__StyledSubmitButton-sc-1dkzbac-0 eIJDxV join-button" width="100%" height="56px" color="white" font-size="16px">
            <div class="DefaultButton__ActionLabel-sc-4mlqfg-0 eaCxDu join-button">참여하기</div>
        </button>
        <!-- 찜 버튼 -->
        <button class="Button-bqxlp0-0 ButtonWish__StyledButton-sc-7k8l60-0 eZGjgL enp_mobon_cart" width="32px" height="32px">
            <img src="data:image/svg+xml,%3Csvg width='40' height='40' viewBox='0 0 32 32' fill='none' xmlns='http://www.w3.org/2000/svg'%3E %3Cg clip-path='url(%23clip0_2519_490)'%3E %3Cmask id='mask0_2519_490' style='mask-type:luminance' maskUnits='userSpaceOnUse' x='0' y='0' width='32' height='32'%3E %3Cpath d='M32 0H0V32H32V0Z' fill='white'/%3E %3C/mask%3E %3Cg mask='url(%23mask0_2519_490)'%3E %3Cmask id='mask1_2519_490' style='mask-type:luminance' maskUnits='userSpaceOnUse' x='0' y='0' width='32' height='32'%3E %3Cpath d='M32 0H0V32H32V0Z' fill='white'/%3E %3C/mask%3E %3Cg mask='url(%23mask1_2519_490)'%3E %3Cpath d='M28 12C28 16.1889 24.9781 19.7916 22.063 22.3047C20.2579 23.8469 18.2982 25.1986 16.2154 26.3381C16.2027 26.3441 16.1898 26.3505 16.1774 26.357C16.1226 26.3861 16.0613 26.4013 15.999 26.4015C15.9401 26.4015 15.882 26.3881 15.8293 26.362L15.8094 26.3511L15.784 26.3378C15.1694 26.0033 14.5659 25.649 13.9743 25.2754C12.5586 24.3847 11.2086 23.3914 9.93704 22.3049C7.02334 19.7914 4.00003 16.1887 4 12C4.00014 10.6766 4.41054 9.38568 5.17469 8.30517C5.93885 7.22466 7.01918 6.4076 8.26696 5.96651C9.51472 5.52542 10.8685 5.482 12.142 5.84222C13.4155 6.20245 14.546 6.94859 15.3778 7.97794C15.5297 8.16589 15.7584 8.2751 16 8.2751C16.2416 8.2751 16.4704 8.16589 16.6222 7.97794C17.4541 6.94859 18.5845 6.20245 19.8579 5.84222C21.1315 5.482 22.4853 5.52542 23.7331 5.96651C24.9808 6.4076 26.0611 7.22466 26.8253 8.30517C27.5894 9.38568 27.9998 10.6766 28 12Z' stroke='%23333333' stroke-width='1.5' stroke-linejoin='round'/%3E %3C/g%3E %3C/g%3E %3C/g%3E %3Cdefs%3E %3CclipPath id='clip0_2519_490'%3E %3Crect width='32' height='32' fill='white'/%3E %3C/clipPath%3E %3C/defs%3E %3C/svg%3E" alt="찜하기">
            <span class="SaveActionButton__Count-sc-18l2t54-1 hxJYoP wish-count">${wishCount}</span>
        </button>
    </div>
    `;
    joinWrap.innerHTML = text;
} else {
    text = `
    <div class="FloatingActionBar__FloatingButtonWrapper-a3gyda-0 dIeHJQ">
        <a href="/proposal/pay?planId=${planDetail.plan.id}" class="Button-bqxlp0-0 BUemN Button__StyledSubmitButton-sc-1dkzbac-0 eIJDxV join-button" width="100%" height="56px" color="white" font-size="16px">
            <div class="DefaultButton__ActionLabel-sc-4mlqfg-0 eaCxDu join-button">참여하기</div>
        </a>
        <!-- 찜 버튼 -->
        <button class="Button-bqxlp0-0 ButtonWish__StyledButton-sc-7k8l60-0 eZGjgL enp_mobon_cart" width="32px" height="32px">
            <img src="data:image/svg+xml,%3Csvg width='40' height='40' viewBox='0 0 32 32' fill='none' xmlns='http://www.w3.org/2000/svg'%3E %3Cg clip-path='url(%23clip0_2519_490)'%3E %3Cmask id='mask0_2519_490' style='mask-type:luminance' maskUnits='userSpaceOnUse' x='0' y='0' width='32' height='32'%3E %3Cpath d='M32 0H0V32H32V0Z' fill='white'/%3E %3C/mask%3E %3Cg mask='url(%23mask0_2519_490)'%3E %3Cmask id='mask1_2519_490' style='mask-type:luminance' maskUnits='userSpaceOnUse' x='0' y='0' width='32' height='32'%3E %3Cpath d='M32 0H0V32H32V0Z' fill='white'/%3E %3C/mask%3E %3Cg mask='url(%23mask1_2519_490)'%3E %3Cpath d='M28 12C28 16.1889 24.9781 19.7916 22.063 22.3047C20.2579 23.8469 18.2982 25.1986 16.2154 26.3381C16.2027 26.3441 16.1898 26.3505 16.1774 26.357C16.1226 26.3861 16.0613 26.4013 15.999 26.4015C15.9401 26.4015 15.882 26.3881 15.8293 26.362L15.8094 26.3511L15.784 26.3378C15.1694 26.0033 14.5659 25.649 13.9743 25.2754C12.5586 24.3847 11.2086 23.3914 9.93704 22.3049C7.02334 19.7914 4.00003 16.1887 4 12C4.00014 10.6766 4.41054 9.38568 5.17469 8.30517C5.93885 7.22466 7.01918 6.4076 8.26696 5.96651C9.51472 5.52542 10.8685 5.482 12.142 5.84222C13.4155 6.20245 14.546 6.94859 15.3778 7.97794C15.5297 8.16589 15.7584 8.2751 16 8.2751C16.2416 8.2751 16.4704 8.16589 16.6222 7.97794C17.4541 6.94859 18.5845 6.20245 19.8579 5.84222C21.1315 5.482 22.4853 5.52542 23.7331 5.96651C24.9808 6.4076 26.0611 7.22466 26.8253 8.30517C27.5894 9.38568 27.9998 10.6766 28 12Z' stroke='%23333333' stroke-width='1.5' stroke-linejoin='round'/%3E %3C/g%3E %3C/g%3E %3C/g%3E %3Cdefs%3E %3CclipPath id='clip0_2519_490'%3E %3Crect width='32' height='32' fill='white'/%3E %3C/clipPath%3E %3C/defs%3E %3C/svg%3E" alt="찜하기">
            <span class="SaveActionButton__Count-sc-18l2t54-1 hxJYoP wish-count" >${wishCount}</span>
        </button>
    </div>
    `;
    joinWrap.innerHTML = text;
}


const readLayOut = (() => {
    const showList = (questionListData) => {
        const questionWrap = document.querySelector("#question-wrap");
        let text = ``;
        questionListData.questionList.forEach((question, i) => {
            if (planDetail.member == null) {
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
            } else {
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

    const getAnswerList = (answerListData) => {
        const replyBox = document.querySelector(".replyBox");
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
        replyBox.querySelector("ul").innerHTML = text;
        console.log(replyBox);
    };
    return {
        showList: showList,
        getAnswerList: getAnswerList
    }
})();



