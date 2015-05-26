import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

//hu- huh -YUHU - hyuk hyk
public class RandomPlayer extends Player
{
	private Random generator = new Random();

	public RandomPlayer(Card[] tops, Card[] bottoms, Card[] privates, Card[] enemyTops, Card[] enemyBottoms, Card[] enemyPrivates)
	{
		super(tops, bottoms, privates, enemyTops, enemyBottoms, enemyPrivates);
	}

	//MOVE: <code><index>
	//code: T for top, B for bottom, P for private
	//index: 0 start
	//Postcondition: move is valid
	public String getMove(Card placedCard)
	{
		ArrayList[] legalPair = getAllLegalMoves(placedCard);
		int randIndex = generator.nextInt(legalPair[0].size());
		return (String)(legalPair[0].get(randIndex));
	}

	public int getSurCall()
	{
		return generator.nextInt(4);
	}
}