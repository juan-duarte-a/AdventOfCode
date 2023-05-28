package adventofcode.utils;

import colors.ConsoleColors;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author juan
 */
public class InputLoader {
    
    public String[] inputArray(File file, boolean verbose) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String[] lines;
        int countLines = 0;
        
        while (sc.hasNext()) {
            sc.nextLine();
            countLines++;
        }
        
        lines = new String[countLines];
        sc = new Scanner(file);
        
        for (int i = 0; i < countLines; i++) {
            lines[i] = sc.nextLine();
        }
        
        sc.close();
        
        if (verbose) {
            System.out.printf(ConsoleColors.BLACK_BOLD + "%d lines were read.%n" + 
                    ConsoleColors.RESET, lines.length);
        }
        
        return lines;
    }
}
