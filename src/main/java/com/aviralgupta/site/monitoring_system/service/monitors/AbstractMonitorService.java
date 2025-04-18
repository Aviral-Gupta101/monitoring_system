package com.aviralgupta.site.monitoring_system.service.monitors;

import com.aviralgupta.site.monitoring_system.util.pojo.MonitorResult;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;
import java.util.concurrent.*;

@Getter
public abstract class AbstractMonitorService {

    protected static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    private final String id;
    private final String userId;
    private final String serverAddress; // (IPv4/domain)
    private ScheduledFuture<?> scheduledTask = null;

    @Setter
    private boolean isScheduled;
    @Setter
    private int scheduledInterval; // seconds
    @Setter
    private boolean isDisabled = false;

    public AbstractMonitorService(String id, String userId, String serverAddress) {
        this.id = id;
        this.userId = userId;
        this.serverAddress = serverAddress;
    }

    public AbstractMonitorService(String userId, String serverAddress) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.serverAddress = serverAddress;
    }

    public void scheduleOnce(){

        scheduledExecutorService.submit(() -> {
            MonitorResult result = run();
            notifyResult(result);
        });
    }

    public void schedule(){

        isScheduled = true;

        if(scheduledInterval <= 5)
            throw new RuntimeException("Scheduled interval cannot be less than 5 seconds");

        this.scheduledTask = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            MonitorResult result = run();
            notifyResult(result);
        }, 0, scheduledInterval, TimeUnit.SECONDS);
    }

    public void stopSchedule(){

        isScheduled = false;

        if(scheduledTask != null)
            scheduledTask.cancel(true);
    }

    public void notifyResult(MonitorResult monitorResult){
        System.out.println(monitorResult);
    }

    public abstract MonitorResult run();
}
