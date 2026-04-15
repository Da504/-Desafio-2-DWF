package sv.edu.udb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alumno_materia",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_alumno", "id_materia"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoMateria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_alumno", nullable = false)
    private Long idAlumno;

    @Column(name = "id_materia", nullable = false)
    private Long idMateria;

    @ManyToOne
    @JoinColumn(name = "id_alumno", insertable = false, updatable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "id_materia", insertable = false, updatable = false)
    private Materia materia;
}