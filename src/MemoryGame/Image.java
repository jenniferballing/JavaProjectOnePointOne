package MemoryGame;

import javax.swing.*;

/**
 * Created by JenniferBalling on 3/19/14.
 */
public class Image {
    ImageIcon Front;
    ImageIcon Back;
    boolean Turned;
    int PointValue;
    JPanel Location;

    public Image(ImageIcon Front, ImageIcon Back, boolean Turned, int PointValue, JPanel Location){
        this.Front= Front;
        this.Back= Back;
        this.Turned=Turned;
        this.PointValue= PointValue;
        this.Location=Location;
    }
    public Image(){

    }
    void FlipCard(){
        if(this.Turned){
            this.Turned=false;
        }
        else {
            this.Turned=true;
        }
    }
}
