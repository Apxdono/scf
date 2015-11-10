package org.apx.scf.domain.security;

import org.apx.scf.domain.DomainObject;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by oleg on 06.05.2015.
 */
@Entity
@Table(name = "security_descriptors", schema = "security")
public class SecurityDescriptor extends DomainObject implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = -387705888479897944L;

    int priority;

    @Column(name = "priority")
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    @Transient
    public String getAuthority() {
        return getSystemName();
    }
}
