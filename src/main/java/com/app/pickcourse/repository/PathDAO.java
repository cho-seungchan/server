package com.app.pickcourse.repository;

import com.app.pickcourse.domain.vo.PathVO;
import com.app.pickcourse.mapper.PathMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PathDAO {
    private final PathMapper pathMapper;

    public void postAddCourse(PathVO path) {
        pathMapper.postAddCourse(path);
    }
}
