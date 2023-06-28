package com.gestion.alumnos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.gestion.alumnos.dto.AlumnoDto;
import com.gestion.alumnos.model.Alumno;
import com.gestion.alumnos.repository.AlumnoRepository;

public class AlumnoService {

	@Autowired
	private final AlumnoRepository alumnoRepository;

	public AlumnoService(final AlumnoRepository alumnoRepository) {
		this.alumnoRepository = alumnoRepository;
	}

	public Alumno crearAlumno(Alumno alumno) {
		
		
		
		return alumnoRepository.save(alumno);
		
		
	}

	public Alumno alumnoDtoToAlumno(AlumnoDto alumnoDto) {

		Alumno result = new Alumno();

		result.setId(alumnoDto.getId());
		result.setNombre(alumnoDto.getNombre());
		result.setApellido(alumnoDto.getApellidos());
		result.setEmail(alumnoDto.getEmail());

		return result;
	}
	
	public Optional<Alumno> findById(final long id) {
		return alumnoRepository.findById(id);
		
	}

	public List<Alumno> findAll() {
		return alumnoRepository.findAll();
	}
}
