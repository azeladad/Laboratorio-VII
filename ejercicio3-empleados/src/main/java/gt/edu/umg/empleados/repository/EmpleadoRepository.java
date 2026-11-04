package gt.edu.umg.empleados.repository;

import gt.edu.umg.empleados.model.Empleado;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmpleadoRepository {

    private final List<Empleado> empleados = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong(1);

    public Empleado guardar(Empleado empleado) {
        empleado.setId(contador.getAndIncrement());
        empleados.add(empleado);
        return empleado;
    }

    public List<Empleado> listarTodos() {
        return new ArrayList<>(empleados);
    }

    public Optional<Empleado> buscarPorId(Long id) {
        return empleados.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }
}
