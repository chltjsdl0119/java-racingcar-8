package racingcar.domain.car;

import racingcar.domain.strategy.MoveStrategy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cars {
    private final Map<String, Car> cars;

    private Cars(List<String> carNames) {
        validateCarNames(carNames);

        cars = new LinkedHashMap<>();

        for (String carName : carNames) {
            Car car = Car.from(carName);

            if (cars.containsKey(carName)) {
                throw new IllegalArgumentException("중복된 자동차 이름이 있습니다: " + carName);
            }

            cars.put(carName, car);
        }
    }

    public static Cars from(List<String> carNames) {
        return new Cars(carNames);
    }

    public void moveAll(MoveStrategy moveStrategy) {
        cars.values().forEach(car -> car.move(moveStrategy));
    }

    public List<Car> getAllCars() {
        return List.copyOf(cars.values());
    }

    private void validateCarNames(List<String> carNames) {
        if (carNames == null || carNames.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름 목록은 비어 있을 수 없습니다.");
        }
    }
}
