package cn.mycommons.easyfeedback.repo;

import cn.mycommons.easyfeedback.dto.FeedbackInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedbackRepo extends MongoRepository<FeedbackInfo, String> {
}