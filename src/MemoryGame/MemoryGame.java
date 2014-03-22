package MemoryGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by JenniferBalling on 3/22/14.
 */
public class MemoryGame {

    public static void main(String [] args){
        GameLogic game= new GameLogic();
        game.pack();
        game.setVisible(true);
    }
}
