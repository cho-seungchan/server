const sendMessageWrap = document.getElementById("sendMessageWrap");
const pageWrap = document.getElementById("page-wrap");

messageService.getSendList(messageLayout.showList)

pageWrap.addEventListener("click", (e) => {
    let targetButton = e.target;

    if (targetButton.tagName.toLowerCase() === "img") {
        targetButton = targetButton.closest("button");
    }

    if (targetButton && targetButton.classList.contains("Button-bqxlp0-0")) {
        const page = targetButton.id;

        messageService.getSendList(messageLayout.showList, page);
    }
});

// =========================================== //

document.addEventListener("DOMContentLoaded", () => {
    const sendMessageWrap = document.getElementById("sendMessageWrap");

    sendMessageWrap.addEventListener("click", (e) => {
        const clickedElement = e.target.closest(".hiddenText");
        if (!clickedElement) return;

        const parentDiv = clickedElement.closest(".userListDiv");
        if (!parentDiv) return;

        document.querySelectorAll(".showList").forEach((list) => {
            if (list !== parentDiv.nextElementSibling) {
                list.style.display = "none";
            }
        });

        let showList = parentDiv.nextElementSibling;

        if (!showList || !showList.classList.contains("showList")) {
            showList = document.createElement("div");
            showList.classList.add("NoticeEntity__Content-sc-1x9h6uc-5", "kOSXdV", "showList");
            showList.innerHTML = `
                <span>${clickedElement.textContent.trim()}</span>
                <button class="answeraButton appearButton">답장</button>
                <button class="deleteButton appearButton">삭제</button>
            `;
            parentDiv.after(showList);
        } else {
            showList.style.display = showList.style.display === "block" ? "none" : "block";
        }
    });
});

