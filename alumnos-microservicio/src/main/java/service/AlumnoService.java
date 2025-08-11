package service;

import org.springframework.stereotype.Service;

import model.Alumno;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import repository.AlumnoRepository;

@Service
public class AlumnoService  {

	private final AlumnoRepository repository;

    public AlumnoService(AlumnoRepository repository) {
        this.repository = repository;
    }

    public Mono<Void> guardarAlumno(Alumno alumno) {
        // Validaciones 
        if (alumno.getId() == null || alumno.getId().isEmpty()) {
            return Mono.error(new IllegalArgumentException("El id no puede estar vacío"));
        }
        if (alumno.getNombre() == null || alumno.getNombre().isEmpty()) {
            return Mono.error(new IllegalArgumentException("El nombre no puede estar vacío"));
        }
        if (alumno.getApellido() == null || alumno.getApellido().isEmpty()) {
            return Mono.error(new IllegalArgumentException("El apellido no puede estar vacío"));
        }
        if (!"activo".equalsIgnoreCase(alumno.getEstado()) && !"inactivo".equalsIgnoreCase(alumno.getEstado())) {
            return Mono.error(new IllegalArgumentException("El estado debe ser 'activo' o 'inactivo'"));
        }
        if (alumno.getEdad() == null || alumno.getEdad() <= 0 || alumno.getEdad() > 150) {
            return Mono.error(new IllegalArgumentException("La edad debe ser un número válido entre 1 y 150"));
        }

        return repository.existsById(alumno.getId())
                .flatMap(existe -> {
                    if (existe) {
                        return Mono.error(new IllegalStateException("El id ya existe, no se puede grabar"));
                    } else {
                        return repository.save(alumno).then();
                    }
                });
    }

    public Flux<Alumno> obtenerAlumnosActivos() {
        return repository.findByEstado("activo");
    }

}
