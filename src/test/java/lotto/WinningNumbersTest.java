package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumbersTest {

	@Test
	@DisplayName("정상적인 당첨번호와 보너스는 예외가 발생하지 않는다")
	void validWinningNumbers_NoException() {
		// given
		List<Integer> winning = List.of(1,2,3,4,5,6);
		int bonus = 7;

		// when / then -- 생성자 내부에서 Validator 호출하므로 예외가 없으면 통과
		new WinningNumbers(winning, bonus);
	}

	@Test
	@DisplayName("당첨번호가 6개가 아니면 예외 발생")
	void invalidCount_Throws() {
		// given
		List<Integer> winning = List.of(1,2,3,4,5);

		// when / then
		assertThatThrownBy(() -> new WinningNumbers(winning, 7))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("당첨번호에 중복이 있으면 예외 발생")
	void duplicateWinningNumbers_Throws() {
		// given
		List<Integer> winning = List.of(1,2,3,4,5,5);

		// when / then
		assertThatThrownBy(() -> new WinningNumbers(winning, 7))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("보너스가 당첨번호와 중복되면 예외 발생")
	void bonusDuplicate_Throws() {
		// given
		List<Integer> winning = List.of(1,2,3,4,5,6);
		int bonus = 6;

		// when / then
		assertThatThrownBy(() -> new WinningNumbers(winning, bonus))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("당첨번호가 범위를 벗어나면 예외 발생")
	void outOfRange_Throws() {
		// given
		List<Integer> winning = List.of(0,2,3,4,5,6);

		// when / then
		assertThatThrownBy(() -> new WinningNumbers(winning, 7))
				.isInstanceOf(IllegalArgumentException.class);
	}
}
