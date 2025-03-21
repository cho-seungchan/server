const payButton = document.querySelector(".pay-button");

payButton.addEventListener("click", (e) => {
    payService.kakaoPay();
})

// 전액 버튼 누르면 반영되게
discountWrapper.addEventListener("click", (e)=> {
    if(e.target.classList.contains("cVMMHP")){
        discountAmount = parseInt(document.querySelector(".discount").textContent.replace(/,/g, ""), 10);
        paymentAmount = parseInt(document.querySelector(".dxsibZ").textContent.replace(/,/g, ""), 10);

        document.querySelector(".Coupon_Input").value = NumberWithCommas(discountAmount);
        document.querySelector(".peakcourseAmount").textContent = NumberWithCommas(paymentAmount - discountAmount);
        document.querySelector(".pointspan").textContent = NumberWithCommas((paymentAmount - discountAmount) / 100);
    }
})
// 전액 버튼 누르면 반영되게