package com.example.biliBuddy.auth.config;

import com.example.biliBuddy.common.util.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtService jwtService;

    //Handle Google and Facebook Login.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
        throws IOException, ServletException {
        String email = authentication.getName();
        String jwt = jwtService.generateToken(email, null);

        // You can also redirect to a frontend URL and pass the token via query param
        response.setContentType("application/json");
        response.getWriter().write("{\"token\":\"" + jwt + "\"}");
        response.getWriter().flush();
    }
}
