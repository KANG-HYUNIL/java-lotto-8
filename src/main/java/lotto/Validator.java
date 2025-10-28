package lotto;

import java.util.List;
import static lotto.ErrorMessage.INVALID_MONEY_UNIT;
import static lotto.ErrorMessage.NUMBER_OUT_OF_RANGE;
import static lotto.ErrorMessage.WINNING_NUMBERS_COUNT;
import static lotto.ErrorMessage.BONUS_NUMBER_DUPLICATE;

public class Validator {

    public static void validateMoney(int money) {
        if (money <= 0 || money % 1000 != 0) {
            throw new IllegalArgumentException(INVALID_MONEY_UNIT.errorWithMessage());
        }
    }

    public static void validateLottoNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException(NUMBER_OUT_OF_RANGE.errorWithMessage());
        }
    }

    public static void validateWinningNumbersCount(List<Integer> winningNumbers) {
        int count = winningNumbers.size();
        if (count != 6) {
            throw new IllegalArgumentException(WINNING_NUMBERS_COUNT.errorWithMessage());
        }
    }

    public static void validateWinningNumbersDuplicate(List<Integer> winningNumbers) {
        long uniqueCount = winningNumbers.stream().distinct().count();
        if (uniqueCount != winningNumbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.WINNING_NUMBERS_DUPLICATE.errorWithMessage());
        }
    }

    public static void validateBonusNumberDuplicate(int bonusNumber, List<Integer> winningNumbers) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_DUPLICATE.errorWithMessage());
        }
    }

}
