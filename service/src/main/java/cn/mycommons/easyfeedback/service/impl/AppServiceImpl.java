package cn.mycommons.easyfeedback.service.impl;

import cn.mycommons.easyfeedback.dto.app.AppDto;
import cn.mycommons.easyfeedback.entity.FeedbackInfo;
import cn.mycommons.easyfeedback.service.IAppService;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppServiceImpl implements IAppService {

    @Autowired
    MongoTemplate template;

    @Override
    public Page<AppDto> list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        TypedAggregation<FeedbackInfo> aggregation = Aggregation.newAggregation(
                FeedbackInfo.class,
                Aggregation.match(
                        Criteria.where("meta.pkgName").ne(null).and("meta.platform").ne(null)
                ),
                Aggregation.group("meta.pkgName", "meta.platform", "meta.appName").count().as("count"),
                Aggregation.sort(Sort.Direction.ASC, "meta.pkgName", "meta.platform"),
                Aggregation.skip((long) (page - 1) * size),
                Aggregation.limit(size)
        );
        AggregationResults<AppDto> results = template.aggregate(aggregation, AppDto.class);

        aggregation = Aggregation.newAggregation(
                FeedbackInfo.class,
                Aggregation.match(
                        Criteria.where("meta.pkgName").ne(null).and("meta.platform").ne(null)
                ),
                Aggregation.group("meta.pkgName", "meta.platform", "meta.appName")
                        .count().as("count"),
                Aggregation.count().as("total")
        );
        AggregationResults<Document> count = template.aggregate(aggregation, Document.class);
        int total = 0;
        if (count.getUniqueMappedResult() != null) {
            total = count.getUniqueMappedResult().getInteger("total");
        }
        return new PageImpl<>(results.getMappedResults(), pageable, total);
    }

    @Override
    public Page<AppDto> search(AppDto appDto, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        TypedAggregation<FeedbackInfo> aggregation = Aggregation.newAggregation(
                FeedbackInfo.class,
                Aggregation.match(
                        Criteria.where("meta.pkgName").is(appDto.getAppName())
                                .and("meta.platform").is(appDto.getPkgName())
                ),
                Aggregation.group("meta.pkgName", "meta.platform", "meta.appName").count().as("count"),
                Aggregation.sort(Sort.Direction.ASC, "meta.pkgName", "meta.platform"),
                Aggregation.skip((long) (page - 1) * size),
                Aggregation.limit(size)
        );
        AggregationResults<AppDto> results = template.aggregate(aggregation, AppDto.class);

        aggregation = Aggregation.newAggregation(
                FeedbackInfo.class,
                Aggregation.match(
                        Criteria.where("meta.pkgName").is(appDto.getAppName())
                                .and("meta.platform").is(appDto.getPkgName())
                ),
                Aggregation.group("meta.pkgName", "meta.platform", "meta.appName").count().as("count"),
                Aggregation.count().as("total")
        );
        AggregationResults<Document> count = template.aggregate(aggregation, Document.class);
        int total = 0;
        if (count.getUniqueMappedResult() != null) {
            total = count.getUniqueMappedResult().getInteger("total");
        }
        return new PageImpl<>(results.getMappedResults(), pageable, total);
    }
}