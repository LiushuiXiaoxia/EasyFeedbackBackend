package cn.mycommons.easyfeedback.service;

import cn.mycommons.easyfeedback.dto.feedback.FeedbackDto;
import cn.mycommons.easyfeedback.dto.feedback.status.UpdateStatusReq;
import org.springframework.data.domain.Page;

public interface IFeedbackService {

    String insert(FeedbackDto dto);

    Page<FeedbackDto> list(int page, int size);

    Page<FeedbackDto> search(FeedbackDto search, int page, int size);

    FeedbackDto findById(String id);

    FeedbackDto update(String id, FeedbackDto dto);

    int updateStatus(String id, UpdateStatusReq req);
}