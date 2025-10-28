package lotto;
import camp.nextstep.edu.missionutils.Console;

public class InputHandler {

    public static String getMoneyInput() {
        System.out.println("구입금액을 입력해 주세요.");
        return Console.readLine();
    }

    public static String getWinningNumbersInput() {
        System.out.println("당첨 번호를 입력해 주세요.");
        return Console.readLine();
    }

    public static String getBonusNumberInput() {
        System.out.println("보너스 번호를 입력해 주세요.");
        return Console.readLine();
    }

}
