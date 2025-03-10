package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReportVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long reportedId;
    private Long memberId;
    private String createDate;
    private String updateDate;
}
