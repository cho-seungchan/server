//2025.03.08 조승찬
// 코스 목록 생성하는 함수
function addCourseDetail(course) {

    // 코스 제목 생성
    document.querySelector(".TextField_field__YTJkY").innerHTML = `
    <input placeholder="제목을 입력해 주세요" maxlength="40" type="text" class="Input_input__M2Q3Y Input_lg__MDE4M"
           aria-invalid="false" value="${course.courseName}"/>
    `;

    // 봉사 코스일 경우
    if (course.courseIsVolunteer == `Y`) {
        // 시작일, 종료일, 모집 마감일 생성
        document.querySelector(".DurationOfTourContainer hidden").innerHTML = `
        <input type="date" placeholder="시작일"
               className="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh startdate" value="${course.volunteerStartDate}"
               style="/>
        <span>~</span>
        <input type="date" placeholder="종료일"
               className="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh enddate" value="${course.volunteerEndDate}"
               style="/>
        <span>마감일</span>
        <input type="date" placeholder="모집 마감일"
               className="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh deadline" value="${course.volunteerDeadline}"
               style="/>
        <button type="button" className="CurrentProfile__MoreButton-sc-1u92qay-6 FvtMb">
            <img
                src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='4' height='17' viewBox='0 0 4 17'%3E %3Cpath fill='%23999' fill-rule='evenodd' d='M1.57 14a1.5 1.5 0 110 3 1.5 1.5 0 010-3zm0-7a1.5 1.5 0 110 3 1.5 1.5 0 010-3zm0-7a1.5 1.5 0 110 3 1.5 1.5 0 010-3z'/%3E %3C/svg%3E"
                alt="더보기"/>
        </button>
        `;

        // 포함, 불포함, 준비물 생성
        document.querySelector(".bmexYYinput").innerHTML = `
            <input placeholder="포함 사항 (0/10)"
                   className="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh include"/>
            <input placeholder="불포함 사항 (0/10)"
                   className="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh exclude"/>
            <input placeholder="준비물 (0/10)"
                   className="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh prepare"/>
        `;


        // 포함 사항 생성
        if (course.includeContents.length > 0) {
            const bDBbNifirst = document.querySelector(".bDBbNifirst");
            bDBbNifirst.innerHTML = `<header class="Article__Header-sc-1mmkltm-0 gScFGo">
                                        <hgroup>
                                            <h2 class="Article__Title-sc-1mmkltm-1 bZNoYF">포함 사항</h2>
                                        </hgroup>
                                      </header>
                                      <div class="Stuff__StuffContainer-sc-8zlrc8-0 iXEvmI"></div>`;

            parentDiv = bDBbNifirst.querySelector(".iXEvmI");
            course.prepaContents.forEach(e => {
                const firstchildDiv = document.createElement("div");
                firstchildDiv.className = "Tag__RoundTag-sxb61j-1 jXxsiv";
                firstchildDiv.innerHTML = `<span>${e.includeContent}</span>
                     <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='nonzero' stroke='%23999' stroke-linecap='square'%3E %3Cpath d='M11.828 6.172l-5.656 5.656M11.828 11.828L6.172 6.172'/%3E %3C/g%3E %3C/svg%3E" alt="delete tags item">`;
                parentDiv.appendChild(firstchildDiv);
            });

            const gcqwwhinclude = document.querySelector(".gcqwwh.include");
            gcqwwhinclude.value = "";
            gcqwwhinclude.placeholder = `포함 사항 (${course.includeContents.length}/10)`;
        }

        // 불포함 사항 생성
        if (course.excludeContents.length > 0) {
            const bDBbNisecond = document.querySelector(".bDBbNisecond");
            bDBbNisecond.innerHTML = `<header class="Article__Header-sc-1mmkltm-0 gScFGo">
                                    <hgroup>
                                        <h2 class="Article__Title-sc-1mmkltm-1 bZNoYF">불포함 사항</h2>
                                    </hgroup>
                                  </header>
                                  <div class="Stuff__StuffContainer-sc-8zlrc8-0 iXEvmI"></div>`;

            parentDiv = bDBbNisecond.querySelector(".iXEvmI");
            course.excludeContents.forEach(e => {
                const secondchildDiv = document.createElement("div");
                secondchildDiv.className = "Tag__RoundTag-sxb61j-1 jXxsiv";
                secondchildDiv.innerHTML = `<span>${e.excludeContent}</span>
                 <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='nonzero' stroke='%23999' stroke-linecap='square'%3E %3Cpath d='M11.828 6.172l-5.656 5.656M11.828 11.828L6.172 6.172'/%3E %3C/g%3E %3C/svg%3E" alt="delete tags item">`;
                parentDiv.appendChild(secondchildDiv);
            });

            const gcqwwhinclude = document.querySelector(".gcqwwh.exclude");
            gcqwwhinclude.value = "";
            gcqwwhinclude.placeholder = `포함 사항 (${course.excludeContents.length}/10)`;
        }

        // 준비물 생성
        if (course.prepaContents.length > 0) {
            const bDBbNithird = document.querySelector(".bDBbNithird");
            bDBbNithird.innerHTML = `<header class="Article__Header-sc-1mmkltm-0 gScFGo">
                                    <hgroup>
                                        <h2 class="Article__Title-sc-1mmkltm-1 bZNoYF">불포함 사항</h2>
                                    </hgroup>
                                  </header>
                                  <div class="Stuff__StuffContainer-sc-8zlrc8-0 iXEvmI"></div>`;

            parentDiv = bDBbNithird.querySelector(".iXEvmI");
            course.prepaContents.forEach(e => {
                const thirdchildDiv = document.createElement("div");
                thirdchildDiv.className = "Tag__RoundTag-sxb61j-1 jXxsiv";
                thirdchildDiv.innerHTML = `<span>${e.prepaContent}</span>
                 <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='18' height='18' viewBox='0 0 18 18'%3E %3Cg fill='none' fill-rule='nonzero' stroke='%23999' stroke-linecap='square'%3E %3Cpath d='M11.828 6.172l-5.656 5.656M11.828 11.828L6.172 6.172'/%3E %3C/g%3E %3C/svg%3E" alt="delete tags item">`;
                parentDiv.appendChild(thirdchildDiv);
            });

            const gcqwwhinclude = document.querySelector(".gcqwwh.prepare");
            gcqwwhinclude.value = "";
            gcqwwhinclude.placeholder = `포함 사항 (${course.prepareContents.length}/10)`;
        }

    }

    // 기타 정보 생성
    document.querySelector(".NumberOfPerson").innerHTML = `
        <input placeholder="총 거리" className="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh max" 
               value="${course.courseDistance}"/>
        <input placeholder="일정" className="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh min"
               value="${course.courseSchedule}"/>
        <input placeholder="테마" className="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh cost"
               value="${course.courseTheme}" style="background-color: rgba( 211, 211, 211, 0.5 );"/>
    `;







    // 경로 정보 생성
    if (course.paths.length > 0) {
        const destinationList = document.querySelector("#destinationList");

        course.paths.forEach((e, i) => {

            let listItem = document.createElement("li");
            listItem.style.display = "flex";
            listItem.style.justifyContent = "space-between";
            listItem.style.padding = "5px";
            listItem.style.borderBottom = "1px solid #ddd";

            let spotText = document.createElement("span");
            spotText.innerHTML = `<b>${i+1}. ${e.pathName}</b><br>${e.pathAddress}`;
            spotText.style.cursor = "pointer";

            spotText.addEventListener("click", () => map.setCenter(spot.latlng));

            let deleteBtn = document.createElement("img");
            deleteBtn.src =
                "http://t1.daumcdn.net/localimg/localimages/07/mapjsapi/2x/bt_close.gif";
            deleteBtn.classList.add("closeBtn");
            deleteBtn.dataset.index = i+1;
            deleteBtn.style.cursor = "pointer";
            deleteBtn.style.marginLeft = "5px";
            deleteBtn.style.marginBottom = "2px";
            deleteBtn.style.width = "14px";
            deleteBtn.style.height = "14px";

            listItem.appendChild(spotText);
            listItem.appendChild(deleteBtn);
            destinationList.appendChild(listItem);
        });
    }

    // 내용 생성
    document.querySelector(".StorySummaryField_container__MWQ0N").innerHTML = `
        <textarea maxLength="1000" placeholder="내용 입력" className="Textarea_textarea__MWJjO" 
            value="${course.courseContent}"> </textarea>
    `;

};
