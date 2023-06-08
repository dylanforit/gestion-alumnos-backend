/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.gestion.alumnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gestion.alumnos.model.Alumno;
/**
 *
 * @author Dylanforit
 */
public interface AlumnoRepository extends JpaRepository<Alumno, Long>{
    
}
