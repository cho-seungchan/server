package com.app.pickcourse.domain.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReplyDTO {
    private Long id;
    private String replyContent;
    private String createDate;
    private String updateDate;
    private Long   feedId;
    private Long   memberId;
    private String memberNickname;
}
