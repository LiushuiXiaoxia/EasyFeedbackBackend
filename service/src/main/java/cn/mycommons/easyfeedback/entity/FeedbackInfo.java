package cn.mycommons.easyfeedback.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Map;

@Data
public class FeedbackInfo {

    @Id
    private String id;

    private String pkgName;
    private String platform;

    private String desc;
    private String contact;
    private Date time;

    private int status = 0;
    private Map<String, Object> extra;
}