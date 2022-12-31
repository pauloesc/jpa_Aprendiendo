package programa;

import org.hsqldb.util.DatabaseManagerSwing;

public class principal {

	public static void main(String[] args) {

		prueba1 p1 = new prueba1();
		p1.hacerCosas();
		System.out.println("-------------------------main-------------------------");

		DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:file:/home/paulo/Escritorio/jpa/db", "--user",
				"", "--password", "" });
	}
}
