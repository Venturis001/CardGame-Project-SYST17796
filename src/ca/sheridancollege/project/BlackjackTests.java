/**
 * SYST 17796 Group Project - Blackjack
 */
package ca.sheridancollege.project;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Our test class for the Blackjack game. We wrote our own small check()
 * method instead of using a JUnit library so that the tests run straight from
 * the project with nothing extra to install. Run this class the same way you
 * run StartBlackjack and it prints a pass or fail line for every test.
 * @author Esosa Emokpae, Jankat Erel, Ranbir Dhanki - July 2026
 */
public class BlackjackTests
{
    private static int passed = 0;
    private static int failed = 0;

    /**
     * Checks one thing and prints whether it worked.
     * @param testName what we are testing, printed on screen
     * @param expected the answer we should get
     * @param actual the answer the code actually gave
     */
    private static void check(String testName, int expected, int actual)
    {
        if (expected == actual)
        {
            System.out.println("  PASS  " + testName);
            passed++;
        }
        else
        {
            System.out.println("  FAIL  " + testName
                    + "   (expected " + expected + " but got " + actual + ")");
            failed++;
        }
    }

    /**
     * Same as above but for a yes/no answer instead of a number.
     * @param testName what we are testing, printed on screen
     * @param condition should be true if the test passed
     */
    private static void check(String testName, boolean condition)
    {
        if (condition)
        {
            System.out.println("  PASS  " + testName);
            passed++;
        }
        else
        {
            System.out.println("  FAIL  " + testName);
            failed++;
        }
    }

    /**
     * Builds a hand out of the ranks we pass in, so the tests stay short.
     * All the cards are spades because the suit makes no difference to the total.
     * @param ranks the ranks the hand should hold
     * @return the hand that was built
     */
    private static Hand handOf(Rank... ranks)
    {
        Hand hand = new Hand();
        for (Rank rank : ranks)
        {
            hand.addCard(new PlayingCard(rank, Suit.SPADES));
        }
        return hand;
    }

    /**
     * Runs every test and prints a summary at the end.
     * @param args not used
     */
    public static void main(String[] args)
    {
        System.out.println("=== SYST 17796 Blackjack - Test Results ===");

        testPlayingCard();
        testDeck();
        testHandTotals();
        testDealerHouseRule();
        testGameSetUp();

        System.out.println();
        System.out.println("===========================================");
        System.out.println("  Tests run: " + (passed + failed)
                + "   Passed: " + passed + "   Failed: " + failed);
        System.out.println("===========================================");
    }

    /**
     * Tests that a card knows its own rank, suit and Blackjack value.
     */
    private static void testPlayingCard()
    {
        System.out.println();
        System.out.println("PlayingCard and Rank");

        PlayingCard seven = new PlayingCard(Rank.SEVEN, Suit.HEARTS);
        check("a card keeps the rank it was built with", seven.getRank() == Rank.SEVEN);
        check("a card keeps the suit it was built with", seven.getSuit() == Suit.HEARTS);
        check("a number card is worth its face value", 7, seven.getValue());

        check("a jack is worth 10", 10, new PlayingCard(Rank.JACK, Suit.CLUBS).getValue());
        check("a queen is worth 10", 10, new PlayingCard(Rank.QUEEN, Suit.CLUBS).getValue());
        check("a king is worth 10", 10, new PlayingCard(Rank.KING, Suit.CLUBS).getValue());
        check("an ace starts out worth 11", 11, new PlayingCard(Rank.ACE, Suit.CLUBS).getValue());

        check("a card prints as RANK of SUIT",
                "ACE of SPADES".equals(new PlayingCard(Rank.ACE, Suit.SPADES).toString()));
        check("there are 13 ranks", 13, Rank.values().length);
        check("there are 4 suits", 4, Suit.values().length);
    }

    /**
     * Tests that a new deck is a proper 52 card deck and that dealing works.
     */
    private static void testDeck()
    {
        System.out.println();
        System.out.println("Deck");

        Deck deck = new Deck();
        check("a new deck holds 52 cards", 52, deck.showCards().size());

        //every card should be different, so 13 ranks times 4 suits with no repeats
        boolean allDifferent = true;
        for (int i = 0; i < deck.showCards().size(); i++)
        {
            for (int j = i + 1; j < deck.showCards().size(); j++)
            {
                if (deck.showCards().get(i).toString().equals(deck.showCards().get(j).toString()))
                {
                    allDifferent = false;
                }
            }
        }
        check("a new deck has no duplicate cards", allDifferent);

        deck.shuffle();
        check("shuffling does not lose any cards", 52, deck.showCards().size());

        Card dealt = deck.dealCard();
        check("dealing gives back a card", dealt != null);
        check("dealing takes the card out of the deck", 51, deck.showCards().size());

        //empty the rest of the deck one card at a time
        for (int i = 0; i < 51; i++)
        {
            deck.dealCard();
        }
        check("the whole deck can be dealt out", 0, deck.showCards().size());

        //asking for one more should give a clear error instead of crashing
        boolean threwProperly = false;
        try
        {
            deck.dealCard();
        }
        catch (IllegalStateException e)
        {
            threwProperly = true;
        }
        check("dealing from an empty deck gives a clear error", threwProperly);
    }

