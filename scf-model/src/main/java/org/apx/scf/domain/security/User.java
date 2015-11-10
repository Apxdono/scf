package org.apx.scf.domain.security;

import org.apx.scf.annotations.EncodeField;
import org.apx.scf.annotations.EncodedFields;
import org.springframework.cglib.reflect.FastMethod;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users", schema = "security")
//@ColumnPrefix("usr")
@AttributeOverrides({
        @AttributeOverride(name = "displayName", column = @Column(name = "username")),
        @AttributeOverride(name = "systemName", column = @Column(name = "login",unique = true,updatable = false,insertable = true,nullable = false))
})
@EncodedFields(fields = {
        @EncodeField(fieldName = "password",encoder = "md5Encoder")
})
public class User extends SecuredBaseEntity implements UserDetails, Serializable {
    private static final long serialVersionUID = -3533190277683860377L;

    String password;

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    /*Spring security stuff*/

    @Override
    @Transient
    public Collection<SecurityDescriptor> getAuthorities() {
        return getSecurityDescriptors();
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return !getDeleted();
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return !getDeleted();
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return !getDeleted();
    }

    @Override
    @Transient
    public String getUsername() {
        return getSystemName();
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return !getDeleted();
    }
}