package org.apx.scf.security.repository;

import org.apx.scf.domain.security.User;
import org.apx.scf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by Oleg on 18.11.2015.
 */
@Service(value = "appUserDetailsService")
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User u = repo.loadUserByUsername(s);
        if(u == null){
            throw new UsernameNotFoundException("No user with username "+s+" found");
        }
        return u;
    }
}
