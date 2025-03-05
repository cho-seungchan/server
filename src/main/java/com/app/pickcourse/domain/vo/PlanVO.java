package com.app.pickcourse.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PlanVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String planName;
    private String planStartDate;
    private String planEndDate;
    private String planDeadline;
    private int planMaxPersonnel;
    private int planMinPersonnel;
    private int planPrice;
    private String planStartAddress;
    private String planContent;
    private String planFilePath;
    private String planFileSize;
    private String planFileName;
    private Long memberId;
    private Long courseId;
    private String CreatedDate;
    private String UpdatedDate;
}
