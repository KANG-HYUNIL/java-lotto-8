package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoRepository {

    private final List<Lotto> LottoList = new ArrayList<Lotto>();
    private int totalMoney = 0;

    public int getTotalMoney() {
        return totalMoney;
    }

    public int getLottoCount() {
        return LottoList.size();
    }

    public void saveLottos(List<Lotto> newLottos, int money) {
        LottoList.addAll(newLottos);
        totalMoney += money;
    }

    public List<Lotto> getLottos() {
        return List.copyOf(LottoList);
    }

}
