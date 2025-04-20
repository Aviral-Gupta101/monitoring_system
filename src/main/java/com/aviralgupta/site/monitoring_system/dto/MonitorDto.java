package com.aviralgupta.site.monitoring_system.dto;

import com.aviralgupta.site.monitoring_system.util.enums.MonitorTypeEnum;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.InetAddressValidator;


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

    @AssertTrue(message = "serverAddress must be a valid IPv4 address or domain name")
    public boolean isServerAddressValid() {
        if (serverAddress == null || serverAddress.isBlank()) return true; // let @NotBlank handle this

        InetAddressValidator ipValidator = InetAddressValidator.getInstance();
        DomainValidator domainValidator = DomainValidator.getInstance(true); // allow localhost, etc.

        return ipValidator.isValidInet4Address(serverAddress) || domainValidator.isValid(serverAddress);
    }

}
