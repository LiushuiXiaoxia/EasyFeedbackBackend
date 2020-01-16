package cn.mycommons.easyfeedback.dto.feedback;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class FeedbackDto {

    private String id;

    private String pkgName;
    private String platform;

    private String desc;
    private String contact;
    private Date time;

    private int status = 0;

    private Map<String, Object> extra;
}