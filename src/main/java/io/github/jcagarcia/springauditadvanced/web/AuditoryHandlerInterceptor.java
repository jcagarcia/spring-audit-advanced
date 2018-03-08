package io.github.jcagarcia.springauditadvanced.web;

import io.github.jcagarcia.springauditadvanced.model.AuditableEntity;
import io.github.jcagarcia.springauditadvanced.model.Auditory;
import io.github.jcagarcia.springauditadvanced.utils.AuditoryContext;
import io.github.jcagarcia.springauditadvanced.utils.AuditoryContextHolder;
import io.github.jcagarcia.springauditadvanced.model.enums.OperationResult;
import io.github.jcagarcia.springauditadvanced.model.enums.OperationType;
import io.github.jcagarcia.springauditadvanced.service.api.AuditoryItemService;
import io.github.jcagarcia.springauditadvanced.service.api.AuditoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * = AuditoryHandlerInterceptor
 * <p>
 * Interceptor that will capture all the HTTP petitions and will register all the events in the Auditory table
 *
 * @author Juan Carlos García
 */
public class AuditoryHandlerInterceptor extends HandlerInterceptorAdapter {

    /**
     * Register a LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditoryHandlerInterceptor.class);

    /**
     * Service to manage auditory
     */
    private AuditoryService auditoryService;

    /**
     * Service to manage auditory item elements
     */
    private AuditoryItemService auditoryItemService;

    /**
     * Constructor that receives all the parameters
     *
     * @param auditoryService
     * @param auditoryItemService
     */
    public AuditoryHandlerInterceptor(AuditoryService auditoryService, AuditoryItemService auditoryItemService) {
        this.auditoryService = auditoryService;
        this.auditoryItemService = auditoryItemService;
    }

    /**
     * Before to start, save some valid information in the request to be able to obtain it when the process
     * finishes.
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // First of all, obtain the authentication
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String userName = "anonymousUser";
        if (auth != null) {
            userName = auth.getName();
        }

        // Save the original URL and the Original User that initializes the request
        request.setAttribute("originalMethod", request.getMethod());
        request.setAttribute("originalIp", request.getRemoteAddr());
        request.setAttribute("originalUrl", request.getRequestURI());
        request.setAttribute("originalUser", userName);

        // If we're in a POST, PUT or DELETE method, we need to save
        if (!request.getMethod().equals("GET")) {
            AuditoryContextHolder.set(new AuditoryContext());
        } else {
            AuditoryContextHolder.clear();
        }
        return true;
    }

    /**
     * When all the process has finised, register all the petitions in the {@link Auditory} table
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        // Obtain the values saved in the pre handler
        String originalMethod = (String) request.getAttribute("originalMethod");
        String originalIp = (String) request.getAttribute("originalIp");
        String originalUrl = (String) request.getAttribute("originalUrl");
        String originalUser = (String) request.getAttribute("originalUser");

        // Obtain the authentication
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String userName = "anonymousUser";
        if (auth != null) {
            userName = auth.getName();
        }

        try {

            // If is valid, an is a CUD method.
            if (!request.getMethod().equals("GET")) {

                // Obtain the auditory context
                AuditoryContext context = AuditoryContextHolder.getContext();

                // Register the event by the provided request method
                OperationType type = null;
                switch (request.getMethod()) {
                    case "POST":
                        type = OperationType.CREATE;
                        break;
                    case "PUT":
                        type = OperationType.UPDATE;
                        break;
                    case "DELETE":
                        type = OperationType.DELETE;
                        break;
                }
                Auditory parentAuditory = auditoryService.registerEvent(request.getContextPath(), originalUrl, originalUser, originalIp, type, OperationResult.SUCCESS);

                // Register a one related child for each item created, updated or deleted.
                context.getItemsToAdd().forEach(e -> {
                    auditoryItemService.registerCreateEvent(e.getAuditoryRepresentation(), parentAuditory);
                });
                context.getItemsUpdated().forEach((k, v) -> {
                    AuditableEntity oldData = context.getItemsToUpdate().get(k);
                    auditoryItemService.registerUpdateEvent(oldData.getAuditoryRepresentation(), v.getAuditoryRepresentation(), parentAuditory);
                });
                context.getItemsToRemove().forEach(e -> {
                    auditoryItemService.registerDeleteEvent(e.getAuditoryRepresentation(), parentAuditory);
                });

            } else if (request.getMethod().equals("GET") && !request.getRequestURI().contains("error")) {

                // Notify navigation between pages
                auditoryService.registerNavigationEvent(request.getContextPath(), request.getRequestURI(), userName, request.getRemoteAddr(),
                        OperationResult.SUCCESS);

            } else if (request.getRequestURI().contains("error")) {

                // If some error appears, means that, depending of the original event, we need to notify some action
                OperationType type = OperationType.UNKNOWN;

                if (originalUrl != null && originalMethod != null && originalMethod.equals("GET")) {
                    type = OperationType.NAVIGATE;
                } else if (originalUrl != null && originalMethod != null && originalMethod.equals("POST")) {
                    type = OperationType.CREATE;
                } else if (originalUrl != null && originalMethod != null && originalMethod.equals("PUT")) {
                    type = OperationType.UPDATE;
                } else if (originalUrl != null && originalMethod != null && originalMethod.equals("DELETE")) {
                    type = OperationType.DELETE;
                }

                // Register failure
                auditoryService.registerEvent(request.getContextPath(), originalUrl, originalUser, originalIp, type,
                        OperationResult.FAILURE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("ERROR: Error trying to audit some registry. Message: " + e.getMessage());
            try {
                // If some exception appears during auditing process. Register an audition failure
                auditoryService.registerEvent(request.getContextPath(), originalUrl, originalUser, originalIp, OperationType.AUDIT,
                        OperationResult.FAILURE);
            } catch (Exception e2) {
                e2.printStackTrace();
                // If other exception appears. Log it
                LOGGER.error("ERROR: Error trying to save an audit failure registry. Message: " + e2.getMessage());
            }
        } finally {
            // Always on finish, clear the context to prevent error in threads
            AuditoryContextHolder.clear();
        }
    }

}
