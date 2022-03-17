package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Cards {

    private static final String ACE_LETTER = "A";
    private static final int ACE_ADDITIONAL_VALUE = 10;
    private static final int BLACKJACK_MAX_VALUE_CRITERIA = 21;

    private final List<Card> cards;

    public Cards(List<Card> initialCards) {
        cards = new ArrayList<>(initialCards);
    }

    public int calculateScore() {
        int aceAmount = countAceAmount();
        if (aceAmount > 0) {
            return judgeAdvantageResult(aceAmount);
        }
        return calculateSum();
    }

    private int judgeAdvantageResult(int aceAmount) {
        return IntStream.range(0, aceAmount + 1)
            .map(aceCount -> calculateSum() + aceCount * ACE_ADDITIONAL_VALUE)
            .filter(result -> result < BLACKJACK_MAX_VALUE_CRITERIA)
            .max()
            .orElse(calculateSum());
    }

    private int calculateSum() {
        return cards.stream()
            .mapToInt(Card::getScore)
            .sum();
    }

    private int countAceAmount() {
        return (int) cards.stream()
            .filter(card -> card.getDenomination().equals(ACE_LETTER))
            .count();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
