const distanceWrap = document.querySelector(".area_address");
const plansWrap = document.querySelector(".plans-wrap");
const headerComent = document.querySelector(".headerComent");
const slickInitialized = document.querySelector(".slick-initialized");
const slickTrack = document.querySelector(".slick-track");
let feedLists = new Array();
let coment = ``;
if (course.courseType == null) {
    coment += `<span>${course.courseName}</span>`;
} else {
    coment += `
    <em class="tit_cos">${course.courseType}</em>
    <span>${course.courseName}</span>
    `;
}
headerComent.innerHTML = coment;

distanceWrap.innerHTML = `
    <span id="dist">코스 총거리 : ${course.courseDistance}</span>
`;

// 지도 보여주기
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(35.409476, 127.396059), // 지도의 중심좌표
        level: 9, // 지도의 확대 레벨
    };
let initialCenter = new kakao.maps.LatLng(35.409476, 127.396059);
var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

let tourSpots = new Array();

course.paths.forEach((path, i) => {
    tourSpots.push({
        name: `${i + 1}. ${path.pathName}`,
        address: path.pathAddress
    });
});

const static_positions = {};
let positions = new Array(tourSpots.length)
let geocoder = new kakao.maps.services.Geocoder();
let remains = tourSpots.length;
tourSpots.forEach((spot, i) => {
    geocoder.addressSearch(spot.address, (result, status) => {
        if (status === kakao.maps.services.Status.OK) {
            positions[i] = {
                content: "<div>" + spot.name + "</div>",
                latlng: new kakao.maps.LatLng(
                    Math.floor(result[0].y * 1000000) / 1000000,
                    Math.floor(result[0].x * 1000000) / 1000000
                ),
            };
        }
        remains--;
        if (remains < 1) {
            createMarkers();
            // position의 순서가 원래 입력된 순서가 아니기 때문에 원래
            tourSpots.forEach((spot, i) => {
                spot.latlng = positions[i]?.latlng; // undefined 방지
            });

            drawLine();

            if (positions[0]?.latlng) {
                map.setCenter(positions[0].latlng);
            }
        }
    });
});

function createMarkers() {
    for (var i = 0; i < positions.length; i++) {
        // 마커의 정보가 항상 나타나게
        var iwContent = `<div id="${i}" style="padding:5px;">${positions[i].content}</div>`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
            iwPosition = positions[i].latlng, //인포윈도우 표시 위치입니다
            iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

        // 인포윈도우를 생성하고 지도에 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            map: map, // 인포윈도우가 표시될 지도
            position: iwPosition,
            content: iwContent,
            removable: iwRemoveable,
        });

        // // 마우스가 위치하면 나타났다가, 마우스가 없어지면 없어짐.
        // // 마커를 생성합니다
        // var marker = new kakao.maps.Marker({
        //     map: map, // 마커를 표시할 지도
        //     position: positions[i].latlng, // 마커의 위치
        // });

        // // 마커에 표시할 인포윈도우를 생성합니다
        // var infowindow = new kakao.maps.InfoWindow({
        //     content: positions[i].content, // 인포윈도우에 표시할 내용
        // });

        // // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
        // // 이벤트 리스너로는 클로저를 만들어 등록합니다
        // // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
        // kakao.maps.event.addListener(marker, "mouseover", makeOverListener(map, marker, infowindow));
        // kakao.maps.event.addListener(marker, "mouseout", makeOutListener(infowindow));
    }
}

// 인포윈도우를 표시하는 클로저를 만드는 함수입니다
function makeOverListener(map, marker, infowindow) {
    return function () {
        infowindow.open(map, marker);
    };
}

// 인포윈도우를 닫는 클로저를 만드는 함수입니다
function makeOutListener(infowindow) {
    return function () {
        infowindow.close();
    };
}

// 지도 보여주기

// 선을 그릴 위치 배열
function drawLine() {
    var linePath = tourSpots.map((position) => position.latlng);

    // 지도에 선을 생성하고 표시
    var polyline = new kakao.maps.Polyline({
        map: map, // 선을 표시할 지도 객체
        path: linePath, // 선을 구성하는 좌표 배열
        strokeWeight: 3, // 선의 두께
        strokeColor: "#FF0000", // 선의 색상
        strokeOpacity: 0.8, // 선의 투명도
        strokeStyle: "solid", // 선의 스타일
    });
}

initialCenter = tourSpots[0].latlng;

// 화면 확장 축소
document.querySelector("#fullMap").addEventListener("click", (e) => {
    if (mapContainer.style.position === "fixed") {
        mapContainer.style.position = "relative";
        mapContainer.style.width = "100%";
        mapContainer.style.height = "40vh";
        mapContainer.style.zIndex = ""; // 맵이 다른 요소 위에 오도록 설정한거 해제
        document.querySelector("#fullMap").style.position = "absolute";
        // 지도의 중심을 새로운 좌표로 설정
        map.relayout();
    } else {
        mapContainer.style.position = "fixed";
        mapContainer.style.top = "0";
        mapContainer.style.left = "0";
        mapContainer.style.width = "100%";
        mapContainer.style.height = "100vh";
        mapContainer.style.zIndex = "1000"; // 맵이 다른 요소 위에 오도록 설정
        document.querySelector("#fullMap").style.position = "fixed";
        // 지도의 중심을 새로운 좌표로 설정
        map.relayout();
    }
});
// 화면 확장 축소

