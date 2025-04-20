package com.aviralgupta.site.monitoring_system.entity;

import com.aviralgupta.site.monitoring_system.util.enums.MonitorTypeEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "monitors")
@Data
@Builder
@ToString(exclude = "user")
@NoArgsConstructor
@AllArgsConstructor
public class Monitor {

    @Id
    private String id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "server_address", nullable = false)
    private String serverAddress;

    @Column(name ="type", nullable = false)
    private MonitorTypeEnum type;

    @Column(name = "scheduled_interval", nullable = false)
    private Integer ScheduleInterval;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "notification_status", nullable = false)
    private Boolean notificationStatus;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "last_notified_at")
    private LocalDateTime lastNotifiedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
