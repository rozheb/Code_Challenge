package model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("alumnos")
public class Alumno {
	@Id
	private String id;
    private String nombre;
    private String apellido;
    private String estado; //activo o inactivo
    private Integer edad;
    

}
