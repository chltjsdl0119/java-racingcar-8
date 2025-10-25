package racingcar.domain.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import racingcar.domain.strategy.MoveStrategy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Cars 테스트")
class CarsTest {

    @Nested
    @DisplayName("정상 입력")
    class 정상_입력 {

        @Test
        @DisplayName("여러 자동차 생성 시 이름 순서와 초기 위치 확인")
        void 생성_이름_초기_위치() {
            // Given
            List<String> names = List.of("laid", "woni");

            // When
            Cars cars = Cars.from(names);

            // Then
            assertEquals(2, cars.getAllCars().size());
            assertEquals("laid", cars.getAllCars().get(0).getName());
            assertEquals("woni", cars.getAllCars().get(1).getName());
            assertEquals(0, cars.getAllCars().get(0).getPositionValue());
            assertEquals(0, cars.getAllCars().get(1).getPositionValue());
        }

        @Test
        @DisplayName("moveAll 호출 시 모든 자동차가 전략에 따라 이동한다")
        void moveAll_전략에_따른_이동() {
            // Given
            List<String> names = List.of("a", "b", "c");
            Cars cars = Cars.from(names);
            MoveStrategy movable = () -> true;

            // When
            cars.moveAll(movable);

            // Then
            for (Car car : cars.getAllCars()) {
                assertEquals(1, car.getPositionValue());
            }
        }

        @Test
        @DisplayName("moveAll 호출 시 모든 자동차가 전략에 따라 이동하지 않는다")
        void moveAll_전략에_따르지_않는_이동() {
            // Given
            List<String> names = List.of("a", "b", "c");
            Cars cars = Cars.from(names);
            MoveStrategy notMovable = () -> false;

            // When
            cars.moveAll(notMovable);

            // Then
            for (Car car : cars.getAllCars()) {
                assertEquals(0, car.getPositionValue());
            }
        }
    }

    @Nested
    @DisplayName("예외 입력")
    class 예외_입력 {

        @Test
        @DisplayName("null 입력 -> IllegalArgumentException")
        void 널_입력() {
            // Given
            List<String> input = null;

            // When & Then
            assertThrows(IllegalArgumentException.class, () -> Cars.from(input));
        }

        @Test
        @DisplayName("빈 목록 입력 -> IllegalArgumentException")
        void 빈_목록_입력() {
            // Given
            List<String> input = List.of();

            // When & Then
            assertThrows(IllegalArgumentException.class, () -> Cars.from(input));
        }

        @Test
        @DisplayName("중복된 이름 존재 -> IllegalArgumentException")
        void 중복된_이름() {
            // Given
            List<String> input = List.of("laid", "laid");

            // When & Then
            assertThrows(IllegalArgumentException.class, () -> Cars.from(input));
        }
    }
}
