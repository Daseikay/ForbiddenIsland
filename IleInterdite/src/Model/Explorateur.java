package Model;

import Utils.Utils.Pion;

import java.util.ArrayList;

public class Explorateur extends Aventurier {


    public Explorateur(String pseudo, Tuile tu){
        super(pseudo,tu);
        setPion(Pion.VERT);
    }

    /* DEPLACEMENT */

    @Override
    public ArrayList<Tuile> getTuilesAccessibles(Grille grille){
        ArrayList<Tuile> tuilesAccessibles = grille.getTuilesAdjacentes(getPos(), Messages.DEPLACER);
        tuilesAccessibles.addAll(grille.getTuilesDiagonales(getPos(), Messages.DEPLACER));
        return tuilesAccessibles;
    }

    @Override
    public ArrayList<Tuile> getTuilesAssechables(Grille grille){
        ArrayList<Tuile> tuilesAssechables = grille.getTuilesAdjacentes(getPos(), Messages.ASSECHER);
        tuilesAssechables.addAll(grille.getTuilesDiagonales(getPos(), Messages.ASSECHER));
        return tuilesAssechables;
    }
}