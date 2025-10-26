package racingcar.contoller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.domain.car.Car;
import racingcar.domain.policy.WinnerPolicy;
import racingcar.domain.strategy.MoveStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RacingController 테스트")
class RacingControllerTest {

    @Nested
    @DisplayName("정상 흐름")
    class 정상_흐름 {

        @Test
        @DisplayName("시작 메시지 출력, 라운드 결과는 시도 횟수만큼, 우승자 출력 검증")
        void run_정상흐름() {
            StubInputView input = new StubInputView(List.of("laid", "woni"), 2);
            StubOutputView output = new StubOutputView();
            WinnerPolicy policy = new WinnerPolicy();

            boolean[] moves = {true, false, true, false};
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

            RacingController controller = new RacingController(input, output, policy, stateful);

            // When
            controller.run();

            // Then
            assertTrue(output.isStartPrinted(), "시작 메시지가 출력되어야 한다");
            assertEquals(2, output.getRoundResults().size(), "라운드 출력은 시도 횟수만큼 이루어져야 한다");
            List<String> printedWinners = output.getWinners();
            assertEquals(1, printedWinners.size(), "우승자 수가 1이어야 한다");
            assertEquals("laid", printedWinners.get(0));
        }

        @Test
        @DisplayName("모두 이동하지 않으면 동률로 모든 차량이 우승자에 포함된다")
        void run_모두_이동없음_동률() {
            // Given: 두 대의 차량 x,y, 시도 1회, 아무도 이동하지 않음
            StubInputView input = new StubInputView(List.of("laid", "woni"), 1);
            StubOutputView output = new StubOutputView();
            WinnerPolicy policy = new WinnerPolicy();

            MoveStrategy neverMove = () -> false;

            RacingController controller = new RacingController(input, output, policy, neverMove);

            // When
            controller.run();

            // Then
            assertTrue(output.isStartPrinted());
            assertEquals(1, output.getRoundResults().size());
            List<String> winners = output.getWinners();
            assertEquals(2, winners.size());
            assertTrue(winners.contains("laid"));
            assertTrue(winners.contains("woni"));
        }
    }

    static class StubInputView extends InputView {
        private final List<String> names;
        private final int attempts;

        StubInputView(List<String> names, int attempts) {
            this.names = new ArrayList<>(names);
            this.attempts = attempts;
        }

        @Override
        public List<String> readCarNames() {
            return new ArrayList<>(names);
        }

        @Override
        public int readAttemptCount() {
            return attempts;
        }
    }

    static class StubOutputView extends OutputView {
        private boolean startPrinted = false;
        private final List<List<String>> roundResults = new ArrayList<>();
        private final List<String> winners = new ArrayList<>();

        @Override
        public void printStartMessage() {
            startPrinted = true;
        }

        @Override
        public void printRoundResult(List<Car> carsInRound) {
            List<String> snapshot = carsInRound.stream()
                    .map(c -> c.getName() + ":" + c.getPositionValue())
                    .collect(Collectors.toList());
            roundResults.add(snapshot);
        }

        @Override
        public void printWinners(List<Car> winnersList) {
            winners.clear();
            winners.addAll(winnersList.stream().map(Car::getName).toList());
        }

        boolean isStartPrinted() {
            return startPrinted;
        }

        List<List<String>> getRoundResults() {
            return roundResults;
        }

        List<String> getWinners() {
            return winners;
        }
    }
}
