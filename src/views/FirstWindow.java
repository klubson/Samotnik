package views;
import models.Image;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstWindow extends JFrame {
    private JFrame window;
    private JButton newGame, exit;
    private Image image;

    public void create() {
            window = new JFrame("Samotnik");
            settings();
            addComponents();
            window.setVisible(true);
        }

    private void settings() {
            window.setSize(400,400);
            window.setLocation(400, 80);
            window.setDefaultCloseOperation(EXIT_ON_CLOSE);
            window.setLayout(new BorderLayout());
        }
    private void components() {
           newGame = new JButton("Nowa gra");
           newGame.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   OptionWindow next = new OptionWindow(); // przechodzimy do okna opcji gry
                   window.setVisible(false);
                   window.dispose();
                   next.create();
               }
           });
           exit = new JButton("Wyjście z gry");
           exit.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   window.setVisible(false);
                   window.dispose();
               }
           });
           image = new Image("samotnik_tytuł.jpg");
       }
       private void addComponents() {
           components();
           window.add(image, BorderLayout.NORTH);
           window.add(newGame, BorderLayout.CENTER);
           window.add(exit, BorderLayout.SOUTH);
       }

}
