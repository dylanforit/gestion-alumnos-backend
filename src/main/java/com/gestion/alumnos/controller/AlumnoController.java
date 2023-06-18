
package com.gestion.alumnos.controller;

/**
 *
 * @author Dylanforit
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.alumnos.exeptions.ResourceNotFoundException;
import com.gestion.alumnos.model.Alumno;
import com.gestion.alumnos.repository.AlumnoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@Api(tags = "Alumnos API")
@RestController
@RequestMapping("/api/v1/")
@CrossOrigin
public class AlumnoController {

	@Autowired
	private AlumnoRepository repositorio;

	//este metodo sirve para listar todos los alumnos
	@GetMapping("/alumnos")
	public List<Alumno> listarAlumnos() {
		return repositorio.findAll();
	}
	

	//este metodo sirve para guardar el alumno
	@PostMapping("/alumnos")
	public Alumno guardarAlumno(@RequestBody Alumno alumno) {
		return repositorio.save(alumno);
	}
    
	//este metodo sirve para buscar un alumno
    @ApiOperation(value = "Return all transaction bundled into Response", notes = "Return 204 if no data found")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "There are not transactions"),
        @ApiResponse(code = 500, message = "Internal error")})
	@GetMapping("/alumnos/{id}")
	public ResponseEntity<Alumno> obtenerAlumnoPorId(@PathVariable Long id){
			Alumno alumno = repositorio.findById(id)
					            .orElseThrow(() -> new ResourceNotFoundException("No existe el alumno con el ID : " + id));
			return ResponseEntity.ok(alumno);
	}
	
	//este metodo sirve para actualizar alumno
	@PutMapping("/alumnos/{id}")
	public ResponseEntity<Alumno> actualizarAlumno(@PathVariable Long id,@RequestBody Alumno detallesAlumno){
		Alumno alumno = repositorio.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el alumno con el ID : " + id));
		
		alumno.setNombre(detallesAlumno.getNombre());
		alumno.setApellido(detallesAlumno.getApellido());
		alumno.setEmail(detallesAlumno.getEmail());
		
		Alumno alumnoActualizado = repositorio.save(alumno);
		return ResponseEntity.ok(alumnoActualizado);
    }
	
	//este metodo sirve para eliminar un alumno
	@DeleteMapping("/alumnos/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarAlumno(@PathVariable Long id){
		Alumno alumno = repositorio.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe el alumno con el ID : " + id));
		
		repositorio.delete(alumno);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }    
}
