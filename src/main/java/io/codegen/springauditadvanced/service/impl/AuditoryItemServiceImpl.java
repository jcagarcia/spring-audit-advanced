package io.codegen.springauditadvanced.service.impl;

import io.codegen.springauditadvanced.model.Auditory;
import io.codegen.springauditadvanced.model.AuditoryItem;
import io.codegen.springauditadvanced.model.enums.OperationType;
import io.codegen.springauditadvanced.repository.AuditoryItemRepository;
import io.codegen.springauditadvanced.service.api.AuditoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * = AuditoryItemServiceImpl
 * <p>
 * Service implementation for {@link AuditoryItem} entity
 *
 * @author Juan Carlos García
 */
@Service
public class AuditoryItemServiceImpl implements AuditoryItemService {

    /**
     * Repository to manage {@link AuditoryItem} entity
     */
    @Autowired
    private AuditoryItemRepository repository;

    /**
     * {@inheritDoc}
     *
     * @param newData
     * @param parentAuditory
     */
    @Override
    @Transactional
    public void registerCreateEvent(String newData, Auditory parentAuditory) {
        registerEvent(OperationType.CREATE, null, newData, parentAuditory);
    }

    /**
     * {@inheritDoc}
     *
     * @param oldData
     * @param newData
     * @param parentAuditory
     */
    @Override
    @Transactional
    public void registerUpdateEvent(String oldData, String newData, Auditory parentAuditory) {
        registerEvent(OperationType.UPDATE, oldData, newData, parentAuditory);
    }

    /**
     * {@inheritDoc}
     *
     * @param oldData
     * @param parentAuditory
     */
    @Override
    @Transactional
    public void registerDeleteEvent(String oldData, Auditory parentAuditory) {
        registerEvent(OperationType.DELETE, oldData, null, parentAuditory);
    }

    /**
     * Register a new item with all the necessary information
     *
     * @param type
     * @param oldData
     * @param newData
     * @param parent
     */
    private void registerEvent(OperationType type, String oldData, String newData, Auditory parent) {
        // Create a new auditory item
        AuditoryItem item = new AuditoryItem();
        item.setOperationType(type);
        item.setOldData(oldData);
        item.setNewData(newData);
        item.setAuditory(parent);
        // Save the item
        repository.saveAndFlush(item);
    }
}
