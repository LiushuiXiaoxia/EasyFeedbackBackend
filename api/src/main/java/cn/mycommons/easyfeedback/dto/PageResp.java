package cn.mycommons.easyfeedback.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PageResp<T> extends CommonResp<List<T>> {

    private int currentPage;

    private int pageSize;

    private boolean more;
}