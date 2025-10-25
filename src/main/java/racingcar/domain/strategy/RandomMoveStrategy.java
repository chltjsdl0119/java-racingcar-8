package racingcar.domain.strategy;

import camp.nextstep.edu.missionutils.Randoms;

public class RandomMoveStrategy implements MoveStrategy {

    // camp.nextstep.edu.missionutils.Randoms의 pickNumberInRange()를 통해 0~9 사이의 숫자를 무작위로 추출한다.
    @Override
    public boolean isMovable() {
        return Randoms.pickNumberInRange(0, 9) >= 4;
    }
}
