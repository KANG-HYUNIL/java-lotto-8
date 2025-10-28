package lotto;

import java.util.List;

public class LottoGameController {

	private final LottoService lottoService;

	public LottoGameController(LottoService lottoService) {
		this.lottoService = lottoService;
	}

	public void start() {
		int money = readPurchaseAmount();
		lottoService.purchaseLotto(money);
		List<Lotto> purchased = lottoService.getPurchasedLottos();
		OutputHandler.printPurchasedLottosFromDomain(purchased);

		WinningNumbers winningNumbers = readWinningNumbersAndBonus();
		RankResults results = lottoService.getRankResults(winningNumbers);
		OutputHandler.printRankResults(results);
	}

	private int readPurchaseAmount() {
		while (true) {
			try {
				String input = InputHandler.getMoneyInput();
				int money = InputParser.parseMoneyToInt(input);
				Validator.validateMoney(money);
				return money;
			} catch (IllegalArgumentException | IllegalStateException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private WinningNumbers readWinningNumbersAndBonus() {
		while (true) {
			try {
				String winningInput = InputHandler.getWinningNumbersInput();
				var numbers = InputParser.parseWinningNumbers(winningInput);
				Validator.validateWinningNumbersCount(numbers);
				Validator.validateWinningNumbersDuplicate(numbers);
				Validator.validateLottoNumberRange(numbers);

				String bonusInput = InputHandler.getBonusNumberInput();
				int bonus = InputParser.parseBonusNumber(bonusInput);
				Validator.validateBonusNumberRange(bonus);
				Validator.validateBonusNumberDuplicate(bonus, numbers);

				return new WinningNumbers(numbers, bonus);
			} catch (IllegalArgumentException | IllegalStateException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
