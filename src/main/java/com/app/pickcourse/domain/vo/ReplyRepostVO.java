package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReplyRepostVO {
    @EqualsAndHashCode.Include
    private Long id;
    private Long replyId;
    private Long memberId;
    private String createDate;
    private String updateDate;
}
