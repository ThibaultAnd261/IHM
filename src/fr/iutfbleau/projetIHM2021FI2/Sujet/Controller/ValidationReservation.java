/** * 
 * Controler pour réserver une chambre
 */
package fr.iutfbleau.projetIHM2021FI2.Sujet.Controller;

import java.awt.event.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.Sujet.View.MultipleResultat;
import fr.iutfbleau.projetIHM2021FI2.Sujet.View.Resultat;
import fr.iutfbleau.projetIHM2021FI2.Sujet.Model.ReservationFactoryModel;
import fr.iutfbleau.projetIHM2021FI2.Sujet.Model.ChambreModel;
import fr.iutfbleau.projetIHM2021FI2.API.Prereservation;
import fr.iutfbleau.projetIHM2021FI2.API.Reservation;
import fr.iutfbleau.projetIHM2021FI2.API.Chambre;
import fr.iutfbleau.projetIHM2021FI2.Sujet.View.RecapView;

public class ValidationReservation implements ActionListener {

    private String NumchambreSelected;
    private MultipleResultat vue;
    private Resultat vueSeul;
    private Prereservation prereserv;

    private Chambre chambreSelected;
    private Reservation reserv;

    private String cmp;

    private RecapView recapV;

    ReservationFactoryModel rfm;

    /**
     * Constructeur
     * 
     * @param vue la vue précédente
     */
    public ValidationReservation(MultipleResultat vue) {
        this.vue = vue;
    }

    public ValidationReservation(Resultat vueSeul) {
        this.vueSeul = vueSeul;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.cmp = e.getActionCommand();
        // Récupèration du numéro de chambre sélectionnné dans la liste déroulante

        /** Quand on fait une recherche par référence */
        if (this.cmp.equals("Réserver la chambre")) {
            // System.out.println("-----------");
            // System.out.println("Résultat seul");
            this.NumchambreSelected = this.vueSeul.getTheItemSelected();
            this.prereserv = this.vueSeul.getCurrentPrereservation();

            chambreSelected = new ChambreModel(Integer.parseInt(this.NumchambreSelected));

            
            try {
                this.rfm = new ReservationFactoryModel();
                reserv = this.rfm.createReservation(prereserv, chambreSelected);

                recapV = new RecapView(reserv);
                recapV.CreateRecap();
                this.vueSeul.closeWindow();
                recapV.setVisible(true);

            } catch(NullPointerException | IllegalStateException erreur) {
                System.err.println("Erreur lors de la réservation");
            }
        }

        /** Quand on fait une recherche par prénom et nom */
        if (this.cmp.equals("Réserver")) {
            // System.out.println("-----------");
            // System.out.println("Résultat multiple");
            this.NumchambreSelected = this.vue.getTheItemSelected();
            this.prereserv = this.vue.getCurrentPrereservation();

            chambreSelected = new ChambreModel(Integer.parseInt(this.NumchambreSelected));

            try {
                this.rfm = new ReservationFactoryModel();
                reserv = this.rfm.createReservation(prereserv, chambreSelected);
                recapV = new RecapView(reserv);
                recapV.CreateRecap();
                this.vue.closeWindow();
                recapV.setVisible(true);
            } catch (NullPointerException errP) {
                JOptionPane.showMessageDialog(new JFrame(), "Impossible de creer la reservation",
                        "Erreur Reservation", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
