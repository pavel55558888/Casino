package com.example.casino;

import com.example.casino.chart.ChartService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CasinoApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(CasinoApplication.class, args);
		ChartService chartService = new ChartService();
		chartService.startChar();
	}



}
