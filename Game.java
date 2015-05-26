//Player has access to all cardsets given by Game, which masks/unmasks cards.
import java.util.Scanner;
import java.util.ArrayList;

public class Game //Handles game state and the visibility of all cards
{
	private Player playerA;
	private Player playerB;

	//Actuals 
	Card[] actualBottomA, actualBottomB;

	//Player A/B
	Card[] topA, hiddenBottomA, topB, hiddenBottomB;

	//Player A
	Card[] visiblePrivateA, hiddenPrivateB;

	//Player B
	Card[] visiblePrivateB, hiddenPrivateA;

	int SUR = -1;

	public Game()
	{
		Deck deck = new Deck();
		actualBottomA = deck.deal(6);
		actualBottomB = deck.deal(6);
		topA = deck.deal(6);
		topB = deck.deal(6);
		visiblePrivateA = deck.deal(3);
		visiblePrivateB = deck.deal(3);

		hiddenBottomA = Card.maskSet(6);
		hiddenBottomB = Card.maskSet(6);
		hiddenPrivateA = Card.maskSet(3);
		hiddenPrivateB = Card.maskSet(3);

		playerA = new MachinePlayer(topA, hiddenBottomA, visiblePrivateA, topB, hiddenBottomB, hiddenPrivateB);
		playerB = new RandomPlayer(topB, hiddenBottomB, visiblePrivateB, topA, hiddenBottomA, hiddenPrivateA);

		// ArrayList[] arrs;
		// long x = System.currentTimeMillis();
		// for (int i = 0; i < 1000000; i++)
		// {
		// 	arrs = playerA.getAllLegalMoves2(null);
			
		// 	arrs = playerB.getAllLegalMoves2(new Card(Card.SPADES, 4));
		// }
		// System.out.println(System.currentTimeMillis() - x);
	}

	public Card[] getAccording(char c, Player p)
	{
		Card[] according = null;
		switch (c) {
			case 'T' : according = (p == playerA ? topA : topB); break;
			case 'B' : according = (p == playerA ? hiddenBottomA : hiddenBottomB); break;
			case 'P' : according = (p == playerA ? visiblePrivateA : visiblePrivateB); break;
		}
		return according;
	}

	public Player other(Player p)
	{
		return (p == playerA ? playerB : playerA);
	}

	public Card winningCard(Card first, Card second)
	{
		if (first.suit() == second.suit())
		{
			return first.number() > second.number() ? first : second; 
		}
		else 
		{
			if (first.suit() == SUR)
			{
				return first;
			}
			else if (second.suit() == SUR)
			{
				return second;
			}
			else 
			{
				return first;
			}
		}
		//lengthened for clarity
	}

	public Card playCard(Player p, Card placedCard)
	{
		String move = p.getMove(placedCard);
		int moveIndex = Character.getNumericValue(move.charAt(1));
		char moveType = move.charAt(0);
		Card[] according = getAccording(moveType, p);
		Card card = according[moveIndex];

		//Cleaning up
		according[moveIndex] = null;
		if (according == topA)
		{
			hiddenBottomA[moveIndex] = actualBottomA[moveIndex];
		}
		else if (according == topB)
		{
			hiddenBottomB[moveIndex] = actualBottomB[moveIndex];
		}
		else if (according == visiblePrivateA)
		{
			hiddenPrivateA[moveIndex] = null;
		}
		else if (according == visiblePrivateB)
		{
			hiddenPrivateB[moveIndex] = null;
		}

		p.numCards--;
		return card;
	}

	public void play()
	{
		Scanner reader = new Scanner(System.in);
		System.out.print("Would you like to be player A or B? Input: ");
		String chosenInput = reader.nextLine();
		Player chosenPlayer = null;
		if (chosenInput.equalsIgnoreCase("A"))
		{
			chosenPlayer = playerA;
		}
		else if (chosenInput.equalsIgnoreCase("B"))
		{
			chosenPlayer = playerB;
		}

		Player firstPlayer = playerA;
		Player secondPlayer = other(firstPlayer);

		System.out.println(chosenPlayer);
		SUR = firstPlayer.getSurCall();
		System.out.println("The SUR is " + Card.suitToString(SUR));
		firstPlayer.SUR = SUR;
		secondPlayer.SUR = SUR;
	
		while (playerA.numCards > 0) 
		{

			//Getting the cards
			Card firstCard = playCard(firstPlayer, null);
			System.out.println("\n" + firstCard);
			Card secondCard = playCard(secondPlayer, firstCard);
			System.out.println(secondCard + "\n");

			//Playing the hand
			Card betterCard = winningCard(firstCard, secondCard);
			Player handWinner = betterCard == firstCard ? firstPlayer : secondPlayer; 
			handWinner.numHands++;

			firstPlayer = handWinner;
			secondPlayer = other(handWinner);

			System.out.println(chosenPlayer);
			System.out.println("Player A: " + playerA.numHands);
			System.out.println("Player B: " + playerB.numHands);
		}
		System.out.println("Final Score:");
		System.out.println("Player A: " + playerA.numHands);
		System.out.println("Player B: " + playerB.numHands);
	}

	public String toString()
	{
		String str = "\n\n";
		str += "\t\t\t\tB\n\n";
		str += "Priv:\t\t\t" + Card.printSet(visiblePrivateB) + "\t\n\n";
		str += "Bots:\t\t" + Card.printSet(actualBottomB) + "\n";
		str += "Tops:\t\t" + Card.printSet(topB) + "\n\n\n";
		
		
		str += "Tops:\t\t" + Card.printSet(topA) + "\n";
		str += "Bots:\t\t" + Card.printSet(actualBottomA) + "\n\n";
		str += "Priv:\t\t\t" + Card.printSet(visiblePrivateA) + "\t\n\n";
		str += "\t\t\t\tA\n\n";

		return str;
	}

	public static void main(String [] args)
	{
		Game g = new Game();
		g.play();
	}
}