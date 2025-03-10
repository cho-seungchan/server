// 2025.03.10 조승찬
// 코스 상세 생성하는 함수
function createCourseDetail() {
    const baseContainer = document.querySelector(".AppLayout_contents__YmI3N");
    baseContainer.innerHTML = `
    <main class="FundingPage_container__MDczO">
        <div id="FundingPage_Content" class="FundingPage_box__YzU3M FundingPage_footer__NTYyM" >
            <section>
                <div class="FundingPage_headBanner__YzIxM" ></div>
            </section>
            <section class="FundingPage_presentation__OGJjZ" >
                <span class="BlindText_textHidden__Y2JmY" >사이트 이동 경로</span >
                <ol class="Breadcrumb_container__MTlmM" ></ol>
                <h2 class="FundingPage_title__YTViN"> 추천 코스 조회 </h2>
                <div class="FundingPage_description__NDA1Z" style="display: none" >
                    추천 코스를 소개해 주세요.
                </div>
                <div class="FundingPage_content__OTYzY">
                    <section class="Section_container__NzdhM spacing-9" style="max-width: 1024px" >
                        <div class="Section_content__Mzc4M">
                            <input type="hidden" name="registered" value="2025-02-12 15:51:48.0" disabled="" style=" background-color: rgba( 211, 211, 211, 0.5 ); cursor: not-allowed; />
                        </div>
                    </section>
                    <div class="">
                        <section
                            class="Section_container__NzdhM spacing-9" >
                            <div style="max-width: 1024px">
                                <div class="Section_header__NDYxN spacing-4" >
                                    <h3 class="Section_title__ODQyN Section_isRequired__MzViM" >
                                        코스 제목<span class="BlindText_textHidden__Y2JmY" >필수</span >
                                        <label for="volunteerBox" class="volunteerLabel" >
                                            <input type="checkbox" id="volunteerBox" class="volunteerBox" disabled="" style=" background-color: rgba( 211, 211, 211, 0.5 ); cursor: not-allowed; " />
                                            봉사 코스
                                        </label>

                                        <div>
                                            <button type="button" class="Tooltip_button__OGZkY Tooltip_withLabel__M2Q1O Section_tooltip__Njk1N" aria-describedby="Tooltip_1" >
                                                <span class="Tooltip_label__MjJkZ" >코스 제목 정보</span >
                                                <span class="Tooltip_helpIconWrap__MDliY" ></span>
                                            </button>
                                        </div>
                                    </h3>
                                    <div class="Section_guide__NmJhM" >
                                        <div class="Section_helperMessage__ZGVjO" ></div>
                                    </div>
                                </div>
                                <div class="Section_content__Mzc4M" >
                                    <div class="TextField_textField__ZmYzN TextField_lg__NGQ5N" >
                                        <label></label>
                                        <div class="TextField_field__YTJkY" >
                                            <input placeholder="제목을 입력해 주세요" maxlength="40" type="text" class="Input_input__M2Q3Y Input_lg__MDE4M" aria-invalid="false" value="달(月)달(月)한 남원여행" disabled="" style=" background-color: rgba( 211, 211, 211, 0.5 ); cursor: not-allowed; " />
                                        </div>
                                        <em class="HelperMessage_helperMessage__ZTRkO" >40자 남음</em >
                                        <div class="DurationOfTourContainer hidden" >
                                            <input type="date" placeholder="시작일" class="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh startdate" value="" disabled="" style=" background-color: rgba( 211, 211, 211, 0.5 ); cursor: not-allowed; " />
                                            <span>~</span>
                                            <input type="date" placeholder="종료일" class="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh enddate" value="" disabled="" style=" background-color: rgba( 211, 211, 211, 0.5 ); cursor: not-allowed; " />
                                            <span>마감일</span >
                                            <input type="date" placeholder="모집 마감일" class="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh deadline" value="" disabled="" style=" background-color: rgba( 211, 211, 211, 0.5 ); cursor: not-allowed; " />
                                            <button type="button" class="CurrentProfile__MoreButton-sc-1u92qay-6 FvtMb" style=" pointer-events: none; opacity: 0.5; " >
                                                <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='4' height='17' viewBox='0 0 4 17'%3E %3Cpath fill='%23999' fill-rule='evenodd' d='M1.57 14a1.5 1.5 0 110 3 1.5 1.5 0 010-3zm0-7a1.5 1.5 0 110 3 1.5 1.5 0 010-3zm0-7a1.5 1.5 0 110 3 1.5 1.5 0 010-3z'/%3E %3C/svg%3E" alt="더보기" />
                                            </button>
                                        </div>
                                        <div class="DurationOfTourContainer1 hidden SocialRecruiteTagsContainer__SocialRecruiteTagsWrapper-sc-2762su-0 bmexYY second" >
                                            <div class="bmexYYinput" >
                                                <input placeholder="포함 사항 (0/10)" class="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh include" value="" disabled="" style=" background-color: rgba( 211, 211, 211, 0.5 ); cursor: not-allowed; " />
                                                <input placeholder="불포함 사항 (0/10)" class="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh exclude" value="" disabled="" style=" background-color: rgba( 211, 211, 211, 0.5 ); cursor: not-allowed; " />
                                                <input placeholder="준비물 (0/10)" class="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh prepare" value="" disabled="" style=" background-color: rgba( 211, 211, 211, 0.5 ); cursor: not-allowed; " />
                                            </div>
                                            <p class="Textarea__Count-sc-1b9phu6-2 jvAusQ" > 입력 후 엔터를 누르면 태그가 생성되요 </p>
                                            <section class="ProductDetailPage__SectionContainer-sc-126q1k2-4 bDBbNi" >
                                                <article class="bDBbNifirst" ></article>
                                                <article class="bDBbNisecond" ></article>
                                                <article class="bDBbNithird" ></article>
                                            </section>
                                        </div>

                                        <div class="NumberOfPerson" >
                                            <input placeholder="총 거리" class="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh max" value="39.6 km" readonly="" disabled="" style=" background-color: rgba( 211, 211, 211, 0.5 ); cursor: not-allowed; " />
                                            <input placeholder="일정" class="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh min" value="당일 여행" disabled="" style=" background-color: rgba( 211, 211, 211, 0.5 ); cursor: not-allowed; " />
                                            <input placeholder="테마"  class="SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh cost" value="힐링 코스" disabled="" style=" background-color: rgba( 211, 211, 211, 0.5 ); cursor: not-allowed; " />
                                        </div>
                                        <div class="GatheringPlace" >
                                            <input placeholder="주소를 입력하세요" class="noBtnStyle SocialRecruiteTagsContainer__SocialRecruiteTagsInput-sc-2762su-1 gcqwwh include1 gather" value="" disabled="" style=" background-color: rgba( 211, 211, 211, 0.5 ); cursor: not-allowed; " />

                                            <ul id="destinationList" tyle=" padding: 10px; margin-top: 10px; list-style: none; " >
                                                <li style=" display: flex; justify-content: space-between; padding: 5px; border-bottom: 1px solid rgb( 221, 221, 221 " >
                                                    <span style=" cursor: pointer; " >1. 전북특별자치도 남원시 원천로 37</span >
                                                    <img src="" style=" cursor: pointer; margin-left: 5px; width: 14px; height: 14px; " />
                                                </li>
                                            </ul>
                                            <!-- 지도 -->
                                            <!-- 지도 -->
                                            <div id="mapContainer" >
                                                <div id="map" ></div>
                                                <div id="fullMap" >
                                                    <img src="data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='24' height='24' fill='none' viewBox='0 0 24 24'%3E %3Cpath stroke='%23333' stroke-linecap='round' stroke-linejoin='round' stroke-width='1.5' d='M17 3h4v4M15 9l6-6M7 21H3v-4M9 15l-6 6M4 11V6c0-1.105.895-2 2-2h5M20 13v5c0 1.105-.895 2-2 2h-6'/%3E %3C/svg%3E" alt="map fullscreen" />
                                                </div>
                                            </div>
                                            <!-- //지도 -->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <section class="Section_container__NzdhM" >
                            <div style="max-width: 1024px">
                                <div class="Section_header__NDYxN" >
                                    <h3 class="Section_title__ODQyN Section_isRequired__MzViM" >코스 요약
                                        <span class="BlindText_textHidden__Y2JmY" >필수</span >
                                        <div>
                                            <button type="button" class="Tooltip_button__OGZkY Tooltip_withLabel__M2Q1O Section_tooltip__Njk1N" aria-describedby="Tooltip_5" >
                                                <span class="Tooltip_label__MjJkZ" >프로젝트 요약 정보</span >
                                            </button>
                                        </div>
                                    </h3>
                                    <div class="Section_guide__NmJhM" ></div>
                                </div>
                                <div class="Section_description__NmFmM" >
                                    <p> 코스를 쉽고 간결하게 소개해 주세요. </p>
                                </div>
                                <div class="Section_content__Mzc4M" >
                                    <div class="StorySummaryField_container__MWQ0N" >
                                        <textarea maxlength="1000" placeholder="내용 입력" class="Textarea_textarea__MWJjO" disabled="" value="" style=" background-color: rgba( 211, 211, 211, 0.5 ); cursor: not-allowed; " > 이번 여행은 사랑1번지 남원시에서의 ''치유와 힐링'' 여행이다. </textarea>
                                        <p class="StorySummaryField_text__ZTEzY" > 1000자 남음 </p>
                                    </div>
                                </div>
                            </div>
                        </section>
                        <section class="Section_container__NzdhM spacing-9" >
                            <div style="max-width: 1024px">
                                <div class="Section_header__NDYxN" >
                                    <h3 class="Section_title__ODQyN Section_isRequired__MzViM" > 대표 이미지
                                    <span class="BlindText_textHidden__Y2JmY" >필수</span >
                                        <div>
                                            <button type="button" class="Tooltip_button__OGZkY Tooltip_withLabel__M2Q1O Section_tooltip__Njk1N" aria-describedby="Tooltip_2" >
                                                <span class="Tooltip_label__MjJkZ" >대표 이미지 정보</span >
                                                <span class="Tooltip_helpIconWrap__MDliY" ></span>
                                            </button>
                                        </div>
                                    </h3>
                                </div>
                                <div class="Section_description__NmFmM" >
                                    <p class="spacing-4"> 픽커스 추천 코스 및 포털 검색 결과, SNS 타겟 광고 등에 노출할 대표 이미지를 등록해 주세요. </p>
                                    <div color="primary" class="MessageBox_messageBox__YzFiZ MessageBox_primary__YmZmM MessageBox_vertical__NGM3N" >
                                        <svg viewBox="0 0 40 40" focusable="false" role="presentation" class="withIcon_icon__MjYzN MessageBox_icon__NzQ3M" aria-hidden="true" >
                                            <path fill="none" d="M0 0h40v40H0z" ></path>
                                            <path d="M20 1a19 19 0 1 0 19 19A19.06 19.06 0 0 0 20 1zm1 28.6h-2v-13h2zm-1-15.84A1.8 1.8 0 1 1 21.8 12a1.8 1.8 0 0 1-1.8 1.76z"></path>
                                        </svg>
                                        <div class="MessageBox_content__MTc0Z" >
                                            <p class="MessageBox_title__MjlmY" > 대표 이미지 등록 가이드 </p>
                                            <div class="MessageBox_description__OGVjN" >
                                                <ul>
                                                    <li> 10MB 이하의 JPG, JPEG, PNG 파일 </li>
                                                    <li> 해상도 1200x675 픽셀 이상 </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="ImageUploadSection__Wrapper-sc-12o1z5n-0 kutLeL" >
                                    <div>
                                        <label class="InputImageReview__Wrapper-sc-1oapt4s-0 ipbuZD" >
                                            <input type="file" accept=".jpg, .jpeg, .png" multiple="" disabled="" style=" background-color: rgba( 211, 211, 211, 0.5 ); cursor: not-allowed; " />
                                            <span> 사진 첨부하기</span >
                                        </label>
                                        <div class="ImageList-sc-9v1mt2-0 hGJMVS" ></div>
                                    </div>
                                </div>
                            </div>
                        </section>
    
                        <section class="Section_container__NzdhM" ></section>
                        <section class="Section_container__NzdhM StoryEditorWrapperItem_container__MmJiN" style="max-width: 1024px" ></section>
                        <section class="Section_container__NzdhM StoryEditorWrapperComingItem_container__OTU5Y" style="max-width: 1024px" ></section>
                        <section class="Section_container__NzdhM TagsField_container__MTFkM spacing-9" ></section>
                    </div>
                    <div class="SaveButtonFooter_footer__YTA1M SaveButtonFooter_expand__Y2ZkM" >
                        <div class="SaveButtonFooter_btnWrapper__ZTk3Z" >
                            <div class="StoryFormPage_saveButtonWrapper__Y2FmO" >
                                <button
                                    class="Button_button__YmRmM Button_primary__YjdmO Button_contained__NjMyO Button_md__NTc2Y StoryFormPage_submitBtn__MzE2M StoryFormPage_desktop__YmRkN" type="button" style=" opacity: 1; cursor: pointer; " >
                                    <span><span class="Button_children__NzZlO" >수정</span></span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </main>                    
   `;
};
