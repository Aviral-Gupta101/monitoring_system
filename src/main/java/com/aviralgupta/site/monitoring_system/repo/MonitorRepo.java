package com.aviralgupta.site.monitoring_system.repo;

import com.aviralgupta.site.monitoring_system.entity.Monitor;
import com.aviralgupta.site.monitoring_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitorRepo extends JpaRepository<Monitor, Integer> {
    List<Monitor> findAllByUser(User user);
}
