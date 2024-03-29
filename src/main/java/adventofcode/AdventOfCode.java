package adventofcode;

import adventofcode.days.*;
import colors.ConsoleColors;
import java.util.Scanner;

/**
 * Solved puzzles of the Advent Of Code 2022 challenge.
 * 
 * @author juan
 */
public class AdventOfCode {

    public static void main(String[] args) {
        // Do not forget to update this!
        int totalDaysSolved = 10;
        
        try (Scanner sc = new Scanner(System.in)) {
            String option;
            
            Day[] days = new Day[totalDaysSolved];
            
            days[0] = new Day1();
            days[1] = new Day2();
            days[2] = new Day3();
            days[3] = new Day4();
            days[4] = new Day5();
            days[5] = new Day6();
            days[6] = new Day7();
            days[7] = new Day8();
            days[8] = new Day9();
            days[9] = new Day10();

            do {
                System.out.printf("%n" + ConsoleColors.PURPLE + "Advent Of Code" + ConsoleColors.RESET);
                System.out.printf("%n%nSelect the day (1 to %d) or 'X' to exit.%n-> ", totalDaysSolved);
                
                option = sc.nextLine().toUpperCase();
                
                if (!option.equals("X")) {
                    try {
                        days[Integer.parseInt(option) - 1].run();
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.out.println(ConsoleColors.YELLOW + "Wrong option! Try again." + ConsoleColors.RESET);
                    }
                }
            } while (!option.equals("X"));
        }
    }
}
