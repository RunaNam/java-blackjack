# 🃏 블랙잭 미션

### 🎰 사용자와 블랙잭 프로그램의 요청, 응답

1. 게임에 참여할 사람 이름 입력 → 이름을 쉼표로 분리 / `Player`로 저장.
2. `Dealer`와 `Player`모두 2장의 카드를 지급받는다 → 딜러는 처음 뽑은 카드를, 플레이어는 모든 카드를 공개한다.
3. `Player`는 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
    1. 사용자는 y 또는 n을 입력한다
        1. y일 경우 → 사용자가 카드를 뽑아간다.
        2. n일 경우 → 해당 사용자의 턴이 끝난다.
    2. 사용자의 카드를 출력한다.
4. `Dealer` 의 카드를 뽑는다.
    1. 2장의 합계가 16이하 → 카드 1장을 추가로 받는다.
    2. 17점 이상→ 딜러의 턴이 끝난다.
5. 각 플레이어의 카드 합을 출력한다
6. 최종 승패를 출력한다

### ✨입출력 예외 사항

- 사람 이름을 입력할 때, 빈값을 입력하면 안된다. (한명 이상은 무조건 참여)
- 카드 한장 더 받는 것에 대한 입력을 할 때, `y/n` 이외에는 입력할 수 없다.

### 🃏 블랙잭 구현

- [x]  사람 이름을 입력한다.
    - [x]  [예외처리] 이름이 공백인 경우
    - [x]  [예외처리] 빈 값을 입력하는 경우
- [ ]  사람 이름을 쉼표로 분리한다.
- [ ]  사용자 객체를 생성한다.
- [ ]  각 사용자 객체에 카드 2장을 추가한다.
- [ ]  사용자의 카드와 딜러의 첫번째 카드를 출력한다.
- [x]  각 사용자별로 카드를 더 받을지 여부를 입력한다.
    - [x]  [예외처리] `y/n` 이외의 값을 입력한 경우
    - [ ]  y를 입력한 경우 - 카드 1장을 더 뽑는다.
    - [ ]  n을 입력한 경우 - 턴을 끝낸다.
    - [ ]  턴이 끝나면 자신의 카드를 출력한다.
- [ ]  딜러의 카드가 16이하일 경우 카드를 한장 더 뽑는다. (아닐경우, 넘어간다)
- [ ]  전체 카드의 결과를 출력한다.
- [ ]  최종 승패를 출력한다.

### ✔ 추가된 요구 사항

- 모든 엔티티를 작게 유지한다.
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.