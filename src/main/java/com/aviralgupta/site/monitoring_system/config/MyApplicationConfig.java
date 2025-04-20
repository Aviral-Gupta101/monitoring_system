package com.aviralgupta.site.monitoring_system.config;

import com.aviralgupta.site.monitoring_system.service.monitors.AbstractMonitor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyApplicationConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public List<AbstractMonitor> getAllMonitors(){
        return new ArrayList<>();
    }
}
