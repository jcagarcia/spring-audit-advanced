package io.codegen.springauditadvanced.repository;

import io.codegen.springauditadvanced.model.AuditoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * = AuditoryItemRepository
 * <p>
 * Spring Data JPA Repository to manage {@link AuditoryItem} entity
 *
 * @author Juan Carlos García
 */
public interface AuditoryItemRepository extends JpaRepository<AuditoryItem, Long> {
}