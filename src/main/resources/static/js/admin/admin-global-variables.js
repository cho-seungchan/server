// 맵 관리를 위한 변수 들
var mapContainer, mapOption, map, geocoder, remains;
var tourSpots = [];
let mapObserverPause = false;   // 동적감시 요소를 일시중단, 재시작할 플래그
let listObserverPause = false;  // 동적감시 요소를 일시중단, 재시작할 플래그
let searchInput, clickLine, totalDistanceOverlay, totalDistanceInput, destinationList, initialCenter;
let dotOverlays = [];
let textOverlays = [];