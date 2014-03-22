package MemoryGame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by JenniferBalling on 3/19/14.
 */
public class Images extends JPanel implements MouseListener{
    int PointValue;
    ImageBoss pics;
    private picNum currentPic;
    private picNum Sport;
    JLabel label;
    GameLogic gameBoard;

    public Images(int PointValue, ImageBoss pics, GameLogic gameBoard){
        super ();
        this.label=new JLabel();
        this.add(label);
        this.PointValue= PointValue;
        this.pics= pics;
        this.gameBoard=gameBoard;
        Sport=picNum.card;
        currentPic= picNum.card;
        label.setIcon(pics.getImage(currentPic));
        label.addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(currentPic==picNum.card){
            this.setCurrentPic(Sport);
        }
        this.gameBoard.CheckMatch(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int getPointValue() {
        return PointValue;
    }

    public void setPointValue(int pointValue) {
        PointValue = pointValue;
    }

    public picNum getCurrentPic() {
        return currentPic;
    }

    public void setCurrentPic(picNum currentPic) {

        this.currentPic = currentPic;
        this.label.setIcon(this.pics.getImage(currentPic));
    }

    public picNum getSport() {
        return Sport;
    }

    public void setSport(picNum sport) {
        Sport = sport;
    }
}
