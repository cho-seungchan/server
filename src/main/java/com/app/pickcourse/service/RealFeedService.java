package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.RealFeedDTO;
import com.app.pickcourse.domain.vo.FeedVO;
import com.app.pickcourse.repository.FeedDAO;
import com.app.pickcourse.repository.MemberDAO;
import com.app.pickcourse.repository.PlanDAO;
import com.app.pickcourse.repository.RealFeedDAO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class RealFeedService {
    private final RealFeedDAO realFeedDAO;
    private final FeedDAO feedDAO;
    private final MemberDAO memberDAO;
    private final PlanDAO planDAO;

//    여행후기 작성
    public void writeReview(@Param("feedVO") FeedVO feedVO, @Param("realFeedDTO") RealFeedDTO realFeedDTO) {

        realFeedDAO.saveReview(feedVO, realFeedDTO);
    }
}
