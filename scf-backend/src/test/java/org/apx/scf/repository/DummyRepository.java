package org.apx.scf.repository;

import org.apx.scf.domain.DummyObject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by oleg on 10/26/15.
 */
@Repository
public interface DummyRepository extends CrudRepository<DummyObject,String> {
}
