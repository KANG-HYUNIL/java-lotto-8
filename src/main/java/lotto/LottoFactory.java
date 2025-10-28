package lotto;

import java.util.List;

public class LottoFactory {

    public static Lotto createLotto(LottoNumberGenerator generator) {

        List<Integer> nums = generator.generateLottoNumbers();

        Validator.validateLottoNumberRange(nums);
        Validator.validateWinningNumbersDuplicate(nums);

        return new Lotto(nums);
    }

}
