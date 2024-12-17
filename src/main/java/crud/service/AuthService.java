package crud.service;

import crud.dto.request.LoginRequest;
import crud.dto.request.RegisterRequest;
import crud.dto.response.LoginResponse;
import crud.dto.response.RegisterResponse;
import crud.dto.response.UserResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

/**
 * @author Mukhammed Asantegin
 */
public interface AuthService {

    RegisterResponse signUp(RegisterRequest signUpRequest);

    LoginResponse signIn(LoginRequest signInRequest);

    ResponseEntity<UserResponse> findById(Long userId);
}
