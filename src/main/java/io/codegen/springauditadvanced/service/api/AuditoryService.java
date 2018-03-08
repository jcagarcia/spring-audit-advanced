package io.codegen.springauditadvanced.service.api;

import io.codegen.springauditadvanced.model.Auditory;
import io.codegen.springauditadvanced.model.enums.OperationResult;
import io.codegen.springauditadvanced.model.enums.OperationType;

/**
 * = AuditoryService
 * <p>
 * Service to manage {@link Auditory} entity
 *
 * @author Juan Carlos García
 */
public interface AuditoryService {

    /**
     * Method that registers a Login event in the {@link Auditory} table
     *
     * @param requestURI
     * @param name
     * @param remoteAddr
     * @param result
     */
    Auditory registerLoginEvent(String requestURI, String name, String remoteAddr, OperationResult result);

    /**
     * Method that registers a Logout event in the {@link Auditory} table
     *
     * @param requestURI
     * @param name
     * @param remoteAddr
     */
    Auditory registerLogoutEvent(String requestURI, String name, String remoteAddr);

    /**
     * Register an event providing the event type.
     *
     * @param contextPath
     * @param requestURI
     * @param userName
     * @param remoteIp
     * @param type
     * @param result
     */
    Auditory registerEvent(String contextPath, String requestURI, String userName, String remoteIp, OperationType type, OperationResult result);

    /**
     * Method that registers a navigation event in the {@link Auditory} table
     *
     * @param contextPath
     * @param requestURI
     * @param userName
     * @param remoteIp
     * @param result
     */
    Auditory registerNavigationEvent(String contextPath, String requestURI, String userName, String remoteIp, OperationResult result);


    /**
     * Method that registers a Create event in the {@link Auditory} table
     *
     * @param contextPath
     * @param requestURI
     * @param userName
     * @param remoteIp
     * @param result
     */
    Auditory registerCreateEvent(String contextPath, String requestURI, String userName, String remoteIp, OperationResult result);

    /**
     * Method that registers a Update event in the {@link Auditory} table
     *
     * @param contextPath
     * @param requestURI
     * @param userName
     * @param remoteIp
     * @param result
     */
    Auditory registerUpdateEvent(String contextPath, String requestURI, String userName, String remoteIp, OperationResult result);

    /**
     * Method that registers a Delete event in the {@link Auditory} table
     *
     * @param contextPath
     * @param requestURI
     * @param userName
     * @param remoteIp
     * @param result
     */
    Auditory registerDeleteEvent(String contextPath, String requestURI, String userName, String remoteIp, OperationResult result);
}
