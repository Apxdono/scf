package org.apx.scf.repository;

import org.apx.scf.domain.security.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Oleg on 22.11.2015.
 */
@Repository
public interface UserRepository extends CrudRepository<User,String> {

    @Query(value = "SELECT u FROM User u WHERE u.systemName = ?#{[0]}")
    User loadUserByUsername(String login);
}
