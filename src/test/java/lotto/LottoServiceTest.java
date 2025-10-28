package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoServiceTest {

    @Test
    @DisplayName("정상 금액으로 구매하면 repository에 요청한 장수만큼 저장되고 totalMoney가 반영된다")
    void purchaseLotto_ValidMoney_SavesTicketsAndTotalMoney() {
        // given
        LottoRepository repo = new LottoRepository();
        LottoNumberGenerator fixedGenerator = new LottoNumberGenerator() {
            @Override
            public List<Integer> generateLottoNumbers() {
                return List.of(1, 2, 3, 4, 5, 6);
            }
        };
        LottoService service = new LottoService(repo, fixedGenerator);

        // when
        service.purchaseLotto(3000); // 3장

        // then
        assertThat(repo.getLottoCount()).isEqualTo(3);
        assertThat(repo.getTotalMoney()).isEqualTo(3000);
    }

    @Test
    @DisplayName("1000원 단위가 아닌 금액으로 구매 시 예외 발생")
    void purchaseLotto_InvalidMoney_Throws() {
        // given
        LottoRepository repo = new LottoRepository();
        LottoNumberGenerator gen = new LottoNumberGenerator() {
            @Override
            public List<Integer> generateLottoNumbers() {
                return List.of(1, 2, 3, 4, 5, 6);
            }
        };
        LottoService service = new LottoService(repo, gen);

        // when / then
        assertThatThrownBy(() -> service.purchaseLotto(1500))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("생성기가 잘못된 숫자를 반환하면 구매는 실패하고 저장소는 변경되지 않는다")
    void purchaseLotto_GeneratorReturnsInvalid_ThrowsAndRepositoryUnchanged() {
        // given
        LottoRepository repo = new LottoRepository();
        LottoNumberGenerator badGen = new LottoNumberGenerator() {
            @Override
            public List<Integer> generateLottoNumbers() {
                return List.of(1,2); // invalid size
            }
        };
        LottoService service = new LottoService(repo, badGen);

        // when / then
        assertThatThrownBy(() -> service.purchaseLotto(1000))
                .isInstanceOf(IllegalArgumentException.class);

        // repository should remain unchanged
        assertThat(repo.getLottoCount()).isEqualTo(0);
        assertThat(repo.getTotalMoney()).isEqualTo(0);
    }


    @Test
    @DisplayName("0원으로 구매 시 예외 발생")
    void purchaseLotto_ZeroMoney_Throws() {
        // given
        LottoRepository repo = new LottoRepository();
        LottoNumberGenerator gen = new LottoNumberGenerator() {
            @Override
            public List<Integer> generateLottoNumbers() {
                return List.of(1,2,3,4,5,6);
            }
        };
        LottoService service = new LottoService(repo, gen);

        // when / then
        assertThatThrownBy(() -> service.purchaseLotto(0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
