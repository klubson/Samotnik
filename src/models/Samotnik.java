package models;
import views.GameWindow;
import views.OptionWindow;
import util.GameVersion;

import java.awt.*;

public class Samotnik {
    private static Samotnik instance;
    private Color pawnColor, tmp, selectionColor, backgroundColor;
    private static OptionWindow settings;
    private GameVersion version, tmp2;
    private Board board;
    private GameWindow window;

    private Samotnik() {
        pawnColor = Parameters.pawnColor;
        selectionColor = Color.RED;
        backgroundColor = Color.ORANGE;
        version = Parameters.version;
    }
    public static Samotnik getInstance() {
        if (instance == null) {
            instance = new Samotnik();}
        return instance;
    }
    public void start() {
        board = new Board(version);
        window = new GameWindow(board);
    }
    public void newGame() {
        board.reset(version);
    }
    public Color getPawnColor(){ return pawnColor; }
    public Color getSelectionColor(){
        return selectionColor;
    }
    public Color getBackgroundColor() {
        return backgroundColor;
    }
    public GameVersion getVersion(){
        return version;
    }
    //public boolean end() { return !board.possibleMove(); }
    public boolean end() { return !board.pMove(); }
    public void update() { window.update(); }

}
