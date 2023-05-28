package adventofcode.utils;

import colors.ConsoleColors;

/**
 *
 * @author juan
 */
public class NewDay {
    public static void newDayText(int day) {
        System.out.printf("%n" + ConsoleColors.CYAN_BOLD + "Day %d" + ConsoleColors.RESET + "%n%n", day);
    }
    
    public static void partText(int part) {
        System.out.printf(ConsoleColors.GREEN + "%nPart %d" + ConsoleColors.RESET + "%n", part);
    }
}
