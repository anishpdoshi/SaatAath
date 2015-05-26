import java.util.ArrayList;
import java.util.Random;

public class MachinePlayer extends Player
{

	private class Best 
	{
		int[] move;
		int score;
	}

	public MachinePlayer(int[][] state, int x)
	{
		super(state, x);
	}

	//side flips between 1 and 2
	public Best chooseMove(int[][] grid, int side, int alpha, int beta, int depth)
	{ 
		Best myBest = new Best();
		Best reply;

		int status = Game.win(grid);
		if (status != 0)
		{
			Best endBest = new Best();
			if (status == -1) {
				endBest.score = 0;
			} else if (status == this.side) {
				endBest.score = 1;
			} else {
				endBest.score = -1;
			}
			return endBest;
		} 

		if (side == this.side) {
			myBest.score = alpha;
		} else {
			myBest.score = beta;
		}

		ArrayList<int[]> moves = allLegalMoves(grid);
		myBest.move = moves.get(0);
		for (int[] move : moves)
		{
			grid[move[0]][move[1]] = side;
			reply = chooseMove(grid, 3 - side, alpha, beta, depth + 1);
			grid[move[0]][move[1]] = 0;
			if (side == this.side && reply.score > myBest.score)
			{
				myBest.move = move;
				myBest.score = reply.score;
				alpha = reply.score;
			}
			else if (side == 3 - this.side && reply.score < myBest.score)
			{
				myBest.move = move;
				myBest.score = reply.score;
				beta = reply.score;
			}
			if (alpha >= beta)
			{
				return myBest;
			}
		}
		return myBest;
	}

	public int[] getMove()
	{
		int[][] grid = Game.copy2D(state);
		Best bestMove = chooseMove(grid, this.side, -1, 1, 0);
		return bestMove.move;
	}
}