package adventofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author juan
 */
public class Day2 {
    private static final char ROCK = 'X';
    private static final char PAPER = 'Y';
    private static final char SCISSORS = 'Z';
    private static final char OPP_ROCK = 'A';
    private static final char OPP_PAPER = 'B';
    private static final char OPP_SCISSORS = 'C';
    
    private String[] strategy;

    public Day2() {
        loadInput();
    }
    
    public final void run() {
        char opponentPlay;
        char onePlay;
        int points = 0;
        
        for (String play : strategy) {
            opponentPlay = play.charAt(0);
            onePlay = play.charAt(2);
            points += playResult(opponentPlay, onePlay);
        }
        
        System.out.println("Total points: " + points);
    }
    
    private short playResult(char opponentPlay, char onePlay) {
        short points;
        
        if (losePlay(opponentPlay, onePlay)) {
            points = 0;
        } else if (samePlay(opponentPlay, onePlay)) {
            points = 3;
        } else {
            points = 6;
        }
        
        switch (onePlay) {
            case ROCK -> points += 1;
            case PAPER -> points += 2;
            case SCISSORS -> points += 3;
        }
        
        return points;
    }
    
    private boolean losePlay(char opponentPlay, char onePlay) {
        return opponentPlay == OPP_ROCK && onePlay == SCISSORS || 
                opponentPlay == OPP_PAPER && onePlay == ROCK || 
                opponentPlay == OPP_SCISSORS && onePlay == PAPER;
    }
    
    private boolean samePlay(char opponentPlay, char onePlay) {
        return opponentPlay == OPP_ROCK && onePlay == ROCK || 
                opponentPlay == OPP_PAPER && onePlay == PAPER ||
                opponentPlay == OPP_SCISSORS && onePlay == SCISSORS;
    }
    
    private Scanner newScanner(File file) {
        Scanner scanner = null;
        
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        
        return scanner;
    }
    
    private void loadInput() {
        File file = new File("inputfiles/inputD2");
        Scanner sc = newScanner(file);
        int lines = 0;
        
        while (sc.hasNext()) {
            sc.nextLine();
            lines++;
        }
        
        strategy = new String[lines];
        sc = newScanner(file);
        
        for (int i = 0; i < lines; i++) {
            strategy[i] = sc.nextLine();
        }
        
        System.out.printf("%n%d lines were read.%n", strategy.length);
    }
}
