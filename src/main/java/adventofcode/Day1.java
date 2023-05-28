package adventofcode;

import adventofcode.day1classes.Elf;
import adventofcode.utils.InputLoader;
import adventofcode.utils.NewDay;
import colors.ConsoleColors;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author juan
 */
public class Day1 implements Day {
    private int[] items;
    private File file;

    @Override
    public void run() {
        file = new File("src/main/resources/inputfiles/inputD1");
        String inputLines[];
        
        NewDay.newDayText(1);
        InputLoader il = new InputLoader();
        
        try {
            inputLines = il.inputArrayInternalFile(file, true);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return;
        }
        
        items = new int[inputLines.length];
        
        for (int i = 0; i < inputLines.length; i++) {
            if (inputLines[i].equals("")) {
                items[i] = 0;
            } else {
                items[i] = Integer.parseInt(inputLines[i]);
            }
        }
        
        NewDay.partText(1);
        System.out.println("Most calories carried by an elf: " + 
                ConsoleColors.WHITE + calories() + ConsoleColors.RESET);
        
        NewDay.partText(2);
        System.out.println("Total calories carried by top 3 elves"
                + " with more calories: " + ConsoleColors.WHITE + part2() + ConsoleColors.RESET);
    }
    
    private int calories() {
        int highestCal = 0;
        int elf = 1;
        int highestCalElf = 1;
        int totalCal = 0;
        
        for (int i = 0; i < items.length; i++) {
            if (items[i] == 0 || i == items.length - 1) {
                if (elf == 1) {
                    highestCal = totalCal;
                } else {
                    if (totalCal > highestCal) {
                        highestCal = totalCal;
                        highestCalElf = elf;
                    }
                }
                
                elf++;
                totalCal = 0;
            } else {
                totalCal += items[i];
            }
            if (i == items.length - 2) {
                totalCal += items[i + 1];
            }
        }
        
        System.out.println("Elf carrying the most calories: " + ConsoleColors.WHITE + 
                highestCalElf + ConsoleColors.RESET);
        
        return highestCal;
    }
    
    private int part2() {
        ArrayList<Elf> elves = new ArrayList<>();
        int topThreeCalories;
        int totalCal = 0;
        int elfNumber = 0;
        int elfPosition;
        
        for (int i = 0; i < items.length; i++) {
            if (items[i] == 0) {
                elfNumber++;
                
                if (elfNumber == 1) {
                    elves.add(new Elf(totalCal, elfNumber));
                } else if (elfNumber <= 3) {
                    elfPosition = elfNumber - 1;
                    
                    for (int j = 0; j < elfNumber - 1; j++) {
                        if (totalCal > elves.get(j).getCalories()) {
                            elfPosition = j;
                        }
                    }
                    elves.add(elfPosition, new Elf(totalCal, elfNumber));
                } else {
                    for (int j = 0; j < 3; j++) {
                        if (elves.get(j) == null || totalCal > elves.get(j).getCalories()) {
                            elves.add(j, new Elf(totalCal, elfNumber));
                            elves.remove(3);
                            break;
                        }
                    }
                }
                
                totalCal = 0;
            } else {
                totalCal += items[i];
            }
        }
        
        System.out.println("Top three elves with the most calories: " + ConsoleColors.WHITE + 
                elves.get(0).getNumber() + ", " + elves.get(1).getNumber() + 
                ", " + elves.get(2).getNumber() + ConsoleColors.RESET);
        
        topThreeCalories = elves.get(0).getCalories() + 
                elves.get(1).getCalories() + elves.get(2).getCalories();
        
        return topThreeCalories;
    }
}