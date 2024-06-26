package crud.api;

import crud.dto.request.LoginRequest;
import crud.dto.request.RegisterRequest;
import crud.dto.response.LoginResponse;
import crud.dto.response.RegisterResponse;
import crud.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mukhammed Asantegin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Authentication endpoints")
public class AuthAPI {
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "User registration", description = "Registers a new user.")
    public RegisterResponse signUp(@RequestBody @Valid RegisterRequest signUpRequest) {
        return authService.signUp(signUpRequest);
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Logs in a user.")
    public LoginResponse signIn(@RequestBody @Valid LoginRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

}
