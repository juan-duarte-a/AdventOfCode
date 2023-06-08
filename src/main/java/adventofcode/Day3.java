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
        final File file = new File("src/main/resources/inputfiles/inputD3");
        InputLoader il = new InputLoader();
        
        NewDay.newDayText(3);
        
        try {
            rucksacks = il.inputArrayInternalFile(file, true);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return;
        }
        
        NewDay.partText(1);
        System.out.println("Sum of the priority of repeated items: " + 
                ConsoleColors.WHITE + priorityRepeatedItems() + ConsoleColors.RESET);
        
        NewDay.partText(2);
        System.out.println("Sum of the priority of badges: " +
                ConsoleColors.WHITE + priorityBadges() + ConsoleColors.RESET);
    }
    
    private int priorityRepeatedItems() {
        int itemQuantity;
        StringBuilder repeatedItems = new StringBuilder("");
        
        for (String rucksack : rucksacks) {
            itemQuantity = rucksack.length();

            foundInAllRucksacks:
            for (int i = 0; i < itemQuantity / 2; i++) {
                char item1 = rucksack.charAt(i);
                
                for (int j = itemQuantity / 2; j < itemQuantity; j++) {
                    char item2 = rucksack.charAt(j);
                    if (item1 == item2) {
                        repeatedItems.append(item2);
                        break foundInAllRucksacks;
                    }
                }
            }
        }
        
        return priorityItems(repeatedItems);
    }
    
    private int priorityBadges() {
        StringBuilder badges = new StringBuilder("");
        boolean foundInAllRucksacks;
        
        for (int i = 0; i < rucksacks.length; i += 3) {
            String rucksack1 = rucksacks[i];
            String rucksack2 = rucksacks[i + 1];
            String rucksack3 = rucksacks[i + 2];
            foundInAllRucksacks = false;
            
            for (int j = 0; j < rucksack1.length(); j++) {
                char item1 = rucksack1.charAt(j);
                
                for (int k = 0; k < rucksack2.length(); k++) {
                    char item2 = rucksack2.charAt(k);
                    
                    if (item1 == item2) {
                        for (int l = 0; l < rucksack3.length(); l++) {
                            char item3 = rucksack3.charAt(l);
                            
                            if (item2 == item3) {
                                badges.append(item3);
                                foundInAllRucksacks = true;
                                break;
                            }
                        }
                        
                        break;
                    }
                }

                if (foundInAllRucksacks) {
                    break;
                }
            }
        }
        
        return priorityItems(badges);
    }
    
    private int priorityItems(StringBuilder items) {
        int priority = 0;
        
        for (int i = 0; i < items.length(); i++) {
            char item = items.charAt(i);
            
            if (Character.isLowerCase(item)) {
                priority += (int) item - 96;
            } else {
                priority += (int) item - 65 + 27;
            }
        }
            
        return priority;
    }
    
}
