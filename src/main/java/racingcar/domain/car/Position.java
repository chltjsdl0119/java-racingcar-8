package racingcar.domain.car;

// 현재 거리 Value Object
public class Position {

    private int position;

    private Position() {
        this.position = 0;
    }

    public static Position initPosition() {
        return new Position();
    }

    public void move() {
        this.position++;
    }

    public int getValue() {
        return this.position;
    }
}
