package racingcar.domain.strategy;

import camp.nextstep.edu.missionutils.Randoms;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RandomMoveStrategy 테스트")
class RandomMoveStrategyTest {

    @Test
    @DisplayName("생성된 숫자가 4 이상이면 이동 가능 반환")
    void 숫자_생성_4이상() {
        RandomMoveStrategy strategy = new RandomMoveStrategy() {
            @Override
            public boolean isMovable() {
                return Randoms.pickNumberInRange(4, 9) >= 4;
            }
        };
        assertTrue(strategy.isMovable());
    }

    @Test
    @DisplayName("생성된 숫자가 3 이하이면 이동 불가 반환")
    void 숫자_생성_3이하() {
        RandomMoveStrategy strategy = new RandomMoveStrategy() {
            @Override
            public boolean isMovable() {
                return Randoms.pickNumberInRange(0, 3) >= 4;
            }
        };
        assertFalse(strategy.isMovable());
    }
}
