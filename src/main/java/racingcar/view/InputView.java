package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import racingcar.util.Validator;

import java.util.List;

public class InputView {
    private static final String INPUT_PROMPT = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ATTEMPT_PROMPT = "시도할 횟수는 몇 회인가요?";
    private static final String DELIMITER = ",";

    // camp.nextstep.edu.missionutils.Console의 readLine()을 통해 경주할 자동차 이름을 입력받는다.
    public List<String> readCarNames() {
        System.out.println(INPUT_PROMPT);

        // 자동차 이름은 쉼표(,) 기준으로 구분한다.
        return List.of(Console.readLine().split(DELIMITER));
    }

    // 시도할 횟수를 입력받는다.
    public int readAttemptCount() {
        System.out.println(ATTEMPT_PROMPT);
        return Validator.parsePositiveInt(Console.readLine());
    }
}
