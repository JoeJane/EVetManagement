package com.vet.manage.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * Handler class for all the success login
 * @author Jane Aarthy Joseph
 * **/
@Configuration
public class CustomLoginSucessHandler extends SimpleUrlAuthenticationSuccessHandler {

    /**
     * Login handler
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param authentication Authentication
     * @throws IOException IO Exception
     */
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if(response.isCommitted()) return;
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    /**
     * Determine target url
     * @param authentication Authentication entity
     * @return target url
     */
    protected String determineTargetUrl(Authentication authentication){
        String url = "/login?error=true";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for(GrantedAuthority a : authorities){
            roles.add(a.getAuthority());
        }

        if(roles.contains("ADMIN")){
            url = "/admin/home";
        }else if(roles.contains("VETERINARIAN")) {
            url = "/veterinarian/home";
        }else if(roles.contains("LAB_ASSISTANT")){
            url = "/labassistant/home";
        }else if(roles.contains("RECEPTIONIST")) {
            url = "/receptionist/home";
        } else {
            url = "/user/home";
        }

        return url;
    }
}