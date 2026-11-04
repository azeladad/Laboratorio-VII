package gt.edu.umg.empleados.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmpleadoNoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> manejarNoEncontrado(EmpleadoNoEncontradoException ex) {
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("status", 404);
        cuerpo.put("error", "NOT FOUND");
        cuerpo.put("mensaje", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cuerpo);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errores.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("status", 400);
        cuerpo.put("error", "BAD REQUEST");
        cuerpo.put("errores", errores);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(cuerpo);
    }
}
