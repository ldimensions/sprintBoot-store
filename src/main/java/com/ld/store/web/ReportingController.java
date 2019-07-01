package com.ld.store.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ld.store.service.ReportService;
import com.ld.store.model.AvgRatingModel;

@RestController
@RequestMapping("store/api/reports")
public class ReportingController {

    private ReportService reportService;

    public ReportingController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("avgRatingsReport")
    public List<AvgRatingModel> avgRatingReport(){
        return this.reportService.getAvgRatingReport();
    }
    
    
}
