package hhplus.clean.architecture.application.dto;

import hhplus.clean.architecture.domain.entity.Registration;
import hhplus.clean.architecture.domain.entity.RegistrationPk;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    private RegistrationPk id;
    private LocalDate registeredAt;

    public RegistrationDto(Registration registration) {
        this.id = registration.getId();
        this.registeredAt = registration.getRegisteredAt();
    }
}
