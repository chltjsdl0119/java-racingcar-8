package racingcar.domain.policy;

import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;

import java.util.List;

public class WinnerPolicy {

    public List<Car> decideWinners(Cars cars) {
        List<Car> allCars = cars.getAllCars();

        // 가장 멀리 이동한 자동차의 위치를 구한다.
        int maxPosition = allCars.stream()
                .mapToInt(Car::getPositionValue)
                .max()
                .orElse(0);

        // 동일한 최대 거리를 가진 자동차가 존재할 경우, 공동 우승 처리한다.
        return allCars.stream()
                .filter(car -> car.getPositionValue() == maxPosition)
                .toList();
    }
}
