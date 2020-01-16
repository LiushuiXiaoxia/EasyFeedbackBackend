package cn.mycommons.easyfeedback.repo;

import cn.mycommons.easyfeedback.entity.FeedbackInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepo extends MongoRepository<FeedbackInfo, String> {
}