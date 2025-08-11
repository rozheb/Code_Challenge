package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Alumno;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.AlumnoService;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
	
	private final AlumnoService service;

    public AlumnoController(AlumnoService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<ResponseEntity<Object>> grabarAlumno(@RequestBody Mono<Alumno> alumnoMono) {
        return alumnoMono
                .flatMap(service::guardarAlumno)
                .thenReturn(ResponseEntity.noContent().build())
                .onErrorResume(e -> Mono.just(ResponseEntity.badRequest().build()));
    }

    @GetMapping("/activos")
    public Flux<Alumno> obtenerAlumnosActivos() {
        return service.obtenerAlumnosActivos();
    }

}
