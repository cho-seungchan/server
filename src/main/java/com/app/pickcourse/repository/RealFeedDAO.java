package com.app.pickcourse.repository;

<<<<<<< HEAD
import com.app.pickcourse.domain.dto.RealFeedDTO;
import com.app.pickcourse.domain.vo.FeedVO;
=======
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.mapper.GeneralFeedMapper;
>>>>>>> beae14ae538352a3eb0b1f84accfb4e3388f64c4
import com.app.pickcourse.mapper.RealFeedMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
@RequiredArgsConstructor
@Repository
public class RealFeedDAO {
    private final RealFeedMapper realFeedMapper;

//    여행후기작성
    public void saveReview(FeedVO feedVO, RealFeedDTO realFeedDTO) {
        realFeedMapper.insertReview(feedVO, realFeedDTO);
=======
@Repository
@RequiredArgsConstructor
public class RealFeedDAO {
    private final RealFeedMapper realFeedMapper;

    public void postFeedWrite(Long id, Long loginId, Long planId) {
        realFeedMapper.postFeedWrite(id, loginId, planId);
>>>>>>> beae14ae538352a3eb0b1f84accfb4e3388f64c4
    }
}
