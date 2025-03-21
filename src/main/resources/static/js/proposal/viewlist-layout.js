const distanceWrap = document.querySelector(".area_address");
const plansWrap = document.querySelector(".plans-wrap");
const headerComent = document.querySelector(".headerComent");

let coment = ``;
if(course.courseType == null){
    coment += `<span>${course.courseName}</span>`;
}else {
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

course.paths.forEach((path, i) =>{
    tourSpots.push({
        name: `${i + 1}. ${path.pathName}`,
        address: path.pathAddress
    });
});

const static_positions = {};
let positions = new Array(tourSpots.length)
let geocoder = new kakao.maps.services.Geocoder();
let remains = tourSpots.length;
tourSpots.forEach((spot,i) => {
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

course.plans.forEach((plan)=>{
    const formatPrice = plan.planPrice.toLocaleString()
    text +=`
     <div >
        <div class="MagazineListPage__MagazineWrapper-hh1ck3-2 jZtIEr">
            <a
                class="MagazineListPage__Magazine-hh1ck3-3 hHOLgL"
                href="/proposal/read?id=${plan.id}"
                ><div class="Image__Wrapper-v97gyx-0 gDuKGF">
                    <img
                        class="Image__StyledImageLoader-v97gyx-2 bUFcfh"
                        width="160"
                        src="https://res.cloudinary.com/frientrip/image/upload/c_fill,f_auto,g_center,h_240,q_auto,w_200/thm_%EC%9D%B4%EB%B2%88%EC%A3%BC%EB%A7%90%EB%AD%90%ED%95%98%EC%A7%80%20(13)_d1a9b6efdfcb34b9d97243819cefc83f4f656f479bdc1686ba582b3c4368848c"
                    />
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
                                        alt="매거진 커버 이미지"
                                        class="Image__StyledImage-v97gyx-1 VUNpu"
                                        width="160"
                                        src="https://res.cloudinary.com/frientrip/image/upload/c_fill,f_auto,g_center,h_240,q_auto,w_200/thm_%EC%9D%B4%EB%B2%88%EC%A3%BC%EB%A7%90%EB%AD%90%ED%95%98%EC%A7%80%20(13)_d1a9b6efdfcb34b9d97243819cefc83f4f656f479bdc1686ba582b3c4368848c"
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
                        <span>참가 : 3/${plan.planMaxPersonnel}(참여/목표)</span><br />
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