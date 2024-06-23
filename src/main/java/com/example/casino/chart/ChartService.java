package com.example.casino.chart;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ChartService {
    private boolean status = false;
    private String bet;
    private double number;
    private final Object lock = new Object();

    public ChartService() {
        ChartThread chartThread = new ChartThread(this);
        chartThread.start();
    }

    public String getBet() {
        return bet;
    }

    public void setBet(String bet) {
        this.bet = bet;
    }

    public void setNumber(double newNumber) {
        synchronized (lock) {
            this.number = newNumber;
        }
    }

    public double getNumber() {
        synchronized (lock) {
            return number;
        }
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void startThreadBet(){
        ThreadBet threadBet = new ThreadBet(this);
        threadBet.start();
    }
}


class ChartThread extends Thread {

    private final ChartService chartService;

    public ChartThread(ChartService chartService) {
        this.chartService = chartService;
    }

    @Override
    public void run() {
        while (!interrupted()) {
            double newNumber = generateRandomNumber();
            double roundedValue = Math.round(newNumber * 10) / 10.0;
            chartService.setNumber(roundedValue);
            chartService.setStatus(true);
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                interrupt();
            }
            chartService.setStatus(false);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private double generateRandomNumber() {
        Random random = new Random();
        double probability = random.nextDouble();
        if (probability <= 0.6) {return 1.1 + random.nextDouble() * 1.9;} // 1.1 - 2 (60% вероятности)
        else if (probability <= 0.98) {return 3 + random.nextDouble() * 3;} // 2 - 5 (28% вероятности)
        else {return 5 + random.nextDouble() * 94;} // 5 - 99 (2% вероятности)
    }
}

class ThreadBet extends Thread{
    private final ChartService chartService;

    public ThreadBet(ChartService chartService) {
        this.chartService = chartService;
    }
    @Override
    public void run() {
        double bet = Double.parseDouble(chartService.getBet());
        while (chartService.isStatus()){
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (bet > chartService.getNumber()){System.out.println("Проиграл");}
        else {System.out.println("Выйграл");}
    }
}