package programa;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import entidades.Autor;
import entidades.Libro;

public class prueba5 {

	public void hacerCosas() {
		
		EntityManagerFactory emf = principal.crearEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		
		
		/*
		 * lo siguiente no va a funcionar bien ya que no le estamos indicando a Libro quien es su autor
		 * si estamos indicando que lisbros escribio el autor
		 * 
		 * la cuestion es que Libros es quien tiene que relacionarse con autor y eso hay que hacerlo de manera manual
		 * y no alcanza con hacer autor1.setLibros(Arrays.asList(libro1));
		 */
		Libro libro1 = new Libro(14L, "PROGRAMAR EN JAVA ES FACIL", null);
		Autor autor1 = new Autor(14L, "pablo perez", "Espaniol");
		em.persist(libro1);		
		autor1.setLibros(Arrays.asList(libro1));
		em.persist(autor1);
		em.getTransaction().commit();
		em.close();

		
		/*
		 * lo siguente no imprimira ningun libro 
		 */
		em = emf.createEntityManager();
		Autor autor = em.find(Autor.class, 14L);
		System.out.println("LIBROS DEL AUTOR");
		List<Libro> libros = autor.getLibros();
		for (Libro libro : libros) {
			System.out.println(libro);
		}
		principal.pausa();
		em.close();
		
		
		/*
		 * para que si se muestre algun libro habria que hacer algo como...
		 */
		Libro libro2 = new Libro(15L, "libro 2", null);
		Autor autor2 = new Autor(15L, "autor 2", "Espaniol");
		Libro libro3 = new Libro(16L, "libro 3", null);
		em = emf.createEntityManager();
		autor2.notificar_el_autor_a_libro(Arrays.asList(libro2,libro3));
		em.getTransaction().begin();
		em.persist(autor2);
		em.getTransaction().commit();
		em.close();
		
		
		/*
		 * imprimimo el libro
		 */
		em = emf.createEntityManager();
		autor = em.find(Autor.class, 15L);
		System.out.println("LIBROS DEL AUTOR");
		libros = autor.getLibros();
		for (Libro libro : libros) {
			System.out.println(libro);
		}
		principal.pausa();
		em.close();
		
		
		/*
		 * eliminar uno de los libro
		 * 
		 * recordar iniciar una transaccion
		 */
		em = emf.createEntityManager();
		em.getTransaction().begin();
		autor = em.find(Autor.class, 15L);
		Libro eliminar = autor.getLibros().get(0);
		autor.removerLibro(eliminar);
		em.getTransaction().commit();
		principal.pausa();
		em.close();
		
		
		/*
		 * imprimimo el libro
		 */
		em = emf.createEntityManager();
		autor = em.find(Autor.class, 15L);
		System.out.println("LIBROS DEL AUTOR luego de eliminar 1");
		libros = autor.getLibros();
		for (Libro libro : libros) {
			System.out.println(libro);
		}
		principal.pausa();
		em.close();
		
		
	}
	
}
