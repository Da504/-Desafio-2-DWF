package sv.edu.udb.service;

import sv.edu.udb.model.Alumno;
import sv.edu.udb.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> findAll() {
        return alumnoRepository.findAll();
    }

    public Optional<Alumno> findById(Long id) {
        return alumnoRepository.findById(id);
    }

    public Alumno save(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public Alumno update(Long id, Alumno alumnoDetails) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado con id: " + id));

        alumno.setNombre(alumnoDetails.getNombre());
        alumno.setApellido(alumnoDetails.getApellido());
        return alumnoRepository.save(alumno);
    }

    public void deleteById(Long id) {
        alumnoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return alumnoRepository.existsById(id);
    }
}