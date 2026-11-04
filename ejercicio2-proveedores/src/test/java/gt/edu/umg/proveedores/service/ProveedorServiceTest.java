package gt.edu.umg.proveedores.service;

import gt.edu.umg.proveedores.exception.ProveedorNoEncontradoException;
import gt.edu.umg.proveedores.model.Proveedor;
import gt.edu.umg.proveedores.repository.ProveedorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProveedorServiceTest {

    private ProveedorService service;

    @BeforeEach
    void preparar() {
        service = new ProveedorService(new ProveedorRepository());
    }

    private Proveedor crearProveedorDePrueba() {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre("Carlos");
        proveedor.setApellido("Lopez");
        proveedor.setCorreo("carlos.lopez@proveedor.com");
        proveedor.setCategoria("Tecnologia");
        proveedor.setEstado("Activo");
        return proveedor;
    }

    @Test
    void crearProveedor_deberiaAsignarId() {
        Proveedor creado = service.crear(crearProveedorDePrueba());

        assertNotNull(creado.getId());
        assertEquals("Carlos", creado.getNombre());
    }

    @Test
    void obtenerProveedorExistente_deberiaRetornarlo() {
        Proveedor creado = service.crear(crearProveedorDePrueba());

        Proveedor encontrado = service.obtenerPorId(creado.getId());

        assertEquals(creado.getId(), encontrado.getId());
        assertEquals("Lopez", encontrado.getApellido());
    }

    @Test
    void obtenerProveedorInexistente_deberiaLanzarExcepcion() {
        assertThrows(ProveedorNoEncontradoException.class,
                () -> service.obtenerPorId(99L));
    }
}