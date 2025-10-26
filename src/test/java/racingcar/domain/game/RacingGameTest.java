package racingcar.domain.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.Car;
import racingcar.domain.car.Cars;
import racingcar.domain.policy.WinnerPolicy;
import racingcar.domain.strategy.MoveStrategy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RacingGame 테스트")
class RacingGameTest {

    @Nested
    @DisplayName("정상 입력")
    class 정상_입력 {

        @Test
        @DisplayName("play 호출 시 모든 자동차가 이동하고 시도 횟수가 증가한다")
        void play_이동_및_시도증가() {
            // Given
            Cars cars = Cars.from(List.of("laid", "woni"));
            int attemptCount = 3;
            WinnerPolicy dummyPolicy = new WinnerPolicy() {
                @Override
                public List<Car> decideWinners(Cars cars) {
                    return List.of();
                }
            };
            MoveStrategy movable = () -> true;
            RacingGame game = new RacingGame(cars, attemptCount, dummyPolicy, movable);

            // When
            List<Car> result = game.play();

            // Then
            assertEquals(2, result.size());
            for (Car car : result) {
                assertEquals(1, car.getPositionValue());
            }
            assertFalse(game.isFinished());

            // When
            game.play();
            game.play();

            // Then - 모든 시도 완료
            assertTrue(game.isFinished());
        }

        @Test
        @DisplayName("getWinners는 게임 종료 후 정책에 따라 승자를 반환한다")
        void getWinners_종료후_정책반환() {
            // Given
            Cars cars = Cars.from(List.of("laid", "woni", "pobi"));
            int attemptCount = 2;
            WinnerPolicy pickFirst = new WinnerPolicy() {
                @Override
                public List<Car> decideWinners(Cars cars) {
                    return List.of(cars.getAllCars().get(0));
                }
            };
            MoveStrategy movable = () -> true;
            RacingGame game = new RacingGame(cars, attemptCount, pickFirst, movable);

            // When
            game.play();
            game.play();

            // Then
            List<Car> winners = game.getWinners();
            assertEquals(1, winners.size());
            assertEquals("laid", winners.get(0).getName());
        }
    }

    @Nested
    @DisplayName("예외 입력")
    class 예외_입력 {

        @Test
        @DisplayName("이미 모든 라운드가 종료된 상태에서 play 호출 -> IllegalArgumentException")
        void play_이미종료_예외() {
            // Given
            Cars cars = Cars.from(List.of("laid"));
            int attemptCount = 1;
            WinnerPolicy dummyPolicy = new WinnerPolicy() {
                @Override
                public List<Car> decideWinners(Cars cars) {
                    return List.of();
                }
            };
            MoveStrategy movable = () -> true;
            RacingGame game = new RacingGame(cars, attemptCount, dummyPolicy, movable);

            // When
            game.play();

            // Then
            assertThrows(IllegalArgumentException.class, game::play);
        }

        @Test
        @DisplayName("게임 종료 전 getWinners 호출 -> IllegalArgumentException")
        void getWinners_미종료_예외() {
            // Given
            Cars cars = Cars.from(List.of("laid", "woni"));
            int attemptCount = 3;
            WinnerPolicy dummyPolicy = new WinnerPolicy() {
                @Override
                public List<Car> decideWinners(Cars cars) {
                    return List.of();
                }
            };
            MoveStrategy movable = () -> false;
            RacingGame game = new RacingGame(cars, attemptCount, dummyPolicy, movable);

            // Then
            assertThrows(IllegalArgumentException.class, game::getWinners);
        }
    }
}
