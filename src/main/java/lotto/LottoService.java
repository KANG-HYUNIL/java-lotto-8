package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoService {

    private final LottoRepository lottoRepository;
    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoService(LottoRepository lottoRepository, LottoNumberGenerator lottoNumberGenerator) {
        this.lottoRepository = lottoRepository;
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public void purchaseLotto(int money) {
        Validator.validateMoney(money);

        int count = money / 1000;
        List<Lotto> lottoList = new ArrayList<Lotto>();

        for (int i = 0; i < count; i++) {
            Lotto lotto = LottoFactory.createLotto(lottoNumberGenerator);
            lottoList.add(lotto);
        }

        lottoRepository.saveLottos(lottoList, money);
    }

}
