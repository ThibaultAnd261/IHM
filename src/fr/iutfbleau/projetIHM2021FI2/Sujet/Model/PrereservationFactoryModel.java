/** * 
 * Model de PrereservationFactoryModel qui reprend l'API PrereservationFactory
 */
package fr.iutfbleau.projetIHM2021FI2.Sujet.Model;

import fr.iutfbleau.projetIHM2021FI2.API.PrereservationFactory;
import fr.iutfbleau.projetIHM2021FI2.API.Prereservation;

import java.util.*;
import java.sql.*;

public class PrereservationFactoryModel implements PrereservationFactory {

    private Connection cnx;
    private ConnectionModel connection = new ConnectionModel();

    private Set<Prereservation> ensemblePrereservation;
    private List<String> ensembleRef;

    /**
     * 
     * @param cnx la connexion à la BD
     */
    public PrereservationFactoryModel() {
    }

    /**
     * @param r la référence indiquée par le client
     * @return la préréservation
     */
    public Prereservation getPrereservation(String r) {

        this.cnx = connection.connexion();

        if (r == null) {
            throw new NullPointerException(" Référence vide (null)");
        }

        try {
            // On regarde si la référence indiquée existe bien, si oui on return la
            // prereservation sinon on return null qui lèvera une exception
            PreparedStatement rqt = cnx.prepareStatement("SELECT id FROM ReservationFA WHERE reference=?");
            rqt.setString(1, r);
            ResultSet rs = rqt.executeQuery();

            if (!(rs.next())) {
                // la référence donnée n'existe pas
                throw new IllegalStateException("Référence " + r + " inexistante");
            }

        } catch (SQLException e3) {
            System.err.println(
                    "Problème dans la requête SQL getPrereservation avec référence DANS PrereservationFactoryModel");
        }

        this.connection.fermeture(this.cnx);

        return new PrereservationModel(r);
    }

    /**
     * @param n le nom du client
     * @param p le prénom du client
     * @return le(s) préréservation(s)
     */
    public Set<Prereservation> getPrereservations(String n, String p) {

        this.cnx = this.connection.connexion();

        if ((n == null) || (p == null)) {
            throw new NullPointerException("Prénom et / ou nom vide(s) (null)");
        }

        try {
            // On regarde s'il y a au moins une reservation a ce nom et prenom, si oui on
            // return un ensemble de prereservation sinon on return null qui lèvera une
            // exception
            PreparedStatement rqt = cnx.prepareStatement(
                    "SELECT reference FROM ReservationFA WHERE client=(SELECT id FROM Client WHERE prenom=? and nom=?)");
            rqt.setString(1, p);
            rqt.setString(2, n);

            ResultSet rs = rqt.executeQuery();

            // Verifie s'il y a au moins un prereservation a ce nom/prenom
            if (!(rs.next())) {
                throw new IllegalStateException("Référence inexistante pour le client " + n + " " + p);
            }

            ensemblePrereservation = new HashSet<Prereservation>();
            ensembleRef = new ArrayList<>();

            rs.beforeFirst();

            while (rs.next()) {
                ensembleRef.add(rs.getString(1));
                String refe = rs.getString(1);
                Prereservation pre = new PrereservationModel(refe);
                ensemblePrereservation.add(pre);
            }

        } catch (SQLException e3) {
            System.err.println(
                    "Problème dans la requête SQL getPrereservations avec nom et prenom DANS PrereservationFactoryModel");
        }

        this.connection.fermeture(this.cnx);
        return ensemblePrereservation;
    }
}