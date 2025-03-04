package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FeedReportVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long feedId;
    private Long memberId;
    private String createDate;
    private String updateDate;
}
