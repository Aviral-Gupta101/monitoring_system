package com.aviralgupta.site.monitoring_system.service.monitors;

import com.aviralgupta.site.monitoring_system.util.enums.MonitorStatusEnum;
import java.net.InetSocketAddress;
import java.net.Socket;


public class HttpsMonitorImpl extends AbstractMonitor {

    int connectionTimeout = 5 * 1000; // milliseconds
    
    public HttpsMonitorImpl(Integer userId, String serverAddress) {
        super(userId, serverAddress);
    }

    @Override
    public MonitorStatusEnum run() {

        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(getServerAddress(), 443);
            Socket socket = new Socket();
            socket.connect(inetSocketAddress, connectionTimeout);
            socket.close();

            return MonitorStatusEnum.HEALTHY;
        } catch (Exception ex) {
            return MonitorStatusEnum.CRITICAL;
        }
    }
}
