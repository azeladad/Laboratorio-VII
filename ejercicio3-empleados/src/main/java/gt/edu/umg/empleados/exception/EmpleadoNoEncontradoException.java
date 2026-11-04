package gt.edu.umg.empleados.exception;

public class EmpleadoNoEncontradoException extends RuntimeException {

    public EmpleadoNoEncontradoException(Long id) {
        super("No se encontró el empleado con id " + id);
    }
}
