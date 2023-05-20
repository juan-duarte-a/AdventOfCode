package adventofcode;

import adventofcode.day1classes.Elf;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author juan
 */
public class Day1 {
    private Scanner sc;
    private int[] items;
    private File file;

    public void run() {
        file = new File("inputfiles/inputD1");
        String line;
        int cont = 0;

        sc = newScanner(file);
        
        while (sc.hasNextLine()) {
            sc.nextLine();
            cont++;
        }
        
        items = new int[cont];
        
        sc = newScanner(file);

        for (int i = 0; i < cont; i++) {
            line = sc.nextLine();
            
            if (line.equals("")) {
                items[i] = 0;
            } else {
                items[i] = Integer.parseInt(line);
            }
        }
        
        System.out.println("Part 1:");
        System.out.println("Most calories carried by an elf: " + calories());
        System.out.println("\nPart 2:");
        System.out.println("Total calories carried by top 3 elves"
                + " with more calories: " + caloriesPart2());
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
        
        System.out.println("Elf carrying the most calories: " + highestCalElf);
        
        return highestCal;
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
    
    private int caloriesPart2() {
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
        
        System.out.println("Top three elves with the most calories: " + 
                elves.get(0).getNumber() + ", " + elves.get(1).getNumber() + 
                ", " + elves.get(2).getNumber());
        
        topThreeCalories = elves.get(0).getCalories() + 
                elves.get(1).getCalories() + elves.get(2).getCalories();
        
        return topThreeCalories;
    }
}