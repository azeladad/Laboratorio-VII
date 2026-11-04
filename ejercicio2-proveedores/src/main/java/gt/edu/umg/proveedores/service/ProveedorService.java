package gt.edu.umg.proveedores.service;

import gt.edu.umg.proveedores.exception.ProveedorNoEncontradoException;
import gt.edu.umg.proveedores.model.Proveedor;
import gt.edu.umg.proveedores.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepository repository;

    public ProveedorService(ProveedorRepository repository) {
        this.repository = repository;
    }

    public Proveedor crear(Proveedor proveedor) {
        return repository.guardar(proveedor);
    }

    public List<Proveedor> listar(String categoria) {
        List<Proveedor> todos = repository.listarTodos();
        if (categoria == null || categoria.isBlank()) {
            return todos;
        }
        return todos.stream()
                .filter(p -> p.getCategoria() != null
                        && p.getCategoria().equalsIgnoreCase(categoria))
                .toList();
    }

    public Proveedor obtenerPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new ProveedorNoEncontradoException(id));
    }
}