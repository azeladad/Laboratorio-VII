package gt.edu.umg.profesores.exception;

public class ProfesorNoEncontradoException extends RuntimeException {

    public ProfesorNoEncontradoException(Long id) {
        super("No se encontró el profesor con id " + id);
    }
}