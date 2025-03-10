package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.PlanDTO;
import com.app.pickcourse.domain.vo.PlanVO;
import com.app.pickcourse.repository.*;
import com.app.pickcourse.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class PlanService {
    private final PlanDAO planDAO;
    private final ScheduleDAO scheduleDAO;
    private final WriteExcludeDAO writeExcludeDAO;
    private final WriteIncludeDAO writeIncludeDAO;
    private final WritePrepareDAO writePrepareDAO;


//    여행계획작성
    public void writePlan(PlanDTO planDTO) {
        PlanVO planVO = planDTO.toVO();

        planDAO.save(planVO);

        planDTO.getExcludeContents().forEach( exclude -> {
                    exclude.setPlanId(planVO.getId());
                    writeExcludeDAO.save(exclude.toVO());
                });
        planDTO.getIncludeContents().forEach(include -> {
            include.setPlanId(planVO.getId());
            writeIncludeDAO.save(include.toVO());
                });
        planDTO.getPrepareContents().forEach(prepare -> {
            prepare.setPlanId(planVO.getId());
            writePrepareDAO.save(prepare.toVO());
                });
        planDTO.getScheduleContents().forEach(schedule -> {
            schedule.setPlanId(planVO.getId());
            scheduleDAO.save(schedule.toVO());
            });
        log.info("check2 {}", planDTO.getPlanContent());

    }
//    나의 여행 계획 목록

    public List<PlanDTO> findMyPlan(Pagination pagination, Long memberId) {
        return planDAO.findByMemberId(pagination, memberId);
    };

}
