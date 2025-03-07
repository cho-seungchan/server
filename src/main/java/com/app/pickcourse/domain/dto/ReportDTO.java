package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.FeedTagVO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.vo.FileVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReportDTO {
    private Long   id;
    private Long   reportedId;
    private String content;
    private Long   reportedMemberId;
    private String reportedMemberNickname;
    private long   reportMemberId;
    private String reportMemberNickname;
    private String createDate;
    private String updateDate;
}
