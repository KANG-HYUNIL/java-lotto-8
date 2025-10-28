package lotto;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    // TODO: 추가 기능 구현

    public Rank checkRank(WinningNumbers winningNumbers) {

        Set<Integer> winningSet = new HashSet<>(winningNumbers.getWinningNumbers());
        int bonusNumber = winningNumbers.getBonusNumber();

        int matchCount = 0;
        for (int number : numbers) {
            if (winningSet.contains(number)) {
                matchCount++;
            }
        }

        boolean bonusMatch = numbers.contains(bonusNumber);

        return Rank.from(matchCount, bonusMatch);
    }

    public String toString() {
        return numbers.toString();
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

}
