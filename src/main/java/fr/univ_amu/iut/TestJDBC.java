package fr.univ_amu.iut;// Ne pas faire un copier/coller du pdf...

// Importer les classes jdbc
import java.sql.*;

public class TestJDBC {
	// Chaine de connexion
	static final String CONNECT_URL = "jdbc:mysql://mysql-turin.alwaysdata.net:3306/turin_tpjava";
	static final String LOGIN = "turin_tpjava";
	static final String PASSWORD = "13012";
	// La requete de test
	static final String req = "SELECT NUM_ET, NOM_ET, PRENOM_ET " +
	                          "FROM ETUDIANT " +
	                          "WHERE VILLE_ET = 'AIX-EN-PROVENCE'";

	static int[] num_et_rajoute = {5000, 5001, 5002, 5003};
	static String[] nom_et_rajoute = {"George", "Michel", "Bernard", "Jean-Mouloude"};
	static final String requete = "INSERT INTO ETUDIANT (NUM_ET, NOM_ET) VALUES(?, ?)";

	public static void main(String[] args) throws SQLException {
		// Connexion a la base
		System.out.println("Connexion a " + CONNECT_URL);
		try (Connection conn = DriverManager.getConnection(CONNECT_URL,LOGIN,PASSWORD)){
			System.out.println("Connecte\n");
			// Creation d'une instruction SQL
			Statement stmt = conn.createStatement();
			// Execution de la requete

			// Statement
			PreparedStatement st = conn.prepareStatement(requete);
			for (int i=0; i < num_et_rajoute.length; ++i)
			{
				st.setInt(1, num_et_rajoute[i]);
				st.setString(2, nom_et_rajoute[i]);
				int nbTupleModifie = st.executeUpdate();
				System.out.println(nbTupleModifie + " Tuple modifié");
			}
			System.out.println("Execution de la requete : " + req );
			ResultSet rset = stmt.executeQuery(req);
			// Affichage du resultat
			while (rset.next()){	
				System.out.print(rset.getInt("NUM_ET") + " ");
				System.out.print(rset.getString("NOM_ET") + " ");
				System.out.println(rset.getString("PRENOM_ET"));
			}
			// Fermeture de l'instruction (liberation des ressources)
			stmt.close();
			System.out.println("\nOk.\n");
		} catch (SQLException e) {
			e.printStackTrace();// Arggg!!!
			System.out.println(e.getMessage() + "\n");
		}
	}
}
