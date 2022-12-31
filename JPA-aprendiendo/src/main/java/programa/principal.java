package programa;

import java.util.Scanner;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hsqldb.util.DatabaseManagerSwing;

public class principal {

	private static EntityManagerFactory emf = null;
	
	public static void main(String[] args) {

		DatabaseManagerSwing.main(new String[] {"--url","jdbc:hsqldb:file:/home/paulo/Escritorio/jpa/db","--user","","--password",""});
		
		prueba1 p1 = new prueba1();
		p1.hacerCosas();
		
		prueba2 p2 = new prueba2();
		p2.hacerCosas();


	}
	
	
	
	
	
	public static EntityManagerFactory crearEntityManagerFactory() {
		if (emf == null) {
			principal.emf = Persistence.createEntityManagerFactory("ujyt");
		}
		return principal.emf;
	}
	
	public static void crearEntityManagerFactoryCerrar() {
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
