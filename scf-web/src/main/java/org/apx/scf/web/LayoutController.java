package org.apx.scf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
