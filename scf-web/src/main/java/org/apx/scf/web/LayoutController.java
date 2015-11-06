package org.apx.scf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Oleg on 26.10.2015.
 */
@Controller
public class LayoutController {

    @RequestMapping("/")
    public String getRootPage(HttpServletRequest request) {
        return "index";
    }

    @RequestMapping("/partials")
    public String getUpdatedPartialPage(@RequestParam("template") String templateName,@RequestParam("partName") String partName){
        System.out.println("Page requested : "+templateName+" with partial: "+partName);
        return templateName+" :: "+partName;
    }
}
