package io.github.jcagarcia.springauditadvanced.utils;

import io.github.jcagarcia.springauditadvanced.model.AuditableEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * = AuditoryContext
 * <p>
 * Class that contains information about all the items that should be audited
 *
 * @author Juan Carlos García
 */
public class AuditoryContext {

    /**
     * List that contains all the items that should be stored
     * in the Auditory table
     */
    private List<AuditableEntity> itemsToAdd = new ArrayList<>();

    /**
     * Map where the key is the identifier of an existing element and the value is the
     * original value
     */
    private Map<TypeAndId, AuditableEntity> itemsToUpdate = new HashMap<>();

    /**
     * Map where the key is the identifier of an existing element and the value is the
     * new value
     */
    private Map<TypeAndId, AuditableEntity> itemsUpdated = new HashMap<>();

    /**
     * List that contains all the items that should be stored in the Auditory table
     * as removed elements
     */
    private List<AuditableEntity> itemsToRemove = new ArrayList<>();

    /**
     * Returns the items to audit
     *
     * @return
     */
    public List<AuditableEntity> getItemsToAdd() {
        return itemsToAdd;
    }

    /**
     * Returns the items to be updated
     *
     * @return
     */
    public Map<TypeAndId, AuditableEntity> getItemsToUpdate() {
        return itemsToUpdate;
    }

    /**
     * Returns the items updated
     *
     * @return
     */
    public Map<TypeAndId, AuditableEntity> getItemsUpdated() {
        return itemsUpdated;
    }

    /**
     * Returns the items to remove
     *
     * @return
     */
    public List<AuditableEntity> getItemsToRemove() {
        return itemsToRemove;
    }

    /**
     * Includes new element
     *
     * @param item
     */
    public void addNewElement(AuditableEntity item) {
        getItemsToAdd().add(item);
    }

    /**
     * Saves an existing element
     *
     * @param obj
     * @param id
     */
    public void loadElement(AuditableEntity obj, Long id) {
        getItemsToUpdate().put(getKey(obj, id), obj);
    }

    /**
     * Updates an existing element
     *
     * @param obj
     * @param id
     */
    public void updateElement(AuditableEntity obj, Long id) {
        getItemsUpdated().put(getKey(obj, id), obj);
    }

    /**
     * Removes an existing element
     */
    public void removeElement(AuditableEntity item) {
        getItemsToRemove().add(item);
    }

    /**
     * Private method to obtain a valid key
     *
     * @param obj
     * @param id
     * @return
     */
    private TypeAndId getKey(Object obj, Long id) {
        return new TypeAndId(obj.getClass().toString(), id);
    }


    /**
     * Inner class used to define the Type and id
     */
    public class TypeAndId {

        /**
         * The class name
         */
        private String className;

        /**
         * The identifier of the entity
         */
        private Long id;

        /**
         * Constructor
         *
         * @param className
         * @param id
         */
        public TypeAndId(String className, Long id) {
            this.className = className;
            this.id = id;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        /**
         * Override the equals method to be able
         * to identify the map key
         *
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            TypeAndId item = (TypeAndId) o;
            return item.getClassName().equals(this.className) && item.getId().equals(this.id);
        }

        /**
         * Override the hashCode method to be able to identify the map key
         *
         * @return
         */
        @Override
        public int hashCode() {
            return this.className.hashCode() + this.id.hashCode();
        }
    }


}
