package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PlanVO {
    @EqualsAndHashCode.Include
    private Long id;

    private String planName;
    private String planStartDate;
    private String planEndDate;
    private int planMaxPersonnel;
    private int planMinPersonnel;
    private int planPrice;
    private String planGatheringAddr;
    private String planIntroduction;
    private String planFilePath;
    private String planFileName;
    private String planRegistDate;
    private String planRegistCancelDate;
    private Long memberId;
    private Long courseId;
    private String createDate;
    private String updateDate;
}
