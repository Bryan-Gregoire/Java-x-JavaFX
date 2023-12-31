package esi.atl.g53735.model;

import java.util.List;

/**
 *
 * @author g53735
 */
public interface Model {

    public Deck getGameDeck();

    public Players getPlayer();

    public Bank getBank();

    public int getGold();

    public int getBet();

    public void setBet(int bet);

    public void beginHandPlayer();

    public void playerDrawCard(Players players);

    public boolean checkAbove21(Players players);

    public void resetCards(List<Card> hand, Deck gameDeck);

    public void winGold();

    public void loseGold();

    public void resetScore();

    public boolean checkBroke();

    public boolean notCorrectBet();

    public void startOfRound();

    public boolean bankScoreUnder17();
    
    public boolean checkPlayerWin();
}
