package lotto;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputHandler {

	public static void printPurchasedLottos(List<LottoView> lottoViews) {
		System.out.println(lottoViews.size() + "개를 구매했습니다.");
		for (LottoView view : lottoViews) {
			System.out.println(view.format());
		}
	}

	public static void printPurchasedLottosFromDomain(List<Lotto> lottos) {
		List<LottoView> views = lottos.stream().map(LottoView::from).collect(Collectors.toList());

		printPurchasedLottos(views);
	}

	public static void printRankResults(RankResults results) {
		System.out.println();
		System.out.println("당첨 통계");
		System.out.println("---");

		Map<Rank, Integer> counts = results.rankCounts();

		printRankLine("3개 일치", Rank.FIFTH, counts);
		printRankLine("4개 일치", Rank.FOURTH, counts);
		printRankLine("5개 일치", Rank.THIRD, counts);
		printRankLine("5개 일치, 보너스 볼 일치", Rank.SECOND, counts);
		printRankLine("6개 일치", Rank.FIRST, counts);

		double yield = results.getYieldPercent();
		DecimalFormat df = new DecimalFormat("0.0#");
		System.out.println("총 수익률은 " + df.format(yield) + "%입니다.");
	}

	private static void printRankLine(String label, Rank rank, Map<Rank, Integer> counts) {
		int count = counts.getOrDefault(rank, 0);
		String prize = String.format("%,d원", rank.getPrize());
		System.out.println(label + " (" + prize + ") - " + count + "개");
	}

}
