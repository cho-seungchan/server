const infoWrap = document.querySelector(".info-wrap");
const titleWrap = document.querySelector(".title-wrap");
const priceWrap = document.querySelector(".price-wrap")
const discountWrapper = document.querySelector(".discountWrapper")

const price = plan.planPrice;
const point = loginMember.memberPoint;
console.log(point)
const startDate =new Date(plan.planStartDate);

const formatStartDate = `${startDate.getMonth()+1}월 ${startDate.getDate()}일`;
const formatPrice = price.toLocaleString();
const formatPoint = point.toLocaleString();

titleWrap.innerHTML = `
<div class="ProductInfo__Title-sc-1af61ub-3 htnJlC">
    [${formatStartDate} 출발✨] ${plan.planName}🚎
</div>
`;

infoWrap.innerHTML = `
<p>${formatStartDate}</p>
<p>결제 금액</p>
<p class="oneAmount"><span>${formatPrice}원</span></p>
`;

priceWrap.innerHTML = `
<span class="PurchaseCell__Price-sc-1g4ze9y-5 dOTuYO"></span><span>${formatPrice}원</span>
`;

discountWrapper.innerHTML = `
 <span class="discount"><span>${formatPoint}원</span></span>
<button class="InnerActionButton-io567l-0 cVMMHP">전액</button>
`;
