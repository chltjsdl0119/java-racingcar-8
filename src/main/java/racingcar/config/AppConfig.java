package racingcar.config;

import racingcar.contoller.RacingController;
import racingcar.domain.policy.WinnerPolicy;
import racingcar.domain.strategy.MoveStrategy;
import racingcar.domain.strategy.RandomMoveStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class AppConfig {

    public static RacingController createRacingController() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        WinnerPolicy winnerPolicy = new WinnerPolicy();
        MoveStrategy moveStrategy = new RandomMoveStrategy();

        return new RacingController(inputView, outputView, winnerPolicy, moveStrategy);
    }
}
