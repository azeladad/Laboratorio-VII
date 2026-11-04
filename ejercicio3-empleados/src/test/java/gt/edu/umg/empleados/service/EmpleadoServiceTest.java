package gt.edu.umg.empleados.service;

import gt.edu.umg.empleados.exception.EmpleadoNoEncontradoException;
import gt.edu.umg.empleados.model.Empleado;
import gt.edu.umg.empleados.repository.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmpleadoServiceTest {

    private EmpleadoService service;

    @BeforeEach
    void preparar() {
        service = new EmpleadoService(new EmpleadoRepository());
    }

    private Empleado crearEmpleadoDePrueba() {
        Empleado empleado = new Empleado();
        empleado.setNombre("Carlos");
        empleado.setApellido("Lopez");
        empleado.setCorreo("carlos.lopez@empresa.com");
        empleado.setEspecialidad("Ventas");
        empleado.setEstado("Activo");
        return empleado;
    }

    @Test
    void crearEmpleado_deberiaAsignarId() {
        Empleado creado = service.crear(crearEmpleadoDePrueba());

        assertNotNull(creado.getId());
        assertEquals("Carlos", creado.getNombre());
    }

    @Test
    void obtenerEmpleadoExistente_deberiaRetornarlo() {
        Empleado creado = service.crear(crearEmpleadoDePrueba());

        Empleado encontrado = service.obtenerPorId(creado.getId());

        assertEquals(creado.getId(), encontrado.getId());
        assertEquals("Lopez", encontrado.getApellido());
    }

    @Test
    void obtenerEmpleadoInexistente_deberiaLanzarExcepcion() {
        assertThrows(EmpleadoNoEncontradoException.class,
                () -> service.obtenerPorId(99L));
    }
}
