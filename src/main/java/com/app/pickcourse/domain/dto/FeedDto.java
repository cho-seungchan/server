package com.app.pickcourse.domain.dto;

import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.domain.vo.FeedTagVO;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class FeedDto {
    private FeedVO feedVO;
    private FeedTagVO tagVO;
    private List<FileVO> files;
}
