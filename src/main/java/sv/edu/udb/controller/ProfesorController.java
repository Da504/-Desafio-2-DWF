package sv.edu.udb.controller;

import sv.edu.udb.model.Profesor;
import sv.edu.udb.service.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/profesores")
@Tag(name = "Profesores", description = "API para gestión de profesores")
@CrossOrigin(origins = "*")
public class ProfesorController {

    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    @Operation(summary = "Obtener todos los profesores")
    public ResponseEntity<List<Profesor>> getAllProfesores() {
        return ResponseEntity.ok(profesorService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener profesor por ID")
    public ResponseEntity<Profesor> getProfesorById(@PathVariable Long id) {
        return profesorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear nuevo profesor")
    public ResponseEntity<Profesor> createProfesor(@RequestBody Profesor profesor) {
        return new ResponseEntity<>(profesorService.save(profesor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar profesor existente")
    public ResponseEntity<Profesor> updateProfesor(@PathVariable Long id, @RequestBody Profesor profesor) {
        try {
            return ResponseEntity.ok(profesorService.update(id, profesor));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar profesor")
    public ResponseEntity<Void> deleteProfesor(@PathVariable Long id) {
        if (profesorService.existsById(id)) {
            profesorService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}