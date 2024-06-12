package com.example.casino.chart;

import java.util.Random;

public class ChartService {
    private double randomNumber;

    public double getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(double randomNumber) {
        this.randomNumber = randomNumber;
    }

    public void startChar() throws InterruptedException {
        while (true){
            ThreadMainChart threadMainChart = new ThreadMainChart();
            threadMainChart.join();


        }
    }
}

class ThreadMainChart extends Thread{
    private ChartService chartService = new ChartService();
    public ThreadMainChart() {
        this.start();
    }
    public void run(){
        Random random = new Random();
        chartService.setRandomNumber(generateRandomNumber(random));
        System.out.println("Сгенерированное число: " + chartService.getRandomNumber());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static double generateRandomNumber(Random random) {
        double probability = random.nextDouble();
        if (probability <= 0.6) {
            return 1.1 + random.nextDouble() * 1.9; // 1.1 - 2 (60% вероятности)
        } else if (probability <= 0.98) {
            return 3 + random.nextDouble() * 3; // 2 - 5 (28% вероятности)
        } else {
            return 5 + random.nextDouble() * 94; // 5 - 99 (2% вероятности)
        }
    }
}
