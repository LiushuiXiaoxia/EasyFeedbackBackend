package cn.mycommons.easyfeedback.service.impl;

import cn.mycommons.easyfeedback.dto.feedback.FeedbackDto;
import cn.mycommons.easyfeedback.entity.FeedbackStatus;
import cn.mycommons.easyfeedback.service.IFeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
class FeedbackServiceImplTest {

    @Autowired
    IFeedbackService service;

    @Test
    void insert() {
        FeedbackDto info = new FeedbackDto();
        info.setContact("10086");
        info.setDesc("垃圾软件，毁我青春");


        Map<String, Object> extra = new HashMap<>();
        extra.put("url2", "https://www.baid.com");
        extra.put("url3", "https://www.baid.com");
        extra.put("date", new Date());
        info.setExtra(extra);

        service.insert(info);
    }

    @Test
    void list() {
        Page<FeedbackDto> list = service.list(1, 20);
        log.info("getNumber = {}", list.getNumber());
        log.info("getTotalPages = {}", list.getTotalPages());
        log.info("getNumberOfElements = {}", list.getNumberOfElements());
        log.info("getTotalElements = {}", list.getTotalElements());
        log.info("isFirst = {}", list.isFirst());
        log.info("isLast = {}", list.isLast());
        log.info("isEmpty = {}", list.isEmpty());
        log.info("hasNext = {}", list.hasNext());
        list.forEach(info -> log.info("info = {}", info));
    }

    @Test
    void findById() {
    }

    @Test
    void search() {
        FeedbackDto search = new FeedbackDto();
        search.setStatus(FeedbackStatus.Created.getStatus());
        Page<FeedbackDto> page = service.search(search, 1, 20);

        log.info("page = {}", page.getContent());
    }

    @Test
    void update() {
        FeedbackDto dto = new FeedbackDto();
        dto.setContact("10086");
        dto.setDesc("垃圾软件，毁我青春");

        Map<String, Object> extra = new HashMap<>();
        extra.put("url2", "https://www.baid.com");
        extra.put("url3", "https://www.baid.com");
        extra.put("date", new Date());
        dto.setExtra(extra);

        String id = service.insert(dto);

        dto = new FeedbackDto();
        dto.setDesc("~~~垃圾软件，毁我青春~~");
        service.update(id, dto);

        dto = service.findById(id);
        log.info("dto = {}", dto);
    }
}