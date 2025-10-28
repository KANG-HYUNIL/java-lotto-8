package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class LottoFactoryTest {

    @Test
    @DisplayName("생성기에서 6개가 아닌 리스트를 반환하면 Lotto 생성 시 예외 발생")
    void createLotto_InvalidSize_Throws() {
        // given
        LottoNumberGenerator badGen = new LottoNumberGenerator() {
            @Override
            public List<Integer> generateLottoNumbers() {
                return List.of(1, 2);
            }
        };

        // when / then
        assertThatThrownBy(() -> LottoFactory.createLotto(badGen))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("생성기에서 6개의 유효한 숫자를 반환하면 Lotto 생성이 정상적으로 이루어진다")
    void createLotto_ValidNumbers_NoException() {
        // given
        LottoNumberGenerator goodGen = new LottoNumberGenerator() {
            @Override
            public List<Integer> generateLottoNumbers() {
                return List.of(6, 5, 4, 3, 2, 1);
            }
        };

        // when
        Throwable thrown = catchThrowable(() -> LottoFactory.createLotto(goodGen));

        // then
        assertThat(thrown).isNull();
    }

    @Test
    @DisplayName("생성기에서 범위를 벗어난 숫자가 있으면 예외 발생")
    void createLotto_OutOfRange_Throws() {
        // given
        LottoNumberGenerator badGen = new LottoNumberGenerator() {
            @Override
            public List<Integer> generateLottoNumbers() {
                return List.of(0, 2, 3, 4, 5, 6);
            }
        };

        // when / then
        assertThatThrownBy(() -> LottoFactory.createLotto(badGen))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("생성기에서 중복된 숫자가 있으면 예외 발생")
    void createLotto_DuplicateNumbers_Throws() {
        // given
        LottoNumberGenerator badGen = new LottoNumberGenerator() {
            @Override
            public List<Integer> generateLottoNumbers() {
                return List.of(1, 2, 3, 4, 5, 5);
            }
        };

        // when / then
        assertThatThrownBy(() -> LottoFactory.createLotto(badGen))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
