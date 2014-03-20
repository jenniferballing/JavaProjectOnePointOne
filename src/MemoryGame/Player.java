package MemoryGame;

/**
 * Created by JenniferBalling on 3/19/14.
 */
public class Player {
    String Name;
    int Score;
    int Wins;
    boolean Turn;

    public Player(String Name, int Score, int Wins, boolean Turn){
        this.Score=Score;
        this.Name=Name;
        this.Wins=Wins;
        this.Turn=Turn;
    }

    public void switchTurns(Player obj){
        if(obj.Turn){
            this.setTurn(true);
            obj.setTurn(false);
        }
        else{
            this.setTurn(false);
            obj.setTurn(true);
        }
    }
    public boolean isTurn() {
        return Turn;
    }

    public void setTurn(boolean turn) {
        Turn = turn;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public String getName() {
        return Name;
    }

    public int getWins() {
        return Wins;
    }

    public void setWins(int wins) {
        Wins = wins;
    }
    public void Reset(){
        this.Name=" ";
        this.Score=0;
        this.Wins=0;
    }
}
