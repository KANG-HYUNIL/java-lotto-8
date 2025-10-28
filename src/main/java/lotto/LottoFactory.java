package lotto;

public class LottoFactory {

    public static Lotto createLotto(LottoNumberGenerator generator) {
        return new Lotto(generator.generateLottoNumbers());
    }

}
