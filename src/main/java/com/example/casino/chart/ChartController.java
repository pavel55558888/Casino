package com.example.casino.chart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChartController {
    @GetMapping("/chart")
    public String chart(Model model){
        ChartService chartService = new ChartService();
        model.addAttribute("x", chartService.getRandomNumber());
        System.out.println(chartService.getRandomNumber());
        return "chart/chart";
    }

}
