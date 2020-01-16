package cn.mycommons.easyfeedback.util;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class PageUtil {

    public static <T, E> Page<T> convert(List<T> list, Page<E> origin) {
        if (list != null && origin != null) {
            return new PageImpl<>(list, origin.getPageable(), origin.getTotalElements());
        }
        return null;
    }
}
