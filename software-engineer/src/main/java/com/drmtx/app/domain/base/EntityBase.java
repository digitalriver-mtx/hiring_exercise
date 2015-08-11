package com.drmtx.app.domain.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity base class to handle technical details of id and versioning.
 */
@MappedSuperclass
public abstract class EntityBase {

    @Transient
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "creation_ts", nullable = false)
    private Date creationTS;

    @Version
    @Column(name = "version")
    private Integer version;

    protected EntityBase() {
        creationTS = new Date();
    }

    protected EntityBase(Date creationTS) {
        this.creationTS = creationTS;
    }

    protected void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Date getCreationTS() {
        return creationTS;
    }

    public void setCreationTS(Date createdAt) {
        this.creationTS = createdAt;
    }

    protected void setVersion(final Integer version) {
        this.version = version;
    }

    public Integer getVersion() {
        return version;
    }

    public boolean isNew() {
        return null == getId();
    }

    @Override
    public boolean equals(Object obj) {

        if (null == obj) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!getClass().equals(obj.getClass())) {
            return false;
        }

        EntityBase that = (EntityBase) obj;

        return null != this.getId() && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode += null == getId() ? 0 : getId().hashCode() * 31;
        return hashCode;
    }
}
