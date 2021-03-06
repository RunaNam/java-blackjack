package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Test
    @DisplayName("참가자들의 이름은 중복이 없어야 한다.")
    public void validateOverlappedNames() {
        List<Participant> participantGroup = Arrays.asList(
            new Player("jason"),
            new Player("jason")
        );

        assertThatCode(() -> {
            Participants.of(participantGroup);
        }).isInstanceOf(IllegalArgumentException.class)
          .hasMessage("참가자들의 이름은 중복이 없어야 합니다.");
    }

    @Test
    @DisplayName("플레이어들이 카드를 2장씩 받는다.")
    public void receiveDefaultCards() {
        CardDeck cardDeck = new CardDeck();
        Participants participants = Participants.of(Arrays.asList(new Player("jason")));
        Participant jason = participants.toList()
                                        .get(0);
        List<Card> jasonCards = jason.getCards();
        participants.receiveDefaultCards(cardDeck);
        int afterSize = jasonCards.size();
        assertThat(afterSize).isEqualTo(2);
    }
}
