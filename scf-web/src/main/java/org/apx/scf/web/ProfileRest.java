package org.apx.scf.web;

import org.apx.scf.beans.ApplicationContextHolder;
import org.apx.scf.domain.security.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Oleg on 03.12.2015.
 */
@RestController
@RequestMapping(value = "/profile")
public class ProfileRest {

    @RequestMapping(method = RequestMethod.GET)
    @Secured("ROLE_USER")
    public User getMyInfo(){
        return ApplicationContextHolder.instance().isFullyAuthenticated() ? (User) ApplicationContextHolder.instance().getAuthentication().getPrincipal() : null;
    }
}
