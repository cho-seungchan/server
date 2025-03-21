document.addEventListener("DOMContentLoaded", function () {
    const memberElement = document.querySelector(".UserInfo__Nickname-bt0psx-3 span");
    const memberId = memberElement ? memberElement.getAttribute("data-id") : null;

    if (!memberId || isNaN(memberId)) {
        return;
    }

    fetch(`/my-page/normalCourseParticipationCount?memberId=${memberId}`)
        .then(response => response.json())
        .then(count => updateBadge(".course_badge", count))
        .catch(() => {});

    fetch(`/my-page/volunteerCourseParticipationCount?memberId=${memberId}`)
        .then(response => response.json())
        .then(count => updateBadge(".volunteer_badge", count))
        .catch(() => {});

    function updateBadge(selector, count) {
        let badgeElement = document.querySelector(selector);
        let countElement = badgeElement.querySelector(".second_comment");

        if (count > 0) {
            badgeElement.classList.remove("none_complete_badge");
            countElement.classList.remove("noneCount");
            countElement.textContent = `${count}회`;
        }
    }
});


document.addEventListener("DOMContentLoaded", function () {
    fetch(`/my-page/recentCourse`)
        .then(response => response.json())
        .then(data => {
            if (data && data.courseName) {
                document.querySelector(".courceName").textContent = data.courseName;
                document.querySelector("#recentCourseImg").src = data.courseFilePath;

                // 최근 코스 상세 페이지 링크 설정 (링크가 있을 경우)
                let recentCourseLink = document.querySelector("#recentCourseLink");
                // 여기 코스 링크 수정 필요
                recentCourseLink.href = `/course/detail/${data.courseType}`;
            }
        })
        .catch(error => console.error("❌ 최근 코스 데이터 가져오기 실패:", error));
});