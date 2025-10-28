package lotto;

import java.util.List;

public class WinningNumbers {

    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> winningNumbers, int bonusNumber) {
        validate(winningNumbers, bonusNumber);
        this.winningNumbers = List.copyOf(winningNumbers);
        this.bonusNumber = bonusNumber;
    }


    private void validate(List<Integer> winningNumbers, int bonusNumber) {
        Validator.validateWinningNumbersCount(winningNumbers);
        Validator.validateWinningNumbersDuplicate(winningNumbers);
        Validator.validateLottoNumberRange(winningNumbers);


        Validator.validateBonusNumberRange(bonusNumber);
        Validator.validateBonusNumberDuplicate(bonusNumber, winningNumbers);
    }

    public List<Integer> getWinningNumbers() {
        return List.copyOf(winningNumbers);
    }

    public int getBonusNumber() {
        return bonusNumber;
    }


}
