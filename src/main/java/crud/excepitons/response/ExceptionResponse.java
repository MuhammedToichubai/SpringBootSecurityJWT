package crud.excepitons.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ExceptionResponse(
        HttpStatus httpStatus,
        String className,
        String message
) {}
