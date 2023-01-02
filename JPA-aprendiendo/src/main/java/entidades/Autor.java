package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Autor {

	@Id
	@Column(name = "AUTOR_ID")
	private long id;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "NACIONALIDAD")
	private String nacionalidad;
	

	/*
	 * un autor para multiples libros... por eso @OneToMany.
	 * para el caso OneToMany el fetch es por defecto es LAZY.
	 */
	 
	 /* importante recordar que en la tabla autor no quiero mas informacion que autor_id, nombre y nacionalidad,
	  * por eso utilizo OneToMany(mappedBy = "autor")
	 */
	 
	 /*   
	 * @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
	 * basicamente dice: agrupar los libros donde el autor (atributo de clase libro) se igual a this,
	 * en otras palabras; indica cual es el atributo de la clase libro que define que autor esta asociado al mismo.
	 */
	
	@OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
	private List<Libro> libros = new ArrayList<Libro>();


	public Autor() {
	}


	public Autor(long id, String nombre, String nacionalidad) {
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
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


	public String getNacionalidad() {
		return nacionalidad;
	}


	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


	public List<Libro> getLibros() {
		return libros;
	}


	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	public void agregarLibro(Libro l) {
		libros.add(l);
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + "]";
	}
	
}
