package funcionariosCiss.funcionariosrestapi.ApplicationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class applicationException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<String> emailException(Exception e) {

        return new ResponseEntity<>("Ooops! Este email esta invalido", HttpStatus.BAD_GATEWAY);

    }

}
