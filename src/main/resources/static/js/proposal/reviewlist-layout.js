const reviewCount = document.querySelector(".review-count");

reviewCount.innerHTML = `
        <div>후기 <span class="ProductReviewPage__Count-mmv016-2 lPYvt">${lists.feedLists.length}</span>개</div>

`;

const reviewLayout = (() => {
    const showList = async (feedListData) => {
        const reviewWrap = document.querySelector(".review-wrap");

        let text = ``;
        let imgPath = new Array();

        feedListData.feedList.forEach((feed)=> {
            console.log(feed)
        const createdDate = feed.createdDate;
        const targetCreatedDate = new Date(createdDate);
        const formatCreatedDate = `${targetCreatedDate.getFullYear()}년 ${targetCreatedDate.getMonth()+1}월 ${targetCreatedDate.getDate()}일`;

        let encodedFilePath = null;
        if(feed.files != null){
            encodedFilePath = feed.files[0]?.filePath && feed.files[0]?.fileName
                ? encodeURIComponent(`${feed.files[0].filePath}/${feed.files[0].fileName}`)
                : null;
        }
        const encodedMemberPath = feed.memberFilePath && feed.memberFileName
            ? encodeURIComponent(`${feed.memberFilePath}/${feed.memberFileName}`)
            : null;

        const defaultImage = "/images/proposal/noImage.png";
        const defaultProfileImage = "/images/proposal/noImage.png"

        text += `
            <div class="CardReview__Wrapper-f2ssd2-0 gPxoog">
                <div class="CardReview__Header-f2ssd2-1 cCFsGh">
                    <div class="CardReview__ProfileImageWrapper-f2ssd2-17 gARvBK">
                        <div class="Image__Wrapper-v97gyx-0 gDuKGF profile-wrap">
                            <img
                                class="Image__StyledImageLoader-v97gyx-2 bUFcfh"
                                width="48"
                                src="https://res.cloudinary.com/frientrip/image/upload/ar_1:1,c_fill,dpr_2,f_auto,q_auto,w_48/ios_image_1307410_20221105221106012_56de93a55028cee4b534c47531b65946996e1c1c47cea3c99ab66d594cf730e3"
                            />
                            <div
                                class="Fade__Wrapper-sc-1s0ipfq-0 koasSX"
                                style="opacity: 1; display: block"
                            >
                                <div class="Ratio" style="display: block">
                                    <div
                                        class="Ratio-ratio"
                                        style="
                                            height: 0px;
                                            position: relative;
                                            width: 100%;
                                            padding-top: 100%;
                                        "
                                    >
                                        <div
                                            class="Ratio-content"
                                            style="
                                                height: 100%;
                                                left: 0px;
                                                position: absolute;
                                                top: 0px;
                                                width: 100%;
                                            "
                                        >
                                            <img
                                                alt="프로필"
                                                class="Image__StyledImage-v97gyx-1 VUNpu"
                                                width="48"
                                                src="${encodedMemberPath ? `/files/display?path=${encodedMemberPath}` : defaultProfileImage}"
                                            />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="CardReview__MetaData-f2ssd2-2 dkvMyh">
                        <div class="CardReview__Title-f2ssd2-4 RFTCR">${feed.memberNickname}</div>
                        <div class="CardReview__Row-f2ssd2-3 llgSwy">
                            <div class="CardReview__Created-f2ssd2-5 fbQFTO">
                                ${formatCreatedDate} 작성
                            </div>
                        </div>
                    </div>
                </div>
                <div class="CardReview__Body-f2ssd2-9 kIUsm">
                    ${feed.feedContent}
                </div>
                <div class="CardReview__ImageWrapper-f2ssd2-14 dbtIvu img-wrap">
                </div>
            </div>
        </div>
        `;

        imgPath.push(`
                    <div class="Image__Wrapper-v97gyx-0 gDuKGF">
                        <div
                            class="Fade__Wrapper-sc-1s0ipfq-0 koasSX"
                            style="opacity: 1; display: block"
                        >
                            <div class="Ratio" style="display: block">
                                <div
                                    class="Ratio-ratio"
                                    style="
                                        height: 0px;
                                        position: relative;
                                        width: 100%;
                                        padding-top: 100%;
                                    "
                                >
                                    <div
                                        class="Ratio-content"
                                        style="
                                            height: 100%;
                                            left: 0px;
                                            position: absolute;
                                            top: 0px;
                                            width: 100%;
                                        "
                                    >
                                        <img
                                            alt="후기 이미지"
                                            class="Image__StyledImage-v97gyx-1 VUNpu"
                                            width="100"
                                            src="${encodedFilePath ? `/files/display?path=${encodedFilePath}` : defaultImage}"
                                        />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
        `)
            })
        console.log(imgPath);
        reviewWrap.innerHTML = text;
        const imgWrap = document.querySelectorAll(".img-wrap");
        imgWrap.forEach((img, i) => {
            img.innerHTML = imgPath[i];
        })
    }
    return{
        showList:showList
    }
})();




