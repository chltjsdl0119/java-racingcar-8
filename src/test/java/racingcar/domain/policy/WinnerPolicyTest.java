package racingcar.domain.policy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;
import racingcar.domain.strategy.MoveStrategy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("WinnerPolicy 테스트")
class WinnerPolicyTest {

    @Nested
    @DisplayName("정상 동작")
    class 정상_동작 {

        @Test
        @DisplayName("단일 우승자를 올바르게 반환한다")
        void 단일_우승자() {
            // Given
            Cars cars = Cars.from(List.of("laid", "woni", "pobi"));
            WinnerPolicy policy = new WinnerPolicy();

            boolean[] moves = {false, true, false, false, true, false};
            MoveStrategy stateful = new MoveStrategy() {
                int idx = 0;

                @Override
                public boolean isMovable() {
                    if (idx < moves.length) {
                        return moves[idx++];
                    }
                    return false;
                }
            };

            // When
            cars.moveAll(stateful);
            cars.moveAll(stateful);

            // Then
            List<Car> winners = policy.decideWinners(cars);
            assertEquals(1, winners.size());
            assertEquals("woni", winners.get(0).getName());
        }

        @Test
        @DisplayName("공동 우승자를 반환한다")
        void 공동_우승자() {
            // Given
            Cars cars = Cars.from(List.of("laid", "woni", "pobi"));
            WinnerPolicy policy = new WinnerPolicy();

            boolean[] moves = {true, true, false, true, true, false};
            MoveStrategy stateful = new MoveStrategy() {
                int idx = 0;

                @Override
                public boolean isMovable() {
                    if (idx < moves.length) {
                        return moves[idx++];
                    }
                    return false;

                }
            };

            // When
            cars.moveAll(stateful);
            cars.moveAll(stateful);

            // Then
            List<Car> winners = policy.decideWinners(cars);
            assertEquals(2, winners.size());
            assertTrue(winners.stream().anyMatch(c -> c.getName().equals("laid")));
            assertTrue(winners.stream().anyMatch(c -> c.getName().equals("woni")));
        }

        @Test
        @DisplayName("모두 같은 위치(0)일 때 모든 차량을 승자로 반환한다")
        void 모두_동률_초기상태() {
            // Given
            Cars cars = Cars.from(List.of("laid", "woni", "pobi"));
            WinnerPolicy policy = new WinnerPolicy();

            boolean[] moves = {false, false, false, false, false, false};
            MoveStrategy stateful = new MoveStrategy() {
                int idx = 0;

                @Override
                public boolean isMovable() {
                    if (idx < moves.length) {
                        return moves[idx++];
                    }

                    return false;
                }
            };

            // When
            cars.moveAll(stateful);
            cars.moveAll(stateful);

            // Then
            List<Car> winners = policy.decideWinners(cars);
            assertEquals(3, winners.size());
            assertTrue(winners.stream().anyMatch(c -> c.getName().equals("laid")));
            assertTrue(winners.stream().anyMatch(c -> c.getName().equals("woni")));
            assertTrue(winners.stream().anyMatch(c -> c.getName().equals("pobi")));
        }
    }
}
