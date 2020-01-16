package cn.mycommons.easyfeedback.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@Slf4j
public class ConvertUtil {

    public static <D, E> D copy(E source, Class<D> clazz) {
        D target = null;
        try {
            target = clazz.newInstance();
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            log.info("copy fail", e);

        }
        return target;
    }
}