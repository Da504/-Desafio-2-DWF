package sv.edu.udb.controller;

import sv.edu.udb.model.Alumno;
import sv.edu.udb.service.AlumnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
@Tag(name = "Alumnos", description = "API para gestión de alumnos")
@CrossOrigin(origins = "*")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    @Operation(summary = "Obtener todos los alumnos")
    public ResponseEntity<List<Alumno>> getAllAlumnos() {
        return ResponseEntity.ok(alumnoService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener alumno por ID")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable Long id) {
        return alumnoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear nuevo alumno")
    public ResponseEntity<Alumno> createAlumno(@RequestBody Alumno alumno) {
        return new ResponseEntity<>(alumnoService.save(alumno), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar alumno existente")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable Long id, @RequestBody Alumno alumno) {
        try {
            return ResponseEntity.ok(alumnoService.update(id, alumno));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar alumno")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
        if (alumnoService.existsById(id)) {
            alumnoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}