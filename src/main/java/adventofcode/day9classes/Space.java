package adventofcode.day9classes;

import java.util.HashSet;
import java.util.Set;

public class Space {
    private Position headPosition;
    private Position tailPosition;
    private final Set<Position> positionsVisitedByHead;
    private final Set<Position> positionsVisitedByTail;

    public enum Direction {L, R, U, D}

    public Space() {
        positionsVisitedByHead = new HashSet<>();
        positionsVisitedByTail = new HashSet<>();
        headPosition = new Position(0, 0);
        tailPosition = headPosition;
        positionsVisitedByHead.add(headPosition);
        positionsVisitedByTail.add(tailPosition);
    }

    public void move(Direction direction, int times) {
        for (int i = 0; i < times; i++) {
            switch (direction) {
                case L -> left();
                case R -> right();
                case U -> up();
                case D -> down();
            }

            positionsVisitedByHead.add(headPosition);

            if (tailMustMove()) {
                moveTail();
                positionsVisitedByTail.add(tailPosition);
            }
        }
    }

    private void left() {
        headPosition = new Position(headPosition.x() - 1, headPosition.y());
    }

    private void right() {
        headPosition = new Position(headPosition.x() + 1, headPosition.y());
    }

    private void up() {
        headPosition = new Position(headPosition.x(), headPosition.y() + 1);
    }

    private void down() {
        headPosition = new Position(headPosition.x(), headPosition.y() - 1);
    }

    private boolean tailMustMove() {
        return Math.abs(tailPosition.x() - headPosition.x()) > 1
                || Math.abs(tailPosition.y() - headPosition.y()) > 1;
    }

    private void moveTail() {
        if (headPosition.x() != tailPosition.x() && headPosition.y() != tailPosition.y()) {
            int x = headPosition.x() - tailPosition.x();
            int y = headPosition.y() - tailPosition.y();
            tailPosition = new Position(tailPosition.x() + x / Math.abs(x),
                    tailPosition.y() + y / Math.abs(y));
        } else {
            tailPosition = new Position(
                    tailPosition.x() + (headPosition.x() - tailPosition.x())
                            - (headPosition.x() == tailPosition.x() ? 0 : 1)
                            * (headPosition.x() < tailPosition.x() ? -1 : 1),
                    tailPosition.y() + (headPosition.y() - tailPosition.y())
                            - (headPosition.y() == tailPosition.y() ? 0 : 1)
                            * (headPosition.y() < tailPosition.y() ? -1 : 1));
        }
    }

    public int getPositionsVisitedByHead() {
        return positionsVisitedByHead.size();
    }

    public int getPositionsVisitedByTail() {
        return positionsVisitedByTail.size();
    }

}
