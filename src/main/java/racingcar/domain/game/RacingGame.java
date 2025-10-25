package racingcar.domain.game;

import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;
import racingcar.domain.strategy.MoveStrategy;

import java.util.List;

public class RacingGame {
    private final Cars cars;
    private final int attemptCount;
    private final MoveStrategy moveStrategy;

    private int currentAttempt = 0;

    public RacingGame(Cars cars, int attemptCount, MoveStrategy moveStrategy) {
        this.cars = cars;
        this.attemptCount = attemptCount;
        this.moveStrategy = moveStrategy;
    }

    public List<Car> play() {
        if (isFinished()) {
            throw new IllegalArgumentException("모든 라운드가 이미 종료되었습니다.");
        }

        cars.moveAll(moveStrategy);
        currentAttempt++;

        return cars.getAllCars();
    }

    public boolean isFinished() {
        return currentAttempt >= attemptCount;
    }
}
