package io.codegen.springauditadvanced.model;

import io.codegen.springauditadvanced.utils.AuditoryContextHolder;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * = AuditableEntity
 * <p>
 * Abstract class that contains all the necessary JPA Event Listeners to save
 * the current status in the {@link AuditoryContextHolder}.
 * <p>
 * All the entities that wants to store his changes in the {@link Auditory} and {@link AuditoryItem} table
 * should extend this abstract class.
 *
 * @author Juan Carlos García
 */
@MappedSuperclass
public abstract class AuditableEntity {

    /**
     * Obtains the representation of this entity for the auditory table. This method
     * could be overridden to modify how the data is stored in the auditory table.
     *
     * @return
     */
    public String getAuditoryRepresentation() {
        return this.toString();
    }

    /**
     * before a new entity is persisted (added to the EntityManager).
     */
    @PrePersist
    public void prePersist() {
        AuditoryContextHolder.prePersist(this);
    }

    /**
     * after an entity has been retrieved from the database.
     */
    @PostLoad
    public void postLoad() {
        AuditoryContextHolder.postLoad(this, getId());
    }

    /**
     * when an entity is identified as modified by the EntityManager.
     */
    @PreUpdate
    public void preUpdate() {
        AuditoryContextHolder.preUpdate(this, getId());
    }

    /**
     * when an entity is marked for removal in the EntityManager.
     */
    @PreRemove
    public void preRemove() {
        AuditoryContextHolder.preRemove(this);
    }

    /**
     * Abstract method that should be implemented by all the entities that
     * extends AuditableEntity
     *
     * @return
     */
    public abstract Long getId();


}

