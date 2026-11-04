package gt.edu.umg.profesores.repository;

import gt.edu.umg.profesores.model.Profesor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProfesorRepository {

    private final List<Profesor> profesores = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong(1);

    public Profesor guardar(Profesor profesor) {
        profesor.setId(contador.getAndIncrement());
        profesores.add(profesor);
        return profesor;
    }

    public List<Profesor> listarTodos() {
        return new ArrayList<>(profesores);
    }

    public Optional<Profesor> buscarPorId(Long id) {
        return profesores.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
}