package org.apx.scf.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
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
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.isNotBlank(templateName)?templateName:"");
        if(StringUtils.isNotBlank(partName)){
            sb.append(" :: ").append(partName);
        }
        return sb.toString();
    }
}
