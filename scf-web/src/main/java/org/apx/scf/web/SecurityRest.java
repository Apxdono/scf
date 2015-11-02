package org.apx.scf.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * Created by Oleg on 02.11.2015.
 */
@RestController
@RequestMapping(name = "/secure")
public class SecurityRest implements Serializable {

    @RequestMapping(name = "/password")
    public boolean passwordCheck(){
        return true;
    }
}
