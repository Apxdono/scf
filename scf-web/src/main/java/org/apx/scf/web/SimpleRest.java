package org.apx.scf.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.AbstractRefreshableWebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Created by oleg on 19.06.2015.
 */
@Controller
public class SimpleRest implements Serializable {

    @Autowired
    ApplicationContext applicationContext;


    @RequestMapping(value = "/test",produces = MediaType.TEXT_PLAIN_VALUE)
    @Secured(value = "ROLE_ADMIN")
    public @ResponseBody String doTest(HttpServletRequest request, HttpServletResponse response){
        String url =request.getRequestURL().toString();
        return "This is sample text to be displayed";
    }

    @RequestMapping(value = "/ctx/reload")
    @Secured(value = "ROLE_ADMIN")
    public void reloadWebConfiguration(@RequestParam("type") String type,HttpServletRequest request, HttpServletResponse response){
        if("root".equals(type)){
            ((ConfigurableApplicationContext)applicationContext.getParent()).refresh();
        }
        if("web".equals(type)){
            ((AbstractRefreshableWebApplicationContext)applicationContext).refresh();
        }
    }



}
