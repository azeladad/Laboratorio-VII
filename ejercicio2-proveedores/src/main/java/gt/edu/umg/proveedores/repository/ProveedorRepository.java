package gt.edu.umg.proveedores.repository;

import gt.edu.umg.proveedores.model.Proveedor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProveedorRepository {

    private final List<Proveedor> proveedores = new ArrayList<>();
    private final AtomicLong contador = new AtomicLong(1);

    public Proveedor guardar(Proveedor proveedor) {
        proveedor.setId(contador.getAndIncrement());
        proveedores.add(proveedor);
        return proveedor;
    }

    public List<Proveedor> listarTodos() {
        return new ArrayList<>(proveedores);
    }

    public Optional<Proveedor> buscarPorId(Long id) {
        return proveedores.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
}