document.querySelectorAll(".heartButton").forEach((button) => {
    button.addEventListener("click", function (event) {
        // 부모의 이벤트를 막아주는 메소드
        event.preventDefault();
        event.stopPropagation();

        const img = this.querySelector("img");
        if (img) {
            img.src = img.src.includes("red.svg")
                ? "/images/main/white.svg"
                : "/images/main/red.svg";
        }
    });
});
