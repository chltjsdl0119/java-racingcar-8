package racingcar.util;

public class Validator {

    // 숫자가 아닌 경우 IllegalArgumentException을 발생시킨다.
    public static int parsePositiveInt(String input) {
        try {
            int value = Integer.parseInt(input);

            // 1 이하의 숫자인 경우 IllegalArgumentException을 발생시킨다.
            if (value <= 0) {
                throw new IllegalArgumentException("1 이상의 숫자를 입력해야 합니다.");
            }

            return value;

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 입력값입니다.");
        }
    }
}
