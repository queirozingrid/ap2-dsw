package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	/* {entityManager} because it's the same value that is in the
	 * <persistence-unit name=""/> 
	 * at persistence.xml file
	*/
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("comercioEM");

	public static EntityManager criarEntityManager() {
		return emf.createEntityManager();
	}
}