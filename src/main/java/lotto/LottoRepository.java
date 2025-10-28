package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoRepository {

    private final List<Lotto> lottoList = new ArrayList<Lotto>();
    private int totalMoney = 0;

    public int getTotalMoney() {
        return totalMoney;
    }

    public int getLottoCount() {
        return lottoList.size();
    }

    public void saveLottos(List<Lotto> newLottos, int money) {

        if (newLottos == null || newLottos.isEmpty()) {
            return;
        }

        lottoList.addAll(newLottos);
        totalMoney = money;
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottoList);
    }

}
