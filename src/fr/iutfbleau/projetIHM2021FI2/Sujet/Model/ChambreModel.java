/** * 
 * Model de chambre qui reprend l'API chambre
 */
package fr.iutfbleau.projetIHM2021FI2.Sujet.Model;

import fr.iutfbleau.projetIHM2021FI2.API.Chambre;
import fr.iutfbleau.projetIHM2021FI2.API.TypeChambre;

import java.sql.*;

public class ChambreModel implements Chambre {

    private ConnectionModel connection = new ConnectionModel();
    private Connection cnx;

    private int numChambre;
    private String type;

    /**
     * Constructeur
     * @param num le numéro de la chambre
     */
    public ChambreModel(int num) {
        this.numChambre = num;
    }

    @Deprecated
    public boolean unLitSimple() {
        return true;
    }

    @Deprecated
    public boolean deuxLitsSimples() {
        return true;
    }

    @Deprecated
    public boolean unLitDouble() {
        return true;
    }

    /**
     * @return le numéro de la chambre
     */
    public int getNumero() {
        return this.numChambre;
    }

    /**
     * Méthode qui retourne le type de la chambre
     * @return le type de la chambre
     */
    public TypeChambre getType() {

        this.cnx = connection.connexionVal();

        try {
            PreparedStatement rqt = cnx.prepareStatement("SELECT type FROM chambreIHM WHERE id=?");
            rqt.setInt(1, this.numChambre);

            ResultSet rs = rqt.executeQuery();

            while (rs.next()) {
                this.type = rs.getString(1);
            }

        } catch (SQLException e3) {
            System.err.println("Problème dans la requête SQL getType de la class ChambreModel");
        }

        if (this.type.equals("UNLS")) {
            return TypeChambre.UNLS;
        } else {
            if (this.type.equals("UNLD")) {
                return TypeChambre.UNLD;
            } else {
                if (this.type.equals("DEUXLS")) {
                    return TypeChambre.DEUXLS;
                }
                else {
                    throw new NullPointerException("Pas de type pour cette chambre");
                }
            }
        }
    }

}
