package org.apx.scf.config.extra;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Oleg on 02.11.2015.
 */
public class LFEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private static final String X_REQUESTED_WITH = "X-Requested-With";

    public LFEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session == null || request.getUserPrincipal() == null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            super.commence(request, response, exception);
        }
    }

}
