# java-lotto-precourse

## 구현할 기능 단위 모듈 선언


1. [ ] 입력을 받는 모듈(InputHandler)
    - 로또 구입 금액, 당첨 번호(6개), 보너스 번호 입력을 담당
    - 기본 파싱/형식 검사 수행, 실패 시 IllegalArgumentException 발생

2. [ ] 양식에 맞게 출력해주는 모듈(OutputHandler)
    - 구매 개수, 로또별 번호, 당첨 통계, 수익률 등 화면 출력 전담
    - "[ERROR]" 접두어를 붙여 에러 메시지 출력(에러 포맷팅 포함)


3. [ ] 로또 생성기 인터페이스(LottoGenerator) 및 구현(RandomLottoGenerator)
    - 중복 없는 6개 숫자 생성하여 LottoTicket 반환
    - 테스트용 고정 시드 구현 가능하게 설계

4. [ ] 구매 관리(PurchaseManager)
    - 구입 금액 검증(1000원 단위), 발행 가능한 장수 계산
    - 잔액/입력 오류 처리 책임

5. [ ] 공통 검증 유틸(Validator)
    - 입력 포맷, 숫자 범위, 중복 여부 등 공통 검증 로직 제공
    - 도메인 객체 내부에서도 재사용


7. [ ] 구매 저장소(LottoRepository)
   - 발행된/구매한 LottoTicket 목록 보관 및 조회 제공

8. [ ] 당첨 번호(WinningNumbers)
   - 당첨 6개 숫자 + 보너스 번호 보유 및 중복 검사
   - 티켓과의 비교용 유틸리티 제공

9. [ ] 등수 정의(Enum: Rank)
   - 1~5등 기준과 상금(금액)을 정의
   - 매칭 수와 보너스 포함 여부로 Rank 결정하는 팩토리 메서드 포함

10. [ ] 결과 계산기(LottoStatistics / ResultCalculator)
    - 모든 티켓을 비교하여 각 Rank별 당첨 수 집계
    - 총 당첨금 계산 및 수익률(ROI) 산출


