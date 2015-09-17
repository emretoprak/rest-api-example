package com.restapi.authentication;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethod;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.spi.Failure;
import org.jboss.resteasy.spi.HttpRequest;
import org.jboss.resteasy.spi.interception.PreProcessInterceptor;
import org.jboss.resteasy.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.WebApplicationException;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

@Provider
@ServerInterceptor
public class AuthorizationFilter implements PreProcessInterceptor {

    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied for this resource", 401, new Headers<>());
    private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403, new Headers<>());
    private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500, new Headers<>());
    private final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Override
    public ServerResponse preProcess(HttpRequest httpRequest, ResourceMethod resourceMethod) throws Failure, WebApplicationException {

        Method method = resourceMethod.getMethod();

        if (method.isAnnotationPresent(PermitAll.class)) {
            return null;
        }
        if (method.isAnnotationPresent(DenyAll.class)) {
            return ACCESS_FORBIDDEN;
        }
        final HttpHeaders headers = httpRequest.getHttpHeaders();
        final List<String> authorization = headers.getRequestHeader(AUTHORIZATION_PROPERTY);

        if (authorization == null || authorization.isEmpty()) {
            return ACCESS_DENIED;
        }

        final String encodedUserPassword = authorization.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");

        String usernameAndPassword;
        try {
            usernameAndPassword = new String(Base64.decode(encodedUserPassword));
        } catch (IOException e) {
            logger.error(e.toString());
            return SERVER_ERROR;
        }

        final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
        final String username = tokenizer.nextToken();
        final String password = tokenizer.nextToken();

        if (method.isAnnotationPresent(RolesAllowed.class)) {
            RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
            Set<String> rolesSet = new HashSet<>(Arrays.asList(rolesAnnotation.value()));

            if (!checkUserAccess(username, password, rolesSet)) {
                return ACCESS_DENIED;
            }
        }
        return null;
    }

    private boolean checkUserAccess(String username, String password, Set<String> rolesSet) {
        if (rolesSet.contains(SecurityRoles.ADMIN)) {
            // TODO : check is admin
            logger.debug("Admin need something");
            return true;
        }else if(rolesSet.contains(SecurityRoles.USER)){
            // TODO : check is user
            logger.debug("User need something");
            return true;
        }
        return false;
    }
}
