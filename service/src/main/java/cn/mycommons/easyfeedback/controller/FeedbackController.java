package cn.mycommons.easyfeedback.controller;

import cn.mycommons.easyfeedback.dto.CommonResp;
import cn.mycommons.easyfeedback.dto.FeedbackInfo;
import cn.mycommons.easyfeedback.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private IFeedbackService service;

    @PostMapping
    public CommonResp<String> add(@RequestBody FeedbackInfo info) {
        String s = service.insert(info);
        return CommonResp.success(s);
    }

    @GetMapping
    public CommonResp<List<FeedbackInfo>> findAll() {
        return CommonResp.success(service.findAll());
    }

    @GetMapping("/{id}")
    public CommonResp<FeedbackInfo> findById(@PathVariable("id") String id) {
        return CommonResp.success(service.findById(id));
    }
}