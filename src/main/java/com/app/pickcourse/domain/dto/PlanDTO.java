package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.WriteExcludeVO;
import com.app.pickcourse.domain.vo.WriteIncludeVO;
import com.app.pickcourse.domain.vo.WritePrepareVO;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PlanDTO {
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
    private List<WriteExcludeVO> excludeContent;
    private List<WriteIncludeVO> includeContent;
    private List<WritePrepareVO> prepareContent;
}
