package graph.railgo.application.utils.security;

import graph.railgo.application.account.service.UserUseCase;
import graph.railgo.domain.utils.exception.BusinessException;
import graph.railgo.infrastructure.security.UserDetail;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    private final UserUseCase userUseCase;

    @Autowired
    public AuthenticationFilter(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String accessToken = authHeader.substring(7);
            try {
                UserDetail userDetail = userUseCase.authenticate(accessToken);
            if (userDetail != null) {
                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(userDetail,
                                null,
                                userDetail.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            } catch (BusinessException e) {
                throw new ServletException(e);
            }

        }

        filterChain.doFilter(request, response);
    }
}
