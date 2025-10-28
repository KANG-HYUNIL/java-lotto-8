package lotto;

import java.util.List;

public class LottoFactory {

    public static Lotto createLotto(LottoNumberGenerator generator) {

        List<Integer> lottoNumbers = generator.generateLottoNumbers();

        Validator.validateLottoNumberRange(lottoNumbers);
        Validator.validateWinningNumbersDuplicate(lottoNumbers); 

        return new Lotto(lottoNumbers);
    }

}
