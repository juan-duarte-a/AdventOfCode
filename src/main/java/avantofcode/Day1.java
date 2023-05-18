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
        String line;
        int cont = 0;
        
        sc = newScanner(sc, file);
        
        while (sc.hasNextLine()) {
            sc.nextLine();
            cont++;
        }
        
        items = new int[cont];
        
        sc = newScanner(sc, file);

        for (int i = 0; i < cont; i++) {
            line = sc.nextLine();
            
            if (line.equals("")) {
                items[i] = 0;
            } else {
                items[i] = Integer.parseInt(line);
            }
        }
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
    
    public Scanner newScanner(Scanner scanner, File file) {
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        
        return scanner;
    }
}