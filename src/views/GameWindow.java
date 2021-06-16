package views;

import listeners.MouseListener;
import models.Board;
import models.Samotnik;
import models.Parameters;
import util.GameVersion;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameWindow extends JFrame {
    private JFrame window;
    private final Board board;
    private final Panel panel;
    private final JLabel label;
    private static int interval;
    private static Timer timer;

    public GameWindow(Board board){
        super("Samotnik");
        this.board = board;
        panel = new Panel(board);
        label = new JLabel("Pozostały czas: ");
        settings();
        add();
        getTheTime(Parameters.time);
    }
    private void settings(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout());
        setLocation(400, 80);
        setVisible(true);
        getContentPane().setBackground(Samotnik.getInstance().getBackgroundColor());
    }
    private void add(){
        add(panel, BorderLayout.CENTER);
        add(label, BorderLayout.SOUTH);
        addMouseListener(new MouseListener(this, board));
    }
    public void update(){
        if(Samotnik.getInstance().end()){
            String message;
            if(board.getNumberOfPawns() == 1) {
                message = "GRATULACJE " + Parameters.nick + "! WYGRAŁEŚ!";
            }
            else message = "Koniec gry " + Parameters.nick +"! Pozostałe pionki: " + board.getNumberOfPawns();
            panel.repaint();
            cancelTimer(timer);
            JOptionPane.showMessageDialog(window, message, "KONIEC GRY!", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            dispose();
        }
        else{
            getContentPane().setBackground(Samotnik.getInstance().getBackgroundColor());
            panel.repaint();
        }
    }
    private void getTheTime(int time){
        timer = new Timer();
        int delay = 1000, period = 1000;
        interval = time * 60;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                label.setText("Pozostały czas: " + setInterval());
                if (interval == 0){
                    JOptionPane.showMessageDialog(window, "Koniec gry " + Parameters.nick +"! Czas minął! Pozostałe pionki: " + board.getNumberOfPawns(),
                            "KONIEC GRY!", JOptionPane.INFORMATION_MESSAGE);
                    cancelTimer(timer);
                    setVisible(false);
                    dispose();
                }
            }
        }, delay, period);
    }
    private void cancelTimer(Timer timer2){
        timer2.cancel();
    }
    private static final String setInterval(){
        String show = "";
        int minutes, seconds;
        if(interval >= 7200){
            show = "02:";
            minutes = (interval - 7200) / 60;
            seconds = interval - 7200 - minutes * 60;
        }
        else if(interval >= 3600) {
            show = "01:";
            minutes = (interval - 3600) / 60;
            seconds = interval - 3600 - minutes * 60;
        }
        else{
            show = "00:";
            minutes = interval / 60;
            seconds = interval - minutes * 60;
        }
        if (minutes<10) show+="0"+Integer.toString(minutes)+":";
        else show+=Integer.toString(minutes)+":";
        if (seconds<10) show+="0"+Integer.toString(seconds);
        else show+=Integer.toString(seconds);

        if(interval == 1) timer.cancel();
        --interval;
        return show;
    }
}
