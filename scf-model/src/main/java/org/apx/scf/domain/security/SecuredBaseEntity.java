package org.apx.scf.domain.security;

import org.apx.scf.domain.DomainObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by oleg on 06.05.2015.
 */
@MappedSuperclass
public class SecuredBaseEntity extends DomainObject implements Serializable {

    Set<SecurityDescriptor> securityDescriptors;

    @OneToMany
    @JoinTable(schema = "security", joinColumns = {@JoinColumn(name = "object_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "security_descriptor_id",referencedColumnName = "id")})
    public Set<SecurityDescriptor> getSecurityDescriptors() {
        return securityDescriptors;
    }

    public void setSecurityDescriptors(Set<SecurityDescriptor> securityDescriptors) {
        this.securityDescriptors = securityDescriptors;
    }

    @Transient
    public SecurityDescriptor getMinimalDescriptor(){
        SecurityDescriptor res = new SecurityDescriptor();
        res.setPriority(10000);
        for (SecurityDescriptor descriptor : getSecurityDescriptors()) {
            if(res.getPriority() > descriptor.getPriority()){
                res = descriptor;
            }
        }
        return res;
    }

    @Transient
    public SecurityDescriptor getMaxDescriptor(){
        SecurityDescriptor res = new SecurityDescriptor();
        res.setPriority(1);
        for (SecurityDescriptor descriptor : getSecurityDescriptors()) {
            if(res == null){
                res = descriptor;
                continue;
            }
            if(res.getPriority() < descriptor.getPriority()){
                res = descriptor;
            }
        }
        return res;
    }
}
