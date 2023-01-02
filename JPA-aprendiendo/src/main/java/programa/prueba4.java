package programa;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import entidades.Autor;
import entidades.Libro;

public class prueba4 {

	public void hacerCosas() {
		
		EntityManagerFactory emf = principal.crearEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		
		Autor a1 = new Autor(1L, "pablo perez", "Espaniol");
		Autor a2 = new Autor(2L, "elena gomez", "mexicana");
		Autor a3 = new Autor(3L, "miguel lopez", "chilena");
		
		Libro l1 = new Libro(1L, "PROGRAMAR EN JAVA ES FACIL", a2);
		Libro l2 = new Libro(2L, "COMO VESTIRSE CON ESTILO", a3);
		Libro l3 = new Libro(3L, "COMO COCINAR SIN QUEMAR LA COINA", a1);
		Libro l4 = new Libro(4L, "PROGRAMAR EN COBOL ES DIVERTIDO", a2);
		Libro l5 = new Libro(5L, "PROGRAMAR EN COBOL NO ES DIVERTIDO", a2);

		/*
		a1.agregarLibro(l3);	
		a2.agregarLibro(l1);
		a2.agregarLibro(l4);
		a2.agregarLibro(l5);
		a3.agregarLibro(l2);
		*/
		
		em.persist(l1);
		em.persist(l2);
		em.persist(l3);
		em.persist(l4);
		em.persist(l5);

		em.persist(a1);
		em.persist(a2);
		em.persist(a3);
		
		em.getTransaction().commit();
		em.close();

		em = emf.createEntityManager();
		Autor autor = em.find(Autor.class, 2L);

		List<Libro> libros = autor.getLibros();
		for (Libro libro : libros) {
			System.out.println(libro);
		}
		
		em.close();
		
	}
	
}
