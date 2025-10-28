package lotto;

import java.util.List;
import java.util.stream.Collectors;

public final class LottoView {
    private final List<Integer> numbers;

    public LottoView(List<Integer> numbers) {
        this.numbers = List.copyOf(numbers);
    }

    public static LottoView from(Lotto lotto) {
        return new LottoView(lotto.getNumbers());
    }

    public String format() {
        String joined = numbers.stream()
            .map(String::valueOf)
            .collect(Collectors.joining(", ", "[", "]"));
        return joined;
    }

    public List<Integer> numbers() {
        return numbers;
    }
}
