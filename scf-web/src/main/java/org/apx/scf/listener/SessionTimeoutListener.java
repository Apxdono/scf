package org.apx.scf.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.IllegalFormatCodePointException;

/**
 * Created by oleg on 19.06.2015.
 */
public class SessionTimeoutListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setMaxInactiveInterval(1*60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        try {
            httpSessionEvent.getSession().invalidate();
        } catch (IllegalStateException o_O){
            System.err.println("Session already invalidated");
        }

    }
}
