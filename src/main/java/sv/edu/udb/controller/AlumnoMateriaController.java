package sv.edu.udb.controller;

import sv.edu.udb.model.AlumnoMateria;
import sv.edu.udb.service.AlumnoMateriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alumno-materia")
@Tag(name = "Alumno-Materia", description = "API para inscripción de alumnos en materias")
@CrossOrigin(origins = "*")
public class AlumnoMateriaController {

    @Autowired
    private AlumnoMateriaService alumnoMateriaService;

    @GetMapping
    @Operation(summary = "Obtener todas las inscripciones")
    public ResponseEntity<List<AlumnoMateria>> getAllInscripciones() {
        return ResponseEntity.ok(alumnoMateriaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener inscripción por ID")
    public ResponseEntity<AlumnoMateria> getInscripcionById(@PathVariable Long id) {
        return alumnoMateriaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/alumno/{idAlumno}")
    @Operation(summary = "Obtener materias de un alumno")
    public ResponseEntity<List<AlumnoMateria>> getMateriasByAlumno(@PathVariable Long idAlumno) {
        return ResponseEntity.ok(alumnoMateriaService.findByAlumno(idAlumno));
    }

    @GetMapping("/materia/{idMateria}")
    @Operation(summary = "Obtener alumnos de una materia")
    public ResponseEntity<List<AlumnoMateria>> getAlumnosByMateria(@PathVariable Long idMateria) {
        return ResponseEntity.ok(alumnoMateriaService.findByMateria(idMateria));
    }

    @PostMapping
    @Operation(summary = "Inscribir alumno en materia")
    public ResponseEntity<?> createInscripcion(@RequestBody AlumnoMateria alumnoMateria) {
        try {
            return new ResponseEntity<>(alumnoMateriaService.save(alumnoMateria), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar inscripción")
    public ResponseEntity<Void> deleteInscripcion(@PathVariable Long id) {
        if (alumnoMateriaService.findById(id).isPresent()) {
            alumnoMateriaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}