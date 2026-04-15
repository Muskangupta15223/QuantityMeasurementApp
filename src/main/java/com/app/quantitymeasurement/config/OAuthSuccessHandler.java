package com.app.quantitymeasurement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.app.quantitymeasurement.model.User;
import com.app.quantitymeasurement.service.UserServiceImpl;

import java.io.IOException; 
import jakarta.servlet.http.*;
@Component
public class OAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    @Lazy
    private UserServiceImpl userService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
                                        throws IOException {

        OAuth2User oAuthUser = (OAuth2User) authentication.getPrincipal();

        String googleId = oAuthUser.getAttribute("sub");
        String email = oAuthUser.getAttribute("email");
        String name = oAuthUser.getAttribute("name");
        String givenName = oAuthUser.getAttribute("given_name");
        String familyName = oAuthUser.getAttribute("family_name");
        String picture = oAuthUser.getAttribute("picture");
        String locale = oAuthUser.getAttribute("locale");
        Boolean emailVerified = oAuthUser.getAttribute("email_verified");

        User dbUser = userService.saveOrUpdateOAuthUser(
                googleId,
                email,
                name,
                givenName,
                familyName,
                picture,
                locale,
                emailVerified != null && emailVerified
        );

        String token = jwtUtil.generateToken(dbUser.getEmail());

        String redirectUrl = "http://localhost:3000/oauth2/redirect?token=" + token;
        response.sendRedirect(redirectUrl);  }
}
