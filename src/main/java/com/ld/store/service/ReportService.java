package com.ld.store.service;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.stereotype.Service;

import com.ld.store.model.AvgRatingModel;
import com.ld.store.model.LegoSet;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@Service
public class ReportService {

    private MongoTemplate mongoTemplate;

    public ReportService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<AvgRatingModel> getAvgRatingReport(){
        ProjectionOperation projectToMatchModel = project()
                .andExpression("name").as("productName")
                .andExpression("{$avg : '$reviews.rating'}").as("avgRating");
        Aggregation avgRatingAggregation = newAggregation(LegoSet.class, projectToMatchModel);

        return this.mongoTemplate
                .aggregate(avgRatingAggregation, LegoSet.class, AvgRatingModel.class)
                .getMappedResults();
    }
    
}
