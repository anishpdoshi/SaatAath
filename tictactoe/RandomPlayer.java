import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer extends Player
{

	public RandomPlayer(int[][] state, int x)
	{
		super(state, x);
	}

	public int[] getMove()
	{
		ArrayList<int[]> allMoves = allLegalMoves(state);
		Random generator = new Random();
		int index = generator.nextInt(allMoves.size());
		return allMoves.get(index);
	}
}