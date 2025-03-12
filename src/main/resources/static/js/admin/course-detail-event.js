
// 2025.03.11  DOM 동적 생성에 맞게 지도를 그릴 수 있도록 수정
// 맵 관리를 위한 변수 들
var mapContainer, mapOption, map, geocoder, remains;
var tourSpots = [];
let mapObserverPause = false;   // 동적감시 요소를 일시중단, 재시작할 플래그
let searchInput, clickLine, totalDistanceOverlay, totalDistanceInput, destinationList, initialCenter;
let dotOverlays = [];
let textOverlays = [];

document.addEventListener("DOMContentLoaded", function () {

    const MapObserver = new MutationObserver((mutationsList) => {
        if (mapObserverPause) return;  // 일시 중지 상태에서는 무시

        console.log(" 맴 동적 감시 시작");
        for (const mutation of mutationsList) {
            if (mutation.type === "childList") {
                mapContainer = document.getElementById("map");

                const YmI3N = document.querySelector(".AppLayout_contents__YmI3N");
                searchInput = YmI3N.querySelector(".noBtnStyle");
                totalDistanceInput = document.querySelector(".max");
                destinationList = document.getElementById("destinationList");

                if (mapContainer && searchInput && totalDistanceInput && destinationList) {
                    console.log("#map 요소를 찾았습니다!");
                    // MapObserver.disconnect();  // 조회가 계속 될 때 계속 감시하기 위해서
                    mapObserverPause = true;

                    // 지도 초기화
                    mapOption = {
                        center: new kakao.maps.LatLng(35.409476, 127.396059), // 지도 중심 좌표
                        level: 9, // 지도 확대 레벨
                    };
                    initialCenter = new kakao.maps.LatLng(35.409476, 127.396059);
                    map = new kakao.maps.Map(mapContainer, mapOption); // 지도 객체 생성
                    console.log("지도 초기화 완료");

                    // 주소 입력 창 활성화
                    searchInput.removeEventListener("keypress", handleAddressEnter); // 삭제되면서 콜백함수 자동 수행
                    searchInput.addEventListener("keypress", handleAddressEnter);

                    break;
                }
            }
        }
    });

    MapObserver.observe(document.body, { childList: true, subtree: true });

    document.body.addEventListener("click", e => {
        console.log("click event  :: " + e.className + " " + e.target.className + " " + e.target.tagName);

        // // 25.03.11 조승찬 추가 시작 :: 맵 화면 확대 축소
        if (e.target.tagName == "IMG") {
            if (e.target.closest("#fullMap")) {
                if (e.target.closest("#mapContainer").style.position === "fixed") {
                    e.target.closest("#mapContainer").style.position = "relative";
                    e.target.closest("#mapContainer").style.width = "100%";
                    e.target.closest("#mapContainer").style.height = "40vh";
                    e.target.closest("#mapContainer").style.zIndex = ""; // 맵이 다른 요소 위에 오도록 설정한거 해제
                    e.target.style.position = "absolute";
                    // 지도의 중심을 새로운 좌표로 설정
                    map.relayout();
                    map.setCenter(initialCenter);
                } else {
                    e.target.closest("#mapContainer").style.position = "fixed";
                    e.target.closest("#mapContainer").style.top = "0";
                    e.target.closest("#mapContainer").style.left = "0";
                    e.target.closest("#mapContainer").style.width = "100%";
                    e.target.closest("#mapContainer").style.height = "100vh";
                    e.target.closest("#mapContainer").style.zIndex = "1000"; // 맵이 다른 요소 위에 오도록 설정
                    e.target.style.position = "fixed";
                    // 지도의 중심을 새로운 좌표로 설정
                    map.relayout();
                    map.setCenter(initialCenter);
                }
            } else if (e.target.classList.contains("closeBtn")) {
                let index = e.target.dataset.index;
                console.log("close button   "+index);
                removeDestination(index);
            }
        }
        // // 2025.03.11 조승찬 추가 끝 :: 화면 확장 축소

        //  25.03.12 조승찬 추가 시작 :: X 버튼 이벤트 위임 (삭제 기능)
        // if (e.target.classList.contains("closeBtn")) {
        //         let index = e.target.dataset.index;
        //         removeDestination(index);
        // }
        //  25.03.12 조승찬 추가 시작 :: X 버튼 이벤트 위임 (삭제 기능)

    });

});

// 25.03.12 조승찬 추가 시작  :: 지도 수정시 작동
// 주소 입력 이벤트 리스너 (중복 실행 방지)
function handleAddressEnter(event) {
    if (event.key === "Enter") {
        console.log("handleAddressEnter 들어옴");
        event.preventDefault();
        addDestination();
    }
}

