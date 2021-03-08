package blackjack.domain.player;

import static blackjack.domain.card.type.CardNumberType.BLACKJACK;

import blackjack.domain.ResultType;
import blackjack.domain.UserDrawContinue;

public class User extends AbstractPlayer {
    private boolean isDrawStop = false;

    public User(String name) {
        super(name);
    }

    public boolean isDrawStop() {
        return isDrawStop;
    }

    @Override
    public boolean isCanDraw() {
        return !(isDrawStop() || isOverBlackJack());
    }

    private boolean isOverBlackJack() {
        return this.getScore() > BLACKJACK;
    }

    public boolean isDrawContinue(UserDrawContinue userDrawContinue) {
        if (userDrawContinue.isContinue()) {
            return true;
        }
        isDrawStop = true;
        return false;
    }

    public ResultType getResult(Dealer dealer) {
        ResultType bustResult = getBustResult(dealer);
        if (bustResult != null) {
            return bustResult;
        }
        ResultType blackJackResult = getBlackJackResult(dealer);
        if (blackJackResult != null) {
            return blackJackResult;
        }
        return getRestResult(dealer);
    }

    private ResultType getBustResult(Dealer dealer) {
        if (isBust()) {
            return ResultType.LOSS;
        }
        if (dealer.isBust()) {
            return ResultType.WIN;
        }
        return null;
    }

    private ResultType getBlackJackResult(Dealer dealer) {
        if (isBlackJack() && dealer.isBlackJack()) {
            return ResultType.DRAW;
        }
        if (isBlackJack() && !dealer.isBlackJack()) {
            return ResultType.WIN;
        }
        if (!isBlackJack() && dealer.isBlackJack()) {
            return ResultType.LOSS;
        }
        return null;
    }

    private ResultType getRestResult(Dealer dealer) {
        if (getScore() > dealer.getScore()) {
            return ResultType.WIN;
        }
        if (getScore() == dealer.getScore()) {
            return ResultType.DRAW;
        }
        return ResultType.LOSS;
    }
}