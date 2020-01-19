package cn.mycommons.easyfeedback.controller;

import cn.mycommons.easyfeedback.dto.PageResp;
import cn.mycommons.easyfeedback.dto.app.AppDto;
import cn.mycommons.easyfeedback.service.IAppService;
import cn.mycommons.easyfeedback.util.RespUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    IAppService appService;

    @GetMapping("/list")
    public PageResp<AppDto> list(@RequestParam(defaultValue = "1") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        Page<AppDto> data = appService.list(page, size);
        return RespUtil.page(data);
    }

    @PostMapping("/search")
    public PageResp<AppDto> search(@RequestBody AppDto search,
                                   @RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "10") int size) {
        Page<AppDto> data = appService.search(search, page, size);
        return RespUtil.page(data);
    }
}