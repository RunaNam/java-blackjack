package controller;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final InputView inputView = InputView.getInstance();
    private final OutputView outputView = OutputView.getInstance();

    public void run() {
        Players players = createPlayers();
        Dealer dealer = new Dealer();
        Deck deck = Deck.of(Card.getShuffledCardCache());

        initialTurn(players, dealer, deck);
        hitCard(players, dealer, deck);
        showFinalTurn(players, dealer);
        showResult(players, dealer);
    }

    private Players createPlayers() {
        List<String> playerNames = inputView.inputPlayerName();
        List<Integer> playerMoneys = inputView.inputPlayerMoney(playerNames);
        try {
            return Players.of(playerNames, playerMoneys);
        } catch (IllegalArgumentException exception) {
            outputView.printError(exception.getMessage());
            return createPlayers();
        }
    }

    private void initialTurn(Players players, Dealer dealer, Deck deck) {
        players.runInitialTurn(deck);
        dealer.hitInitialTurn(deck);
        outputView.showInitialTurnStatus(players, dealer);
    }

    private void hitCard(Players players, Dealer dealer, Deck deck) {
        hitCardByPlayers(players, deck);
        hitCardByDealer(dealer, deck);
    }


    private void hitCardByPlayers(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            hitCardByPlayer(player, deck);
        }
    }

    private void hitCardByPlayer(Player player, Deck deck) {
        while (player.canDrawCard() && inputView.inputMoreCardOrNot(player.getName())) {
            player.hit(deck.handOut());
            outputView.showPlayerCardStatus(player);
        }
    }

    private void hitCardByDealer(Dealer dealer, Deck deck) {
        while (dealer.canDrawCard()) {
            dealer.hit(deck.handOut());
            outputView.showDealerHitCardMessage();
        }
    }

    private void showFinalTurn(Players players, Dealer dealer) {
        outputView.showFinalTurnStatus(players, dealer);
    }

    private void showResult(Players players, Dealer dealer) {
        Map<Player, Integer> playerResult = players.checkResults(dealer);
        List<Integer> playerMoney = new ArrayList<>(playerResult.values());
        outputView.showResult(dealer.getName(), dealer.getResultMoney(playerMoney), playerResult);
    }
}
