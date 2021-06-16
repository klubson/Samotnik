package views;
import models.Samotnik;
import models.Parameters;
import util.GameVersion;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionWindow extends JFrame {
    private JFrame window;
    private JCheckBox classic, modern, white, blue, green;
    private Label setGameVer, setPawnColor, setTime, getTime, setNick;
    private JTextField nick;
    private JSlider timeSlider;
    private JButton start, back;
    private JPanel buttons, name, rest;
        // 0 - biały, 1 - niebieski, 2 - zielony
    // 0 - współczesna(33), 1 - klasyczna(37)

    public void create() {
        window = new JFrame("Samotnik");
        settings();
        addComponents();
        window.setVisible(true);
    }
    private void settings() {
        window.setSize(600 ,350);
        window.setLocation(400, 80);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
    }
    private void buttons()
    {
        start = new JButton("START");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                window.dispose();
                Parameters.nick = nick.getText();
                Samotnik.getInstance().start(); // start gry
            }
        });
        back = new JButton("POWRÓT");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FirstWindow goBack = new FirstWindow(); // powrót do menu głównego
                window.setVisible(false);
                window.dispose();
                goBack.create();
            }
        });
    }
    private void labels() {
        setGameVer = new Label("Wybierz typ planszy (domyślnie współczesny): ");
        setNick = new Label("Podaj nazwę gracza: ");
        setPawnColor = new Label("Wybierz kolor pionków (domyślnie białe): ");
        setTime = new Label ("Ustaw czas rozgrywki (domyślnie 20 minut): ");
        getTime = new Label ("Ustawiony czas (min): " + Integer.toString(Parameters.time));
    }
    private void boxes() {
        classic = new JCheckBox("Klasyczna (37 pól)");
        classic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            classic.setSelected(true);
            modern.setSelected(false);
            Parameters.version = GameVersion.CLASSIC;
            }
        });
        modern = new JCheckBox("Współczesna (33 pola)");
        modern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classic.setSelected(false);
                modern.setSelected(true);
                Parameters.version = GameVersion.MODERN;
            }
        });
        white = new JCheckBox("Białe");
        white.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                white.setSelected(true);
                blue.setSelected(false);
                green.setSelected(false);
                Parameters.pawnColor = Color.WHITE;
            }
        });
        blue = new JCheckBox("Niebieskie");
        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                white.setSelected(false);
                blue.setSelected(true);
                green.setSelected(false);
                Parameters.pawnColor = Color.BLUE;
            }
        });
        green = new JCheckBox("Zielone");
        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                white.setSelected(false);
                blue.setSelected(false);
                green.setSelected(true);
                Parameters.pawnColor = Color.GREEN;
            }
        });

    }
    private void rest() {
        nick = new JTextField("Gracz 1");
        nick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(nick.getText());
                //Parameters.nick = nick.getText();
                //System.out.println(Parameters.nick);
            }
        });
        timeSlider = new JSlider(1,120,20);
        timeSlider.setMinorTickSpacing(5);
        timeSlider.setMajorTickSpacing(10);
        timeSlider.setPaintTicks(true);
        timeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                timeSlider = (JSlider) e.getSource();
                Parameters.time = timeSlider.getValue();
                getTime.setText("Ustawiony czas (min): " + Integer.toString(timeSlider.getValue()));
            }
        });
    }
    private void panels() {
        buttons();
        labels();
        boxes();
        rest();
        name = new JPanel();
        name.add(setNick);
        name.add(nick);
        rest = new JPanel();
        rest.setLayout(new BoxLayout(rest, BoxLayout.PAGE_AXIS));
        rest.add(setGameVer);
        rest.add(classic);
        rest.add(modern);
        rest.add(setPawnColor);
        rest.add(white);
        rest.add(blue);
        rest.add(green);
        rest.add(setTime);
        rest.add(timeSlider);
        rest.add(getTime);
        buttons = new JPanel();
        buttons.add(back);
        buttons.add(start);
    }

    private void addComponents() {
        panels();
        window.add(name, BorderLayout.NORTH);
        window.add(rest, BorderLayout.CENTER);
        window.add(buttons, BorderLayout.SOUTH);
    }
   }
