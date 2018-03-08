package io.github.jcagarcia.springauditadvanced.model;

import io.github.jcagarcia.springauditadvanced.model.enums.OperationType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * = AuditoryItem
 * <p>
 * Entity that contains all the necessary information about an item that has been created, updated or deleted
 * in the application. It will be related with a parent Auditory element that will contain all the items
 * that has been modified during the operation.
 *
 * @author Juan Carlos García
 */
@Entity
public class AuditoryItem {

    /**
     * Identifier field
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Type of Operation
     */
    @NotNull
    @Enumerated
    private OperationType operationType;

    /**
     * The old data
     */
    private String oldData;

    /**
     * The new data
     */
    private String newData;

    /**
     * The parent element that has generated this item
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Auditory auditory;

    // GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getOldData() {
        return oldData;
    }

    public void setOldData(String oldData) {
        this.oldData = oldData;
    }

    public String getNewData() {
        return newData;
    }

    public void setNewData(String newData) {
        this.newData = newData;
    }

    public Auditory getAuditory() {
        return auditory;
    }

    public void setAuditory(Auditory auditory) {
        this.auditory = auditory;
    }
}
