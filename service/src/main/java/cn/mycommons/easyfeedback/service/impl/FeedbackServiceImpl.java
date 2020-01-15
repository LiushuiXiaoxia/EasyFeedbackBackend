package cn.mycommons.easyfeedback.service.impl;

import cn.mycommons.easyfeedback.dto.FeedbackInfo;
import cn.mycommons.easyfeedback.repo.FeedbackRepo;
import cn.mycommons.easyfeedback.service.IFeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FeedbackServiceImpl implements IFeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo;

    @Override
    public String insert(FeedbackInfo info) {
        FeedbackInfo ret = feedbackRepo.save(info);
        log.info("ret = {}", ret);
        return ret.getId();
    }

    @Override
    public List<FeedbackInfo> findAll() {
        List<FeedbackInfo> all = feedbackRepo.findAll();
        log.info("find all size = {}", all.size());
        return all;
    }

    @Override
    public FeedbackInfo findById(String id) {
        Optional<FeedbackInfo> optional = feedbackRepo.findById(id);

        log.info("findById id = {}, optional = {}", id, optional);

        FeedbackInfo info = null;
        if (optional.isPresent()) {
            info = optional.get();
        }

        return info;
    }
}