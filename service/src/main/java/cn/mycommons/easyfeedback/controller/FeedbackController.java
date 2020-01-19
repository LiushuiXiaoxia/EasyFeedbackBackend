package cn.mycommons.easyfeedback.controller;

import cn.mycommons.easyfeedback.dto.CommonResp;
import cn.mycommons.easyfeedback.dto.PageResp;
import cn.mycommons.easyfeedback.dto.feedback.FeedbackDto;
import cn.mycommons.easyfeedback.dto.feedback.status.UpdateStatusReq;
import cn.mycommons.easyfeedback.service.IFeedbackService;
import cn.mycommons.easyfeedback.util.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private IFeedbackService service;

    @PostMapping
    public CommonResp<String> add(@RequestBody FeedbackDto dto) {
        String s = service.insert(dto);
        return CommonResp.success(s);
    }

    @GetMapping("/list")
    public PageResp<FeedbackDto> list(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        Page<FeedbackDto> data = service.list(page, size);
        return RespUtil.page(data);
    }

    @GetMapping("/search")
    public PageResp<FeedbackDto> search(FeedbackDto search,
                                        @RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        Page<FeedbackDto> data = service.search(search, page, size);
        return RespUtil.page(data);
    }

    @GetMapping("/{id}")
    public CommonResp<FeedbackDto> findById(@PathVariable("id") String id) {
        return CommonResp.success(service.findById(id));
    }

    @PostMapping("/{id}")
    public CommonResp<FeedbackDto> update(@PathVariable("id") String id,
                                          @RequestBody FeedbackDto dto) {
        return CommonResp.success(service.update(id, dto));
    }

    @PostMapping("/status/{id}")
    public CommonResp<Integer> updateStatus(@PathVariable("id") String id,
                                            @RequestBody UpdateStatusReq req) {
        return CommonResp.success(service.updateStatus(id, req));
    }
}