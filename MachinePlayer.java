import java.util.Scanner;
import java.util.ArrayList;

public class MachinePlayer extends Player
{
	boolean MACHINE = true;

	private class myBest
	{

	}

	public MachinePlayer(Card[] tops, Card[] bottoms, Card[] privates, Card[] enemyTops, Card[] enemyBottoms, Card[] enemyPrivates)
	{
		super(tops, bottoms, privates, enemyTops, enemyBottoms, enemyPrivates);
	}

	public int evaluate(Player a, Player b)
	{
		return a.numHands - b.numHands;
	}



	public Object[] chooseMove(boolean side, int alpha, int beta, Card placedCard)
	{
		Object[] myBest = new Object[]; //move followed by score
		Object[] reply;

		if (numCards == 0)
		{
			return a.numHands - b.numHands;
		}

		if (side == MACHINE) {
			myBest.score = alpha;
		} else {
			myBest.score = beta;
		}

		ArrayList legalMoves = getAllLegalMoves2(placedCard)[0];
		myBest[0] = legalMoves[0];
		for (Object[] move : legalMoves)
		{

		}
	}

	//MOVE: <code><index>
	//code: T for top, B for bottom, P for private
	//index: 0 start
	//Postcondition: move is valid
	public String getMove(Card placedCard)
	{
		Object[] state = new Object[];
		state[0] = Array.copyOf(tops, tops.length);
		state[1] = Array.copyOf(bottoms, bottoms.length);
		state[2] = Array.copyOf(privateCards, privateCards.length);
		state[3] = Array.copyOf(enemyTops, enemyTops.length);
		state[4] = Array.copyOf()

		// boolean isValid = true;
		// String move = "";
		// Scanner reader = new Scanner(System.in);
		// ArrayList legalMoves = getAllLegalMoves(placedCard)[0];
		// do {
		// 	System.out.print("What move should we do? Input:  ");
		// 	move = reader.nextLine();
		// } while (!legalMoves.contains(move));	
		// return move;
	}

	public int maxIndex(int[] nums)
	{
		if (nums.length > 0)
		{
			int max = nums[0];
			int index = 0;
			for (int i = 0; i < nums.length; i++)
			{
				if (nums[i] > max)
				{
					max = nums[i];
					index = i;
				}
			}
			return index;
		}
		return -1;		
	}

	public int getSurCall()
	{
		int[] suitScores = new int[] {0, 0, 0, 0};
		for (int i = 0; i < 6; i++)
		{
			suitScores[tops[i].suit()] += tops[i].number();
			if (i < 3)
			{
				suitScores[privateCards[i].suit()] += privateCards[i].number();
			}
		}
		return maxIndex(suitScores);
		//tops, privates, enemyTops
	}
}