package programa;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import entidades.Direccion;
import entidades.Empleado2;

public class prueba3 {

	public prueba3() {
		// TODO Auto-generated constructor stub
	}
	
	public void hacerCosas() {
		EntityManagerFactory emf = principal.crearEntityManagerFactory();
		EntityManager em = emf.createEntityManager();
		
		Empleado2 emp = new Empleado2(10L, "Perez", "Pepito", LocalDate.of(1997, 3, 21));
		emp.setDireccion(new Direccion(15L, "CALLE FALSA","Springfild","eSTAD", "EEUU"));
		em.getTransaction().begin();
		em.persist(emp);
		em.getTransaction().commit();
		
		Empleado2 EM_BD = em.find(Empleado2.class, 10L);
		principal.pausa();
		System.out.println(EM_BD);
		
		em.close();
	}
	
}
