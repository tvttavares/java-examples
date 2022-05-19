package com.tavares.springscheduler.scheduler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerClass {

    /*
     * The duration between the end of the last execution and the start of the next execution is fixed
     */
    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
    }

    /*
     * Even if we used fixedRate, the next task won't be invoked until the previous one is done.
     */
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTask() {
        System.out.println("Fixed rate task - " + System.currentTimeMillis() / 1000);
    }

    /*
     * With @Async is now possible to run scheduled tasks in parallel
     */
    @Async
    @Scheduled(fixedRate = 1000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        System.out.println("Fixed rate task async - " + System.currentTimeMillis() / 1000);
        Thread.sleep(2000);
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void scheduleFixedRateWithInitialDelayTask() {
        long now = System.currentTimeMillis() / 1000;
        System.out.println("Fixed rate task with one second initial delay - " + now);
    }

    /*
     * By default, Spring will use the server's local time zone for the cron expression
     */
//    @Scheduled(cron = "0 15 10 15 * ?")
    @Scheduled(cron = "0 15 10 15 * ?", zone = "Europe/Paris")
    public void scheduleTaskUsingCronExpression() {

        long now = System.currentTimeMillis() / 1000;
        System.out.println("schedule tasks using cron jobs - " + now);
    }

    @Scheduled(cron = "${cron.expression}")
    public void scheduleTaskUsingParametrizedCronExpression() {

        long now = System.currentTimeMillis() / 1000;
        System.out.println("schedule tasks using parametrized cron jobs - " + now);
    }
}
