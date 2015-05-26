public class Card
{
	public final static int SPADES = 0, 
							HEARTS = 1, 
							DIAMONDS = 2,
							CLUBS = 3;
	public final static Card CARD_MASK = new Card(-1, -1);

	public static String printSet(Card[] set)
	{
		String str = "";
		for (int i = 0; i < set.length; i++)
		{
			if (set[i] != null)
			{
				str += set[i].toShortString();
			}
			else 
			{
				str += "__";
			}
			str += "\t";
		}
		return str;
	}

	public static Card[] maskSet(int numMasks)
	{
		Card[] c = new Card[numMasks];
		for (int i = 0; i < numMasks; i++)
		{
			c[i] = Card.CARD_MASK;
		}
		return c;
	}

	private int suit;
	private int number;

	public Card(int suit, int number)
	{
		this.suit = suit;
		this.number = number;
	}

	public int number()
	{
		return number;
	}

	public int suit()
	{
		return suit;
	}

	public static String suitToString(int suit)
	{
		switch (suit) {
			case 0: return "Spades";  
			case 1: return "Hearts";  
			case 2: return "Diamonds";  
			case 3: return "Clubs";  
			default: return "invalid suit";
		}
	}

	public String toString()
	{
		if (this.number == -1)
		{
			return "??";
		}
		String numString = "INVALID NUM";
		switch (this.number) {
			case 2: numString = "Two"; break; 
			case 3: numString = "Three"; break; 
			case 4: numString = "Four"; break; 
			case 5: numString = "Five"; break; 
			case 6: numString = "Six"; break; 
			case 7: numString = "Seven"; break; 
			case 8: numString = "Eight"; break; 
			case 9: numString = "Nine"; break; 
			case 10: numString = "Ten"; break; 
			case 11: numString = "Jack"; break; 
			case 12: numString = "Queen"; break; 
			case 13: numString = "King"; break; 
			case 14: numString = "Ace"; break; 
		}
		return numString + " of " + Card.suitToString(this.suit);
	}

	public String toShortString()
	{
		if (this.number == -1)
		{
			return "??";
		}
		String numString = "";
		numString += this.number;
		switch (this.number) { 
			case 11: numString = "J"; break; 
			case 12: numString = "Q"; break; 
			case 13: numString = "K"; break; 
			case 14: numString = "A"; break; 
		}
		String suitString = "?";
		switch (this.suit) {
			case 0: suitString = "\u2660"; break; 
			case 1: suitString = "\u2665"; break; 
			case 2: suitString = "\u2666"; break; 
			case 3: suitString = "\u2663"; break; 
		}
		return numString + suitString;
	}
}