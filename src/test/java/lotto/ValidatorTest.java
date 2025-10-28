package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;

public class ValidatorTest {

    @Test
    @DisplayName("정상적인 1000원 단위 숫자가 로또 구입금으로 들어오면 예외가 발생하지 않고 통과된다")
    void getMoneyInt_ValidMoneyInput_NoException() {
        // given
        int money1 = 1000;
        int money2 = 5000;

        // when
        Throwable thrown1 = catchThrowable(() -> Validator.validateMoney(money1));
        Throwable thrown2 = catchThrowable(() -> Validator.validateMoney(money2));

        // then
        assertThat(thrown1).isNull();
        assertThat(thrown2).isNull();
    }

    @Test
    @DisplayName("1000원 단위가 아닌 숫자가 로또 구입금으로 들어오면 예외가 발생한다")
    void getMoneyInt_InvalidMoneyInput_ExceptionThrown() {
        // given
        int notUnit = 1500;
        int zero = 0;
        int negative = -1000;

        // when
        Throwable thrown1 = catchThrowable(() -> Validator.validateMoney(notUnit));
        Throwable thrown2 = catchThrowable(() -> Validator.validateMoney(zero));
        Throwable thrown3 = catchThrowable(() -> Validator.validateMoney(negative));

        // then
        assertThat(thrown1).isInstanceOf(IllegalArgumentException.class);
        assertThat(thrown2).isInstanceOf(IllegalArgumentException.class);
        assertThat(thrown3).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호들이 1~45 사이의 숫자들로 이루어져 있으면 예외가 발생하지 않고 통과된다")
    void validateLottoNumbers_ValidNumbers_NoException() {
        // given
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 45);

        // when
        Throwable thrown = catchThrowable(() -> Validator.validateLottoNumberRange(numbers));

        // then
        assertThat(thrown).isNull();
    }

    @Test
    @DisplayName("로또 번호들 중 1~45 사이의 숫자가 아닌 숫자가 있으면 예외가 발생한다")
    void validateLottoNumbers_InvalidNumbers_ExceptionThrown() {
        // given
        List<Integer> numbers = List.of(1, 2, 0, 4, 5, 6);

        // when
        Throwable thrown = catchThrowable(() -> Validator.validateLottoNumberRange(numbers));

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호가 6개이면 예외가 발생하지 않고 통과된다")
    void validateWinningNumbersCount_ValidCount_NoException() {
        // given
        List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);

        // when
        Throwable thrown = catchThrowable(() -> Validator.validateWinningNumbersCount(winning));

        // then
        assertThat(thrown).isNull();
    }

    @Test
    @DisplayName("당첨 번호가 6개가 아니면 예외가 발생한다")
    void validateWinningNumbersCount_InvalidCount_ExceptionThrown() {
        // given
        List<Integer> winningShort = List.of(1, 2, 3, 4, 5);
        List<Integer> winningLong = List.of(1, 2, 3, 4, 5, 6, 7);

        // when
        Throwable thrownShort = catchThrowable(() -> Validator.validateWinningNumbersCount(winningShort));
        Throwable thrownLong = catchThrowable(() -> Validator.validateWinningNumbersCount(winningLong));

        // then
        assertThat(thrownShort).isInstanceOf(IllegalArgumentException.class);
        assertThat(thrownLong).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호에 중복된 숫자가 없으면 예외가 발생하지 않고 통과된다")
    void validateWinningNumbersDuplicate_NoDuplicates_NoException() {
        // given
        List<Integer> winning = List.of(3, 11, 19, 28, 35, 42);

        // when
        Throwable thrown = catchThrowable(() -> Validator.validateWinningNumbersDuplicate(winning));

        // then
        assertThat(thrown).isNull();
    }

    @Test
    @DisplayName("당첨 번호에 중복된 숫자가 있으면 예외가 발생한다")
    void validateWinningNumbersDuplicate_WithDuplicates_ExceptionThrown() {
        // given
        List<Integer> winning = List.of(3, 11, 19, 19, 35, 42);

        // when
        Throwable thrown = catchThrowable(() -> Validator.validateWinningNumbersDuplicate(winning));

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 번호가 1~45 사이의 숫자이면 예외가 발생하지 않고 통과된다")
    void validateBonusNumberRange_ValidNumber_NoException() {
        // given
        int low = 1;
        int high = 45;

        // when
        Throwable thrownLow = catchThrowable(() -> Validator.validateBonusNumberRange(low));
        Throwable thrownHigh = catchThrowable(() -> Validator.validateBonusNumberRange(high));

        // then
        assertThat(thrownLow).isNull();
        assertThat(thrownHigh).isNull();
    }

    @Test
    @DisplayName("보너스 번호가 1~45 사이의 숫자가 아니면 예외가 발생한다")
    void validateBonusNumberRange_InvalidNumber_ExceptionThrown() {
        // given
        int tooLow = 0;
        int tooHigh = 46;

        // when
        Throwable thrownLow = catchThrowable(() -> Validator.validateBonusNumberRange(tooLow));
        Throwable thrownHigh = catchThrowable(() -> Validator.validateBonusNumberRange(tooHigh));

        // then
        assertThat(thrownLow).isInstanceOf(IllegalArgumentException.class);
        assertThat(thrownHigh).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되지 않으면 예외가 발생하지 않고 통과된다")
    void validateBonusNumberDuplicate_NoDuplicate_NoException() {
        // given
        List<Integer> winning = List.of(10, 20, 30, 31, 32, 33);
        int bonus = 7;

        // when
        Throwable thrown = catchThrowable(() -> Validator.validateBonusNumberDuplicate(bonus, winning));

        // then
        assertThat(thrown).isNull();
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
    void validateBonusNumberDuplicate_WithDuplicate_ExceptionThrown() {
        // given
        List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);
        int bonus = 3;

        // when
        Throwable thrown = catchThrowable(() -> Validator.validateBonusNumberDuplicate(bonus, winning));

        // then
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }
}
