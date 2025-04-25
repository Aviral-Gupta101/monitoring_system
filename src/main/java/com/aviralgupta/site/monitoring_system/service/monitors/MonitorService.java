package com.aviralgupta.site.monitoring_system.service.monitors;

import com.aviralgupta.site.monitoring_system.exception.custom_exceptions.MonitorException;
import com.aviralgupta.site.monitoring_system.util.enums.MonitorTypeEnum;
import com.aviralgupta.site.monitoring_system.util.factory.MonitorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MonitorService {

    private final List<AbstractMonitor> monitorList;

    @Autowired
    public MonitorService(List<AbstractMonitor> monitorList) {
        this.monitorList = monitorList;
    }

    public String createMonitor(int userId, String serverAddress, int scheduleInterval, MonitorTypeEnum type){

        AbstractMonitor monitor = MonitorFactory.getMonitor(type, userId, serverAddress);
        monitor.setScheduledInterval(scheduleInterval);
        monitor.schedule();
        monitorList.add(monitor);

        return monitor.getId();
    }

    public void deleteMonitor(String monitorId){

        try{
            disableMonitor(monitorId);
            monitorList.removeIf(item -> item.getId().equals(monitorId));

        } catch (MonitorException exception){
            throw new MonitorException("Unable to delete monitor : " + exception.getMessage());
        }
    }

    public void enableMonitor(String monitorId){

        AbstractMonitor monitor = getMonitorById(monitorId);

        if(monitor == null)
            throw new MonitorException("Unable to find monitor in system cache");

        monitor.schedule();
    }

    public void disableMonitor(String monitorId){

        AbstractMonitor monitor = getMonitorById(monitorId);

        if(monitor == null)
            throw new MonitorException("Unable to find monitor in system cache");

        monitor.stopSchedule();
    }

    private AbstractMonitor getMonitorById(String monitorId){

        for (AbstractMonitor monitor : monitorList) {
            if(monitor.getId().equals(monitorId))
                return monitor;
        }

        return null;
    }
}
