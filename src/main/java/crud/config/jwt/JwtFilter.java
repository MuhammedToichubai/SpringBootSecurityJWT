package crud.config.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import crud.models.User;
import crud.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Mukhammed Asantegin
 */

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        String headerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        String bearer = "Bearer ";
        if (headerToken != null && headerToken.startsWith(bearer)) {
            String token = headerToken.substring(bearer.length());

            try {
                String email = jwtService.verifyToken(token);
                User user = userRepository.findByEmail(email);

                SecurityContextHolder.getContext()
                        .setAuthentication(
                                new UsernamePasswordAuthenticationToken(
                                        user.getEmail(),
                                        null,
                                        user.getAuthorities()
                                )
                        );

            } catch (JWTVerificationException e) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN,
                        "Invalid JWT Token");
            }
        }
        filterChain.doFilter(request, response);
    }
}
