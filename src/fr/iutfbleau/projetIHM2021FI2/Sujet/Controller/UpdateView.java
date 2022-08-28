package fr.iutfbleau.projetIHM2021FI2.Sujet.Controller;

import java.awt.event.*;

import fr.iutfbleau.projetIHM2021FI2.Sujet.View.MultipleResultat;

public class UpdateView implements ActionListener {


    private String cmp;
    private int posTab = 1, nbReservation;

    MultipleResultat vue;

    /**
     * 
     * @param vue la vue à mettre à jour
     * @param nbReservation le nombre de préréservation pour la même personne
     */
    public UpdateView(MultipleResultat vue, int nbReservation) {
        this.vue = vue;
        this.nbReservation = nbReservation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.cmp = e.getActionCommand();

        if (this.cmp.equals("Préréservation suivante")) {

            // retour au début du tableau quand on est au dernier élément et qu'on veut le suivant
            if (posTab == nbReservation) {
                posTab = 0;
            }

            this.vue.updateInformations(posTab);

            posTab++;
        }

    }
        
}
