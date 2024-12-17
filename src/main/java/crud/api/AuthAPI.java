package crud.api;

import crud.dto.request.LoginRequest;
import crud.dto.request.RegisterRequest;
import crud.dto.response.LoginResponse;
import crud.dto.response.RegisterResponse;
import crud.dto.response.UserResponse;
import crud.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public RegisterResponse signUp(@RequestBody @Valid RegisterRequest signUpRequest) throws AccessDeniedException {
        return authService.signUp(signUpRequest);
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Logs in a user.")
    public LoginResponse signIn(@RequestBody @Valid LoginRequest signInRequest) {
        return authService.signIn(signInRequest);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findById(@PathVariable("userId") Long userId) {
        return authService.findById(userId);
    }

}
