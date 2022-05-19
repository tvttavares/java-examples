package com.tavares.schedulerschedlock.scheduler;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerWithShedLock {

    @Scheduled(cron = "*/2 * * * * *")
    @SchedulerLock(name = "revenueReport", lockAtMostFor = "30s")
    public void report() {
        // report revenue based on e.g. orders in database
        System.out.println("Reporting revenue");
    }

    @Scheduled(cron = "${cron.expression}")
    @SchedulerLock(name = "shortRunningTask", lockAtMostFor = "50s", lockAtLeastFor = "30s")
    public void shortRunningTask() {
        System.out.println("Start short running task");
    }
}
