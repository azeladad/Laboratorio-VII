package gt.edu.umg.profesores.service;

import gt.edu.umg.profesores.exception.ProfesorNoEncontradoException;
import gt.edu.umg.profesores.model.Profesor;
import gt.edu.umg.profesores.repository.ProfesorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProfesorServiceTest {

    private ProfesorService service;

    @BeforeEach
    void preparar() {
        service = new ProfesorService(new ProfesorRepository());
    }

    private Profesor crearProfesorDePrueba() {
        Profesor profesor = new Profesor();
        profesor.setNombre("Carlos");
        profesor.setApellido("Lopez");
        profesor.setCorreo("carlos.lopez@umg.edu.gt");
        profesor.setEspecialidad("Matematica");
        profesor.setEstado("Activo");
        return profesor;
    }

    @Test
    void crearProfesor_deberiaAsignarId() {
        Profesor creado = service.crear(crearProfesorDePrueba());

        assertNotNull(creado.getId());
        assertEquals("Carlos", creado.getNombre());
    }

    @Test
    void obtenerProfesorExistente_deberiaRetornarlo() {
        Profesor creado = service.crear(crearProfesorDePrueba());

        Profesor encontrado = service.obtenerPorId(creado.getId());

        assertEquals(creado.getId(), encontrado.getId());
        assertEquals("Lopez", encontrado.getApellido());
    }

    @Test
    void obtenerProfesorInexistente_deberiaLanzarExcepcion() {
        assertThrows(ProfesorNoEncontradoException.class,
                () -> service.obtenerPorId(99L));
    }
}