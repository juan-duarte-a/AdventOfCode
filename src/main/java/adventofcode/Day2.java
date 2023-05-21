package adventofcode;

import colors.ConsoleColors;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author juan
 */
public class Day2 implements Day {
    private static final char ROCK = 'X';
    private static final char PAPER = 'Y';
    private static final char SCISSORS = 'Z';
    private static final char OPP_ROCK = 'A';
    private static final char OPP_PAPER = 'B';
    private static final char OPP_SCISSORS = 'C';
    private static final char LOSE = 'X';
    private static final char DRAW = 'Y';
    private static final char WIN = 'Z';
    
    private String[] strategy;

    @Override
    public final void run() {
        char opponentPlay;
        char onePlay;
        int points = 0;
        
        System.out.printf("%n" + ConsoleColors.CYAN_BOLD + "Day 2" + ConsoleColors.RESET + "%n");
        loadInput(false);

        for (String play : strategy) {
            opponentPlay = play.charAt(0);
            onePlay = play.charAt(2);
            points += playResult(opponentPlay, onePlay);
        }
        
        System.out.printf(ConsoleColors.GREEN + "%nPart 1" + ConsoleColors.RESET + "%n");
        System.out.println("Total points obtained: " + ConsoleColors.WHITE + points + ConsoleColors.RESET);
        
        part2();
    }
    
    public void part2() {
        char opponentPlay;
        char onePlay;
        int points = 0;
        
        for (String play : strategy) {
            opponentPlay = play.charAt(0);
            onePlay = play.charAt(2);
            points += playResult2(opponentPlay, onePlay);
        }
        
        System.out.printf(ConsoleColors.GREEN + "%nPart 2" + ConsoleColors.RESET + "%n");
        System.out.println("Total points obtained: " + ConsoleColors.WHITE + points + ConsoleColors.RESET);
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
    
    private short playResult2(char opponentPlay, char onePlay) {
        switch (onePlay) {
            case LOSE -> {
                switch (opponentPlay) {
                    case OPP_ROCK -> onePlay = SCISSORS;
                    case OPP_PAPER -> onePlay = ROCK;
                    case OPP_SCISSORS -> onePlay = PAPER;
                }
            }
            case DRAW -> {
                switch (opponentPlay) {
                    case OPP_ROCK -> onePlay = ROCK;
                    case OPP_PAPER -> onePlay = PAPER;
                    case OPP_SCISSORS -> onePlay = SCISSORS;
                }
            }
            case WIN -> {
                switch (opponentPlay) {
                    case OPP_ROCK -> onePlay = PAPER;
                    case OPP_PAPER -> onePlay = SCISSORS;
                    case OPP_SCISSORS -> onePlay = ROCK;
                }
            }
        }
        
        return playResult(opponentPlay, onePlay);
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
    
    private void loadInput(boolean verbose) {
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
        
        if (verbose) {
            System.out.println("");
            System.out.printf(ConsoleColors.BLACK_BOLD + "%d lines were read.%n" + 
                    ConsoleColors.RESET, strategy.length);
        }
    }
}
