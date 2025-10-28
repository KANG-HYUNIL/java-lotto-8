package lotto;

public class Application {
    public static void main(String[] args) {
        LottoRepository repository = new LottoRepository();
        LottoNumberGenerator generator = new RandomLottoNumberGenerator();
        LottoService service = new LottoService(repository, generator);
        LottoGameController controller = new LottoGameController(service);
        controller.start();
    }
}
