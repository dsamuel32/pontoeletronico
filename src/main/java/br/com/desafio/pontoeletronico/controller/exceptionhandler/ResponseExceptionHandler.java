package br.com.desafio.pontoeletronico.controller.exceptionhandler;

import br.com.desafio.pontoeletronico.dominio.dto.DetalheErroDTO;
import br.com.desafio.pontoeletronico.negocio.exceptions.ValidacaoNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger LOGGER = Logger.getLogger("ResponseExceptionHandler");

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DetalheErroDTO> handleDefaultException(Exception ex, WebRequest request) {
        DetalheErroDTO detalheErro = new DetalheErroDTO(LocalDateTime.now(), ex.getMessage());
        LOGGER.severe(ex.getMessage());
        return new ResponseEntity(detalheErro, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidacaoNegocioException.class)
    public ResponseEntity<DetalheErroDTO> handleValidacaoNegocioException(Exception ex, WebRequest request) {
        DetalheErroDTO detalheErro = new DetalheErroDTO(LocalDateTime.now(), ex.getMessage());
        LOGGER.severe(ex.getMessage());
        return new ResponseEntity(detalheErro, HttpStatus.BAD_REQUEST);
    }

}
