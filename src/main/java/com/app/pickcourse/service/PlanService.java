package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.MyPLanListDTO;
import com.app.pickcourse.domain.dto.PlanDTO;
import com.app.pickcourse.domain.vo.MemberVO;
import com.app.pickcourse.domain.vo.PlanVO;
import com.app.pickcourse.repository.*;
import com.app.pickcourse.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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
    private final MemberDAO memberDAO;


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

//    나의 계획 목록
    public MyPLanListDTO getMyPlanList(@Param("pagination") Pagination pagination,
                                       @Param("memberId") Long id) {
        MyPLanListDTO planListDTO = new MyPLanListDTO();

        pagination.create(planDAO.findTotal(id));

        planListDTO.setPagination(pagination);
        planListDTO.setPlanList(planDAO.findMyPlan(pagination, id));

        return planListDTO;
    }

//    목록 갯수
    public int getTotal(Long memberId) { return planDAO.findTotal(memberId); }

//    여행 상세 조회
    public Optional<PlanDTO> getPlanById(Long id) {
        PlanDTO planDTO = new PlanDTO();
        MemberVO memberVO = new MemberVO();

        Optional<PlanDTO> foundPlan = planDAO.findById(id);

        planDTO = foundPlan.orElseThrow(()-> new RuntimeException());

        memberVO.setId(planDTO.getId());

        planDTO.setMemberNickname(memberVO.getMemberNickname());
        planDTO.setScheduleContents(scheduleDAO.findByPlanId(id));
        planDTO.setExcludeContents(writeExcludeDAO.findByPlanId(id));
        planDTO.setIncludeContents(writeIncludeDAO.findByPlanId(id));
        planDTO.setPrepareContents(writePrepareDAO.findByPlanId(id));

        return Optional.ofNullable(foundPlan.orElse(new PlanDTO()));
    }

}