// 목적지 추가 함수
function addDestination() {

    if (tourSpots.length >= 10) {
        alert("목적지는 최대 10개까지만 추가할 수 있습니다! 🚫");
        return;
    }

    let inputAddress = searchInput.value.trim();
    if (!inputAddress) {
        alert("주소를 입력하세요!");
        return;
    }

    let tempAddress = inputAddress;
    searchInput.value = "";
    searchInput.placeholder = "제목을 입력하세요.";

    searchInput.removeEventListener("keypress", handleAddressEnter);
    searchInput.addEventListener("keypress", handleTitleEnter);

    setTimeout(() => {
        searchInput.focus();
    }, 10);

    function handleTitleEnter(event) {
        if (event.key === "Enter") {
            event.preventDefault();
            let inputTitle = searchInput.value.trim();
            if (!inputTitle) {
                alert("제목을 입력하세요!");
                return;
            }

            searchInput.value = "";
            searchInput.placeholder = "주소를 입력하세요.";
            searchInput.removeEventListener("keypress", handleTitleEnter);
            searchInput.addEventListener("keypress", handleAddressEnter);

            geocoder.addressSearch(tempAddress, (result, status) => {
                if (status === kakao.maps.services.Status.OK) {
                    let newLatLng = new kakao.maps.LatLng(
                        result[0].y,
                        result[0].x
                    );
                    let spotNumber = tourSpots.length + 1;

                    let newSpot = {
                        number: spotNumber,
                        title: inputTitle,
                        address: tempAddress,
                        latlng: newLatLng,
                    };

                    let index = tourSpots.length;
                    tourSpots.push(newSpot);

                    let dotOverlay = new kakao.maps.CustomOverlay({
                        content: `<span class="dot"></span>`,
                        position: newLatLng,
                        zIndex: 1,
                    });
                    dotOverlay.setMap(map);
                    dotOverlays.push(dotOverlay);

                    let textOverlay = new kakao.maps.CustomOverlay({
                        content: createOverlayContent(newSpot, index),
                        position: newLatLng,
                        yAnchor: 1.2,
                        zIndex: 2,
                    });
                    textOverlay.setMap(map);
                    textOverlays.push(textOverlay);

                    addDestinationToList(newSpot, index);
                    map.setCenter(newLatLng);
                    updateRoute();
                } else {
                    alert("🚫 주소를 찾을 수 없습니다. 다시 입력해주세요.");
                }
            });

            searchInput.value = "";
        }
    }
}

// 지도에 표시할 말풍선(목적지 태그) 생성 함수
function createOverlayContent(spot, index) {
    return `<div class="dotOverlay addedDestination">
                <b>${spot.number}. ${spot.title}</b><br>
                ${spot.address}
                <img src="http://t1.daumcdn.net/localimg/localimages/07/mapjsapi/2x/bt_close.gif" 
                     class="closeBtn" 
                     data-index="${index}" 
                     style="cursor:pointer; vertical-align: middle; margin-left: 5px; margin-bottom: 2px; width: 14px; height: 14px;">
            </div>`;
}

// 목적지 리스트 UI에 추가하는 함수
function addDestinationToList(spot, index) {
    let listItem = document.createElement("li");
    listItem.style.display = "flex";
    listItem.style.justifyContent = "space-between";
    listItem.style.padding = "5px";
    listItem.style.borderBottom = "1px solid #ddd";

    let spotText = document.createElement("span");
    spotText.innerHTML = `<b>${spot.number}. ${spot.title}</b><br>${spot.address}`;
    spotText.style.cursor = "pointer";

    spotText.addEventListener("click", () => map.setCenter(spot.latlng));

    let deleteBtn = document.createElement("img");
    deleteBtn.src =
        "http://t1.daumcdn.net/localimg/localimages/07/mapjsapi/2x/bt_close.gif";
    deleteBtn.classList.add("closeBtn");
    deleteBtn.dataset.index = index;
    deleteBtn.style.cursor = "pointer";
    deleteBtn.style.marginLeft = "5px";
    deleteBtn.style.marginBottom = "2px";
    deleteBtn.style.width = "14px";
    deleteBtn.style.height = "14px";

    listItem.appendChild(spotText);
    listItem.appendChild(deleteBtn);
    destinationList.appendChild(listItem);
}

// 목적지 삭제 함수 (마지막 항목도 삭제 가능 & 번호 재정렬)
function removeDestination(index) {
    index = parseInt(index);

    if (index >= 0 && index < tourSpots.length) {
        // 지도에서 오버레이 및 라인 제거
        dotOverlays[index].setMap(null);
        textOverlays[index].setMap(null);

        // 배열에서도 삭제
        tourSpots.splice(index, 1);
        dotOverlays.splice(index, 1);
        textOverlays.splice(index, 1);

        // UI 목록에서도 삭제
        let listItems = document.querySelectorAll("#destinationList li");
        listItems[index].remove();
        listItems.forEach((e,i) => {  // 인덱스 번호 수정
            e.dataset.index = i;
        })

        // 삭제 후 번호 다시 정렬
        tourSpots.forEach((spot, i) => {
            spot.number = i + 1;
        });

        // 전체 UI 다시 렌더링 (정확한 번호 정렬 보장)
        refreshDestinationList();

        // 경로 업데이트
        updateRoute();
    }
}

