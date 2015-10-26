package org.apx.scf.web;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by Oleg on 26.10.2015.
 */
@Controller(value = "testservice")
@SessionAttributes("bitches")
public class TestService {

    public String greeting(){
        return "Бляди!";
    }

}
