package algs.exercise.c2.s1;

import java.util.Comparator;

public class Card implements Comparable<Card> {
    // Comparators by suit and by denomination
    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();
    public static final Comparator<Card> DENOM_ORDER = new DenomOrder();
    // Suit of the card (CLUBS = 1, DIAMONDS = 2, HEARTS = 3, SPADES = 4)
    private final int suit;

    // Denomination of the card
    private final int denom;
    public Card(int suit, int denom) {
      if (suit < 1 || suit > 4)
        throw new IllegalArgumentException("Invalid suit");
      if (denom < 1 || denom > 13)
        throw new IllegalArgumentException("Invalid denomination");
      this.suit = suit;
      this.denom = denom;
    }
    // COMPLETE THE FOLLOWING FUNCTION
    public int compareTo(Card that) {
    	if(this.suit<that.suit)
    		return -1;
    	else if(this.suit>that.suit)
    		return 1;
    	else
    	{
    		if(this.denom<that.denom)
    			return -1;
    		else if(this.denom>that.denom)
    			return 1;
    		else
    			return 0;
    	}
    }

    // Compare cards according to the suit only
    private static class SuitOrder implements Comparator<Card> {

		@Override
		public int compare(Card o1, Card o2) {
			// TODO Auto-generated method stub
			return 0;
		}
      // Implementation not shown
    }
    // Compare cards according to the denomination only
    private static class DenomOrder implements Comparator<Card> {

		@Override
		public int compare(Card o1, Card o2) {
			// TODO Auto-generated method stub
			return 0;
		}
      // Implementation not shown
    }
}
