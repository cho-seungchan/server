package com.app.pickcourse.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReportListDTO {
    private String source;
    private String reportReason;
    private String createdDate;
    private Long reportedId;
    private Long memberId;
    private String memberNickname;
}
