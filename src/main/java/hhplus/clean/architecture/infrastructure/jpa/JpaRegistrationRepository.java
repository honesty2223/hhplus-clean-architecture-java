package hhplus.clean.architecture.infrastructure.jpa;

import hhplus.clean.architecture.domain.entity.Registration;
import hhplus.clean.architecture.domain.entity.RegistrationPk;
import hhplus.clean.architecture.domain.repository.RegistrationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRegistrationRepository extends JpaRepository<Registration, RegistrationPk>, RegistrationRepository {
}
