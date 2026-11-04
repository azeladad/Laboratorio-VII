package gt.edu.umg.profesores.service;

import gt.edu.umg.profesores.exception.ProfesorNoEncontradoException;
import gt.edu.umg.profesores.model.Profesor;
import gt.edu.umg.profesores.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorRepository repository;

    public ProfesorService(ProfesorRepository repository) {
        this.repository = repository;
    }

    public Profesor crear(Profesor profesor) {
        return repository.guardar(profesor);
    }

    public List<Profesor> listar(String especialidad) {
        List<Profesor> todos = repository.listarTodos();
        if (especialidad == null || especialidad.isBlank()) {
            return todos;
        }
        return todos.stream()
                .filter(p -> p.getEspecialidad() != null
                        && p.getEspecialidad().equalsIgnoreCase(especialidad))
                .toList();
    }

    public Profesor obtenerPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new ProfesorNoEncontradoException(id));
    }
}