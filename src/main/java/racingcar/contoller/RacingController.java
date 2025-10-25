package racingcar.contoller;

import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;
import racingcar.domain.game.RacingGame;
import racingcar.domain.strategy.MoveStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RacingController {
    private final InputView inputView;
    private final OutputView outputView;
    private final MoveStrategy moveStrategy;

    public RacingController(InputView inputView, OutputView outputView, MoveStrategy moveStrategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.moveStrategy = moveStrategy;
    }

    public void run() {
        List<String> carNames = inputView.readCarNames();
        int tryCount = inputView.readAttemptCount();

        Cars cars = Cars.from(carNames);
        RacingGame game = new RacingGame(cars, tryCount, moveStrategy);

        outputView.printStartMessage();

        // 사용자가 입력한 시도 횟수만큼 경주를 반복한다.
        while (!game.isFinished()) {
            List<Car> carsInRound = game.play();
            outputView.printRoundResult(carsInRound);
        }
    }
}
