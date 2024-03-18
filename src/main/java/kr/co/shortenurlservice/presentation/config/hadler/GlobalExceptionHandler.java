package kr.co.shortenurlservice.presentation.config.hadler;

import kr.co.shortenurlservice.domain.exception.LackOfShortUrlKeyException;
import kr.co.shortenurlservice.domain.exception.NotFoundShortenUrlException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundShortenUrlException.class)
    public ResponseEntity<String> handleNotFoundShortenUrlException(NotFoundShortenUrlException ex){
        return new ResponseEntity<>("단축 URL 을 찾지 못했습니다.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LackOfShortUrlKeyException.class)
    public ResponseEntity<String> handleLackOfShortUrlKeyException(LackOfShortUrlKeyException ex){
        return new ResponseEntity<>("단축 URL 자원이 부족함", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
