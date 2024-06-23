package com.example.casino.chart;

import com.example.casino.main.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChartController {
    private final ChartService chartService;

    @Autowired
    public ChartController(ChartService chartService) {
        this.chartService = chartService;
    }

    @GetMapping("/chart")
    public String chart(){
        return "chart/chart";
    }

    @GetMapping("/chart/number")
    @ResponseBody
    public double getCurrentNumber() {
        return chartService.getNumber();
    }

    @PostMapping("/chart_bet")
    @ResponseBody
    public void chartBet(@RequestParam String bet){
        chartService.setBet(bet);
        chartService.startThreadBet();
    }

}
