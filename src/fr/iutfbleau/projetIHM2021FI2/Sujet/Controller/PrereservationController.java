/** * 
 * Controler de la fenêtre de recherche de préréservation en fonction de la référence ou du nom et prénom 
 */
package fr.iutfbleau.projetIHM2021FI2.Sujet.Controller;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

import fr.iutfbleau.projetIHM2021FI2.Sujet.Model.*;
import fr.iutfbleau.projetIHM2021FI2.Sujet.View.*;
import fr.iutfbleau.projetIHM2021FI2.API.Prereservation;
import fr.iutfbleau.projetIHM2021FI2.API.Chambre;

public class PrereservationController implements ActionListener {

    private Prereservation prereservation;
    private PrereservationFactoryModel pfm;
    private ReservationFactoryModel rfm;

    private JTextField nom, prenom, idReservation;

    private String nomData, prenomData, cmp, reference;

    private JFrame fenetre;

    private Set<Prereservation> ensemblePre = new HashSet<Prereservation>();
    private Set<Chambre> ensembleCh = new HashSet<Chambre>();

    /**
     * 
     * @param prenom prénom du client
     * @param nom nom du client
     * @param idReservation le référence de la réservation
     * @param fenetre la fenêtre de l'accueil
     */
    public PrereservationController(JTextField prenom, JTextField nom, JTextField idReservation, JFrame fenetre) {
        this.prenom = prenom;
        this.nom = nom;
        this.idReservation = idReservation;
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.cmp = e.getActionCommand();
        if (this.cmp.equals("Chercher") && !(this.nom.getText().isEmpty()) && !(this.prenom.getText().isEmpty())) {

            this.nomData = nom.getText();
            this.prenomData = prenom.getText();

            // RAJOUTER FACON DE VERIFIER SI CLIENT EXISTE + THROWS

            this.pfm = new PrereservationFactoryModel();
            this.rfm = new ReservationFactoryModel();

            try {
                this.ensemblePre = pfm.getPrereservations(this.nomData, this.prenomData);

                try {
                    this.ensembleCh = rfm.getChambres(ensemblePre.stream().findFirst().get());
                
                    MultipleResultat mr = new MultipleResultat(this.ensemblePre, this.ensembleCh);
                    this.fenetre.dispose();
                    mr.createWindow();
                    
                } catch (NullPointerException | IllegalStateException err2) {
                    JOptionPane.showMessageDialog(new JFrame(), "Aucune prereservation renseigner",
                    "Aucune chambre dsponible", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NullPointerException | IllegalStateException err2) {
                JOptionPane.showMessageDialog(new JFrame(), "Il n'y a pas de préréservation pour ce client",
                        "Erreur préréservation", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            if (!(this.idReservation.getText().isEmpty()) && !(this.idReservation.getText().equals("Référence"))) {

                this.reference = this.idReservation.getText();

                // Création de la factory Prereservation
                this.pfm = new PrereservationFactoryModel();

                try {
                    // On récupère la prereservation grâce à la référence, Si elle n'existe pas
                    // (return null), lève une exception
                    this.prereservation = pfm.getPrereservation(this.reference);


                    this.rfm = new ReservationFactoryModel();
                    this.ensembleCh = rfm.getChambres(this.prereservation);

                    Resultat result = new Resultat(this.prereservation, this.ensembleCh);
                    this.fenetre.dispose();
                    result.windowCreate();

                } catch (NullPointerException | IllegalStateException err2) {
                    JOptionPane.showMessageDialog(new JFrame(), "La référence indiquée n'existe pas ou n'est pas bonne",
                            "Erreur référence", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                // Si aucun des champs n'a été renseigné
                JOptionPane.showMessageDialog(new JFrame(), "Veuillez renseigner les champs demandés",
                        "Erreur recherche", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
