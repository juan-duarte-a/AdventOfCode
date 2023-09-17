package adventofcode.days;

import adventofcode.Day;
import adventofcode.day9classes.Space;
import adventofcode.utils.InputLoader;
import adventofcode.utils.NewDay;
import colors.ConsoleColors;

import java.io.File;
import java.io.FileNotFoundException;

public class Day9 implements Day {

    private String[] motions;
    private Space space;

    @Override
    public void run() {
        InputLoader il = new InputLoader();
        File file = new File("src/main/resources/inputfiles/inputD9");

        NewDay.newDayText(9);

        try {
            motions = il.inputArrayInternalFile(file, true);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            return;
        }

        NewDay.partText(1);

        space = new Space(2);
        move();

        System.out.println("Number of positions that the tail visited at least once: " +
                ConsoleColors.WHITE + space.getPositionsVisitedByTail() + ConsoleColors.RESET);
        System.out.println("Number of positions that the head visited at least once: " +
                ConsoleColors.WHITE + space.getPositionsVisitedByHead() + ConsoleColors.RESET);

        NewDay.partText(2);

        space = new Space(10);
        move();

        System.out.println("Number of positions that the tail visited at least once: " +
                ConsoleColors.WHITE + space.getPositionsVisitedByTail() + ConsoleColors.RESET);
        System.out.println("Number of positions that the head visited at least once: " +
                ConsoleColors.WHITE + space.getPositionsVisitedByHead() + ConsoleColors.RESET);
    }

    private void move() {
        for (String line : motions) {
            String[] movement = line.split(" ");
            String direction = movement[0];
            int times = Integer.parseInt(movement[1]);

            space.move(Space.Direction.valueOf(direction), times);
        }
    }

}
