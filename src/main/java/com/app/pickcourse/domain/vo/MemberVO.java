package com.app.pickcourse.domain.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter @Setter @ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MemberVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberBirth;
    private String memberGender;
    private int memberPoints;
    private String createdDate;
    private String updatedDate;
}
