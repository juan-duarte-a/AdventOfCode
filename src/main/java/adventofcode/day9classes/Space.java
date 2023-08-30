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
        headPosition = new Position(headPosition.getX() - 1, headPosition.getY());
    }

    private void right() {
        headPosition = new Position(headPosition.getX() + 1, headPosition.getY());
    }

    private void up() {
        headPosition = new Position(headPosition.getX(), headPosition.getY() + 1);
    }

    private void down() {
        headPosition = new Position(headPosition.getX(), headPosition.getY() - 1);
    }

    private boolean tailMustMove() {
        return Math.abs(tailPosition.getX() - headPosition.getX()) > 1
                || Math.abs(tailPosition.getY() - headPosition.getY()) > 1;
    }

    private void moveTail() {
        if (headPosition.getX() != tailPosition.getX() && headPosition.getY() != tailPosition.getY()) {
            int x = headPosition.getX() - tailPosition.getX();
            int y = headPosition.getY() - tailPosition.getY();
            tailPosition = new Position(tailPosition.getX() + x / Math.abs(x),
                    tailPosition.getY() + y / Math.abs(y));
        } else {
            tailPosition = new Position(
                    tailPosition.getX() + (headPosition.getX() - tailPosition.getX())
                            - (headPosition.getX() == tailPosition.getX() ? 0 : 1)
                            * (headPosition.getX() < tailPosition.getX() ? -1 : 1),
                    tailPosition.getY() + (headPosition.getY() - tailPosition.getY())
                            - (headPosition.getY() == tailPosition.getY() ? 0 : 1)
                            * (headPosition.getY() < tailPosition.getY() ? -1 : 1));
        }
    }

    public int getPositionsVisitedByHead() {
        return positionsVisitedByHead.size();
    }

    public int getPositionsVisitedByTail() {
        return positionsVisitedByTail.size();
    }

}
