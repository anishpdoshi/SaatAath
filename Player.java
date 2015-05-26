import java.util.ArrayList;

public abstract class Player
{
	protected Card[] tops;
	protected Card[] bottoms;
	protected Card[] privateCards;
	protected Card[] enemyTops;
	protected Card[] enemyBottoms;
	protected Card[] enemyPrivateCards;
	public int SUR;
	public int numHands = 0;
	public int numCards = 0;

	public Player(Card[] tops, Card[] bottoms, Card[] privates, Card[] enemyTops, Card[] enemyBottoms, Card[] enemyPrivates)
	{
		this.tops = tops;
		this.bottoms = bottoms;
		privateCards = privates; //hehe

		this.enemyTops = enemyTops;
		this.enemyBottoms = enemyBottoms;
		enemyPrivateCards = enemyPrivates; //hehe
		
		numCards += tops.length + bottoms.length + privateCards.length;
	}

	protected void putNone(char c, int index)
	{
		getAccording(c)[index] = null;
	}

	protected Card[] getAccording(char c)
	{
		Card[] selected = null;
		switch (c) 
		{
			case 'T' : selected = tops; break;
			case 'B' : selected = bottoms; break;
			case 'P' : selected = privateCards; break;
		}
		return selected;
	}

	// protected char getAbbrev(Card[] arr)
	// {
	// 	if (arr == tops) {
	// 		return "T";
	// 	} else if (arr == bottoms) {
	// 		return "B";
	// 	} else if (arr == privateCards) {
	// 		return "P";
	// 	} else {
	// 		return "?";
	// 	}
	// }

	//moves then cards
	public ArrayList[] getAllLegalMoves(Card placedCard)
	{
		ArrayList<String> moves = new ArrayList<String>();
		ArrayList<Card> legalCards = new ArrayList<Card>();

		ArrayList<String> suitMoves = null;
		ArrayList<Card> suitLegalCards = null;

		int playedSuit = -1;
		boolean cardPresent = (placedCard != null);
		if (cardPresent)
		{
			playedSuit = placedCard.suit();
			suitMoves = new ArrayList<String>();
			suitLegalCards = new ArrayList<Card>();
		}

		for (int i = 0; i < 6; i++)
		{
			if (tops[i] != null)
			{
				legalCards.add(tops[i]);
				moves.add("T" + i);
				if (cardPresent && tops[i].suit() == playedSuit)
				{
					suitLegalCards.add(tops[i]);
					suitMoves.add("T" + i);
				}
			}
			else if (bottoms[i] != null)
			{
				legalCards.add(bottoms[i]);
				moves.add("B" + i);	
				if (cardPresent && bottoms[i].suit() == playedSuit)
				{
					suitLegalCards.add(bottoms[i]);
					suitMoves.add("B" + i);
				}
			}
		}

		for (int i = 0; i < 3; i++)
		{
			if (privateCards[i] != null)
			{
				legalCards.add(privateCards[i]);
				moves.add("P" + i);	
				if (cardPresent && privateCards[i].suit() == playedSuit)
				{
					suitLegalCards.add(privateCards[i]);
					suitMoves.add("P" + i);
				}
			}
		}
		if (cardPresent && suitMoves.size() > 0)
		{
			return new ArrayList[] {suitMoves, suitLegalCards};
		}
		else 
		{
			return new ArrayList[] {moves, legalCards};
		}	
	}

	public ArrayList[] getAllLegalMoves2(Card placedCard)
	{
		ArrayList<Object[]> moves = new ArrayList<Object[]>();
		ArrayList<Card> legalCards = new ArrayList<Card>();

		ArrayList<Object[]> suitMoves = null;
		ArrayList<Card> suitLegalCards = null;

		int playedSuit = -1;
		boolean cardPresent = (placedCard != null);
		if (cardPresent)
		{
			playedSuit = placedCard.suit();
			suitMoves = new ArrayList<Object[]>();
			suitLegalCards = new ArrayList<Card>();
		}
		Object[] mv;
		for (int i = 0; i < 6; i++)
		{
			if (tops[i] != null)
			{
				legalCards.add(tops[i]);
				mv = new Object[] {"T", i};
				moves.add(mv);
				if (cardPresent && tops[i].suit() == playedSuit)
				{
					suitLegalCards.add(tops[i]);
					suitMoves.add(mv);
				}
			}
			else if (bottoms[i] != null)
			{
				legalCards.add(bottoms[i]);
				mv = new Object[] {"B", i};
				moves.add(mv);	
				if (cardPresent && bottoms[i].suit() == playedSuit)
				{
					suitLegalCards.add(bottoms[i]);
					suitMoves.add(mv);
				}
			}
			if (i < 3 && privateCards[i] != null)
			{
				legalCards.add(privateCards[i]);
				mv = new Object[] {"P", i};
				moves.add(mv);	
				if (cardPresent && privateCards[i].suit() == playedSuit)
				{
					suitLegalCards.add(privateCards[i]);
					suitMoves.add(mv);
				}
			}
		}

		if (cardPresent && suitMoves.size() > 0)
		{
			return new ArrayList[] {suitMoves, suitLegalCards};
		}
		else 
		{
			return new ArrayList[] {moves, legalCards};
		}	
	}

	protected ArrayList<Card> getAllAvailable()
	{
		ArrayList<Card> availables = new ArrayList<Card>();
		for (int i = 0; i < 6; i++)
		{
			if (tops[i] != null)
			{
				availables.add(tops[i]);
			}
			else if (bottoms[i] != null)
			{
				availables.add(bottoms[i]);	
			}
		}

		for (int i = 0; i < 3; i++)
		{
			if (privateCards[i] != null)
			{
				availables.add(privateCards[i]);
			}
		}
		return availables;
	}

	public String toString()
	{
		String str = "\n\n";
		str += "\t\t\t\tTHEM\n\n";
		str += "Priv:\t\t\t" + Card.printSet(enemyPrivateCards) + "\t\n\n";
		str += "Bots:\t\t" + Card.printSet(enemyBottoms) + "\n";
		str += "Tops:\t\t" + Card.printSet(enemyTops) + "\n\n\n";
		
		
		str += "Tops:\t\t" + Card.printSet(tops) + "\n";
		str += "Bots:\t\t" + Card.printSet(bottoms) + "\n\n";
		str += "Priv:\t\t\t" + Card.printSet(privateCards) + "\t\n\n";
		str += "\t\t\t\tUS\n\n";

		return str;
	}

	//Returns the call of sur
	public abstract int getSurCall();

	//Returns the chosen move, given that it is legal
	public abstract String getMove(Card placedCard);
}