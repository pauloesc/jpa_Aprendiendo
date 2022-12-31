package ujyt;

import javax.persistence.*;

import org.hsqldb.util.DatabaseManagerSwing;

public class JPAExample {

	
	
	public static void main(String[] args) {

		// Creamos una Entidad basado en el Persitence Unit Prueba definido en el persistence.xml
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ujyt");
		EntityManager em = emf.createEntityManager();
		
		
		// Cerramos el Entity Manager. Es importante para que cierre las conexiones
		// con la base de datos.
		em.close();
		emf.close();
		
		DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:file:/home/paulo/Escritorio/jpa/db", "--user", "", "--password", ""});
	
	}

}
