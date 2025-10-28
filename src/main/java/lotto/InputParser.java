package lotto;

import java.util.List;

public class InputParser {

    public static int parseMoneyToInt(String moneyInput) {

        try {
            int money =  Integer.parseInt(moneyInput);

            //TODO : 금액이 1000원 단위인지 검증, Validate 이용

            return money;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입금액은 숫자여야 합니다.");
        }
    }

    public static List<Integer> parseWinningNumbers(String winningNumbersInput) {

        String splitRegex = ",";
        List<String> strNumbers = List.of(winningNumbersInput.split(splitRegex));

        List<Integer> intNumbers = strNumbers.stream()
                .map(String::trim)
                .map(str -> {
                    try {
                        return Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("[ERROR] 당첨 번호는 숫자여야 합니다.");
                    }
                })
                .toList();

        //TODO : 당첨 번호 개수, 범위, 중복 검증, Validate 이용

        return intNumbers;
    }

    public static int parseBonusNumber(String bonusNumberInput) {
        try {
            int bonusNumber = Integer.parseInt(bonusNumberInput);

            //TODO : 보너스 번호 범위 검증, Validate 이용

            return bonusNumber;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 숫자여야 합니다.");
        }
    }


}
