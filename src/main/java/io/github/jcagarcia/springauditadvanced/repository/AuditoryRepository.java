package io.github.jcagarcia.springauditadvanced.repository;

import io.github.jcagarcia.springauditadvanced.model.Auditory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * = AuditoryRepository
 * <p>
 * Spring Data JPA Repository to manage {@link Auditory} entity
 *
 * @author Juan Carlos García
 */
public interface AuditoryRepository extends JpaRepository<Auditory, Long> {
}
