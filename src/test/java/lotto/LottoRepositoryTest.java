package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoRepositoryTest {

    @Test
    @DisplayName("saveLottos 후 getLottos는 저장된 개수를 반환하고 반환 리스트는 수정 불가이다")
    void saveAndGetLottos_CountAndImmutability() {
        // given
        LottoRepository repo = new LottoRepository();
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        repo.saveLottos(List.of(lotto), 1000);
        List<Lotto> returned = repo.getLottos();

        // then
        assertThat(repo.getLottoCount()).isEqualTo(1);
        assertThat(repo.getTotalMoney()).isEqualTo(1000);
        assertThat(returned).hasSize(1);
        // 불변성 확인: 수정 시 UnsupportedOperationException 기대
        assertThatThrownBy(() -> returned.add(lotto)).isInstanceOf(UnsupportedOperationException.class);
    }

}

