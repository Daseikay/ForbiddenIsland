/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;


public class VueAssechement {

        private final JFrame windows;
        private final JPanel mainPanels;
        private final JButton btnRetour;
        private final JButton btnAssecher;
        private final JPanel panelBoutons ;

        public VueAssechement (String nomJoueur, String NomAventurier, Color couleur){

            this.windows = new JFrame();
            windows.setSize(350, 200);
            //le titre = nom du joueur
            windows.setTitle(nomJoueur);
            mainPanels = new JPanel(new BorderLayout());
            this.windows.add(mainPanels);

            mainPanels.setBackground(new Color(230, 230, 230));
            mainPanels.setBorder(BorderFactory.createLineBorder(couleur, 2)) ;


            // SUD : les boutons
            this.panelBoutons = new JPanel(new GridLayout(2,2));
            this.panelBoutons.setOpaque(false);
            mainPanels.add(this.panelBoutons, BorderLayout.SOUTH);

            this.btnRetour = new JButton("Retour") ;
            this.btnAssecher = new JButton( "Assecher");


            this.panelBoutons.add(btnRetour);
            this.panelBoutons.add(btnAssecher);


            this.windows.setVisible(true);
}
    public JButton getBtnRetour() {
        return btnRetour;
    }

    public JButton getBtnAssecher() {
        return btnAssecher;
    }



    public static void main(String [] args) {
        // Instanciation de la fenêtre
        VueAssechement vueass = new VueAssechement ("Manon", "Explorateur", Utils.Pion.ROUGE.getCouleur() );
    }
    //============================================================================


}


/* fuuuuuuuuuuuuuuck*/