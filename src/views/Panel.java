package views;

import models.Board;
import models.Samotnik;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Panel extends JPanel{ //stan graficzny gry, rysowanie pionków
    private final Samotnik samotnik = Samotnik.getInstance();
    private final Board board;

    public Panel(Board board){ this.board = board;}

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int Width = getWidth() / 9;
        int Height = getHeight() / 9;
        int y = board.getSelectedY();
        int x = board.getSelectedX();

        Color color;
        color = samotnik.getPawnColor();
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 7; j++){
                Ellipse2D ellipse = new Ellipse2D.Double((j + 1) * Width, (i + 1) * Height, Width, Height);
                if(board.isFilled(i, j)){
                    if (x == i && y == j){
                        if(board.isSelected()){
                            g2d.setPaint(samotnik.getSelectionColor());
                        }
                        g2d.fill(ellipse);
                        }
                    else {
                        g2d.setPaint(color);
                        g2d.fill(ellipse);
                    }

                }
                if (board.isAvailable(i, j)) {
                    g2d.setPaint(Color.BLACK);
                    g2d.draw(ellipse);
                }
            }
        }
        this.setBackground(samotnik.getBackgroundColor());
    }
}
