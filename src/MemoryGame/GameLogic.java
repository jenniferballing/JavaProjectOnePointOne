package MemoryGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by JenniferBalling on 3/19/14.
 */
public class GameLogic extends JFrame implements ActionListener{

    protected JPanel ContainerP, MovesP, TitleP, GridP, ScoreP, ImageP, QuestionP, ButtonP, BonusP, WinsP;
    protected JLabel Title, Rings, QuestionTF, p1WinsL, p2WinsL, p1WinsTF, p2WinsTF, SpaceTF, Player1L, Player2L, Player1TF, Player2TF, p2MovesL, p2MovesTF, p1MovesL, p1MovesTF;
    protected ImageIcon OlympicRings;
    protected JButton TwelveB, SixteenB, TwentyB, YesB, NoB;
    protected int GridR, GridC;
    protected Images panelHolder[][];
    protected ImageBoss boss;
    protected Player p1, p2;
    protected int p1Score, p2Score, matchCount=0;
    protected String temp1, temp2;


    public GameLogic(){
        InitSetup();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700,700);
        pack();
        setVisible(true);

    }
    void InitSetup(){

        //INITIALIZATIONS

        GridP= new JPanel();
        WinsP= new JPanel();
        ScoreP= new JPanel();
        MovesP=new JPanel();
        Player1L= new JLabel();
        Player1TF= new JLabel();
        Player2L= new JLabel();
        Player2TF= new JLabel();
        p1MovesL= new JLabel();
        p1MovesTF= new JLabel();
        p2MovesL= new JLabel();
        p2MovesTF= new JLabel();
        p1Score=0;
        p2Score=0;

        p1= new Player("p1", 0, 0, true);
        p2= new Player("p2", 0, 0, false);

        p1WinsL= new JLabel();
        p1WinsTF= new JLabel();
        p2WinsL= new JLabel();
        p2WinsTF= new JLabel();
        SpaceTF= new JLabel();

        OlympicRings= new ImageIcon(this.getClass().getResource("Card.png"));
        Rings = new JLabel ("", OlympicRings, JLabel.CENTER);

        //TITLE PANEL
        Title= new JLabel("Olympic Memory Game");
        TitleP= new JPanel();
        TitleP.setLayout(new FlowLayout(FlowLayout.CENTER));
        TitleP.setPreferredSize(new Dimension(500, 75));
        TitleP.setBackground(Color.lightGray);
        Title.setText("Olympic Memory Game");
        Title.setForeground(Color.BLACK);
        Title.setFont(Title.getFont().deriveFont(30f));
        TitleP.add(Title);

        //IMAGE PANEL
        ImageP= new JPanel();
        ImageP.setLayout(new FlowLayout(FlowLayout.CENTER));
        ImageP.setPreferredSize(new Dimension(500, 150));
        ImageP.setBackground(Color.white);
        OlympicRings = new ImageIcon(this.getClass().getResource("OlympicRings.png"));
        Rings = new JLabel ("", OlympicRings, JLabel.CENTER);
        ImageP.add(Rings);

        //QUESTION PANEL
        QuestionP = new JPanel();
        QuestionTF = new JLabel("Would you like to play the Olympic Memory Game?");
        QuestionTF.setFont(QuestionTF.getFont().deriveFont(15f));
        QuestionP.setBackground(Color.white);
        QuestionP.setPreferredSize(new Dimension(500, 100));
        QuestionP.add(QuestionTF);

        //BUTTON PANEL
        TwelveB= new JButton("12");
        TwelveB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwelveButtonListener(e);
            }
      });
        SixteenB= new JButton("16");
        SixteenB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SixteenButtonListener(e);
            }
        });
        TwentyB= new JButton("20");
        TwentyB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TwentyButtonListener(e);
            }
        });
        ButtonP = new JPanel();
        ButtonP.setPreferredSize(new Dimension(500, 50));
        ButtonP.setBackground(Color.white);
        YesB= new JButton("YES");
        YesB.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                YesButtonListener(e);
            }
        });
        NoB= new JButton("NO");
        NoB.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NoButtonListener(e);
            }
        });
        ButtonP.add(YesB);
        ButtonP.add(NoB);

        //BONUS PANEL
        BonusP = new JPanel();
        BonusP.setPreferredSize(new Dimension(500, 75));
        BonusP.setBackground(Color.lightGray);

        //BACKGROUND PANEL
        ContainerP= new JPanel();
        ContainerP.setBackground(Color.white);
        ContainerP.setPreferredSize(new Dimension(600, 600));
        ContainerP.add(TitleP);
        ContainerP.add(ImageP);
        ContainerP.add(QuestionP);
        ContainerP.add(ButtonP);
        ContainerP.add(BonusP);
        add(ContainerP, BorderLayout.NORTH);
    }
    void GameSetup(){

        ContainerP.removeAll();
        ContainerP.setSize(new Dimension(700, 700));
        ContainerP.setBackground(Color.white);

        //RESET TITLE PANEL FEATURES
        Title.setForeground(Color.black);
        Title.setFont(Title.getFont().deriveFont(40f));
        Title.setLayout(new FlowLayout(FlowLayout.CENTER));

        TitleP.setPreferredSize(new Dimension(700, 80));
        TitleP.add(Title);
        TitleP.setBackground(Color.white);

        //GRID PANEL
        GridP.setLayout(new FlowLayout(FlowLayout.CENTER));
        GridP.setBackground(Color.black);
        GridP.setPreferredSize(new Dimension((GridR*95),(GridC*95)));
        PlaceCards();

        //WINS PANEL
        p1WinsL.setText("Player 1 Wins:");
        p1WinsL.setForeground(Color.white);
        p1WinsL.setFont(p1WinsL.getFont().deriveFont(16f));

        SpaceTF.setText("       ");

        p1WinsTF.setText("0");
        p1WinsTF.setFont(p1WinsTF.getFont().deriveFont(16f));
        p1WinsTF.setForeground(Color.white);

        p2WinsL.setText("Player 2 Wins:");
        p2WinsL.setForeground(Color.white);
        p2WinsL.setFont(p2WinsL.getFont().deriveFont(16f));

        p2WinsTF.setText("0");
        p2WinsTF.setFont(p2WinsTF.getFont().deriveFont(16f));
        p2WinsTF.setForeground(Color.white);

        WinsP.setBackground(Color.darkGray);
        WinsP.setPreferredSize(new Dimension(600, 40));
        WinsP.setLayout(new FlowLayout(FlowLayout.CENTER));
        WinsP.add(p1WinsL);
        WinsP.add(p1WinsTF);
        WinsP.add(SpaceTF);
        WinsP.add(p2WinsL);
        WinsP.add(p2WinsTF);

        //MOVES PANEL
        p1MovesL.setText("Player 1 Moves:");
        p1MovesL.setFont(p1MovesL.getFont().deriveFont(16f));
        p1MovesL.setForeground(Color.white);

        p1MovesTF.setText("0");
        p1MovesTF.setFont(p1MovesTF.getFont().deriveFont(16f));
        p1MovesTF.setForeground(Color.white);

        p2MovesL.setText("Player 2 Moves:");
        p2MovesL.setFont(p2MovesL.getFont().deriveFont(16f));
        p2MovesL.setForeground(Color.white);

        p2MovesTF.setText("0");
        p2MovesTF.setFont(p2MovesTF.getFont().deriveFont(16f));
        p2MovesTF.setForeground(Color.white);

        MovesP.setBackground(Color.darkGray);
        MovesP.setPreferredSize(new Dimension(600, 40));
        MovesP.setLayout(new FlowLayout(FlowLayout.CENTER));
        MovesP.add(p1MovesL);
        MovesP.add(p1MovesTF);
        MovesP.add(SpaceTF);
        MovesP.add(p2MovesL);
        MovesP.add(p2MovesTF);

        //SCORE PANEL
        Player1L.setText("Player 1:");
        Player1L.setForeground(Color.blue);
        Player1L.setFont(Player1L.getFont().deriveFont(24f));

        Player1TF.setText("0");
        Player1TF.setFont(Player1TF.getFont().deriveFont(20f));

        Player2L.setText("Player 2:");
        Player2L.setForeground(Color.RED);
        Player2L.setFont(Player2L.getFont().deriveFont(20f));

        Player2TF.setText("0");
        Player2TF.setFont(Player2TF.getFont().deriveFont(20f));

        ScoreP.setLayout(new FlowLayout(FlowLayout.CENTER));
        ScoreP.setBackground(Color.WHITE);
        ScoreP.setPreferredSize(new Dimension(600, 50));
        ScoreP.add(Player1L);
        ScoreP.add(Player1TF);
        ScoreP.add(Player2L);
        ScoreP.add(Player2TF);

        //CONTAINER PANEL
        ContainerP.add(TitleP);
        ContainerP.add(WinsP);
        ContainerP.add(GridP);
        ContainerP.add(MovesP);
        ContainerP.add(ScoreP);
        add(ContainerP, BorderLayout.CENTER);

        ContainerP.update(this.getGraphics());
        ContainerP.revalidate();
        ContainerP.repaint();
    }

    void ResetFunc(){
        for(int i=0; i<GridR; i++){
            for(int j=0; j<GridC; j++){
                panelHolder[i][j].setCurrentPic(picNum.card);
            }
        }
    }
    void PlaceCards(){
        ArrayList<picNum> picArr= new ArrayList<picNum>();
        int count=0;

        picArr.add(picNum.values()[2]);
        picArr.add(picNum.values()[3]);
        for(int l=4; l<(GridC*GridR)/2+5; l++){
            picArr.add(picNum.values()[l]);
            picArr.add(picNum.values()[l]);
        }
        Collections.shuffle(picArr);

        for(int i=0; i<GridR; i++){
            for(int j=0; j<GridC; j++){
                panelHolder[i][j].setSport(picArr.get(count));
                count++;
            }
        }
    }

    //OK BUTTON LISTENER
    public void TwelveButtonListener (ActionEvent e){
        GridR=3;
        GridC=4;
        cardValue();
    }
    public void SixteenButtonListener (ActionEvent e){
        GridR=4;
        GridC=4;
        cardValue();
    }
    public void TwentyButtonListener (ActionEvent e){
        GridR=5;
        GridC=4;
        cardValue();
    }

    //YES BUTTON HANDLER
    void YesButtonListener (ActionEvent e){

        ButtonP.removeAll();
        QuestionTF.setText("How many cards would you like to play with?");
        ButtonP.add(TwelveB);
        ButtonP.add(SixteenB);
        ButtonP.add(TwentyB);
        ButtonP.revalidate();
        ButtonP.repaint();
    }
    //NO BUTTON HANDLER
    void NoButtonListener (ActionEvent e){
        int NoOption = JOptionPane.showConfirmDialog(null,"Do you want to play?","Are you sure?",JOptionPane.YES_NO_OPTION);
        if (NoOption == JOptionPane.YES_OPTION) {
            YesButtonListener(e);
        }
        else{
            NoButtonListener(e);
        }
    }
    @Override
    public void actionPerformed (ActionEvent e){

    }
    void CheckMatch(Images obj){
        increaseMovesFunc();
        //LOOP THROUGH THE BOARD
        for(int i=0; i<GridR; i++){
            for(int j=0; j<GridC; j++){
                //NOT FLIPPED YET
                if(panelHolder[i][j].getCurrentPic()!=picNum.card && panelHolder[i][j].getCurrentPic()!=picNum.done){
                    //GOLD METAL CARD
                    if(panelHolder[i][j].getCurrentPic()==picNum.goldMetal||obj.getCurrentPic()==picNum.goldMetal){
                        WinGif();
                        if(JOptionPane.showConfirmDialog(this, "Would you like to play again?")==JOptionPane.OK_OPTION){
                            increaseWinsFunc();
                            storeWins();
                            GameSetup();
                            ResetFunc();
                            resetWins();
                        }
                        else{
                            System.exit(0);
                        }
                    }
                    //FAILURE CARD
                    else if(panelHolder[i][j].getCurrentPic()==picNum.Fail|| obj.getCurrentPic()==picNum.Fail){
                         failGif();
                         increaseOtherPlayerWins();
                            storeWins();
                            GameSetup();
                            ResetFunc();
                            resetWins();
                        }
                    //NOT CHECKING AGAINST ITSELF
                    else if(obj!=panelHolder[i][j]){
                        //GOT A MATCH
                        if(obj.getCurrentPic()==panelHolder[i][j].getCurrentPic()){
                            if(matchCount>0){
                                AnotherMatchGif();
                                matchCount++;
                                bonusPoint();
                                if((panelHolder[i][j].PointValue==3)){
                                    ThreePointMatchGif();
                                    matchCount++;
                                }
                            }
                            else if(panelHolder[i][j].PointValue==3){
                                ThreePointMatchGif();
                                matchCount++;
                            }
                            else if(matchCount<1){
                                MatchGif();
                                matchCount++;
                            }

                            increaseScore(obj, panelHolder[i][j]);
                            WinCheck();
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "Not a match. Other player's turn...");
                            matchCount=0;

                            obj.setCurrentPic(picNum.card);
                            panelHolder[i][j].setCurrentPic(picNum.card);

                            p1.switchTurns(p2);
                            if(p1.Turn){
                                Player1L.setFont(Player1L.getFont().deriveFont(24f));
                                Player2L.setFont(Player2L.getFont().deriveFont(20f));
                            }
                            else{
                                Player1L.setFont(Player1L.getFont().deriveFont(20f));
                                Player2L.setFont(Player2L.getFont().deriveFont(24f));
                            }
                        }
                    }
                }
            }
        }
        update(this.getGraphics());
        revalidate();
        repaint();
    }
    void WinCheck(){
        int counter=0;
        for(int i=0; i<GridR; i++){
            for(int j=0; j<GridC; j++){
                if(panelHolder[i][j].getCurrentPic()==picNum.done){
                    counter++;
                }
            }
            if(counter>= (GridR*GridC)){
                increaseWinsFunc();
                storeWins();
                winnerMessage();
            }
        }
    }
    public void cardValue(){
        boss = new ImageBoss();
        panelHolder=new Images[GridR][GridC];

        for(int i=0; i<(GridR); i++){
            for(int j=0; j<(GridC); j++){
                if (j==2){
                    panelHolder[i][j]=new Images(3, boss, this);
                }
                else{
                    panelHolder[i][j]=new Images(1, boss, this);
                }
                GridP.add(panelHolder[i][j]);
            }
        }

        GameSetup();
        update(this.getGraphics());
    }
    void increaseMovesFunc(){
        if(p1.Turn){
            String str= p1MovesTF.getText();
            int num= Integer.parseInt(str);
            num++;
            String newStr =Integer.toString(num);
            p1MovesTF.setText(newStr);
        }
        else{
            String str= p2MovesTF.getText();
            int num= Integer.parseInt(str);
            num++;
            String newStr =Integer.toString(num);
            p2MovesTF.setText(newStr);
        }
    }
    void increaseWinsFunc(){
        if(p1.Turn){
            String str= p1WinsTF.getText();
            int num= Integer.parseInt(str);
            num++;
            String newStr =Integer.toString(num);
            p1WinsTF.setText(newStr);
        }
        else{
            String str= p2WinsTF.getText();
            int num= Integer.parseInt(str);
            num++;
            String newStr =Integer.toString(num);
            p2WinsTF.setText(newStr);
        }
    }
    void increaseOtherPlayerWins(){
        if(p2.Turn){
            String str= p1WinsTF.getText();
            int num= Integer.parseInt(str);
            num++;
            String newStr =Integer.toString(num);
            p1WinsTF.setText(newStr);
        }
        else{
            String str= p2WinsTF.getText();
            int num= Integer.parseInt(str);
            num++;
            String newStr =Integer.toString(num);
            p2WinsTF.setText(newStr);
        }

    }
    void increaseScore(Images obj, Images panelHolder){
        if(p1.Turn){
            int score=p1.getScore();
            score+=panelHolder.PointValue;
            p1.setScore(score);
            String scoreShow= Integer.toString(score);
            Player1TF.setText(scoreShow);
            panelHolder.setCurrentPic(picNum.done);
            obj.setCurrentPic(picNum.done);
        }
        else{
            int score=p2.getScore();
            score+=panelHolder.PointValue;
            p2.setScore(score);
            String scoreShow= Integer.toString(score);
            Player2TF.setText(scoreShow);
            panelHolder.setCurrentPic(picNum.done);
            obj.setCurrentPic(picNum.done);
        }
    }
    void winnerMessage(){
        String winner;
        String now;
        if(p1.Score>p2.Score){
            winner=" Player 1 wins!";
            increaseWinsFunc();
        }
        else if (p1.Score<p2.Score){
            winner= " Player 2 wins!";
            increaseWinsFunc();
        }
        else{
            winner = " Both Players are winners!";
        }
        String output= "Congrats! "+ winner + " Would you like to play again?";
        if(JOptionPane.showConfirmDialog(this, output)==JOptionPane.OK_OPTION){

            storeWins();
            GameSetup();
            ResetFunc();
            resetWins();
        }
        else{
            System.exit(0);
        }
    }
    void storeWins(){
        temp1= p1WinsTF.getText();
        temp2=p2WinsTF.getText();
    }
    void resetWins(){
        p1WinsTF.setText(temp1);
        p2WinsTF.setText(temp2);
    }
    void bonusPoint(){
        if(p1.Turn){
            int score=p1.getScore();
            score+=1;
            p1.setScore(score);
            String scoreShow= Integer.toString(score);
            Player1TF.setText(scoreShow);
        }
        else{
            int score=p2.getScore();
            score+=1;
            p2.setScore(score);
            String scoreShow= Integer.toString(score);
            Player2TF.setText(scoreShow);
        }
    }
    void failGif(){
        URL image;
        try{
            final ImageIcon icon = new ImageIcon(new URL("http://25.media.tumblr.com/311dce708db1e7520a13a538076c4495/tumblr_n1em70ptR71sa8164o1_400.gif"));
            JOptionPane.showMessageDialog(null, "You chose the FAILURE CARD!!! You lost with one... little... click. \nYour career is over. \n(Or you can desperately train for four years in an attempt to redeem yourself...)", "Failure IS an option... for you!", JOptionPane.INFORMATION_MESSAGE, icon);
            image = new URL ("http://25.media.tumblr.com/311dce708db1e7520a13a538076c4495/tumblr_n1em70ptR71sa8164o1_400.gif");

            URLConnection connect = new URLConnection(image) {
                @Override
                public void connect() throws IOException {

                }
            };
            try{
                InputStream in = connect.getInputStream();

            }
            catch(IOException e){
                e.getLocalizedMessage();
            }

        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
    void WinGif(){
        URL image;
        try{
            final ImageIcon icon = new ImageIcon(new URL("http://25.media.tumblr.com/7de508f6800048ae7f5497d719e2a6bf/tumblr_n1i5ijSexP1qdlh1io1_r1_400.gif"));
            JOptionPane.showMessageDialog(null, "You got the gold metal! \nYOU WIN!", "CONGRATULATIONS!!!", JOptionPane.INFORMATION_MESSAGE, icon);
            image = new URL ("http://25.media.tumblr.com/7de508f6800048ae7f5497d719e2a6bf/tumblr_n1i5ijSexP1qdlh1io1_r1_400.gif");

            URLConnection connect = new URLConnection(image) {
                @Override
                public void connect() throws IOException {

                }
            };
            try{
                InputStream in = connect.getInputStream();

            }
            catch(IOException e){
                e.getLocalizedMessage();
            }

        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
    void MatchGif(){
        URL image;
        try{
            final ImageIcon icon = new ImageIcon(new URL("http://pixel.nymag.com/imgs/daily/intelligencer/2014/02/15/Olympics%202/celebration.o.jpg/a_560x375.jpg"));
            JOptionPane.showMessageDialog(null, "You got a match! Its your turn again.", "Congrats!!!", JOptionPane.INFORMATION_MESSAGE, icon);
            image = new URL ("http://pixel.nymag.com/imgs/daily/intelligencer/2014/02/15/Olympics%202/celebration.o.jpg/a_560x375.jpg");

            URLConnection connect = new URLConnection(image) {
                @Override
                public void connect() throws IOException {

                }
            };
            try{
                InputStream in = connect.getInputStream();

            }
            catch(IOException e){
                e.getLocalizedMessage();
            }

        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
    void ThreePointMatchGif(){
        URL image;
        try{
            final ImageIcon icon = new ImageIcon(new URL("http://pixel.nymag.com/imgs/daily/intelligencer/2014/02/15/Olympics%202/celebration.o.jpg/a_560x375.jpg"));
            JOptionPane.showMessageDialog(null, "You got a match worth 3 points! Its your turn again.", "Congrats!!!", JOptionPane.INFORMATION_MESSAGE, icon);
            image = new URL ("http://pixel.nymag.com/imgs/daily/intelligencer/2014/02/15/Olympics%202/celebration.o.jpg/a_560x375.jpg");

            URLConnection connect = new URLConnection(image) {
                @Override
                public void connect() throws IOException {

                }
            };
            try{
                InputStream in = connect.getInputStream();

            }
            catch(IOException e){
                e.getLocalizedMessage();
            }

        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
    void AnotherMatchGif(){
        URL image;
        try{
            final ImageIcon icon = new ImageIcon(new URL("http://24.media.tumblr.com/tumblr_lz0isdhwDL1qk915bo1_400.gif"));
            JOptionPane.showMessageDialog(null, "You got another match! Its your turn again.", "Congrats!!!", JOptionPane.INFORMATION_MESSAGE, icon);
            image = new URL ("http://24.media.tumblr.com/tumblr_lz0isdhwDL1qk915bo1_400.gif");

            URLConnection connect = new URLConnection(image) {
                @Override
                public void connect() throws IOException {

                }
            };
            try{
                InputStream in = connect.getInputStream();

            }
            catch(IOException e){
                e.getLocalizedMessage();
            }

        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
    }
}
