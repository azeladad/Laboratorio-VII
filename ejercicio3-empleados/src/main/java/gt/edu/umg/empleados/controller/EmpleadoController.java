package gt.edu.umg.empleados.controller;

import gt.edu.umg.empleados.model.Empleado;
import gt.edu.umg.empleados.service.EmpleadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService service;

    public EmpleadoController(EmpleadoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Empleado> crear(@Valid @RequestBody Empleado empleado) {
        Empleado creado = service.crear(empleado);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> listar(
            @RequestParam(name = "especialidad", required = false) String especialidad) {
        return ResponseEntity.ok(service.listar(especialidad));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }
}
