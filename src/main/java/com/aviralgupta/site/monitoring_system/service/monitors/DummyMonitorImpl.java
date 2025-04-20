package com.aviralgupta.site.monitoring_system.service.monitors;

import com.aviralgupta.site.monitoring_system.util.enums.MonitorStatusEnum;

public class DummyMonitorImpl extends AbstractMonitor {

    public DummyMonitorImpl(String id, Integer userId, String serverAddress) {
        super(id, userId, serverAddress);
    }

    public DummyMonitorImpl(Integer userId, String serverAddress) {
        super(userId, serverAddress);
    }

    @Override
    public MonitorStatusEnum run() {

        return MonitorStatusEnum.HEALTHY;
    }
}
