package com.app.pickcourse.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReportDetailDTO {
    private Long   id;
    private String source;
    private String memberNickname;
    private Long   memberId;
    private String createdDate;
    private String content;
}
