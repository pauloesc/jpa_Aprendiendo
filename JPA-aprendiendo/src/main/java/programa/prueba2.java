package programa;

import java.security.Principal;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import entidades.Empleado;

public class prueba2 {
	
	public prueba2() {}
	
	public void hacerCosas() {
		EntityManagerFactory emf = principal.crearEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		Empleado p1 = new Empleado(3, "Muerto", "carlos", new GregorianCalendar(1945, 1, 1).getTime());
		
		em.getTransaction().begin();
		em.persist(p1);
		em.getTransaction().commit();
		@SuppressWarnings("unchecked")
		List<Empleado> empleadosLista = (List<Empleado>) em.createQuery("select a FROM Empleado a").getResultList();
		for (Empleado e : empleadosLista) {
			System.out.println(e.toString());
		}
		em.close();
		System.out.println("----");
		principal.pausa();
		
		
		
		
		/*
		 * no deberia haber cambios ya que p1 no es automanejada
		 * p1 perdio la propiedad de ser automaejado cuando se realizo el em.close()
		 */
		em = emf.createEntityManager();
		em.getTransaction().begin();
		p1.setNombre("Noel");
		/*
		 * si quiero que funcion en este punto tendria que poner 
		 * em.merge(p1);
		 * 
		 * tambien podria hacer 
		 * p1 = em.merge(p1); antes de la instruccion p1.setNombre("Noel");
		 */
		em.getTransaction().commit();
		empleadosLista = (List<Empleado>) em.createQuery("select a FROM Empleado a").getResultList();
		for (Empleado e : empleadosLista) {
			System.out.println(e.toString());
		}
		em.close();
		System.out.println("----");
		principal.pausa();
		
		
		
		
		em = emf.createEntityManager();
		em.getTransaction().begin();
		p1.setNombre("Noel");
		em.merge(p1);
		/*
		 * que pasa si hago p1.setApellido("no cambia en la base de datos")
		 * respuesta: no cambia ya que p1 no es automanejable
		 * em.merge(p1) NO LE DA A P1 LA PROPIEDAD DE SER AUTOMANEJABLE
		 * P1 = em.merge(p1) SI LE DA LA PROPIEDAD
		 */
		p1.setApellido("no cambia en la base de datos");
		em.getTransaction().commit();
		empleadosLista = (List<Empleado>) em.createQuery("select a FROM Empleado a").getResultList();
		for (Empleado e : empleadosLista) {
			System.out.println(e.toString());
		}
		System.out.println("--marca--");
		principal.pausa();
		/*
		 * para que cambie tengo que hacer p1 = em.merge(p1);
		 */
		em.getTransaction().begin();
		p1 = em.merge(p1);
		p1.setNombre("cambio nombre");
		p1.setApellido("cambio apellido");
		em.getTransaction().commit();
		
		empleadosLista = (List<Empleado>) em.createQuery("select a FROM Empleado a").getResultList();
		for (Empleado e : empleadosLista) {
			System.out.println(e.toString());
		}
		em.close();
		System.out.println("----");
		principal.pausa();
		
		
		
		
		/*
		 * A partir de este punto voy a probar borrar objetos
		 */
		System.out.println("--Borrar--");
		
		/*
		 * lo siguiente da error
		 * por eso lo pongo dentro de un try
		 */
		try {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(p1); //Entity must be managed to call remove:
		em.getTransaction().commit();
		}
		catch(  java.lang.IllegalArgumentException e) {
			System.out.println("//Entity must be managed to call remove:");
		}
		
		em = emf.createEntityManager();
		em.getTransaction().begin();
		p1 = em.merge(p1);
		em.remove(p1); //Entity must be managed to call remove:
		em.getTransaction().commit();
		
	}

}
