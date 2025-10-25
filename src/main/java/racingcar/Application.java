package racingcar;

import racingcar.config.AppConfig;
import racingcar.contoller.RacingController;

public class Application {
    public static void main(String[] args) {
        RacingController racingController = AppConfig.createRacingController();
        racingController.run();
    }
}
