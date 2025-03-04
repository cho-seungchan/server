package com.app.pickcourse.domain.vo;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FeedTagVO {
    @EqualsAndHashCode.Include
    private Long id;
    private String FeedTagContent;
    private Long feedId;
}
