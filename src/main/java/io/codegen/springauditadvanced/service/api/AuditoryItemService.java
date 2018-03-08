package io.codegen.springauditadvanced.service.api;

import io.codegen.springauditadvanced.model.AuditoryItem;
import io.codegen.springauditadvanced.model.Auditory;

/**
 * = AuditoryItemService
 * <p>
 * Service to manage {@link AuditoryItem} entity
 *
 * @author Juan Carlos García
 */
public interface AuditoryItemService {

    /**
     * Operation to register new created elements as child of an operation
     *
     * @param newData
     * @param parentAuditory
     */
    void registerCreateEvent(String newData, Auditory parentAuditory);

    /**
     * Operation to register updated elements as child of an operation
     *
     * @param oldData
     * @param newData
     * @param parentAuditory
     */
    void registerUpdateEvent(String oldData, String newData, Auditory parentAuditory);

    /**
     * Operation to register deleted elements as child of an operation
     *
     * @param oldData
     * @param parentAuditory
     */
    void registerDeleteEvent(String oldData, Auditory parentAuditory);
}
