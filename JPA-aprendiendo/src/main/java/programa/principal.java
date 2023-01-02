package programa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class principal {

	private static EntityManagerFactory emf = null;
	
	public static void main(String[] args) {

		/*
		prueba1 p1 = new prueba1();
		p1.hacerCosas();
		
		prueba2 p2 = new prueba2();
		p2.hacerCosas();

		
		prueba3 p3 = new prueba3();
		p3.hacerCosas();
		*/
		
		
		prueba4 p4 = new prueba4();
		p4.hacerCosas();
		
		//DatabaseManagerSwing.main(new String[] {"--url","jdbc:hsqldb:file:/home/paulo/Escritorio/jpa/db","--user","","--password",""});
		principal.emf.close();
		
	}
	
	
	
	
	
	public static EntityManagerFactory crearEntityManagerFactory() {
		if (emf == null) {
			principal.emf = Persistence.createEntityManagerFactory("ujyt");
		}
		return principal.emf;
	}
	
	public static void cerrarEntityManagerFactory() {
		principal.emf.close();
		principal.emf = null;
	}

	public static void pausa() {
		System.out.println("Press enter to continue...");
		try {
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
