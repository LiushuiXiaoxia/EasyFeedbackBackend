package cn.mycommons.easyfeedback.service;

import cn.mycommons.easyfeedback.dto.FeedbackInfo;

import java.util.List;

public interface IFeedbackService {

    String insert(FeedbackInfo info);

    List<FeedbackInfo> findAll();

    FeedbackInfo findById(String id);
}