package io.github.jcagarcia.springauditadvanced.service.impl;

import io.github.jcagarcia.springauditadvanced.model.Auditory;
import io.github.jcagarcia.springauditadvanced.model.enums.OperationResult;
import io.github.jcagarcia.springauditadvanced.model.enums.OperationType;
import io.github.jcagarcia.springauditadvanced.repository.AuditoryRepository;
import io.github.jcagarcia.springauditadvanced.service.api.AuditoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * = AuditoryServiceImpl
 * <p>
 * Service to manage {@link Auditory} entity
 *
 * @author Juan Carlos García
 */
@Service
public class AuditoryServiceImpl implements AuditoryService {

    /**
     * Repository related with the {@link Auditory} entity
     */
    @Autowired
    private AuditoryRepository repository;

    /**
     * {@inheritDoc}
     *
     * @param requestURI
     * @param userName
     * @param remoteIp
     */
    @Override
    @Transactional
    public Auditory registerLoginEvent(String requestURI, String userName, String remoteIp, OperationResult result) {
        // Obtains the auditory using the provided params
        Auditory auditory = populateAuditory(userName, remoteIp, requestURI, OperationType.LOGIN, result);
        // save the element
        return repository.saveAndFlush(auditory);
    }

    /**
     * {@inheritDoc}
     *
     * @param requestURI
     * @param userName
     * @param remoteIp
     */
    @Override
    @Transactional
    public Auditory registerLogoutEvent(String requestURI, String userName, String remoteIp) {

        // Obtains the auditory using the provided params
        Auditory auditory = populateAuditory(userName, remoteIp, requestURI, OperationType.LOGOUT, OperationResult.SUCCESS);
        // save the element
        return repository.saveAndFlush(auditory);
    }


    /**
     * {@inheritDoc}
     *
     * @param contextPath
     * @param requestURI
     * @param userName
     * @param remoteIp
     * @param type
     * @param result
     */
    @Override
    @Transactional
    public Auditory registerEvent(String contextPath, String requestURI, String userName, String remoteIp, OperationType type, OperationResult result) {
        // Create new Auditory event
        Auditory auditory = populateAuditory(userName, remoteIp, requestURI, type, result);
        // save the element
        return repository.saveAndFlush(auditory);
    }

    /**
     * {@inheritDoc}
     *
     * @param contextPath
     * @param requestURI
     * @param userName
     * @param remoteIp
     * @param result
     */
    @Override
    @Transactional
    public Auditory registerNavigationEvent(String contextPath, String requestURI, String userName, String remoteIp, OperationResult result) {
        return registerEvent(contextPath, requestURI, userName, remoteIp, OperationType.NAVIGATE, result);
    }

    /**
     * {@inheritDoc}
     *
     * @param contextPath
     * @param requestURI
     * @param userName
     * @param remoteIp
     * @param result
     */
    @Override
    @Transactional
    public Auditory registerCreateEvent(String contextPath, String requestURI, String userName, String remoteIp, OperationResult result) {
        return registerEvent(contextPath, requestURI, userName, remoteIp, OperationType.CREATE, result);
    }

    /**
     * {@inheritDoc}
     *
     * @param contextPath
     * @param requestURI
     * @param userName
     * @param remoteIp
     * @param result
     */
    @Override
    @Transactional
    public Auditory registerUpdateEvent(String contextPath, String requestURI, String userName, String remoteIp, OperationResult result) {
        return registerEvent(contextPath, requestURI, userName, remoteIp, OperationType.UPDATE, result);
    }


    /**
     * {@inheritDoc}
     *
     * @param contextPath
     * @param requestURI
     * @param userName
     * @param remoteIp
     * @param result
     */
    @Override
    @Transactional
    public Auditory registerDeleteEvent(String contextPath, String requestURI, String userName, String remoteIp, OperationResult result) {
        return registerEvent(contextPath, requestURI, userName, remoteIp, OperationType.DELETE, result);
    }

    /**
     * Create the Auditory
     *
     * @param userName
     * @param ip
     * @param url
     * @param operationType
     * @param result
     * @return
     */
    private Auditory populateAuditory(String userName, String ip, String url, OperationType operationType, OperationResult result) {
        // Create new Auditory event
        Auditory auditory = new Auditory();

        // Set the default navigation elements
        auditory.setDate(new Date());
        auditory.setOperationType(operationType);
        auditory.setOperationResult(result);

        // Set the provided info
        auditory.setUserName(userName);
        auditory.setIp(ip);
        auditory.setUrl(url);

        return auditory;
    }

}
