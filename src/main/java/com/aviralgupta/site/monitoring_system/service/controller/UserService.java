package com.aviralgupta.site.monitoring_system.service.controller;

import com.aviralgupta.site.monitoring_system.dto.MonitorDto;
import com.aviralgupta.site.monitoring_system.entity.Monitor;
import com.aviralgupta.site.monitoring_system.entity.User;
import com.aviralgupta.site.monitoring_system.exception.custom_exceptions.NotFoundException;
import com.aviralgupta.site.monitoring_system.repo.MonitorRepo;
import com.aviralgupta.site.monitoring_system.repo.UserRepo;
import com.aviralgupta.site.monitoring_system.util.GetPrincipalUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final MonitorRepo monitorRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepo userRepo, MonitorRepo monitorRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.monitorRepo = monitorRepo;
        this.modelMapper = modelMapper;
    }

    private User getCurrentUser() {
        String currentUserEmail = GetPrincipalUser.getCurrentUserEmail();
        return userRepo.findByEmail(currentUserEmail).get();
    }

    public List<Monitor> getAllMonitor(){

        User foundUser = getCurrentUser();
        return foundUser.getMonitors();
    }

    public Monitor createMonitor(MonitorDto dto){

        User foundUser = getCurrentUser();

        Monitor newMonitor = Monitor.builder()
                .user(foundUser)
                .type(dto.getType())
                .serverAddress(dto.getServerAddress())
                .ScheduleInterval(dto.getScheduleInterval())
                .status(true)
                .notificationStatus(true)
                .build();

        if(foundUser.getMonitors() == null){
            foundUser.setMonitors(new ArrayList<>());
        }

        foundUser.getMonitors().add(newMonitor);

        return monitorRepo.save(newMonitor);
    }

    public void deleteMonitor(Integer monitorId){

        Optional<Monitor> optionalMonitor = monitorRepo.findById(monitorId);

        if(optionalMonitor.isEmpty())
            throw new NotFoundException("Invalid monitor ID");

        Monitor monitor = optionalMonitor.get();

        if(monitor.getUser().getEmail() != getCurrentUser().getEmail())
            throw new NotFoundException("Invalid monitor ID");

        monitorRepo.deleteById(monitorId);
    }

    public void disableMonitor(Integer monitorId){

        Optional<Monitor> optionalMonitor = monitorRepo.findById(monitorId);

        if(optionalMonitor.isEmpty())
            throw new NotFoundException("Invalid monitor ID");

        Monitor monitor = optionalMonitor.get();
        monitor.setStatus(false);
        monitorRepo.save(monitor);
    }

    public void enableMonitor(Integer monitorId){

        Optional<Monitor> optionalMonitor = monitorRepo.findById(monitorId);

        if(optionalMonitor.isEmpty())
            throw new NotFoundException("Invalid monitor ID");

        Monitor monitor = optionalMonitor.get();
        monitor.setStatus(true);
        monitorRepo.save(monitor);
    }
}
