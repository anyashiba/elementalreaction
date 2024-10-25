package ui;

import java.io.FileNotFoundException;

// Main class to run entire program
public class Main {
    public static void main(String[] args) {
        try {
            new StartGame();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
