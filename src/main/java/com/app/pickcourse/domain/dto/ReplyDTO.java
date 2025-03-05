package com.app.pickcourse.domain.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReplyDTO {
    @EqualsAndHashCode.Include
    private Long id;
    private String replyContent;
    private String createDate;
    private String updateDate;
    private Long   feedId;
    private Long   memberId;
    private String memberNickname;
}
