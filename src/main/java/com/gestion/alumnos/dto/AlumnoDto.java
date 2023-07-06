/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gestion.alumnos.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author dylanforit
 */
@ApiModel(description = "This model represent a Invoice data that user receive when make a request method")
public class AlumnoDto {

	@ApiModelProperty(name = "id", required = true, example = "1", value = "Unique Id of alumno taht represent the owner of invoice")
	private long id;
	@ApiModelProperty(name = "nombre", required = true, example = "2548975", value = "Bussines number that identified a invoice", allowEmptyValue = false)
	private String nombre;
	@ApiModelProperty(name = "apellidos", required = false, example = "Professional services")
	private String apellidos;
	@ApiModelProperty(name = "email", required = true, example = "3659.23")
	private String email;



	public AlumnoDto(long id, String nombre, String apellidos, String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
	}

	public AlumnoDto() {
		super();

		// TODO Auto-generated constructor stub 
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
