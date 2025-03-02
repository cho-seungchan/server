package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class NoticeVO {
    @EqualsAndHashCode.Include
    private Long id;

    private String noticeName;
    private String noticeContent;
    private Long   adminId;
    private String createdDate;
    private String updatedDate;

}
