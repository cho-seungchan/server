package com.app.pickcourse.mapper;

import com.app.pickcourse.domain.dto.FeedListDTO;
import com.app.pickcourse.domain.vo.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class GeneralFileMapperTests {
    @Autowired
    private GeneralFileMapper mapper;
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private GeneralFeedMapper GeneralfeedMapper;
    @Autowired
    private FeedTagMapper feedTagMapper;

    @Test
    public void postFeedWrite(){
        FileVO fileVO = new FileVO();
        fileVO.setFileName("test");
        fileVO.setFilePath("test");
        fileVO.setFileSize("125");
        fileMapper.postFeedWrite(fileVO);

        mapper.postFeedWrite(fileVO.getId(),33l);
    }

    @Test
    public void getFeedList(){

        List<FeedListDTO> feedList = GeneralfeedMapper.getFeedList();
        feedList.forEach(feed -> {
            feed.setFiles(mapper.getFileList(feed.getId()));
            feed.setFeedTags(feedTagMapper.getFeedList(feed.getId()));
        });
        feedList.forEach(System.out::println);

    }
}
