package hhplus.clean.architecture.domain.repository;

import hhplus.clean.architecture.domain.entity.Registration;
import hhplus.clean.architecture.domain.entity.RegistrationPk;

public interface RegistrationRepository {

    boolean existsById(RegistrationPk registrationPk);

    Registration save(Registration newRegistration);

}
