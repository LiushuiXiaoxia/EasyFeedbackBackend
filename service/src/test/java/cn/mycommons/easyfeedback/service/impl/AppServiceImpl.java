package cn.mycommons.easyfeedback.service.impl;

import cn.mycommons.easyfeedback.dto.app.AppDto;
import cn.mycommons.easyfeedback.entity.FeedbackInfo;
import cn.mycommons.easyfeedback.repo.FeedbackRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;

@Slf4j
@SpringBootTest
public class AppServiceImpl {

    @Autowired
    FeedbackRepo repo;

    @Autowired
    MongoTemplate template;

    @Test
    public void testApp() {
        int page = 1;
        int size = 1;
        TypedAggregation<FeedbackInfo> aggregation = Aggregation.newAggregation(
                FeedbackInfo.class,
                Aggregation.match(
                        Criteria.where("meta.pkgName").ne(null).and("meta.platform").ne(null)
                ),
                Aggregation.group("meta.pkgName", "meta.platform", "meta.appName")
                        .count().as("count"),
                Aggregation.skip((page - 1) * size),
                Aggregation.limit(size)
        );
        AggregationResults<AppDto> results = template.aggregate(aggregation, AppDto.class);
        results.getMappedResults().forEach(result -> log.info("result = {}", result));
    }
}