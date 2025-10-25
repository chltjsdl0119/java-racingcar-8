package racingcar.domain.car;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Position 테스트")
class PositionTest {

    @Nested
    @DisplayName("정상 동작")
    class 정상_동작 {

        @Test
        @DisplayName("초기 위치는 0이다")
        void 초기_위치_0() {
            // Given, When
            Position position = Position.initPosition();

            // Then
            assertEquals(0, position.getValue());
        }

        @Test
        @DisplayName("move 호출 시 위치가 1 증가한다")
        void move_1_증가() {
            // Given
            Position position = Position.initPosition();

            // When
            position.move();

            // Then
            assertEquals(1, position.getValue());
        }

        @Test
        @DisplayName("여러 번 move 호출 시 누적해서 증가한다")
        void move_누적증가() {
            // Given
            Position position = Position.initPosition();
            int moves = 5;

            // When
            for (int i = 0; i < moves; i++) {
                position.move();
            }

            // Then
            assertEquals(moves, position.getValue());
        }
    }

    @Nested
    @DisplayName("기본 검증")
    class 기본_검증 {

        @Test
        @DisplayName("initPosition 호출 시 null이 아닌 새로운 Position 객체를 반환한다")
        void initPosition_새로운_객체() {
            // Given, When
            Position position1 = Position.initPosition();
            Position position2 = Position.initPosition();

            // Then
            assertNotNull(position1);
            assertNotNull(position2);
        }
    }
}
