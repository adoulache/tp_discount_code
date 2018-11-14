/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import simplejdbc.DAOException;

/**
 *
 * @author Anass
 */
public class DAO {

    	private final DataSource myDataSource;

	/**
	 *
	 * @param dataSource la source de données à utiliser
	 */
	public DAO(DataSource dataSource) {
            this.myDataSource = dataSource;
	}
        
	public List<DiscountEntity> findall() throws DAOException {
            List<DiscountEntity> result = new LinkedList<>(); // Liste vIde

            String sql = "SELECT * FROM DISCOUNT_CODE";
            try (Connection connection = myDataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)) {

                    try (ResultSet rs = stmt.executeQuery()) {
                            while (rs.next()) { // Tant qu'il y a des enregistrements
                                    // On récupère les champs nécessaires de l'enregistrement courant
                                    String code = rs.getString("DISCOUNT_CODE");
                                    float taux = rs.getFloat("RATE");
                                    // On crée l'objet entité
                                    DiscountEntity c = new DiscountEntity(code, taux);
                                    // On l'ajoute à la liste des résultats
                                    result.add(c);
                            }
                    }
            }  catch (SQLException ex) {
                    Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
                    throw new DAOException(ex.getMessage());
            }

            return result;
	}
        
        public void addDiscountCode(String code, float taux ) throws SQLException {
            String sql = "INSERT INTO DISCOUNT_CODE (DISCOUNT_CODE,RATE) VALUES (?,?)";
            try (   Connection connection = myDataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)
            ) {
                    // Définir la valeur du paramètre
                    stmt.setString(1, code);
                    stmt.setFloat(2, taux);
                    stmt.executeUpdate();

            }  catch (SQLException ex) {
                System.out.println("Erreur : " + ex.getMessage());
            }
        }
        
        public void deleteDiscountCode(String code) throws SQLException {
            String sql = "DELETE FROM DISCOUNT_CODE WHERE DISCOUNT_CODE = ?";
            try (   Connection connection = myDataSource.getConnection();
                    PreparedStatement stmt = connection.prepareStatement(sql)
            ) {
                    // Définir la valeur du paramètre
                    stmt.setString(1, code);
                    stmt.executeUpdate();

            }  catch (SQLException ex) {
                System.out.println("Erreur : " + ex.getMessage());
            }
        }
}
