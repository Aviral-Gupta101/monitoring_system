package com.aviralgupta.site.monitoring_system.dto;

import com.aviralgupta.site.monitoring_system.util.enums.MonitorTypeEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MonitorDto {

    private Integer id;

    @NotNull
    @NotBlank
    private String serverAddress;

    @NotNull
    @Min(value = 10)
    private Integer ScheduleInterval;

    private MonitorTypeEnum type;

}
