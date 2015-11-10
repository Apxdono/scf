package org.apx.scf.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by oleg on 10/18/15.
 */
@Entity
@Table(name = "dummy_object",schema = "test")
public class DummyObject extends DomainObject {
}
