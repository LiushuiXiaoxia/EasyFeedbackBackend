package cn.mycommons.easyfeedback.service;

import cn.mycommons.easyfeedback.dto.app.AppDto;
import org.springframework.data.domain.Page;

public interface IAppService {

    Page<AppDto> list(int page, int size);

    Page<AppDto> search(AppDto appDto, int page, int size);
}