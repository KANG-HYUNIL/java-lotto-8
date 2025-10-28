package lotto;

import java.util.List;
import static lotto.ErrorMessage.INVALID_MONEY_FORMAT;
import static lotto.ErrorMessage.INVALID_NUMBER_FORMAT;

public class InputParser {

    public static int parseMoneyToInt(String moneyInput) {

        try {
            return Integer.parseInt(moneyInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_MONEY_FORMAT.errorWithMessage());
        }
    }

    public static List<Integer> parseWinningNumbers(String winningNumbersInput) {

        String splitRegex = ",";
        List<String> strNumbers = List.of(winningNumbersInput.split(splitRegex));

        return strNumbers.stream()
                .map(String::trim)
                .map(str -> {
                    try {
                        return Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(INVALID_NUMBER_FORMAT.errorWithMessage());
                    }
                })
                .toList();
    }

    public static int parseBonusNumber(String bonusNumberInput) {
        try {

            return Integer.parseInt(bonusNumberInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_FORMAT.errorWithMessage());
        }
    }


}
