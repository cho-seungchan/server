package com.app.pickcourse.domain.dto;

import lombok.*;

@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SendMessageDTO {
    @EqualsAndHashCode.Include
    private Long messageId;  // TBL_MESSAGE.ID (TBL_SEND_MESSAGE.ID 아님)
    private Long senderId;   // 보낸 사람 (TBL_MEMBER.ID)
    private String senderEmail;
    private String senderNickname;
    private Long receiverId; // 받은 사람 (TBL_MEMBER.ID)
    private String receiverEmail;
    private String receiverNickname;
    private String content;  // 메시지 내용 (TBL_MESSAGE.MESSAGE_ALL_CONTENT)
    private String sendDate; // 보낸 날짜 (TBL_MESSAGE.MESSAGE_ALL_DATE)
}
