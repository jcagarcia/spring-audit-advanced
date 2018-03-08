package io.codegen.springauditadvanced.model;

import io.codegen.springauditadvanced.model.enums.OperationResult;
import io.codegen.springauditadvanced.model.enums.OperationType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * = Auditory
 * <p>
 * Entity that contains all the necessary information about the Auditory
 *
 * @author Juan Carlos García
 */
@Entity
public class Auditory {

    /**
     * Identifier field
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Date and time. Date/Time should be stored in GMT+0 and should be showed in operating system browser time zone.
     */
    @NotNull
    private Date date;

    /**
     * User that carry out the operation/attempt
     */
    @NotNull
    private String userName;

    /**
     * IP of user browser
     */
    @NotNull
    @Size(max = 15)
    private String ip;

    /**
     * Type of Operation
     */
    @NotNull
    @Enumerated
    private OperationType operationType;

    /**
     * The complete URL where the user access
     */
    private String url;

    /**
     * The result of the operation
     */
    @NotNull
    @Enumerated
    private OperationResult operationResult;

    /**
     * All the items related with this auditory operation
     */
    @OneToMany(cascade = {javax.persistence.CascadeType.MERGE, javax.persistence.CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "auditory")
    private Set<AuditoryItem> auditoryItems = new HashSet<>();


    // GETTERS AND SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public OperationResult getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(OperationResult operationResult) {
        this.operationResult = operationResult;
    }

    public Set<AuditoryItem> getAuditoryItems() {
        return auditoryItems;
    }

    public void setAuditoryItems(Set<AuditoryItem> auditoryItems) {
        this.auditoryItems = auditoryItems;
    }
}
