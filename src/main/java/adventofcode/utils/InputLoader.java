package adventofcode.utils;

import colors.ConsoleColors;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author juan
 */
public class InputLoader {
    
    public String[] inputArrayExternalFile(File file, boolean verbose) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String[] lines;
        int countLines = 0;
        
        while (sc.hasNext()) {
            sc.nextLine();
            countLines++;
        }
        
        sc.close();
        
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

    public String[] inputArrayInternalFile(File file, boolean verbose) throws FileNotFoundException {
        String[] lines = null;
        int countLines = 0;
        BufferedReader bufferedReader;
        InputStream inputStream = 
                adventofcode.AdventOfCode.class.getResourceAsStream("/inputfiles/" + file.getName());
        
        try {
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                while (bufferedReader.readLine() != null) {
                    countLines++;
                }
                
                lines = new String[countLines];
                inputStream = adventofcode.AdventOfCode.class.getResourceAsStream("/inputfiles/" + file.getName());
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                
                for (int i = 0; i < countLines; i++) {
                    lines[i] = bufferedReader.readLine();
                } 
            } else {
                throw new FileNotFoundException("File " + file.getName() + " not found!");
            }
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }
        
        if (verbose) {
            assert lines != null;
            System.out.printf(ConsoleColors.BLACK_BOLD + "%d line(s) were read.%n" + 
                    ConsoleColors.RESET, lines.length);
        }
        
        return lines;
    }
}
