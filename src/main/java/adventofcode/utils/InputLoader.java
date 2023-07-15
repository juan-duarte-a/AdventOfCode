package adventofcode.utils;

import colors.ConsoleColors;

import java.io.*;

/**
 *
 * @author juan
 */
public class InputLoader {
    
    public String[] inputArrayExternalFile(File file, boolean verbose) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String[] lines;
        int countLines = 0;

        try {
            while (bufferedReader.readLine() != null) {
                countLines++;
            }

            bufferedReader.close();

            lines = new String[countLines];
            bufferedReader = new BufferedReader(new FileReader(file));

            for (int i = 0; i < countLines; i++) {
                lines[i] = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }

        if (verbose) {
            System.out.printf(ConsoleColors.BLACK_BOLD + "%d lines were read.%n" +
                    ConsoleColors.RESET, lines.length);
        }

        return lines;
    }

    public String[] inputArrayInternalFile(File file, boolean verbose) throws FileNotFoundException {
        String[] lines;
        int countLines = 0;
        BufferedReader bufferedReader;
        InputStream inputStream = 
                adventofcode.AdventOfCode.class.getResourceAsStream("/inputfiles/" + file.getName());

        if (inputStream != null) {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                while (bufferedReader.readLine() != null) {
                    countLines++;
                }
                
                bufferedReader.close();
                inputStream.close();
                
                lines = new String[countLines];
                inputStream = adventofcode.AdventOfCode.class.getResourceAsStream(
                        "/inputfiles/" + file.getName());

                assert inputStream != null;
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                
                for (int i = 0; i < countLines; i++) {
                    lines[i] = bufferedReader.readLine();
                } 

                bufferedReader.close();
                inputStream.close();
            } catch (IOException e) {
                System.err.println(e);
                return null;
            }
        } else {
            throw new FileNotFoundException("File " + file.getName() + " not found!");
        }

        if (verbose) {
            System.out.printf(ConsoleColors.BLACK_BOLD + "%d line(s) were read.%n" +
                    ConsoleColors.RESET, lines.length);
        }
        
        return lines;
    }
}
