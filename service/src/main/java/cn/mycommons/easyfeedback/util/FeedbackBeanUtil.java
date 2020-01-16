package cn.mycommons.easyfeedback.util;

import cn.mycommons.easyfeedback.dto.feedback.FeedbackDto;
import cn.mycommons.easyfeedback.entity.FeedbackInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeedbackBeanUtil {

    public static FeedbackDto info2Dot(FeedbackInfo obj) {
        return ConvertUtil.copy(obj, FeedbackDto.class);
    }

    public static FeedbackInfo dto2Info(FeedbackDto obj) {
        return ConvertUtil.copy(obj, FeedbackInfo.class);
    }
}