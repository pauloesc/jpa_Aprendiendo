package programa;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Empleado;

/*
 * este ejemplo contiene un cosas relacionada a un objeto managed
 */

public class prueba1 {

	
	
	public void hacerCosas() {

		// Creamos una Entidad basado en el Persitence Unit Prueba definido en el
		// persistence.xml
		EntityManagerFactory emf = principal.crearEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		Empleado e1 = new Empleado(11, "sanchez", "paulo", new GregorianCalendar(1997, 12, 12).getTime());
		Empleado e2 = new Empleado(12, "cuello", "alejandra", new GregorianCalendar(1950, 1, 1).getTime());

		em.getTransaction().begin();
		em.persist(e1);
		em.persist(e2);
		em.getTransaction().commit();

		/*
		 * no funciona List<Empleado> empl = (List<Empleado>)
		 * em.createQuery("select a from Empleado").getResultList();
		 */

		@SuppressWarnings("unchecked")
		List<Empleado> empleadosLista = (List<Empleado>) em.createQuery("select a FROM Empleado a").getResultList();
		for (Empleado e : empleadosLista) {
			System.out.println(e.toString());
		}
		System.out.println("----");

		em.getTransaction().begin();
		Empleado modificar1 = em.find(Empleado.class, 11);
		modificar1.setNombre("elder");
		modificar1.setApellido("cuello");
		em.getTransaction().commit();

		empleadosLista = (List<Empleado>) em.createQuery("select a FROM Empleado a").getResultList();
		for (Empleado e : empleadosLista) {
			System.out.println(e.toString());
		}
		System.out.println("----");

		// esto es posible de hacer porque modificar1 es una entidad autoadministrada
		modificar1.setNombre("paulo elder");
		modificar1.setApellido(" sanchez cuello");

		empleadosLista = (List<Empleado>) em.createQuery("select a FROM Empleado a").getResultList();
		for (Empleado e : empleadosLista) {
			System.out.println(e.toString());
		}
		System.out.println("----");

		em.close();

		/*
		 * una vez cerrado el entity manager modificar1 ya no tiene la propiedad de ser
		 * autoadministrado
		 */

		/*
		 * lo siguiente no surtira efecto
		 */
		modificar1.setNombre("sin efecto");
		modificar1.setApellido("sin efecto");

		try {
			empleadosLista = (List<Empleado>) em.createQuery("select a FROM Empleado a").getResultList();
			for (Empleado e : empleadosLista) {
				System.out.println(e.toString());
			}
			System.out.println("----.");
		} catch (java.lang.IllegalStateException e) {
			System.out.println("da error porque esta cerrado el entity manager");
			System.out.println("");
		}

		/*
		 * para lograr hacer una modificacion sobre modificar1 primero hay que abrir un
		 * nuevo entityManager hay que tomar modificar1 y restaurarle la propiedad de
		 * autoadministrado
		 */
		em = emf.createEntityManager();

		/*
		 * recordar que merge(modificar1) va a guardar los cambios que le hice a
		 * modificar1 recordara que mas arriva en el codigo hice
		 * modificar1.setNombre("sin efecto"); modificar1.setApellido("sin efecto");
		 */
		// hace la persistencia de una no espera a nada ni nadie
		modificar1 = em.merge(modificar1);

		empleadosLista = (List<Empleado>) em.createQuery("select a FROM Empleado a").getResultList();
		for (Empleado e : empleadosLista) {
			System.out.println(e.toString());
		}
		System.out.println("----");

		em.getTransaction().begin();
		modificar1.setNombre("con efecto");
		modificar1.setApellido("con efecto");
		em.getTransaction().commit();

		empleadosLista = (List<Empleado>) em.createQuery("select a FROM Empleado a").getResultList();
		for (Empleado e : empleadosLista) {
			System.out.println(e.toString());
		}
		System.out.println("----");

		em.close();

	}

}
