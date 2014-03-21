package MemoryGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by JenniferBalling on 3/19/14.
 */

// MOVES NOT CHANGING - MATCHFUNC-GAMELOGIC
// P1WINSTF NOT SHOWING - GAMESETUP()
// DOESN't ASSIGN CORRECT POINTS - CARDVALUE - MATCHFUNC
// FAIL OR GOLD AT ANY TIME
public class Main {

    public static void main(String [] args){

        GameLogic game= new GameLogic();
        game.pack();
        game.setVisible(true);

    }


}
