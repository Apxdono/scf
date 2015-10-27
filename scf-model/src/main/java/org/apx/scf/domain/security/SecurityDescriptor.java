package org.apx.scf.domain.security;

import com.fasterxml.jackson.annotation.JsonView;
import com.kmware.adb.domain.model.jsonview.View;
import com.kmware.adb.domain.utils.ColumnPrefix;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * Created by oleg on 06.05.2015.
 */
@Entity
@Table(name = "security_descriptors",catalog = "mrg", schema = "security")
@ColumnPrefix("sd")
public class SecurityDescriptor extends BaseEntity implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = -387705888479897944L;

    int priority;

    @Column(name = "priority")
    @JsonView(View.Exposed.class)
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    @Transient
    public String getAuthority() {
        return getInternalName();
    }
}
