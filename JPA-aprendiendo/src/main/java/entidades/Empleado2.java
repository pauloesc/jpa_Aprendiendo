package entidades;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLEADO")
public class Empleado2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "COD_EMPLEADO")
	private long codigo;
	
	@Column(name = "APELLIDO")
	private String apellido;
	
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "FECHA_NACIMIENTO")
	private LocalDate fechaNacimiento;
	
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn( name = "ID_DIRECCION" ) //esto indica que vamos a crear una ccolumna ID_DIRECCION que va a ser la forengKey de la entidad Direccion
	private Direccion direccion;
	
	
	public Empleado2() {}





	public Empleado2(long codigo, String apellido, String nombre, LocalDate fechaNacimiento, Direccion direccion) {
		this.codigo = codigo;
		this.apellido = apellido;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
	}





	public long getCodigo() {
		return codigo;
	}


	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public Direccion getDireccion() {
		return direccion;
	}


	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}


	@Override
	public String toString() {
		return "Empleado2 [codigo=" + codigo + ", apellido=" + apellido + ", nombre=" + nombre + ", fechaNacimiento="
				+ fechaNacimiento + ", direccion=" + direccion + "]";
	}
	
	
	
}


