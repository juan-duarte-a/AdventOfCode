package adventofcode;

import adventofcode.utils.InputLoader;
import adventofcode.utils.NewDay;
import colors.ConsoleColors;
import java.io.File;
import java.io.FileNotFoundException;

public class Day6 implements Day {
    
    private String[] code;

    @Override
    public void run() {
        InputLoader il = new InputLoader();
        File file = new File("src/main/resources/inputfiles/inputD6");
        
        NewDay.newDayText(6);
        
        try {
            code = il.inputArrayInternalFile(file, true);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            return;
        }
        
        NewDay.partText(1);
        System.out.println("Characters need to be processed before the first start-of-packet marker: " + 
                ConsoleColors.WHITE + countCharactersProcessed() + ConsoleColors.RESET);

        NewDay.partText(2);
        System.out.println("Characters need to be processed before the first start-of-message marker: " + 
                ConsoleColors.WHITE + countCharactersProcessed2(14) + ConsoleColors.RESET);
    }
    
    private int countCharactersProcessed() {
        int characters = 3;
        boolean found = false;
        
        for (; characters < code[0].length(); characters++) {
            char c1 = code[0].charAt(characters - 3);
            char c2 = code[0].charAt(characters - 2);
            char c3 = code[0].charAt(characters - 1);
            char c4 = code[0].charAt(characters - 0);
            
            if ((c1 != c2 && c1 != c3 && c1 != c4) 
                    && (c2 != c3 && c2 != c4)
                    && (c3 != c4)) {
                found = true;
                break;
            }
        }
        
        return found ? characters + 1 : -1;
    }
    
    private int countCharactersProcessed2(int length) {
        int characters = 0;
        boolean found = false;
        
        searchstart:
        for (; characters < code[0].length() - length; characters++) {
            subsearch:
            for (int i = characters; i < characters + length - 1; i++) {
                char c1 = code[0].charAt(i);
                
                for (int j = i + 1; j < characters + length; j++) {
                    char c2 = code[0].charAt(j);
                    
                    if (c1 == c2) {
                        break subsearch;
                    }
                }
                
                if (i == characters + length - 2) {
                    found = true;
                    break searchstart;
                }
            }
        }
        
        return found ? characters + length : -1;
    }
    
}
