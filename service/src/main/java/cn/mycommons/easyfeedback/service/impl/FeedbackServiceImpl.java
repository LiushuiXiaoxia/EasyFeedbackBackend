package cn.mycommons.easyfeedback.service.impl;

import cn.mycommons.easyfeedback.dto.feedback.FeedbackDto;
import cn.mycommons.easyfeedback.dto.feedback.status.UpdateStatusReq;
import cn.mycommons.easyfeedback.entity.FeedbackInfo;
import cn.mycommons.easyfeedback.entity.FeedbackStatus;
import cn.mycommons.easyfeedback.repo.FeedbackRepo;
import cn.mycommons.easyfeedback.service.IFeedbackService;
import cn.mycommons.easyfeedback.util.FeedbackBeanUtil;
import cn.mycommons.easyfeedback.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FeedbackServiceImpl implements IFeedbackService {

    public static final int MAX_PAGE_SIZE = 20;

    @Autowired
    private FeedbackRepo feedbackRepo;

    @Override
    public String insert(FeedbackDto dto) {
        FeedbackInfo info = FeedbackBeanUtil.dto2Info(dto);
        info.setStatus(FeedbackStatus.Created.getStatus());
        log.info("info = {}", info);

        FeedbackInfo ret = feedbackRepo.insert(info);
        log.info("ret = {}", ret);
        return ret.getId();
    }

    @Override
    public Page<FeedbackDto> list(int page, int size) {
        size = Math.min(size, MAX_PAGE_SIZE);

        PageRequest request = PageRequest.of(page - 1, size);
        Page<FeedbackInfo> all = feedbackRepo.findAll(request);
        log.info("find all size = {}", all.getNumber());

        List<FeedbackDto> list = all.stream()
                .map(FeedbackBeanUtil::info2Dot)
                .collect(Collectors.toList());

        return PageUtil.convert(list, all);
    }

    @Override
    public Page<FeedbackDto> search(FeedbackDto search, int page, int size) {
        size = Math.min(size, MAX_PAGE_SIZE);

        Example<FeedbackInfo> query = Example.of(FeedbackBeanUtil.dto2Info(search));
        log.info("query info = {}", query);

        PageRequest request = PageRequest.of(page - 1, size);
        Page<FeedbackInfo> all = feedbackRepo.findAll(query, request);
        log.info("find all size = {}", all.getNumber());

        List<FeedbackDto> list = all.stream()
                .map(FeedbackBeanUtil::info2Dot)
                .collect(Collectors.toList());

        return PageUtil.convert(list, all);
    }

    @Override
    public FeedbackDto findById(String id) {
        Optional<FeedbackInfo> optional = feedbackRepo.findById(id);
        log.info("findById id = {}, optional = {}", id, optional);

        FeedbackDto dto = null;
        if (optional.isPresent()) {
            dto = FeedbackBeanUtil.info2Dot(optional.get());
        }

        return dto;
    }

    @Override
    public FeedbackDto update(String id, FeedbackDto dto) {
        Optional<FeedbackInfo> optional = feedbackRepo.findById(id);
        if (dto != null && optional.isPresent()) {
            FeedbackInfo info = FeedbackBeanUtil.dto2Info(dto);
            info.setId(id);
            FeedbackInfo newInfo = feedbackRepo.save(info);
            return FeedbackBeanUtil.info2Dot(newInfo);
        }
        return null;
    }

    @Override
    public int updateStatus(String id, UpdateStatusReq req) {
        Optional<FeedbackInfo> optional = feedbackRepo.findById(id);
        if (req != null && optional.isPresent()) {
            FeedbackInfo info = optional.get();
            info.setStatus(req.getStatus());
            FeedbackInfo newInfo = feedbackRepo.save(info);
            return newInfo.getStatus();
        }
        return 0;
    }
}