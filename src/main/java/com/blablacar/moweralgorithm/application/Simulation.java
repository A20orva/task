package com.blablacar.moweralgorithm.application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Simulation {

    private final List<MowerAgent> mowerAgents;

    private Simulation(List<MowerAgent> mowerAgents) {
        this.mowerAgents = new ArrayList<>(mowerAgents);
    }

    public static Simulation of(List<MowerAgent> mowerAgents) {
        return new Simulation(mowerAgents);
    }

    public void run() throws InterruptedException {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(processors);
        try {
            executorService.invokeAll(mowerAgents).forEach(stringFuture -> {
                try {
                    System.out.println(stringFuture.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
            executorService.shutdown();
        } catch (InterruptedException e) {
            executorService.shutdown();
            executorService.awaitTermination(10_000L, TimeUnit.MILLISECONDS);
        }
    }
}
