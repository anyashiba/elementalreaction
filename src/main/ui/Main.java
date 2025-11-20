package ui;

import javax.swing.*;

import model.EventLog;
import model.Event;
import model.TeamComp;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// Main class to run entire program
public class Main extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private StartGameGUI startGame;

    public Main(TeamComp userTeam) {
        super("Genshin Mini Fighting Game");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString() + "\n\n");
                }
                System.exit(0);
            }
        });

        JLabel statusLabel = new JLabel("Welcome!");
        add(statusLabel, BorderLayout.NORTH);

        startGame = new StartGameGUI(userTeam);
        
        add(startGame);

        setVisible(true);
    }

    //Creates a new instance of user's team and the console and gui game
    public static void main(String[] args) {
        TeamComp userTeam = new TeamComp();

        Thread startGameGUI = new Thread(() -> new Main(userTeam));
        Thread startGame = new Thread(() -> new StartGame(userTeam));

        startGameGUI.start();
        startGame.start();

    }

}
