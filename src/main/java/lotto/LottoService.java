package lotto;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

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

    public RankResults getRankResults(WinningNumbers winningNumbers) {
        List<Lotto> lottos = lottoRepository.getLottos();
        Map<Rank, Integer> counts = new EnumMap<>(Rank.class);
        for (Rank r : Rank.values()) {
            counts.put(r, 0);
        }

        long totalPrize = 0L;
        for (Lotto lotto : lottos) {
            Rank rank = lotto.checkRank(winningNumbers);
            counts.put(rank, counts.get(rank) + 1);
            totalPrize += rank.getPrize();
        }

        int totalSpent = lottoRepository.getTotalMoney();
        return new RankResults(counts, totalPrize, totalSpent);
    }

    public List<Lotto> getPurchasedLottos() {

        return lottoRepository.getLottos();
    }

}
