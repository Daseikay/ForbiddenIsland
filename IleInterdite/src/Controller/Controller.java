/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author barrerat
 */
public class Controller implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void tourDeJeu (Joueur j) {
        int nbaction = 4; 
        boolean btnTerminer = false;
        boolean btnAssecher = false;
        boolean btnDeplacer = false;
        while (nbaction != 0 && btnTerminer != true) {
            if (btnDeplacer = true ){
                nbaction = nbaction -1;
                deplacer(j); //coder deplacer//
            
        }
            else if(btnAssecher = true ){
                nbaction = nbaction-1;
                assecher(case); //coder assecher//
            }
            
            else {
                    break;
            }
            
        } 
    }
}