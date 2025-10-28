package test.java.lotto;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoGameControllerTest {

    @Test
    void fullGame_flow_printsPurchasedAndStatistics() {
        var repo = new LottoRepository();
        var generator = new LottoNumberGenerator() {
            private final List<List<Integer>> preset = presetTickets();
            private int idx = 0;

            @Override
            public List<Integer> generateLottoNumbers() {
                return preset.get(idx++);
            }

            private List<List<Integer>> presetTickets() {
                List<List<Integer>> list = new ArrayList<>();
                list.add(List.of(8, 21, 23, 41, 42, 43));
                list.add(List.of(3, 5, 11, 16, 32, 38));
                list.add(List.of(7, 11, 16, 35, 36, 44));
                list.add(List.of(1, 8, 11, 31, 41, 42));
                list.add(List.of(13, 14, 16, 38, 42, 45));
                list.add(List.of(7, 11, 30, 40, 42, 43));
                list.add(List.of(2, 13, 22, 32, 38, 45));
                list.add(List.of(1, 3, 5, 14, 22, 45));
                return list;
            }
        };

        var service = new LottoService(repo, generator);
        var controller = new LottoGameController(service);

        String userInput = String.join(System.lineSeparator(), "8000", "1,3,5,9,10,11", "8") + System.lineSeparator();

        var in = new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        var baos = new ByteArrayOutputStream();
        var ps = new PrintStream(baos);
        var oldOut = System.out;
        System.setOut(ps);

        controller.start();

        System.out.flush();
        System.setOut(oldOut);

        String output = baos.toString(StandardCharsets.UTF_8);

        assertThat(output).contains("8개를 구매했습니다.");
        assertThat(output).contains("[8, 21, 23, 41, 42, 43]");
        assertThat(output).contains("[1, 3, 5, 14, 22, 45]");
        assertThat(output).contains("당첨 통계");
        assertThat(output).contains("3개 일치 (5,000원) - 1개");
        assertThat(output).contains("총 수익률은 62.5%입니다.");
    }

}
