package com.aviralgupta.site.monitoring_system.util.factory;

import com.aviralgupta.site.monitoring_system.service.monitors.AbstractMonitor;
import com.aviralgupta.site.monitoring_system.service.monitors.SslMonitorImpl;
import com.aviralgupta.site.monitoring_system.util.enums.MonitorTypeEnum;

public class MonitorFactory {

    public static AbstractMonitor getMonitor(MonitorTypeEnum type, Integer userId, String serverAddress) {

        if(type == MonitorTypeEnum.SSL)
            return new SslMonitorImpl(userId, serverAddress);

        throw new RuntimeException("Monitor type not defined, in factory method");
    }
}
