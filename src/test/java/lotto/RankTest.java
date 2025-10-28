package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RankTest {

    @Test
    @DisplayName("6개 일치하면 FIRST 반환")
    void from_SixMatches_FIRST() {
        // given / when
        Rank rank = Rank.from(6, false);

        // then
        assertThat(rank).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("5개+보너스면 SECOND, 5개만이면 THIRD")
    void from_FiveMatches_BonusDistinguishes() {
        // given / when
        Rank r2 = Rank.from(5, true);
        Rank r3 = Rank.from(5, false);

        // then
        assertThat(r2).isEqualTo(Rank.SECOND);
        assertThat(r3).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("2개 이하 일치하면 MISS 반환")
    void from_LessThanThree_MISS() {
        // given / when
        Rank r = Rank.from(2, false);

        // then
        assertThat(r).isEqualTo(Rank.MISS);
    }

    @Test
    @DisplayName("6개 일치시 보너스 여부와 관계없이 FIRST 반환")
    void from_SixMatches_RegardlessOfBonus_FIRST() {
        // given / when
        Rank r1 = Rank.from(6, false);
        Rank r2 = Rank.from(6, true);

        // then
        assertThat(r1).isEqualTo(Rank.FIRST);
        assertThat(r2).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("4개 일치하면 FOURTH 반환, 3개 일치하면 FIFTH 반환")
    void from_FourAndThree_ReturnsFourthAndFifth() {
        // given / when
        Rank r4 = Rank.from(4, false);
        Rank r5 = Rank.from(3, false);

        // then
        assertThat(r4).isEqualTo(Rank.FOURTH);
        assertThat(r5).isEqualTo(Rank.FIFTH);
    }
}
