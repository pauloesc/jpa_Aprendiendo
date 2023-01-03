package entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Comentario {

	@Id
	@Column
	private Long id;
	
	@Column
	private String mensaje;
	
	/*
	 * si pongo cascade = CascadeType.ALL, al borrar un comentario se borra toda la publicacion
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ddd")
	private Publicacion publicacion;

	public Comentario() {
	}

	public Comentario(long id, String mensaje, Publicacion publicacion) {
		this.id = id;
		this.mensaje = mensaje;
		this.publicacion = publicacion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", mensaje=" + mensaje + "]";
	}
	
	
	
}
