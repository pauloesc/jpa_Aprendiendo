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
public class Libro {

	@Id
	@Column(name = "LIBRO_ID")
	private long id;
	
	@Column(name = "TITULO")
	private String titulo;
	
	
	/*
	 * muchos libros para un autor... por eso @ManyToOne
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTOR_ID") //indicamos que el id del OBJETO "autor" se guarde en la columna AUTOR_ID
	private Autor autor;


	public Libro(long id, String titulo, Autor autor) {
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
	}


	public Libro() {
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


	public Autor getAutor() {
		return autor;
	}


	public void setAutor(Autor autor) {
		this.autor = autor;
	}



	@Override
	public String toString() {
		return "Libro [id=" + id + ", titulo=" + titulo + ", autor=" + autor + "]";
	}
	
	
}
