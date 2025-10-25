package racingcar.domain.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import racingcar.domain.strategy.MoveStrategy;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Car 테스트")
class CarTest {

    @Nested
    @DisplayName("정상 입력")
    class 정상_입력 {

        @Test
        @DisplayName("유효한 이름으로 생성 시 이름과 초기 위치 확인")
        void 유효한_이름_초기_위치() {
            // Given
            String name = "laid";

            // When
            Car car = Car.from(name);

            // Then
            assertEquals("laid", car.getName());
            assertEquals(0, car.getPositionValue());
        }

        @Test
        @DisplayName("전진 가능한 전략일 때 위치가 증가한다")
        void 전진_가능하면_위치_증가() {
            // Given
            Car car = Car.from("laid");
            MoveStrategy movable = () -> true;

            // When
            car.move(movable);

            // Then
            assertEquals(1, car.getPositionValue());
        }

        @Test
        @DisplayName("전진 불가능한 전략일 때 위치가 유지된다")
        void 전진_불가능하면_위치_유지() {
            // Given
            Car car = Car.from("laid");
            MoveStrategy notMovable = () -> false;

            // When
            car.move(notMovable);

            // Then
            assertEquals(0, car.getPositionValue());
        }
    }

    @Nested
    @DisplayName("예외 입력")
    class 예외_입력 {

        @Test
        @DisplayName("null 입력 -> IllegalArgumentException")
        void 널_입력() {
            // Given
            String input = null;

            // When & Then
            assertThrows(IllegalArgumentException.class, () -> Car.from(input));
        }

        @Test
        @DisplayName("빈 문자열 입력 -> IllegalArgumentException")
        void 빈_문자열_입력() {
            // Given
            String input = "";

            // When & Then
            assertThrows(IllegalArgumentException.class, () -> Car.from(input));
        }

        @Test
        @DisplayName("공백만 입력 -> IllegalArgumentException")
        void 공백만_입력() {
            // Given
            String input = "   ";

            // When & Then
            assertThrows(IllegalArgumentException.class, () -> Car.from(input));
        }

        @Test
        @DisplayName("이름 길이 초과(6자 이상) -> IllegalArgumentException")
        void 이름_길이_초과() {
            // Given
            String input = "abcdef";

            // When & Then
            assertThrows(IllegalArgumentException.class, () -> Car.from(input));
        }
    }
}
