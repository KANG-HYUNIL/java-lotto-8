package lotto;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public record RankResults(Map<Rank, Integer> rankCounts, long totalPrize, int totalSpent) {

    public RankResults(Map<Rank, Integer> rankCounts, long totalPrize, int totalSpent) {
        this.rankCounts = Collections.unmodifiableMap(new EnumMap<>(rankCounts));
        this.totalPrize = totalPrize;
        this.totalSpent = totalSpent;
    }

    public double getYieldPercent() {
        if (totalSpent == 0) {
            return 0.0;
        }
        return (double) totalPrize / totalSpent * 100.0;
    }


}
