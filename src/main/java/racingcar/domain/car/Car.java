package racingcar.domain.car;

import racingcar.domain.strategy.MoveStrategy;

public class Car {
    // 자동차는 이름과 현재 거리를 가진다.
    private final String name;
    private final Position position;

    // 입력받은 이름으로 자동차를 생성한다.
    private Car(String name) {
        validateName(name);
        this.name = name;
        this.position = Position.initPosition();
    }

    public static Car from(String name) {
        return new Car(name);
    }

    public void move(MoveStrategy moveStrategy) {
        // 3 이하라면 멈추고, 4 이상이면 전진한다.
        if (moveStrategy.isMovable()) {
            position.move();
        }
    }

    public String getName() {
        return name;
    }

    public int getPositionValue() {
        return position.getValue();
    }

    // 자동차 이름은 1자 이상 5자 이하여야 한다.
    private void validateName(String name) {
        if (name == null || name.isBlank() || name.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 1자 이상 5자 이하의 입력 값이어야 합니다: " + name);
        }
    }
}
