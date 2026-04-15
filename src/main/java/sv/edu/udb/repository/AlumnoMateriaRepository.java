package sv.edu.udb.repository;

import sv.edu.udb.model.AlumnoMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlumnoMateriaRepository extends JpaRepository<AlumnoMateria, Long> {
    List<AlumnoMateria> findByIdAlumno(Long idAlumno);
    List<AlumnoMateria> findByIdMateria(Long idMateria);
    Optional<AlumnoMateria> findByIdAlumnoAndIdMateria(Long idAlumno, Long idMateria);
    boolean existsByIdAlumnoAndIdMateria(Long idAlumno, Long idMateria);
}