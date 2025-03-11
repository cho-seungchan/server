
// 2025.03.11  DOM 동적 생성에 맞게 지도를 그릴 수 있도록 수정
// document.addEventListener("DOMContentLoaded", function () {
//     var mapContainer = document.querySelector(".AppLayout_contents__YmI3N #map"),
//         mapOption = {
//             center: new kakao.maps.LatLng(35.409476, 127.396059), // 지도의 중심좌표
//             level: 9, // 지도의 확대 레벨
//         };
//     let initialCenter = new kakao.maps.LatLng(35.409476, 127.396059);
//     var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
// });
// 지도 초기 설정
var mapContainer, mapOption, map, geocoder, remains;
var tourSpots = [];
var positions = [];
document.addEventListener("DOMContentLoaded", function () {
    const observer = new MutationObserver((mutationsList) => {
        for (const mutation of mutationsList) {
            if (mutation.type === "childList") {
                mapContainer = document.getElementById("map");
                if (mapContainer) {
                    console.log("#map 요소를 찾았습니다!");
                    observer.disconnect();  // 조회가 계속 될 때 계속 감시하기 위해서

                    // 지도 초기화
                    mapOption = {
                        center: new kakao.maps.LatLng(35.409476, 127.396059), // 지도 중심 좌표
                        level: 9, // 지도 확대 레벨
                    };
                    map = new kakao.maps.Map(mapContainer, mapOption); // 지도 객체 생성
                    console.log("지도 초기화 완료");

                    tourSpots = [
                        { name: "1. 허브마을 채마루", address: "남원시 원천로 37" },
                        { name: "2. 광한루원", address: "남원시 요천로 1447" },
                        { name: "3. 김병종미술관", address: "남원시 함파우길 65-14" },
                        { name: "4. 지리산 허브밸리", address: "남원시 바래봉길 24" },
                        { name: "5. 구서도역", address: "남원시 서도길 32" },
                        { name: "6. 혼불문학관", address: "남원시 노봉안길52" },
                    ];

                    geocoder = new kakao.maps.services.Geocoder();
                    remains = tourSpots.length;

                    // Promise.all()을 사용하여 모든 주소 검색이 끝난 후 실행
                    Promise.all(
                        tourSpots.map((spot) => {
                            return new Promise((resolve) => {
                                geocoder.addressSearch(spot.address, (result, status) => {
                                    if (status === kakao.maps.services.Status.OK) {
                                        spot.latlng = new kakao.maps.LatLng(
                                            Math.floor(result[0].y * 1000000) / 1000000,
                                            Math.floor(result[0].x * 1000000) / 1000000
                                        );
                                    } else {
                                        console.warn(`${spot.name} 좌표 변환 실패`);
                                    }
                                    resolve(); // 변환 완료
                                });
                            });
                        })
                    ).then(() => {
                        console.log("모든 좌표 변환 완료");

                        // 좌표 기반 마커 생성
                        createMarkers(tourSpots, map);

                        // 선 그리기
                        drawLine(tourSpots, map);
                    });

                    break;
                }
            }
        }
    });

    observer.observe(document.body, { childList: true, subtree: true });

    const destinationList = document.querySelector(".AppLayout_contents__YmI3N #destinationList");
    document.querySelectorAll(".AppLayout_contents__YmI3N #destinationList li").forEach( (item, i) => {
        const span = item.querySelector("span");
        const name = span.querySelector("b").textContent; // "1. 채마우" 같은 이름 추출
        const address = span.innerHTML.split("<br>")[1].trim(); // "남원시 원천로 37" 추출
        console.log(name+" "+address);
        // tourSpots 배열에 객체 추가
        tourSpots.push({
            name: name,
            address: address,
        });
    });
    tourSpots.forEach(e=>{console.log(e);})

    document.body.addEventListener("click", e => {
        console.log("click event  :: " + e.className + " " + e.target.className + " " + e.target.tagName);

        // // 25.03.11 조승찬 추가 시작 :: 맵 화면 확대 축소
        // if (e.target.id = "fullMap") {
        //     if (mapContainer.style.position === "fixed") {
        //         mapContainer.style.position = "relative";
        //         mapContainer.style.width = "100%";
        //         mapContainer.style.height = "40vh";
        //         mapContainer.style.zIndex = ""; // 맵이 다른 요소 위에 오도록 설정한거 해제
        //         document.querySelector("#fullMap").style.position = "absolute";
        //         // 지도의 중심을 새로운 좌표로 설정
        //         map.relayout();
        //         map.setCenter(initialCenter);
        //     } else {
        //         mapContainer.style.position = "fixed";
        //         mapContainer.style.top = "0";
        //         mapContainer.style.left = "0";
        //         mapContainer.style.width = "100%";
        //         mapContainer.style.height = "100vh";
        //         mapContainer.style.zIndex = "1000"; // 맵이 다른 요소 위에 오도록 설정
        //         document.querySelector("#fullMap").style.position = "fixed";
        //         // 지도의 중심을 새로운 좌표로 설정
        //         map.relayout();
        //         map.setCenter(initialCenter);
        //     }
        // };
        // // 2025.03.11 조승찬 추가 끝 :: 화면 확장 축소

    });

});

// 마커 생성 함수
function createMarkers(tourSpots, map) {
    tourSpots.forEach((spot) => {
        const iwContent = `<div style="padding:5px;">${spot.name}</div>`; // 인포윈도우 내용
        const iwPosition = spot.latlng; // 인포윈도우 위치
        const iwRemoveable = true; // 인포윈도우 제거 가능

        // 인포윈도우 생성
        const infowindow = new kakao.maps.InfoWindow({
            map: map,
            position: iwPosition,
            content: iwContent,
            removable: iwRemoveable,
        });
    });
}

// 선을 그리는 함수
function drawLine(tourSpots, map) {
    let linePath = [];
    tourSpots.forEach((tourSpot) => {
        linePath.push(tourSpot.latlng);
    });

    const clickLine = new kakao.maps.Polyline({
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
function showDistance(content, position, map) {
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
function displayCircleDot(position, distance, map) {
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
