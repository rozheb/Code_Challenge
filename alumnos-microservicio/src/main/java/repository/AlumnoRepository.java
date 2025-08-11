package repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import model.Alumno;
import reactor.core.publisher.Flux;

public interface AlumnoRepository extends ReactiveCrudRepository<Alumno, String> {
    Flux<Alumno> findByEstado(String estado);
}