// 목적지 목록 UI 새로고침 (번호 재정렬)
function refreshDestinationList() {
    destinationList.innerHTML = "";
    tourSpots.forEach((spot, index) => {
        addDestinationToList(spot, index);
    });
}
// 목적지 삭제 함수 (마지막 항목도 삭제 가능 & 번호 재정렬)

// 지도 경로 업데이트 (총 거리 정상 표시)
function updateRoute() {
    if (clickLine) clickLine.setMap(null);
    if (totalDistanceOverlay) totalDistanceOverlay.setMap(null);

    let linePath = tourSpots.map((spot) => spot.latlng);

    if (tourSpots.length === 0) {
        totalDistanceInput.value = "";
        return;
    }

    clickLine = new kakao.maps.Polyline({
        map: map,
        path: linePath,
        strokeWeight: 3,
        strokeColor: "#db4040",
        strokeOpacity: 1,
        strokeStyle: "solid",
    });

    let totalDistance = (clickLine.getLength() / 1000).toFixed(1);

    totalDistanceInput.value = `${totalDistance} km`;
}
// 25.03.12 조승찬 추가 끝  :: 지도 수정시 작동

// 마커 생성 함수
function createMarkers(tourSpots, map) {  // displayCircleDot 에 기능 있음
    console.log("create markers "+tourSpots.length);
    tourSpots.forEach((spot, i) => {

        let dotOverlay = new kakao.maps.CustomOverlay({
            content: `<span class="dot"></span>`,
            position: spot.latlng,
            zIndex: 1,
        });
        dotOverlay.setMap(map);
        dotOverlays.push(dotOverlay);

        let textOverlay = new kakao.maps.CustomOverlay({
            content: createOverlayContent(spot, spot.number-1), // 인덱스를 맞춰주기 위해 1을 빼줌
            position: spot.latlng,
            yAnchor: 1.2,
            zIndex: 2,
        });
        textOverlay.setMap(map);
        textOverlays.push(textOverlay);

    });
}

// 선을 그리는 함수
function drawLine(tourSpots, map) {
    let linePath = [];
    tourSpots.forEach((tourSpot) => {
        linePath.push(tourSpot.latlng);
    });

    clickLine = new kakao.maps.Polyline({
        map: map,
        path: linePath,
        strokeWeight: 3,
        strokeColor: "#db4040",
        strokeOpacity: 1,
        strokeStyle: "solid",
    });

    // 각 지점에 대한 거리 정보 표시
    let distance = (Math.round(clickLine.getLength()) / 1000).toFixed(1);
    let path = clickLine.getPath();
    displayCircleDot(tourSpots[0].latlng, distance, map);

    let content = getTimeHTML(distance); // 거리와 시간을 HTML로 변환
    showDistance(content, path[path.length - 1], map);
}

// 거리와 시간을 표시하는 함수
function getTimeHTML(distance) {
    // 도보 시간 계산
    let walkkTime = (distance / 0.067) | 0;
    let walkHour = "",
        walkMin = "";

    if (walkkTime > 60) {
        walkHour =
            '<span class="number">' +
            Math.floor(walkkTime / 60) +
            "</span>시간 ";
    }
    walkMin = '<span class="number">' + (walkkTime % 60) + "</span>분";

    // 자전거 시간 계산
    let bycicleTime = (distance / 0.227) | 0;
    let bycicleHour = "",
        bycicleMin = "";

    if (bycicleTime > 60) {
        bycicleHour =
            '<span class="number">' +
            Math.floor(bycicleTime / 60) +
            "</span>시간 ";
    }
    bycicleMin = '<span class="number">' + (bycicleTime % 60) + "</span>분";

    let content = '<ul class="dotOverlay distanceInfo">';
    content += "    <li>";
    content +=
        '        <span class="label">총거리</span><span class="number">' +
        distance +
        "</span>Km";
    content += "    </li>";
    content += "    <li>";
    content += '        <span class="label">도보</span>' + walkHour + walkMin;
    content += "    </li>";
    content += "    <li>";
    content +=
        '        <span class="label">자전거</span>' + bycicleHour + bycicleMin;
    content += "    </li>";
    content += "</ul>";

    return content;
}

// 거리 표시 함수
function showDistance(content, position, map) {   // displayCircleDot 에 유사 기능 있음
    const distanceOverlay = new kakao.maps.CustomOverlay({
        map: map,
        content: content,
        position: position,
        xAnchor: 0,
        yAnchor: -1,
        zIndex: 3,
    });
}

// 지점에 빨간 동그라미 표시하는 함수
function displayCircleDot(position, distance, map) {   //createMarkers, showDistance 를 합친 기능과 유사
    const circleOverlay = new kakao.maps.CustomOverlay({
        content: '<span class="dot"></span>',
        position: position,
        zIndex: 1,
    });
    circleOverlay.setMap(map);

    if (distance > 0) {
        const distanceOverlay = new kakao.maps.CustomOverlay({
            content:
                '<div class="dotOverlay">거리 <span class="number">' +
                distance +
                "</span>Km</div>",
            position: position,
            yAnchor: 2.2,
            zIndex: 2,
        });
        distanceOverlay.setMap(map);
    }
}
