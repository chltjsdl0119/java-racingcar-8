package racingcar.domain.strategy;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomMoveStrategy implements MoveStrategy {

    private static final int RANDOM_MIN = 0;
    private static final int RANDOM_MAX = 9;
    private static final int MOVE_THRESHOLD = 4;

    // camp.nextstep.edu.missionutils.Randoms의 pickNumberInRange()를 통해 0~9 사이의 숫자를 무작위로 추출한다.
    @Override
    public boolean isMovable() {
        return Randoms.pickNumberInRange(RANDOM_MIN, RANDOM_MAX) >= MOVE_THRESHOLD;
    }
}
