package sv.edu.udb.service;

import sv.edu.udb.model.Profesor;
import sv.edu.udb.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Profesor> findAll() {
        return profesorRepository.findAll();
    }

    public Optional<Profesor> findById(Long id) {
        return profesorRepository.findById(id);
    }

    public Profesor save(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public Profesor update(Long id, Profesor profesorDetails) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado con id: " + id));

        profesor.setNombre(profesorDetails.getNombre());
        return profesorRepository.save(profesor);
    }

    public void deleteById(Long id) {
        profesorRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return profesorRepository.existsById(id);
    }
}