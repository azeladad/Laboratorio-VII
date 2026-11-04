package gt.edu.umg.empleados.service;

import gt.edu.umg.empleados.exception.EmpleadoNoEncontradoException;
import gt.edu.umg.empleados.model.Empleado;
import gt.edu.umg.empleados.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    private final EmpleadoRepository repository;

    public EmpleadoService(EmpleadoRepository repository) {
        this.repository = repository;
    }

    public Empleado crear(Empleado empleado) {
        return repository.guardar(empleado);
    }

    public List<Empleado> listar(String especialidad) {
        List<Empleado> todos = repository.listarTodos();
        if (especialidad == null || especialidad.isBlank()) {
            return todos;
        }
        return todos.stream()
                .filter(e -> e.getEspecialidad() != null
                        && e.getEspecialidad().equalsIgnoreCase(especialidad))
                .toList();
    }

    public Empleado obtenerPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new EmpleadoNoEncontradoException(id));
    }
}
