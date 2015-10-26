package org.apx.scf.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by oleg on 10/18/15.
 */
@MappedSuperclass
@Access(AccessType.PROPERTY)
public class DomainObject implements Serializable {
    private static final long serialVersionUID = -8895731874597653746L;

    String id;
    Long version;
    String systemName;
    String displayName;
    Boolean deleted;
    Calendar createDate;
    Calendar lastModifiedDate;

    public DomainObject() {
        version = 0L;
        systemName = "";
        displayName = "";
        deleted = Boolean.FALSE;
    }

    @Id
    @GeneratedValue(generator="system-uuid")
    @Column(name = "id",length = 36)
    public String getId() {
        return id;
    }

    @Version
    @Column(name = "version")
    public Long getVersion() {
        return version;
    }

    @Column(name = "system_name",length = 256)
    public String getSystemName() {
        return systemName;
    }

    @Column(name = "display_name",length = 256)
    public String getDisplayName() {
        return displayName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    @CreatedDate
    public Calendar getCreateDate() {
        return createDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date")
    @LastModifiedDate
    public Calendar getLastModifiedDate() {
        return lastModifiedDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public void setLastModifiedDate(Calendar lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainObject that = (DomainObject) o;
        return new EqualsBuilder().append(id,that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+" {" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", systemName='" + systemName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
