import java.util.ArrayList;
import java.util.Collections;

public class Deck //Just for Saat Aath!
{
	private ArrayList<Card> cardList;

	public Deck()
	{
		cardList = new ArrayList<Card>();
		for (int i = 8; i <= 14; i++)
		{
			cardList.add(new Card(Card.SPADES, i));
			cardList.add(new Card(Card.HEARTS, i));
			cardList.add(new Card(Card.DIAMONDS, i));
			cardList.add(new Card(Card.CLUBS, i));
		}
		cardList.add(new Card(Card.SPADES, 7));
		cardList.add(new Card(Card.HEARTS, 7));
	}

	public String toString()
	{
		String deckString = "";
		for (Card c : cardList)
		{
			deckString += c.toString() + "\n";
		}
		return deckString;
	}

	public void shuffle()
	{
		Collections.shuffle(cardList);
	}

	//care : this mutates the deck
	public Card[] deal(int numCards) 
	{
		shuffle();
		Card[] dealtCards = new Card[numCards];
		for (int i = 0; i < numCards; i++)
		{
			dealtCards[i] = cardList.remove(0);
		}
		return dealtCards;
	}
}