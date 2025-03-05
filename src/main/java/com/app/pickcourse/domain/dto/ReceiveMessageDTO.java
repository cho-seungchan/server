package com.app.pickcourse.domain.dto;

import lombok.*;

@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReceiveMessageDTO {
    @EqualsAndHashCode.Include
    private Long messageId;  // TBL_MESSAGE.ID (TBL_RECEIVE_MESSAGE.ID 아님)
    private Long receiverId; // 받은 사람 (TBL_MEMBER.ID)
    private String receiverEmail;
    private String receiverNickname;
    private Long senderId;   // 보낸 사람 (TBL_MEMBER.ID)
    private String senderEmail;
    private String senderNickname;
    private String content;  // 메시지 내용 (TBL_MESSAGE.MESSAGE_ALL_CONTENT)
    private String receiveDate; // 받은 날짜 (TBL_MESSAGE.MESSAGE_ALL_DATE)
    private boolean isChecked; // 읽음 여부 (TBL_RECEIVE_MESSAGE.RECEIVE_MESSAGE_CHECK)
}
