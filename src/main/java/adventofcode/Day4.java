package adventofcode;

import adventofcode.utils.InputLoader;
import adventofcode.utils.NewDay;
import colors.ConsoleColors;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.function.Function;
import java.util.function.Predicate;

public class Day4 implements Day {

    private String[] assignedSections;
    
    @Override
    public void run() {
        InputLoader il = new InputLoader();
        File file = new File("src/main/resources/inputfiles/inputD4");
        
        NewDay.newDayText(4);
        
        try {
            assignedSections = il.inputArrayInternalFile(file, true);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return;
        }
        
        NewDay.partText(1);
        System.out.println("Number of fully contained sections: " + 
                ConsoleColors.WHITE + countFullyContainedSections() + ConsoleColors.RESET);
        
        NewDay.partText(2);
        System.out.println("Number of overlapping sections: " + 
                ConsoleColors.WHITE + countOverlappingSections() + ConsoleColors.RESET);
    }
    
    private int countSections(Function<String, Integer[]> separateSections, 
            Predicate<Integer[]> fullyContained) {
        int sectionsCount = 0;
        
        for (String assignedSection : assignedSections) {
            Integer[] pairSections = separateSections.apply(assignedSection);
            
            if (fullyContained.test(pairSections)) {
                sectionsCount++;
            }
        }
        
        return sectionsCount;
    }
    
    private int countFullyContainedSections() {
        Predicate<Integer[]> fullyContained = sections
                -> (sections[0] <= sections[2] && sections[1] >= sections[3])
                || (sections[2] <= sections[0] && sections[3] >= sections[1]);

        return countSections(separateSections(), fullyContained);
    }
    
    private Function<String, Integer[]> separateSections() {
        Function<String, Integer[]> separate = codedSections -> {
            Integer[] sections = new Integer[4];
            String[] pairs = codedSections.split(",");
            String[] pairOneSections = pairs[0].split("-");
            String[] pairTwoSections = pairs[1].split("-");
            sections[0] = Integer.valueOf(pairOneSections[0]);
            sections[1] = Integer.valueOf(pairOneSections[1]);
            sections[2] = Integer.valueOf(pairTwoSections[0]);
            sections[3] = Integer.valueOf(pairTwoSections[1]);
            return sections;
        };
        
        return separate;
    }
    
    private int countOverlappingSections() {
        Predicate<Integer[]> overlappingSections = sections
                -> ((sections[0] >= sections[2] && sections[0] <= sections[3])
                || (sections[1] >= sections[2] && sections[1] <= sections[3]))
                || ((sections[2] >= sections[0] && sections[2] <= sections[1])
                || (sections[3] >= sections[0] && sections[3] <= sections[1]));

        return countSections(separateSections(), overlappingSections);
    }
    
}
