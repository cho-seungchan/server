// 2025.03.21 조승찬 작성

document.querySelector(".real-feed").addEventListener("click", e=>{
    const planId = document.querySelector(".planId").textContent.trim();
    const form = document.createElement("form");
    form.setAttribute("method", "GET")
    form.setAttribute("action", "/feeds/real-write?"+planId)

    // 폼 제출
    document.body.appendChild(form); // 폼을 body에 추가
    form.submit(); // 폼 제출
})