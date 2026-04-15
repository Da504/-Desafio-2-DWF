package sv.edu.udb.service;

import sv.edu.udb.model.AlumnoMateria;
import sv.edu.udb.repository.AlumnoMateriaRepository;
import sv.edu.udb.repository.AlumnoRepository;
import sv.edu.udb.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoMateriaService {

    @Autowired
    private AlumnoMateriaRepository alumnoMateriaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    public List<AlumnoMateria> findAll() {
        return alumnoMateriaRepository.findAll();
    }

    public Optional<AlumnoMateria> findById(Long id) {
        return alumnoMateriaRepository.findById(id);
    }

    public AlumnoMateria save(AlumnoMateria alumnoMateria) {
        // Validar que el alumno existe
        if (!alumnoRepository.existsById(alumnoMateria.getIdAlumno())) {
            throw new RuntimeException("El alumno con id " + alumnoMateria.getIdAlumno() + " no existe");
        }

        // Validar que la materia existe
        if (!materiaRepository.existsById(alumnoMateria.getIdMateria())) {
            throw new RuntimeException("La materia con id " + alumnoMateria.getIdMateria() + " no existe");
        }

        // Validar que no exista la misma asignación
        if (alumnoMateriaRepository.existsByIdAlumnoAndIdMateria(
                alumnoMateria.getIdAlumno(), alumnoMateria.getIdMateria())) {
            throw new RuntimeException("El alumno ya está inscrito en esta materia");
        }

        return alumnoMateriaRepository.save(alumnoMateria);
    }

    public void deleteById(Long id) {
        alumnoMateriaRepository.deleteById(id);
    }

    public List<AlumnoMateria> findByAlumno(Long idAlumno) {
        return alumnoMateriaRepository.findByIdAlumno(idAlumno);
    }

    public List<AlumnoMateria> findByMateria(Long idMateria) {
        return alumnoMateriaRepository.findByIdMateria(idMateria);
    }
}