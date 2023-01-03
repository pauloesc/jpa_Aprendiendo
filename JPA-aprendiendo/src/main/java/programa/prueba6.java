package programa;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import entidades.Comentario;
import entidades.Publicacion;

public class prueba6 {

	public void hacerCosas() {

		EntityManagerFactory emf = principal.crearEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		Publicacion p1 = new Publicacion(1L, "como cocinar pollo a la naranja", null);
		Comentario c1 = new Comentario(1L, "los amo a todos", p1);
		Comentario c2 = new Comentario(2L, "los odio ", p1);

		/*
		 * no usar Arrays.asList(c1,c2) provoca un error 
		 */
		List<Comentario> listComentariosDePublicacion = new ArrayList<Comentario>();
		listComentariosDePublicacion.add(c2);
		listComentariosDePublicacion.add(c1);

		p1.setComentarios(listComentariosDePublicacion);

		em.getTransaction().begin();
		em.persist(p1);
		em.persist(c1);
		em.persist(c2);
		em.getTransaction().commit();
		em.close();

		/*
		 * imprimo publicacion y comentarios
		 */
		System.out.println("------------------- 0");
		em = emf.createEntityManager();
		Publicacion p_extraida = em.find(Publicacion.class, 1L);
		System.out.println(p_extraida);
		p_extraida = null;
		em.close();
		System.out.println("------------------- 0");

		
		
		/*
		 * verifico funcionamiento del fetch = lazy
		 *
		 * el siguente codigo deberia de fallar ya que la cerrar la conexion antes de hacer
		 * p_extraida.getComentarios() va a dar una excepcion cuando intente imprimir
		 * los comentarios org.hibernate.LazyInitializationException:
		 */
		System.out.println("------------------- 0.5");
		try {
			em = emf.createEntityManager();
			p_extraida = em.find(Publicacion.class, 1L);
			em.close();
			for (Comentario comentario : p_extraida.getComentarios()) {
				System.out.println(comentario);
			}
		} catch (org.hibernate.LazyInitializationException e) {
			System.out.println("excepcion");
		}
		System.out.println("------------------- 0.5");
		
		
		
		//imprimir todos los comentarios
		System.out.println("------------------- 1");
		em = emf.createEntityManager();
		p_extraida = em.find(Publicacion.class, 1L);
		for (Comentario comentario : p_extraida.getComentarios()) {
			System.out.println(comentario);
		}
		em.close();
		System.out.println("------------------- 1");
		
		
		
		//elimino un comentario y imprimo los comentarios restantes
		System.out.println("------------------- 2");
		em = emf.createEntityManager();
		Comentario ce1 = em.find(Comentario.class, 1L);
		em.getTransaction().begin();
		ce1 = em.merge(ce1);
		em.remove(ce1);
		em.getTransaction().commit();
		p_extraida = em.find(Publicacion.class, 1L);
		System.out.println(p_extraida);
		p_extraida = null;
		em.close();
		System.out.println("------------------- 2");
		
		
		
		/*
		 * verifico que haya un solo comentario en la publicacion
		 */
		System.out.println("------------------- 3");
		em = emf.createEntityManager();
		p_extraida = em.find(Publicacion.class, 1L);
		System.out.println("cantida de comentarios: " + p_extraida.getComentarios().size());
		
		if ( em.find(Comentario.class, 1L) == null ) {
			System.out.println("no existe");
		}
		em.close();
		System.out.println("------------------- 3");
		
		
		
		/*
		 * elimino el comentario que queda pero de otra forma
		 */
		em = emf.createEntityManager();
		ce1 = em.find(Comentario.class, 2L);
		em.getTransaction().begin();
		ce1.getPublicacion().eliminarComentario(ce1);
		em.getTransaction().commit();
		em.close();
		
		/*
		 * verifico que haya un solo comentario en la publicacion
		 */
		System.out.println("------------------- 4");
		em = emf.createEntityManager();
		p_extraida = em.find(Publicacion.class, 1L);
		System.out.println("cantida de comentarios: " + p_extraida.getComentarios().size());
		if ( em.find(Comentario.class, 1L) == null ) {
			System.out.println("no existe");
		}
		if ( em.find(Comentario.class, 2L) == null ) {
			System.out.println("no existe");
		}
		em.close();
		System.out.println("------------------- 4");
		
	}

}
