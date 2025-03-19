package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FileDAO {
    private final FileMapper fileMapper;

//    추가하기
    public void saveMessageFile(FileVO fileVO) {
        fileMapper.insertMessageFile(fileVO);
    }
}
