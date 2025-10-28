package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성
    @Test
    @DisplayName("당첨 번호와 비교하여 FIRST 반환")
    void checkRank_First() {
        // given
        Lotto lotto = new Lotto(List.of(1,2,3,4,5,6));
        WinningNumbers winning = new WinningNumbers(List.of(1,2,3,4,5,6), 7);

        // when
        Rank rank = lotto.checkRank(winning);

        // then
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("5개 일치 + 보너스 일치 시 SECOND 반환")
    void checkRank_Second() {
        // given
        Lotto lotto = new Lotto(List.of(1,2,3,4,5,7));
        WinningNumbers winning = new WinningNumbers(List.of(1,2,3,4,5,6), 7);

        // when
        Rank rank = lotto.checkRank(winning);

        // then
        assertThat(rank).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("5개만 일치하면 THIRD 반환")
    void checkRank_Third() {
        // given
        Lotto lotto = new Lotto(List.of(1,2,3,4,5,8));
        WinningNumbers winning = new WinningNumbers(List.of(1,2,3,4,5,6), 7);

        // when
        Rank rank = lotto.checkRank(winning);

        // then
        assertThat(rank).isEqualTo(Rank.THIRD);
    }
}
