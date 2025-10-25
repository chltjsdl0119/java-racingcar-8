package racingcar.view;

import racingcar.domain.car.Car;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String RESULT_MESSAGE = "실행 결과";
    private static final String WINNER_MESSAGE = "최종 우승자 : ";

    public void printStartMessage() {
        System.out.println();
        System.out.println(RESULT_MESSAGE);
    }

    // 각 시도 결과를 출력 형식에 맞게 출력한다.
    public void printRoundResult(List<Car> cars) {
        for (Car car : cars) {
            System.out.println(formatCarProgress(car));
        }

        System.out.println();
    }

    // 각 시도 결과를 출력 형식에 맞게 출력한다.
    public void printWinners(List<Car> winners) {
        String names = winners.stream()
                .map(Car::getName)
                .collect(Collectors.joining(", "));

        System.out.println(WINNER_MESSAGE + names);
    }

    private String formatCarProgress(Car car) {
        String progressBar = "-".repeat(car.getPositionValue());

        return car.getName() + " : " + progressBar;
    }
}
