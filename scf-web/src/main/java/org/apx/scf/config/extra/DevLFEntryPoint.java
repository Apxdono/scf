package org.apx.scf.config.extra;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Oleg on 02.11.2015.
 */
public class DevLFEntryPoint implements AuthenticationEntryPoint {



    public DevLFEntryPoint(String loginFormUrl) {

    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        if(session == null){
            session = request.getSession(true);
        }
        if(request.getUserPrincipal() == null) {
            request.login("Oleg", "oleg");
            boolean dodo = request.authenticate(response);
            System.out.println("Logged in a user");
            String redirect =
                    response.encodeRedirectURL(request.getRequestURI());
            response.sendRedirect(redirect);
        }

    }

}
