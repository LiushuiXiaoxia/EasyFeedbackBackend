package cn.mycommons.easyfeedback.entity;

import cn.mycommons.easyfeedback.dto.feedback.MetaDto;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class FeedbackInfo {
    @Id
    private String id;

    private String desc;
    private String contact;

    private List<String> images;
    private Map<String, Object> extra;
    private MetaDto meta;

    private Integer status;

    private Boolean delete = false;
    private Date createAt = null;
    private Date updateAt = null;
}