package com.aviralgupta.site.monitoring_system.service.monitors;

import com.aviralgupta.site.monitoring_system.util.enums.MonitorStatusEnum;
import com.aviralgupta.site.monitoring_system.util.pojo.MonitorResult;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;
import java.util.concurrent.*;

@Getter
public abstract class AbstractMonitor {

    protected static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);

    private final String id;
    private final Integer userId;
    private final String serverAddress; // (IPv4/domain)
    private ScheduledFuture<?> scheduledTask = null;

    @Setter
    private Boolean isScheduled = false; // becomes true when schedule method is called
    @Setter
    private Integer scheduledInterval; // seconds
    @Setter
    private Boolean isDisabled = false;

    public AbstractMonitor(Integer userId, String serverAddress) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.serverAddress = serverAddress;
    }

    public void scheduleOnce(){

        scheduledExecutorService.submit(() -> {
            MonitorStatusEnum status = run();
            notifyResult(status);
        });
    }

    public void schedule(){

        if(scheduledInterval < 5)
            throw new RuntimeException("Scheduled interval is less than 5");

        if(isScheduled)
            return;

        isScheduled = true;

        this.scheduledTask = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            MonitorStatusEnum status = run();
            notifyResult(status);
        }, 0, scheduledInterval, TimeUnit.SECONDS);
    }

    public void stopSchedule(){

        if(!isScheduled)
                return;

        isScheduled = false;

        if(scheduledTask != null)
            scheduledTask.cancel(true);
    }

    public void notifyResult(MonitorStatusEnum status){
        System.out.println(new MonitorResult(id, userId, serverAddress, status));
    }

    public abstract MonitorStatusEnum run();
}
