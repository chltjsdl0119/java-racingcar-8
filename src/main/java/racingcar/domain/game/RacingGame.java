package racingcar.domain.game;

import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;
import racingcar.domain.policy.WinnerPolicy;
import racingcar.domain.strategy.MoveStrategy;

import java.util.List;

public class RacingGame {
    private final Cars cars;
    private final int attemptCount;
    private final WinnerPolicy winnerPolicy;
    private final MoveStrategy moveStrategy;

    private int currentAttempt = 0;

    public RacingGame(Cars cars, int attemptCount, WinnerPolicy winnerPolicy, MoveStrategy moveStrategy) {
        this.cars = cars;
        this.attemptCount = attemptCount;
        this.winnerPolicy = winnerPolicy;
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

    public List<Car> getWinners() {
        if (!isFinished()) {
            throw new IllegalArgumentException("게임이 종료되지 않았습니다.");
        }

        return winnerPolicy.decideWinners(cars);
    }

    public boolean isFinished() {
        return currentAttempt >= attemptCount;
    }
}
