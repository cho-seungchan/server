const receiveMessageWrap = document.getElementById("receiveMessageWrap");
const pageWrap = document.getElementById("page-wrap");

messageService.getReceiveList(messageLayout.showList)

pageWrap.addEventListener("click", (e) => {
    let targetButton = e.target;

    if (targetButton.tagName.toLowerCase() === "img") {
        targetButton = targetButton.closest("button");
    }

    if (targetButton && targetButton.classList.contains("Button-bqxlp0-0")) {
        const page = targetButton.id;

        messageService.getReceiveList(messageLayout.showList, page);
    }
});

//===========================//
document.addEventListener("DOMContentLoaded", () => {
    const receiveMessageWrap = document.getElementById("receiveMessageWrap");

    receiveMessageWrap.addEventListener("click", async (e) => {
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
            const senderEmail = parentDiv.dataset.senderEmail;
            const messageId = parentDiv.dataset.id; // 메시지 ID 가져오기

            showList = document.createElement("div");
            showList.classList.add("NoticeEntity__Content-sc-1x9h6uc-5", "kOSXdV", "showList");
            showList.innerHTML = `
                <span>${clickedElement.textContent.trim()}</span>
                <div id="fileContainer-${messageId}" style="display: none; margin-top: 10px;">
                    <a id="fileLink-${messageId}" href="#" target="_blank">첨부 파일</a>
                </div>
                <button class="answeraButton appearButton" data-sender-email="${senderEmail}">답장</button>
                <button class="deleteButton appearButton" data-id="${messageId}">삭제</button>
            `;

            parentDiv.after(showList);

            // 📌 AJAX로 해당 메시지의 파일 정보 불러오기
            try {
                const response = await fetch(`/my-page/files/${messageId}`);
                const file = await response.json();

                if (file && file.fileName) {
                    const fileContainer = document.getElementById(`fileContainer-${messageId}`);
                    const fileLink = document.getElementById(`fileLink-${messageId}`);

                    fileLink.href = `/uploads/${file.fileName}`;
                    fileLink.textContent = file.fileName;
                    fileContainer.style.display = "block"; // 파일이 있으면 보이게 설정
                }
            } catch (error) {
                console.error("파일 조회 실패:", error);
            }
        } else {
            showList.style.display = showList.style.display === "block" ? "none" : "block";
        }
    });

    receiveMessageWrap.addEventListener("click", (e) => {
        if (e.target.classList.contains("answeraButton")) {
            const senderEmail = e.target.dataset.senderEmail;
            if (!senderEmail) {
                alert("보낼 대상의 이메일을 찾을 수 없습니다.");
                return;
            }
            window.location.href = `/my-page/messageWrite?receiver=${encodeURIComponent(senderEmail)}`;
        }
    });

    receiveMessageWrap.addEventListener("click", (e) => {
        if (e.target.classList.contains("deleteButton")) {
            const messageId = e.target.dataset.id || e.target.closest(".userListDiv")?.dataset.id;
            if (!messageId) {
                alert("삭제할 메시지를 찾을 수 없습니다.");
                return;
            }

            fetch(`/my-page/deleteReceiveMessage`, {
                method: "POST", // DELETE → POST 변경
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: new URLSearchParams({ id: messageId }) // ID를 요청 본문에 포함
            })
                .then(response => response.json())
                .then(isDeleted => {
                    if (isDeleted) {
                        alert("메시지가 삭제되었습니다.");
                        location.reload();
                    } else {
                        alert("삭제 실패");
                    }
                })
                .catch(error => alert("삭제 중 오류가 발생했습니다."));
        }
    });
});
//===========================//

// document.addEventListener("DOMContentLoaded", () => {
//     const receiveMessageWrap = document.getElementById("receiveMessageWrap");
//
//     receiveMessageWrap.addEventListener("click", (e) => {
//         const clickedElement = e.target.closest(".hiddenText");
//         if (!clickedElement) return;
//
//         const parentDiv = clickedElement.closest(".userListDiv");
//         if (!parentDiv) return;
//
//         document.querySelectorAll(".showList").forEach((list) => {
//             if (list !== parentDiv.nextElementSibling) {
//                 list.style.display = "none";
//             }
//         });
//
//         let showList = parentDiv.nextElementSibling;
//
//         if (!showList || !showList.classList.contains("showList")) {
//             const senderEmail = parentDiv.dataset.senderEmail;
//             showList = document.createElement("div");
//             showList.classList.add("NoticeEntity__Content-sc-1x9h6uc-5", "kOSXdV", "showList");
//             showList.innerHTML = `
//                 <span>${clickedElement.textContent.trim()}</span>
//                 <button class="answeraButton appearButton" data-sender-email="${senderEmail}">답장</button>
//                 <button class="deleteButton appearButton" data-id="${parentDiv.dataset.id}">삭제</button>
//             `;
//             parentDiv.after(showList);
//         } else {
//             showList.style.display = showList.style.display === "block" ? "none" : "block";
//         }
//     });
//
//     receiveMessageWrap.addEventListener("click", (e) => {
//         if (e.target.classList.contains("answeraButton")) {
//             const senderEmail = e.target.dataset.senderEmail;
//
//             if (!senderEmail) {
//                 alert("보낼 대상의 이메일을 찾을 수 없습니다.");
//                 return;
//             }
//
//             window.location.href = `/my-page/messageWrite?receiver=${encodeURIComponent(senderEmail)}`;
//         }
//     });
//
//     receiveMessageWrap.addEventListener("click", (e) => {
//         if (e.target.classList.contains("deleteButton")) {
//             const messageId = e.target.dataset.id || e.target.closest(".userListDiv")?.dataset.id;
//
//             if (!messageId) {
//                 alert("삭제할 메시지를 찾을 수 없습니다.");
//                 return;
//             }
//
//             fetch(`/my-page/deleteReceiveMessage`, {
//                 method: "POST", // DELETE → POST 변경
//                 headers: { "Content-Type": "application/x-www-form-urlencoded" },
//                 body: new URLSearchParams({ id: messageId }) // ID를 요청 본문에 포함
//             })
//                 .then(response => response.json())
//                 .then(isDeleted => {
//                     if (isDeleted) {
//                         alert("메시지가 삭제되었습니다.");
//                         location.reload();
//                     } else {
//                         alert("삭제 실패");
//                     }
//                 })
//                 .catch(error => alert("삭제 중 오류가 발생했습니다."));
//         }
//     });
// });
