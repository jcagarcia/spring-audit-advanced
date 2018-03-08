package io.github.jcagarcia.springauditadvanced.utils;

import io.github.jcagarcia.springauditadvanced.model.AuditableEntity;
import org.springframework.util.Assert;

/**
 * = AuditoryContextHolder
 * <p>
 * Class that registers the AuditoryContext in a ThreadLocal
 *
 * @author Juan Carlos García
 */
public class AuditoryContextHolder {

    /**
     * The Context
     */
    private static ThreadLocal<AuditoryContext> CONTEXT = new ThreadLocal<>();

    /**
     * Sets the new Auditory context
     * @param auditoryContext
     */
    public static void set(AuditoryContext auditoryContext) {
        Assert.notNull(auditoryContext, "auditory context cannot be null");
        CONTEXT.set(auditoryContext);
    }

    /**
     * Obtains the context
     *
     * @return
     */
    public static AuditoryContext getContext() {
        return CONTEXT.get();
    }

    /**
     * Clears the context
     */
    public static void clear() {
        CONTEXT.remove();
    }

    /**
     * Utility method with common operation in the @PrePersist phase
     * @param item
     */
    public static void prePersist(AuditableEntity item) {
        AuditoryContext context = getContext();
        if(context != null){
            context.addNewElement(item);
        }
    }

    /**
     * Utility method with common operation in @PostLoad phase
     * @param obj
     * @param id
     */
    public static void postLoad(AuditableEntity obj, Long id) {
        AuditoryContext context = getContext();
        if(context != null){
            context.loadElement(obj, id);
        }
    }

    /**
     * Utility method with common operation in @PostLoad phase
     * @param obj
     * @param id
     */
    public static void preUpdate(AuditableEntity obj, Long id) {
        AuditoryContext context = getContext();
        if(context != null){
            context.updateElement(obj, id);
        }
    }

    /**
     * Utility method with common operation in the @PreRemove phase
     * @param item
     */
    public static void preRemove(AuditableEntity item) {
        AuditoryContext context = getContext();
        if(context != null){
            context.removeElement(item);
        }
    }
}

