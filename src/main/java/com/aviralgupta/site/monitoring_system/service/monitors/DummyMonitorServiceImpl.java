package com.aviralgupta.site.monitoring_system.service.monitors;

import com.aviralgupta.site.monitoring_system.util.enums.MonitorStatusEnum;
import com.aviralgupta.site.monitoring_system.util.pojo.MonitorResult;

import java.util.concurrent.TimeUnit;

public class DummyMonitorServiceImpl extends AbstractMonitorService {

    public DummyMonitorServiceImpl(String id, String userId, String serverAddress) {
        super(id, userId, serverAddress);
    }

    public DummyMonitorServiceImpl(String userId, String serverAddress) {
        super(userId, serverAddress);
    }

    @Override
    public MonitorResult run() {

        MonitorResult result = new MonitorResult(getId(), getUserId(), getServerAddress(), MonitorStatusEnum.HEALTHY);
        return result;
    }
}
