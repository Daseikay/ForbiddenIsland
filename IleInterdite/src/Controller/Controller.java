/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.Parameters;
import Utils.Utils;
import Utils.Utils.*;
import Vues.*;
import Model.*;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ThreadLocalRandom;


/**
 *
 * @author barrerat
 */
public class Controller implements Observer {

    private int joueurCourant = 0;
    private int nbActions = 0;
    private ArrayList<VueAventurier> vuesAventurier = new ArrayList<>();
    private ArrayList<Aventurier>aventuriers = new ArrayList<>();
    private ArrayList<Vue>vues = new ArrayList<>();
    Grille grille = new Grille();


    public Controller(){
        if (Parameters.LOGS) System.out.println("Début du jeu");

        getGrille().getTuiles()[2][2].setEtat(2);
        getGrille().getTuiles()[3][2].setEtat(2);
        getGrille().getTuiles()[4][2].setEtat(2);
        getGrille().getTuiles()[3][4].setEtat(2);
        getGrille().getTuiles()[0][3].setEtat(1);
        getGrille().getTuiles()[3][1].setEtat(1);
        getGrille().getTuiles()[3][3].setEtat(1);
        getGrille().getTuiles()[3][5].setEtat(1);
        getGrille().getTuiles()[5][3].setEtat(1);


        //Création des vues
        VueInscription vueInscription = new VueInscription();
        VueDeplacement vueDeplacement = new VueDeplacement();
        VueAssechement vueAssechement = new VueAssechement();

        //Abonnement

        addView(vueInscription);
        addView(vueDeplacement);
        addView(vueAssechement);


        vueInscription.setVisible(true);



    }

    public void addView(Vue vue){
        vue.abonner(this);
        vues.add(vue);
    }

    public void addViewAventurier(VueAventurier vue){
        vue.abonner(this);
        vuesAventurier.add(vue);
    }

    public void lancerPartie(){
        for (int i =0; i < vuesAventurier.size(); i++){
            vuesAventurier.get(i).setVisible(true);
        }
    }





    @Override
    public void update(Observable o, Object arg){
        /* INSCRIPTION JOUEURS */

        if (arg == Message.LANCERPARTIE){

            //On crée les aventuriers

            ArrayList<String> pseudosJoueurs = ((VueInscription)o).getPseudosJoueurs();

            ArrayList<String> typeAventuriers = new ArrayList<>();
            typeAventuriers.addAll(Arrays.asList("Messager", "Ingénieur", "Navigateur", "Pilote", "Explorateur")); // Plongeur pas encore géré

            //Attribution des types d'aventurier au hasard

            for(int i = 0; i < pseudosJoueurs.size(); i++){
                int randomAventurier = ThreadLocalRandom.current().nextInt(0,5-i);


                switch(typeAventuriers.get(randomAventurier)){
                    case "Messager":
                        Aventurier messager = new Messager(pseudosJoueurs.get(i),this.getGrille().getTuile(NomTuile.LA_PORTE_DE_CUIVRE));
                        aventuriers.add(messager);

                        VueAventurier vueAventurier = new VueAventurier(pseudosJoueurs.get(i), "Messager", Pion.VIOLET.getCouleur(), aventuriers.size()-1);
                        addViewAventurier(vueAventurier);
                        break;
                    case "Ingénieur":
                        Aventurier ingenieur = new Ingenieur(pseudosJoueurs.get(i),this.getGrille().getTuile(NomTuile.LA_PORTE_DE_BRONZE));
                        aventuriers.add(ingenieur);

                        VueAventurier vueAventurier2 = new VueAventurier(pseudosJoueurs.get(i), "Ingénieur", Pion.ROUGE.getCouleur(), aventuriers.size()-1);
                        addViewAventurier(vueAventurier2);
                        break;
                    case "Navigateur":
                        Aventurier navigateur = new Navigateur(pseudosJoueurs.get(i),this.getGrille().getTuile(NomTuile.LA_PORTE_D_OR));
                        aventuriers.add(navigateur);

                        VueAventurier vueAventurier3 = new VueAventurier(pseudosJoueurs.get(i), "Navigateur", Pion.JAUNE.getCouleur(), aventuriers.size()-1);
                        addViewAventurier(vueAventurier3);
                        break;
                    case "Pilote":
                        Aventurier pilote = new Pilote(pseudosJoueurs.get(i),this.getGrille().getTuile(NomTuile.HELIPORT));
                        aventuriers.add(pilote);

                        VueAventurier vueAventurier4 = new VueAventurier(pseudosJoueurs.get(i), "Pilote", Pion.BLEU.getCouleur(), aventuriers.size()-1);
                        addViewAventurier(vueAventurier4);
                        break;
                    case "Explorateur":
                        Aventurier explorateur = new Explorateur(pseudosJoueurs.get(i),this.getGrille().getTuile(NomTuile.LA_PORTE_DE_FER));
                        aventuriers.add(explorateur);

                        VueAventurier vueAventurier5 = new VueAventurier(pseudosJoueurs.get(i), "Explorateur", Pion.VERT.getCouleur(), aventuriers.size()-1);
                        addViewAventurier(vueAventurier5);
                        break;
                }

                typeAventuriers.remove(typeAventuriers.get(randomAventurier)); //Pour éviter les doublons
            }

            ((VueInscription) o).setVisible(false);
            lancerPartie();
        }

        /* ACTIONS DES AVENTURIERS */


        if(arg == Message.DEPLACER){
            vues.get(1).setTuilesDispo(getJoueurCourant().getTuilesAccessibles(this.grille));
            vues.get(1).setVisible(true);

        }

        if(arg == Message.VALIDERDEPLACEMENT){
            if(((Vue) o).getTuileSelectionnee() == null){
                JOptionPane erreur = new JOptionPane();
                erreur.showMessageDialog(null, "Aucune tuile n'a été sélectionnée.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }

            else{
                getJoueurCourant().setPos(getGrille().getTuile(((Vue) o).getTuileSelectionnee()));

                vues.get(1).setVisible(false);
                nbActions++;
            }
        }
    }



    /* GETTERS ET SETTERS */


    private Grille getGrille(){
        return this.grille;
    }

    public Aventurier getJoueurCourant(){
        return aventuriers.get(joueurCourant);
    }



    
}

