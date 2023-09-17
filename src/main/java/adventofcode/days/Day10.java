package adventofcode.days;

import adventofcode.Day;
import adventofcode.utils.InputLoader;
import adventofcode.utils.NewDay;
import colors.ConsoleColors;

import java.io.File;
import java.io.FileNotFoundException;

public class Day10 implements Day {

    private String[] registerInstructions;

    @Override
    public void run() {
        InputLoader il = new InputLoader();
        File file = new File("src/main/resources/inputfiles/inputD10");

        NewDay.newDayText(10);

        try {
            registerInstructions = il.inputArrayInternalFile(file, true);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            return;
        }

        NewDay.partText(1);

        int[] cycles = {20, 60, 100, 140, 180, 220};

        System.out.println("Sum of the signal strengths of the 20th, 60th, 100th, 140th, 180th, and 220th cycles: " +
                ConsoleColors.WHITE + sumSignalStrengths(cycles) + ConsoleColors.RESET);
    }

    private int sumSignalStrengths(int[] cyclesToSum) {
        int x = 1, sum = 0, cycle = 0, cycleCheck = 0;

        for (String instruction : registerInstructions) {
            String[] line = instruction.split(" ");
            cycle++;

            boolean isAddxInstruction = line[0].equals("addx");

            if ((cycle == cyclesToSum[cycleCheck] - 1 && isAddxInstruction) || cycle == cyclesToSum[cycleCheck]) {
                if (isAddxInstruction && cycle == cyclesToSum[cycleCheck] - 1)
                    sum += (cycle + 1) * x;
                else
                    sum += cycle * x;

                if (cycleCheck == cyclesToSum.length - 1)
                    return sum;

                cycleCheck++;
            }

            if (isAddxInstruction) {
                x += Integer.parseInt(line[1]);
                cycle++;
            }
        }

        return sum;
    }

}
