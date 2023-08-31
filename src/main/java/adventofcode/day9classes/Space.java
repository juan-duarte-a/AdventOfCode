package adventofcode.day9classes;

import java.util.HashSet;
import java.util.Set;

public class Space {
    private final Set<Position> positionsVisitedByHead;
    private final Set<Position> positionsVisitedByTail;
    private final int numberOfKnots;
    private final Position[] knots;

    public enum Direction {L, R, U, D}

    public Space(int numberOfKnots) {
        positionsVisitedByHead = new HashSet<>();
        positionsVisitedByTail = new HashSet<>();
        Position headPosition = new Position(0, 0);
        positionsVisitedByHead.add(headPosition);
        this.numberOfKnots = numberOfKnots;
        knots = new Position[numberOfKnots];

        for (int i = 0; i < numberOfKnots; i++) {
            knots[i] = headPosition;
        }

        positionsVisitedByTail.add(knots[numberOfKnots - 1]);
    }

    public void move(Direction direction, int times) {
        for (int i = 0; i < times; i++) {
            switch (direction) {
                case L -> left();
                case R -> right();
                case U -> up();
                case D -> down();
            }

            positionsVisitedByHead.add(knots[0]);

            for (int j = 1; j < numberOfKnots; j++) {
                if (knotMustMove(j)) {
                    moveKnot(j);
                }
            }
            positionsVisitedByTail.add(knots[numberOfKnots - 1]);
        }
    }

    private void left() {
        knots[0] = new Position(knots[0].getX() - 1, knots[0].getY());
    }

    private void right() {
        knots[0] = new Position(knots[0].getX() + 1, knots[0].getY());
    }

    private void up() {
        knots[0] = new Position(knots[0].getX(), knots[0].getY() + 1);
    }

    private void down() {
        knots[0] = new Position(knots[0].getX(), knots[0].getY() - 1);
    }

    private boolean knotMustMove(int knotNumber) {
        return Math.abs(knots[knotNumber].getX() - knots[knotNumber - 1].getX()) > 1
                || Math.abs(knots[knotNumber].getY() - knots[knotNumber - 1].getY()) > 1;
    }

    private void moveKnot(int knotNumber) {
        if (knots[knotNumber - 1].getX() != knots[knotNumber].getX()
                && knots[knotNumber - 1].getY() != knots[knotNumber].getY()) {
            int x = knots[knotNumber - 1].getX() - knots[knotNumber].getX();
            int y = knots[knotNumber - 1].getY() - knots[knotNumber].getY();
            knots[knotNumber] = new Position(knots[knotNumber].getX() + x / Math.abs(x),
                    knots[knotNumber].getY() + y / Math.abs(y));
        } else {
            knots[knotNumber] = new Position(
                    knots[knotNumber].getX() + (knots[knotNumber - 1].getX() - knots[knotNumber].getX())
                            - (knots[knotNumber - 1].getX() == knots[knotNumber].getX() ? 0 : 1)
                            * (knots[knotNumber - 1].getX() < knots[knotNumber].getX() ? -1 : 1),
                    knots[knotNumber].getY() + (knots[knotNumber - 1].getY() - knots[knotNumber].getY())
                            - (knots[knotNumber - 1].getY() == knots[knotNumber].getY() ? 0 : 1)
                            * (knots[knotNumber - 1].getY() < knots[knotNumber].getY() ? -1 : 1));
        }
    }

    public int getPositionsVisitedByHead() {
        return positionsVisitedByHead.size();
    }

    public int getPositionsVisitedByTail() {
        return positionsVisitedByTail.size();
    }

}
