package adventofcode;

import adventofcode.utils.InputLoader;
import adventofcode.utils.NewDay;
import colors.ConsoleColors;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Day5 implements Day {

    private String[] rearrangementProcedure;
    private List<Stack<Character>> stacks;
    private List<Short[]> rearrangement;
    private short numberOfStacks;
    private short initialHigherStack;

    @Override
    public void run() {
        stacks = new ArrayList<>();
        rearrangement = new ArrayList<>();
        InputLoader il = new InputLoader();
        File file = new File("src/main/resources/inputfiles/inputD5");
        
        NewDay.newDayText(5);
        
        try {
            rearrangementProcedure = il.inputArrayInternalFile(file, true);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            return;
        }
        
        numberOfStacks = countNumberOfStacks();
        
        initializeStacks();
        decodeInitialArrangement();
        decodeRearrangement(initialHigherStack + 2);
        rearrange();
        
        NewDay.partText(1);
        System.out.println("Crates in top of each stack: " + 
                ConsoleColors.WHITE + getCratesOnTop() + ConsoleColors.RESET);
        
        initializeStacks();
        decodeInitialArrangement();
        rearrangeWithCrateMover9001();
        
        NewDay.partText(2);
        System.out.println("Crates in top of each stack: " + 
                ConsoleColors.WHITE + getCratesOnTop() + ConsoleColors.RESET);
        
    }
    
    private short countNumberOfStacks() {
        short number = 0;
        short higherStack = 0;
        
        while (!rearrangementProcedure[higherStack].startsWith(" ")) {
            higherStack++;
        } 
        
        for (int i = 1; i < rearrangementProcedure[higherStack - 1].length(); i += 4) {
            number++;
        }
        
        initialHigherStack = higherStack;
        
        return number;
    }
    
    private void initializeStacks() {
        stacks.clear();
        
        for (int i = 0; i < numberOfStacks; i++) {
            stacks.add(new Stack<>());
        }
    }
    
    private void decodeInitialArrangement () {
        for (int i = initialHigherStack - 1; i >= 0; i--) {
            int stack = 0;
            
            for (int j = 1; j < 2 + (8 * 4); j += 4) {
                char crate = rearrangementProcedure[i].charAt((j));
                
                if (crate != ' ') {
                    stacks.get(stack).add(crate);
                }
                
                stack++;
            }
        }
    }
    
    private void decodeRearrangement(int initialLine) {
        short endPosition;
        
        for (int i = initialLine; i < rearrangementProcedure.length; i++) {
            Short[] move = new Short[3];
            String moveLine = rearrangementProcedure[i];
            endPosition = (short) (moveLine.length() == 19 ? 1 : 0);
            
            move[0] = Short.valueOf(moveLine.substring(5, 6 + endPosition));
            move[1] = Short.valueOf(moveLine.substring(12 + endPosition, 13 + endPosition));
            move[2] = Short.valueOf(moveLine.substring(17 + endPosition, 18 + endPosition));
            
            rearrangement.add(move);
        }
    }
    
    private void rearrange() {
        for (Short[] move : rearrangement) {
            for (int i = 0; i < move[0]; i++) {
                stacks.get(move[2] - 1).add(stacks.get(move[1] - 1).pop());
            }
        }
    }
    
    private String getCratesOnTop() {
        String cratesOnTop = "";
        
        for (int i = 0; i < numberOfStacks; i++) {
            cratesOnTop += stacks.get(i).peek();
        }
        
        return cratesOnTop;
    }
    
    private void rearrangeWithCrateMover9001() {
        Stack<Character> temporaryStack = new Stack<>();
        
        for (Short[] move : rearrangement) {
            for (int i = 0; i < move[0]; i++) {
                temporaryStack.add(stacks.get(move[1] - 1).pop());
            }
    
            for (int i = 0; i < move[0]; i++) {
                stacks.get(move[2] - 1).add(temporaryStack.pop());
            }
        }
    }
    
}
