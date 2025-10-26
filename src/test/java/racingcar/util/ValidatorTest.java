package racingcar.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Validator 테스트")
class ValidatorTest {

    @Test
    @DisplayName("양의 정수 문자열이 들어오면 해당 값을 반환")
    void 양의_정수_검증() {
        assertEquals(5, Validator.parsePositiveInt("5"));
    }

    @Test
    @DisplayName("null 값이 들어오면 IllegalArgumentException 발생")
    void null_값_검증() {
        assertThrows(IllegalArgumentException.class, () ->
            Validator.parsePositiveInt(null));
    }

    @Test
    @DisplayName("빈 문자열이 들어오면 IllegalArgumentException 발생")
    void 빈_문자열_검증() {
        assertThrows(IllegalArgumentException.class, () ->
            Validator.parsePositiveInt(""));
    }
}
