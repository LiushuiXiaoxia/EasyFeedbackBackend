package cn.mycommons.easyfeedback.dto.feedback;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class FeedbackDto {

    private String id;

    private String desc;
    private String contact;

    private List<String> images;
    private Map<String, Object> extra;
    private MetaDto meta;

    private Integer status;
}