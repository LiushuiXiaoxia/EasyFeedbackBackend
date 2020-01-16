package cn.mycommons.easyfeedback.util;

import cn.mycommons.easyfeedback.dto.PageResp;
import org.springframework.data.domain.Page;

public class RespUtil {

    public static <T> PageResp<T> page(Page<T> page) {
        PageResp<T> resp = new PageResp<>();
        if (page != null) {
            resp.setData(page.getContent());
            resp.setCurrentPage(page.getNumber());
            resp.setPageSize(page.getSize());
            resp.setMore(page.hasNext());
        }
        return resp;
    }
}