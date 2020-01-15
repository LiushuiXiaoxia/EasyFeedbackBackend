package cn.mycommons.easyfeedback.service.impl;

import cn.mycommons.easyfeedback.dto.FeedbackInfo;
import cn.mycommons.easyfeedback.service.IFeedbackService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class FeedbackServiceImplTest {

    @Autowired
    IFeedbackService service;

    @Test
    void insert() {
        FeedbackInfo info = new FeedbackInfo();
        info.setTime(new Date());
        info.setContact("10086");
        info.setDesc("垃圾软件，毁我青春");

        info.setPkgName("cn.mycommons.feedback.example");
        info.setPlatform("Android");

        Map<String, Object> extra = new HashMap<>();
        extra.put("url2", "https://www.baid.com");
        extra.put("url3", "https://www.baid.com");
        extra.put("date", new Date());
        info.setExtra(extra);

        service.insert(info);
    }
}