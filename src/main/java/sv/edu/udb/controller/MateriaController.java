package sv.edu.udb.controller;

import sv.edu.udb.model.Materia;
import sv.edu.udb.service.MateriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/materias")
@Tag(name = "Materias", description = "API para gestión de materias")
@CrossOrigin(origins = "*")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    @Operation(summary = "Obtener todas las materias")
    public ResponseEntity<List<Materia>> getAllMaterias() {
        return ResponseEntity.ok(materiaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener materia por ID")
    public ResponseEntity<Materia> getMateriaById(@PathVariable Long id) {
        return materiaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/profesor/{idProfesor}")
    @Operation(summary = "Obtener materias por profesor")
    public ResponseEntity<List<Materia>> getMateriasByProfesor(@PathVariable Long idProfesor) {
        return ResponseEntity.ok(materiaService.findByProfesor(idProfesor));
    }

    @PostMapping
    @Operation(summary = "Crear nueva materia")
    public ResponseEntity<?> createMateria(@RequestBody Materia materia) {
        try {
            return new ResponseEntity<>(materiaService.save(materia), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar materia existente")
    public ResponseEntity<?> updateMateria(@PathVariable Long id, @RequestBody Materia materia) {
        try {
            return ResponseEntity.ok(materiaService.update(id, materia));
        } catch (RuntimeException e) {
            if (e.getMessage().contains("no existe")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar materia")
    public ResponseEntity<Void> deleteMateria(@PathVariable Long id) {
        if (materiaService.existsById(id)) {
            materiaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}