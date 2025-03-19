package com.app.pickcourse.repository;

import com.app.pickcourse.domain.dto.FeedDTO;
<<<<<<< HEAD
import com.app.pickcourse.domain.vo.FeedVO;
=======
import com.app.pickcourse.domain.dto.RealDTO;
import com.app.pickcourse.domain.dto.ReplyDetailDTO;
>>>>>>> beae14ae538352a3eb0b1f84accfb4e3388f64c4
import com.app.pickcourse.mapper.FeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

=======
>>>>>>> beae14ae538352a3eb0b1f84accfb4e3388f64c4
@Repository
@RequiredArgsConstructor
public class FeedDAO {
    private final FeedMapper feedMapper;

<<<<<<< HEAD
//    추가
    public void save(FeedVO feedVO) {
        feedMapper.postFeedWrite(feedVO);
    }

=======
    public ReplyDetailDTO getReportDetail(Long id) {
        return feedMapper.getReportDetail(id).orElseThrow(() -> new RuntimeException("Report detail with ID " + id + " not found"));
    }

    public void postFeedWrite(FeedDTO feedDTO) {
        feedMapper.postFeedWrite(feedDTO);
    }

    public void postRealWrite(RealDTO realDTO) {
        feedMapper.postRealWrite(realDTO);
    }
>>>>>>> beae14ae538352a3eb0b1f84accfb4e3388f64c4
}
