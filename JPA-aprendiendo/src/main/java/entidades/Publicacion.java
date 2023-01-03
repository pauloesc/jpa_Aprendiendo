package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Publicacion {

	@Id
	@Column(name = "ddd")
	private Long id;

	
	@Column
	private String titulo;
	
	/*
	 * con orphanRemoval basicamente le estoy diciendo a jpa que "Comentario"
	 * es una antidad que no puede existir independientemente...
	 * si intento eliminar la asociacion entre un comentario y su publicacion el comentario sera borrado, debido a orphanRemoval.
	 */
	
	/*
	 * al borrar una publicacion del sistema cascade = CascadeType.ALL borrara todos los comentarios
	 */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publicacion", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Comentario> comentarios = new ArrayList<Comentario>();


	public Publicacion() {
	}


	public Publicacion(long id, String titulo, List<Comentario> comentarios) {
		this.id = id;
		this.titulo = titulo;
		this.comentarios = comentarios;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public List<Comentario> getComentarios() {
		return comentarios;
	}


	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}


	@Override
	public String toString() {
		return "Publicacion [id=" + id + ", titulo=" + titulo + ", comentarios=" + comentarios + "]";
	}

	public void eliminarComentario(Comentario c) {
			comentarios.remove(c);
			c.setPublicacion(null);
	}

	
}
