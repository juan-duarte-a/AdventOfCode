package avantofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author juan
 */
public class Day1 {
    private Scanner sc;
    
    private int[] items;

    private File file;

    public Day1() {
    }

    public void run() {
        file = new File("input");
        int cont = 1;
        
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        
        while (sc.hasNextLine()) {
            sc.nextLine();
            cont++;
        }
        
        System.out.println("Lines: " + cont);
        
        System.out.println("Most calories carried by an elf: " + calories());
    }
    
    public int calories() {
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
}