let text = ``;

course.plans.forEach((plan) => {
    if (plan.feedList !== null) {
        plan.feedList.forEach((feed) => {
            feedLists.push(feed);
        })
    }
})

if (feedLists.length > 0) {
    feedLists.slice(0, 6).forEach((feed, i) => {
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
}

if (feedLists.length === 0) {
    text = `
    <div
        class="Padded-sc-1mbfr4n-0 frip__Wrapper-sc-1th48wc-0 cJASPK"
    >
        <div
            class="Fade__Wrapper-sc-1s0ipfq-0 koasSX"
            style="opacity: 1; display: block"
        >
            <div class="Base__Wrapper-cxjyd-0 eOsQaF">
                <strong
                    class="Base__Header-cxjyd-1 bLfngk"
                    >아직 작성한 후기가 없어요 😂🤣</strong
                >
            </div>
        </div>
    </div>
    `;
    slickInitialized.innerHTML = text;
}

if (feedLists.length > 3) {
    const leftArrow = document.createElement('img');

    leftArrow.setAttribute('data-role', 'none'); // 'data-role' 속성 설정
    leftArrow.className = 'CoverReviewSlider__PrevArrow-sc-1ty45po-0 gENQzC slick-arrow slick-prev slick-disabled'; // 클래스 설정
    leftArrow.src = 'data:image/png;base64,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'; // base64 데이터 설정 (여기서 긴 부분은 생략)
    leftArrow.style.display = 'block'; // style="display: block" 속성 설정

    const rightArrow = document.createElement('img');

    rightArrow.setAttribute('data-role', 'none'); // 'data-role' 속성 추가
    rightArrow.className = 'CoverReviewSlider__NextArrow-sc-1ty45po-1 eGlkoV slick-arrow slick-next'; // 클래스 설정
    rightArrow.src = 'data:image/png;base64,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'; // base64 데이터 설정 (긴 부분 생략)
    rightArrow.style.display = 'block'; // style 속성 추가

    slickInitialized.prepend(leftArrow);
    slickInitialized.append(rightArrow);
}

text = ``;

course.plans.forEach((plan) => {
    const formatPrice = plan.planPrice.toLocaleString()
    const encodedFilePath = plan.planFilePath && plan.planFileName
        ? encodeURIComponent(`${plan.planFilePath}/${plan.planFileName}`)
        : null;
    const defaultImage = "/images/proposal/noImage.png";
    text += `
     <div >
        <div class="MagazineListPage__MagazineWrapper-hh1ck3-2 jZtIEr">
            <a
                class="MagazineListPage__Magazine-hh1ck3-3 hHOLgL"
                href="/proposal/read?id=${plan.id}"
                ><div class="Image__Wrapper-v97gyx-0 gDuKGF">
                    <div
                        class="Fade__Wrapper-sc-1s0ipfq-0 koasSX"
                        style="opacity: 1; display: block"
                    >
                        <div class="Ratio" style="display: block">
                            <div
                                class="Ratio-ratio"
                                style="
                                    height: 0px;
                                    position: relative;
                                    width: 100%;
                                    padding-top: 120%;
                                "
                            >
                                <div
                                    class="Ratio-content"
                                    style="
                                        height: 100%;
                                        left: 0px;
                                        position: absolute;
                                        top: 0px;
                                        width: 100%;
                                    "
                                >
                                    <img
                                        class="Image__StyledImage-v97gyx-1 VUNpu"
                                        width="160"
                                        src="${encodedFilePath ? `/files/display?path=${encodedFilePath}` : defaultImage}"
                                    />
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="MagazineListPage__Title-hh1ck3-4 kBOcSr">
                        <span>${plan.planName}</span><br />
                        <span>일정 : ${plan.planStartDate} ~ ${plan.planEndDate}</span><br />
                        <span>비용 : ${formatPrice}원</span><br />
                        <span>참가 : ${plan.participants.length}/${plan.planMaxPersonnel}(참여/목표)</span><br />
                    </div>
                    <div class="MagazineListPage__CatchPhrase-hh1ck3-5 dfnTnv">
                        캡틴 : ${plan.memberNickname}
                    </div>
                </div></a
            >
        </div>
    </div>
    `;

    plansWrap.innerHTML = text;
})

if(course.plans.length === 0){
    text = `
    <div
        class="Padded-sc-1mbfr4n-0 frip__Wrapper-sc-1th48wc-0 cJASPK plan-start"
    >
        <div
            class="Fade__Wrapper-sc-1s0ipfq-0 koasSX"
            style="opacity: 1; display: block"
        >
            <div class="Base__Wrapper-cxjyd-0 eOsQaF">
                <strong
                    class="Base__Header-cxjyd-1 bLfngk"
                    >아직 진행중인 여행이 없어요 😂🤣</strong
                >
            </div>
        </div>
    </div>
    `;
    plansWrap.innerHTML = text;
}

