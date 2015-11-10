package org.apx.scf.domain.security;

import org.apx.scf.domain.DomainObject;
import org.apx.scf.domain.enums.ESecurityAction;

import javax.persistence.*;

/**
 * Created by Oleg on 02.11.2015.
 */
@Entity
@Table(name = "security_action", schema = "security")
public class SecurityAction extends DomainObject {

    String targetObjectId;
    String targetClassName;
    ESecurityAction action;

    public SecurityAction() {
        super();
        setDisplayName("Security action");
        setSystemName("Security action");
    }

    @Column(name = "target_object_id")
    public String getTargetObjectId() {
        return targetObjectId;
    }

    @Column(name = "target_class_name")
    public String getTargetClassName() {
        return targetClassName;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "action")
    public ESecurityAction getAction() {
        return action;
    }

    public void setTargetObjectId(String targetObjectId) {
        this.targetObjectId = targetObjectId;
    }

    public void setTargetClassName(String targetClassName) {
        this.targetClassName = targetClassName;
    }

    public void setAction(ESecurityAction action) {
        this.action = action;
    }
}
