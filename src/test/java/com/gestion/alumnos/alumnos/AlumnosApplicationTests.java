package com.gestion.alumnos.alumnos;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion.alumnos.dto.AlumnoDto;
import com.gestion.alumnos.model.Alumno;
import com.gestion.alumnos.repository.AlumnoRepository;
import com.gestion.alumnos.service.AlumnoService;


@ExtendWith(MockitoExtension.class)
class AlumnosApplicationTests {


	@Autowired
	@Mock 
	private AlumnoRepository alumnoRepository;

	@Autowired
	@InjectMocks
	private AlumnoService alumnoService;

	@DisplayName("Creación de alumno")
	
	@Test
	public void crearAlumno() {
		System.out.println("hola");
		Alumno alumno = new Alumno();
		Alumno alumnoMock = new Alumno(1L,"Juan","Pruebas", "mail");
		AlumnoDto alumnoDto = new AlumnoDto(1L,"Juan","Pruebas", "mail");
		Mockito.when(alumnoRepository.save(alumnoMock)).thenReturn(alumnoMock);

		final Alumno resultado = alumnoService.crearAlumno(alumnoMock);
		
		Assertions.assertEquals(alumnoMock.getId(),resultado.getId());
		Assertions.assertEquals(alumnoMock.getApellido(),resultado.getApellido());
		Assertions.assertEquals(alumnoMock.getEmail(),resultado.getEmail());
		Assertions.assertEquals(alumnoMock.getNombre(),resultado.getNombre());
		Mockito.verify(alumnoRepository).save(alumnoMock);
		
	}
	
	@Test
	@DisplayName("Obtención de alumno por id")
	public void getAlumnoById() {
		Optional<Alumno> alumnoMock = Optional.of(new Alumno(1L,"Juan","Pruebas", "mail"));
		Alumno alumnoMock2 = new Alumno(1L,"Juan","Pruebas", "mail");

		
		Mockito.when(alumnoRepository.findById(1L)).thenReturn(alumnoMock);
		
		final Optional<Alumno> result = alumnoService.findById(1L);
		
		Assertions.assertEquals(alumnoMock.get(),result.get());

		Mockito.verify(alumnoRepository).findById(1L);
		
	}
	
	
	@DisplayName("Obtenemos todos los alumnos")

	@Test
	public void getAlumnos() {
		List<Alumno> alumnosMock = new ArrayList<Alumno>();
		Alumno alumnoMock1 = new Alumno(1L,"Juan","Pruebas", "mail");
		Alumno alumnoMock2 = new Alumno(1L,"Pedro","Pruebas", "mail");
		
		alumnosMock.add(alumnoMock2);
		alumnosMock.add(alumnoMock1);
		
		
		Mockito.when(alumnoRepository.findAll()).thenReturn(alumnosMock);
		
		final List<Alumno> result = alumnoService.findAll();
		
		Assertions.assertEquals(alumnosMock.toString(),result.toString());

		Mockito.verify(alumnoRepository).findAll();
		
	}
	
	@DisplayName("Creación de alumno (error fozado)")

	@Test
	public void crearAlumnoError() {
		System.out.println("hola");
		Alumno alumno = new Alumno();
		Alumno alumnoMock = new Alumno(1L,"Juan","Pruebas", "mail");
		AlumnoDto alumnoDto = new AlumnoDto(1L,"Juan","Pruebas", "mail");
		Mockito.when(alumnoRepository.save(alumnoMock)).thenReturn(alumnoMock);

		final Alumno resultado = alumnoService.crearAlumno(alumnoMock);
		Assertions.assertEquals(alumnoMock.getId(),resultado.getId());
		Assertions.assertEquals(alumnoMock.getApellido(),resultado.getApellido());
		Assertions.assertEquals(alumnoMock.getEmail(),resultado.getEmail());
		Assertions.assertEquals(alumnoMock.getNombre(),resultado.getNombre());
		Mockito.verify(alumnoRepository).save(alumnoMock);
		
	}

}
