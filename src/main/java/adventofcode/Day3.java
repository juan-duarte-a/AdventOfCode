package adventofcode;

import adventofcode.utils.InputLoader;
import adventofcode.utils.NewDay;
import colors.ConsoleColors;
import java.io.File;
import java.io.FileNotFoundException;

public class Day3 implements Day {
    
    private String[] rucksacks;

    @Override
    public void run() {
        InputLoader il = new InputLoader();
        File file = new File("inputfiles/inputD3");
        
        NewDay.newDayText(3);
        
        try {
            rucksacks = il.inputArray(file, true);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return;
        }
        
        NewDay.partText(1);
        System.out.println("Sum of the priority of repeated items: " + 
                ConsoleColors.WHITE + priorityRepeatedItems() + ConsoleColors.RESET);
    }
    
    private int priorityRepeatedItems() {
        int priority = 0;
        int itemQuantity;
        boolean found;
        StringBuilder repeatedItems = new StringBuilder("");
        
        for (String rucksack : rucksacks) {
            found = false;
            itemQuantity = rucksack.length();
            
            for (int i = 0; i < itemQuantity / 2; i++) {
                char item1 = rucksack.charAt(i);
                
                for (int j = itemQuantity / 2; j < itemQuantity; j++) {
                    char item2 = rucksack.charAt(j);
                    if (item1 == item2) {
                        repeatedItems.append(item2);
                        found = true;
                        break;
                    }
                }

                if (found) {
                    break;
                }
            }
        }
        
        for (int i = 0; i < repeatedItems.length(); i++) {
            char item = repeatedItems.charAt(i);
            
            if (Character.isLowerCase(item)) {
                priority += (int) item - 96;
            } else {
                priority += (int) item - 65 + 27;
            }
        }
        return priority;
    }
    
}
