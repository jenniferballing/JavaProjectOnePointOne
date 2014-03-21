package MemoryGame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by JenniferBalling on 3/19/14.
 */
public class ImageBoss {
    private ArrayList<ImageIcon> imageArr;

    public ImageBoss(){
        imageArr = new ArrayList<ImageIcon>();
        imageArr.add(new ImageIcon(this.getClass().getResource("done.png")));
        imageArr.add(new ImageIcon(this.getClass().getResource("Card.png")));
        imageArr.add(new ImageIcon(this.getClass().getResource("Fail.png")));
        imageArr.add(new ImageIcon(this.getClass().getResource("goldMetal.png")));
        imageArr.add(new ImageIcon(this.getClass().getResource("Skater.png")));
        imageArr.add(new ImageIcon(this.getClass().getResource("SnowB.png")));
        imageArr.add(new ImageIcon(this.getClass().getResource("upsideSki.png")));
        imageArr.add(new ImageIcon(this.getClass().getResource("Luge.png")));
        imageArr.add(new ImageIcon(this.getClass().getResource("Hockey.png")));
        imageArr.add(new ImageIcon(this.getClass().getResource("DownHill.png")));
        imageArr.add(new ImageIcon(this.getClass().getResource("BobSled.png")));
        imageArr.add(new ImageIcon(this.getClass().getResource("Biathlon.png")));
        imageArr.add(new ImageIcon(this.getClass().getResource("Skeleton.png")));
        imageArr.add(new ImageIcon(this.getClass().getResource("XCountry.png")));
    }
    public ImageIcon getImage (picNum index){
        return imageArr.get(index.ordinal());
    }
}
