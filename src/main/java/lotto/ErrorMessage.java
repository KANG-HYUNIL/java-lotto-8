package lotto;

public enum ErrorMessage {

    INVALID_MONEY_FORMAT("구입 금액은 숫자여야 합니다."),
    INVALID_MONEY_UNIT("구입 금액은 1000원 단위의 양수여야 합니다."),
    INVALID_NUMBER_FORMAT("숫자 형식이 올바르지 않습니다."),
    NUMBER_OUT_OF_RANGE("숫자는 1~45 사이여야 합니다."),
    WINNING_NUMBERS_COUNT("당첨번호는 6개여야 합니다."),
    WINNING_NUMBERS_DUPLICATE("당첨번호는 중복될 수 없습니다."),
    BONUS_NUMBER_DUPLICATE("보너스 번호는 당첨번호와 중복될 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }


    // 헬퍼: 예외를 바로 생성해서 던질 수 있게 함
    public String errorWithMessage() {
        return "[ERROR] " + message;
    }

}
