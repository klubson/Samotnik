package models;
import util.GameVersion;
import util.Turn;

public class Board {
    private GameVersion version;
    private boolean[][] available, filled;
    private int numberOfPawns, selectedX, selectedY;
    private Turn turn;

    public Board(GameVersion version) {reset(version);}
    public void reset(GameVersion version) {
        this.version = version;
        filled = version.getBoard();
        available = version.getBoard();
        numberOfPawns = version.getNumberOfPawns();
        filled[version.getStartX()][version.getStartY()] = false;
        turn = Turn.MOVEMENT;
        selectedX = version.getStartX();
        selectedY = version.getStartY();
    }
    public void select() {
        if(turn == Turn.SELECTION) {
            turn = Turn.MOVEMENT;
        }
        else if (isFilled(selectedX, selectedY)) {
            turn = Turn.SELECTION;
        }
        else {turn = Turn.MOVEMENT;
        }
    }

    public void move(int x, int y) {
        switch (turn) {
            case MOVEMENT:
                if(isAvailable(x, y)) {
                    selectedX = x;
                    selectedY = y;
                }
                break;
            case SELECTION:
                if ((Math.abs(x - selectedX) == 2 && y == selectedY || Math.abs(y - selectedY) == 2 && x == selectedX) && isFree(x, y)
                        && isFilled((x + selectedX)/2,(y + selectedY)/2) && isFilled(selectedX, selectedY)) {
                    filled[(selectedX + x) / 2][(selectedY + y) / 2] = false;
                    filled[x][y] = true;
                    filled[selectedX][selectedY] = false;
                    numberOfPawns--;
                    selectedY = y;
                    selectedX = x;
                }
                turn = Turn.MOVEMENT;
                break;
        }
    }

    public void moveMouse(int y, int x){
        switch (turn) {
            case MOVEMENT:
                move(x, y);
                select();
                break;
            case SELECTION:
                move(x, y);
                break;
        }
    }
    /*public boolean possibleMove() {
        for (int i = 0; i < version.getMaxRow(); i++)
            for (int j = 0; j < version.getStartY(); j++) {
                System.out.println(i + " " + j);
                if (possibleMoveDown(i, j) || possibleMoveUp(i, j) || possibleMoveRight(i, j) || possibleMoveLeft(i, j)) {
                    return true;
                }
            }
        return false;
    }*/

    public boolean pMove(){
        for (int i = 0; i < version.getMaxRow(); i++){
            for (int j = 0; j < version.getMaxColumn(); j++){
                if(isAvailable(i,j)){
                    if (possibleMoveDown(i, j) || possibleMoveUp(i, j) || possibleMoveRight(i, j) || possibleMoveLeft(i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean possibleMoveDown(int x, int y) {
        String b = Boolean.toString(isFilled(x, y) && isFilled(x + 1, y) && isFree(x + 2, y));
        System.out.println("Down: " + b);
        return isFilled(x, y) && isFilled(x + 1, y) && isFree(x + 2, y);
    }
    private boolean possibleMoveUp(int x, int y) {
        String b = Boolean.toString(isFree(x, y) && isFilled(x + 1, y) && isFilled(x + 2, y));
        System.out.println("Up: " + b);
        return isFree(x, y) && isFilled(x + 1, y) && isFilled(x + 2, y);
    }
    private boolean possibleMoveLeft(int x, int y) {
        String b = Boolean.toString(isFree(x, y) && isFilled(x, y + 1) && isFilled(x, y + 2));
        System.out.println("Left: " + b);
        return isFree(x, y) && isFilled(x, y + 1) && isFilled(x, y + 2);
    }
    private boolean possibleMoveRight(int x, int y) {
        String b = Boolean.toString(isFilled(x, y) && isFilled(x, y + 1) && isFree(x, y + 2));
        System.out.println("Right: " + b);
        return isFilled(x, y) && isFilled(x, y + 1) && isFree(x, y + 2);
    }
    public int getNumberOfPawns() {return numberOfPawns;}
    public boolean isAvailable(int x, int y){
        return 0 <= x && x < version.getMaxRow() && 0 <= y && y < version.getMaxColumn() && available[x][y];
    }
    public boolean isFilled(int x, int y){
        return isAvailable(x, y) && filled[x][y];
    }
    private boolean isFree(int x, int y){
        return isAvailable(x, y) && !filled[x][y];
    }
    public int getSelectedX(){ return selectedX;}
    public int getSelectedY() {return selectedY;}
    public boolean isSelected() {return turn == Turn.SELECTION;}
}
