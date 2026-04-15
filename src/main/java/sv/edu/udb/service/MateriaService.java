package sv.edu.udb.service;

import sv.edu.udb.model.Materia;
import sv.edu.udb.repository.MateriaRepository;
import sv.edu.udb.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Materia> findAll() {
        return materiaRepository.findAll();
    }

    public Optional<Materia> findById(Long id) {
        return materiaRepository.findById(id);
    }

    public Materia save(Materia materia) {
        // Validar que el profesor existe
        if (!profesorRepository.existsById(materia.getIdProfesor())) {
            throw new RuntimeException("El profesor con id " + materia.getIdProfesor() + " no existe");
        }
        return materiaRepository.save(materia);
    }

    public Materia update(Long id, Materia materiaDetails) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Materia no encontrada con id: " + id));

        // Validar que el profesor existe
        if (!profesorRepository.existsById(materiaDetails.getIdProfesor())) {
            throw new RuntimeException("El profesor con id " + materiaDetails.getIdProfesor() + " no existe");
        }

        materia.setNombre(materiaDetails.getNombre());
        materia.setIdProfesor(materiaDetails.getIdProfesor());
        return materiaRepository.save(materia);
    }

    public void deleteById(Long id) {
        materiaRepository.deleteById(id);
    }

    public List<Materia> findByProfesor(Long idProfesor) {
        return materiaRepository.findByIdProfesor(idProfesor);
    }

    public boolean existsById(Long id) {
        return materiaRepository.existsById(id);
    }
}