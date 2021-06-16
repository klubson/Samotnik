package listeners;

import models.Board;
import models.Samotnik;
import views.GameWindow;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
    private final Samotnik samotnik;
    private final GameWindow window;
    private final Board board;

    public MouseListener(GameWindow window, Board board){
        this.board = board;
        this.window = window;
        this.samotnik = Samotnik.getInstance();
    }

    @Override
    public void mouseClicked(MouseEvent event){
        if(event.getButton() == MouseEvent.BUTTON1){
            Point position = toArray(event.getPoint());
            board.moveMouse(position.x, position.y);
            samotnik.update();
        }
    }
    private Point toArray(Point last){
        int x = window.getWidth() / 9;
        int y = window.getHeight() / 9;
        return new Point((last.x / x) - 1, (last.y / y) - 1);
    }
}
