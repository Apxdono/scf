package org.apx.scf.web;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

/**
 * Created by oleg on 19.06.2015.
 */
@Controller
public class SimpleRest implements Serializable {

    @RequestMapping(value = "/test",produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String doTest(){
        return "This is sample text to be displayed";
    }
}
