package cn.mycommons.easyfeedback.entity;

import cn.mycommons.easyfeedback.dto.feedback.MetaDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Document(collection = "feedback")
@CompoundIndexes({
        @CompoundIndex(name = "app_index", def = "{'meta.pkgName':1,'meta.platform':1}"),
        @CompoundIndex(name = "status_index", def = "{'status':1}")
})
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