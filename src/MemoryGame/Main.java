package MemoryGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by JenniferBalling on 3/19/14.
 */

//Not using the bonus point menu very well
//could store wins in file with real names/ gif in joption pane....
//try-catch blocks
//About button
//README file
public class Main {

    public static void main(String [] args){

        GameLogic game= new GameLogic();
        game.pack();
        game.setVisible(true);

    }


}
