package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputParserTest {

    @Test
    @DisplayName("정상적 숫자로 로또 구매금이 들어오면 예외가 발생하지 않고 Int로 변환된 값이 반환된다")
    void parseMoneyToInt_ValidInput_NoException() {
        // given
        String moneyInput = "5000";

        // when
        int money = InputParser.parseMoneyToInt(moneyInput);

        // then
        assertThat(money).isEqualTo(5000);
    }

    @Test
    @DisplayName("비정상적 숫자로 로또 구매금이 들어오면 예외가 발생한다")
    void parseMoneyToInt_InvalidInput_ExceptionThrown() {
        // given
        String moneyInput = "five thousand";

        // when / then
        assertThatThrownBy(() -> InputParser.parseMoneyToInt(moneyInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입금 입력에 공백이 포함되어 있어도 trim 후 정상적으로 파싱된다")
    void parseMoneyToInt_TrimmedInput_NoException() {
        // given
        String moneyInput = "  3000  ";

        // when
        int money = InputParser.parseMoneyToInt(moneyInput.trim());

        // then
        assertThat(money).isEqualTo(3000);
    }

    @Test
    @DisplayName("정상적 숫자들로 된 문자열이 당첨 번호로 들어오면 예외가 발생하지 않고 Int 리스트로 변환된 값이 반환된다")
    void parseWinningNumbers_ValidInput_NoException() {
        // given
        String winningNumbersInput = "3, 15, 22, 28, 35, 42";

        // when
        List<Integer> result = InputParser.parseWinningNumbers(winningNumbersInput);

        // then
        assertThat(result).containsExactly(3, 15, 22, 28, 35, 42);
    }

    @Test
    @DisplayName("비정상적 숫자들로 된 문자열이 당첨 번호로 들어오면 예외가 발생한다")
    void parseWinningNumbers_InvalidInput_ExceptionThrown() {
        // given
        String winningNumbersInput = "3, fifteen, 22, 28, 35, 42";

        // when / then
        assertThatThrownBy(() -> InputParser.parseWinningNumbers(winningNumbersInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("정상적 숫자로 보너스 번호가 들어오면 예외가 발생하지 않고 Int로 변환된 값이 반환된다")
    void parseBonusNumber_ValidInput_NoException() {
        // given
        String bonusNumberInput = "7";

        // when
        int bonusNumber = InputParser.parseBonusNumber(bonusNumberInput);

        // then
        assertThat(bonusNumber).isEqualTo(7);
    }

    @Test
    @DisplayName("비정상적 숫자로 보너스 번호가 들어오면 예외가 발생한다")
    void parseBonusNumber_InvalidInput_ExceptionThrown() {
        // given
        String bonusNumberInput = "seven";

        // when / then
        assertThatThrownBy(() -> InputParser.parseBonusNumber(bonusNumberInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
