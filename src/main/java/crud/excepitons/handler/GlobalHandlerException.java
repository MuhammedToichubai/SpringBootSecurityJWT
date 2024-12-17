package crud.excepitons.handler;

import crud.excepitons.BadRequestException;
import crud.excepitons.ForbiddenException;
import crud.excepitons.NotFoundException;
import crud.excepitons.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {

    //404
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse notFound(NotFoundException notFoundException) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .className(notFoundException.getClass().getName())
                .message(notFoundException.getMessage())
                .build();
    }


    //403
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse forbidden(ForbiddenException forbiddenException) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.FORBIDDEN)
                .className(forbiddenException.getClass().getName())
                .message(forbiddenException.getMessage())
                .build();
    }

    // 400 client exception
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse badRequest(BadRequestException badRequestException) {
        return ExceptionResponse.builder()
                .httpStatus(HttpStatus.FORBIDDEN)
                .className(badRequestException.getClass().getName())
                .message(badRequestException.getMessage())
                .build();
    }

}
