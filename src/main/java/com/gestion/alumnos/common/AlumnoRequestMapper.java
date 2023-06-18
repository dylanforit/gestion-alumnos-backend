/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.alumnos.common;


import com.gestion.alumnos.dto.AlumnoDto;
import com.gestion.alumnos.model.Alumno;


import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 *
 * @author dylanforit
 */
@Mapper(componentModel = "spring")
public interface AlumnoRequestMapper {
    
      @Mappings({
        @Mapping(source = "customer", target = "customerId")})
    Alumno AlumnoRequestToAlumno(AlumnoDto source);
                
    List<Alumno> AlumnoRequestListToAlumnoList(List<AlumnoDto> source);
    
}
