package com.app.pickcourse.service;

import com.app.pickcourse.domain.dto.FeedDTO;
import com.app.pickcourse.repository.FeedDAO;
import com.app.pickcourse.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Setter
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class FeedService {

}
