package com.example.authenticationservice.config;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConcurrentSessionStrategy extends ConcurrentSessionControlAuthenticationStrategy {
    private static final String FORCE_PARAMETER_NAME = "force";

    private final SessionsManager sessionsManager;

    public ConcurrentSessionStrategy(SessionRegistry sessionRegistry, SessionsManager sessionsManager) {
        super(sessionRegistry);
        super.setExceptionIfMaximumExceeded(true);
        super.setMaximumSessions(1);
        this.sessionsManager = sessionsManager;
    }

    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request,
                                 HttpServletResponse response)
            throws SessionAuthenticationException {
        try {
            super.onAuthentication(authentication, request, response);
        } catch (SessionAuthenticationException e) {
            log.debug("onAuthentication#SessionAuthenticationException");
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String force = request.getParameter(FORCE_PARAMETER_NAME);

            if (StringUtils.isBlank(force)) {
                log.debug("onAuthentication#Multiple choices when login for user: {}", userDetails.getUsername());
                throw e;
            }

            if (!Boolean.parseBoolean(force)) {
                log.debug("onAuthentication#Invalidate current session for user: {}", userDetails.getUsername());
                throw e;
            }

            log.debug("onAuthentication#Invalidate old session for user: {}", userDetails.getUsername());
            sessionsManager.deleteSessionExceptCurrentByUser(userDetails.getUsername());
        }
    }
}