    /**
     * Tests the Blackjack totals. Most of these are about Aces because that is
     * the part of the total that is easiest to get wrong.
     */
    private static void testHandTotals()
    {
        System.out.println();
        System.out.println("Hand totals");

        check("an empty hand is 0", 0, new Hand().getTotal());
        check("7 + 5 is 12", 12, handOf(Rank.SEVEN, Rank.FIVE).getTotal());
        check("king + queen is 20 and not 40", 20, handOf(Rank.KING, Rank.QUEEN).getTotal());
        check("ace + king is 21, the ace stays at 11",
                21, handOf(Rank.ACE, Rank.KING).getTotal());
        check("ace + king + queen is 21, the ace drops to 1",
                21, handOf(Rank.ACE, Rank.KING, Rank.QUEEN).getTotal());
        check("two aces are 12, they cannot both be 11",
                12, handOf(Rank.ACE, Rank.ACE).getTotal());
        check("ace + ace + 9 is 21, only one ace drops",
                21, handOf(Rank.ACE, Rank.ACE, Rank.NINE).getTotal());
        check("three aces + 8 is 21",
                21, handOf(Rank.ACE, Rank.ACE, Rank.ACE, Rank.EIGHT).getTotal());
        check("king + queen + jack really does bust at 30",
                30, handOf(Rank.KING, Rank.QUEEN, Rank.JACK).getTotal());
        check("a hand still busts when dropping the ace is not enough",
                31, handOf(Rank.ACE, Rank.KING, Rank.QUEEN, Rank.JACK).getTotal());
        check("a hand counts the cards it holds",
                2, handOf(Rank.TWO, Rank.THREE).showCards().size());
    }

    /**
     * Tests the dealer's house rule: hit on 16 or less, stand on 17 or more.
     * The dealer prints while it plays, so the printing is sent somewhere else
     * during these tests to keep the results readable.
     */
    private static void testDealerHouseRule()
    {
        System.out.println();
        System.out.println("Dealer house rule");

        PrintStream realOutput = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream()));

        Dealer standsOn18 = new Dealer();
        standsOn18.setDeck(new Deck());
        standsOn18.setHand(handOf(Rank.KING, Rank.EIGHT));
        standsOn18.play();

        Dealer standsOn17 = new Dealer();
        standsOn17.setDeck(new Deck());
        standsOn17.setHand(handOf(Rank.KING, Rank.SEVEN));
        standsOn17.play();

        Dealer hitsOn16 = new Dealer();
        hitsOn16.setDeck(new Deck());
        hitsOn16.setHand(handOf(Rank.KING, Rank.SIX));
        hitsOn16.play();

        Dealer startsLow = new Dealer();
        startsLow.setDeck(new Deck());
        startsLow.setHand(handOf(Rank.TWO, Rank.THREE));
        startsLow.play();

        System.setOut(realOutput);

        check("the dealer is named Dealer", "Dealer".equals(new Dealer().getPlayerID()));
        check("the dealer stands on 18 and takes no card",
                2, standsOn18.getHand().showCards().size());
        check("the dealer stands on exactly 17",
                2, standsOn17.getHand().showCards().size());
        check("the dealer hits on 16", hitsOn16.getHand().showCards().size() > 2);
        check("the dealer always finishes on 17 or more",
                startsLow.getHand().getTotal() >= 17);
    }

    /**
     * Tests that players can be added to the game and that the game keeps its name.
     */
    private static void testGameSetUp()
    {
        System.out.println();
        System.out.println("Game set up");

        BlackjackGame game = new BlackjackGame("Blackjack");
        check("the game keeps the name it was given", "Blackjack".equals(game.getGameName()));
        check("a brand new game has no players yet", 0, game.getPlayers().size());

        game.getPlayers().add(new BlackjackPlayer("Esosa"));
        game.getPlayers().add(new BlackjackPlayer("Jankat"));
        game.getPlayers().add(new BlackjackPlayer("Ranbir"));
        check("three players can join the game", 3, game.getPlayers().size());
        check("a player keeps the name they typed in",
                "Esosa".equals(game.getPlayers().get(0).getPlayerID()));

        BlackjackPlayer player = new BlackjackPlayer("Test");
        check("a new player starts with an empty hand", 0, player.getHand().showCards().size());
        player.setHand(handOf(Rank.TEN, Rank.NINE));
        check("a player's hand can be replaced for a new round",
                19, player.getHand().getTotal());
    }
}
