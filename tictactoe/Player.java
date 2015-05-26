import java.util.ArrayList;

public abstract class Player
{
	protected int[][] state;
	public int side;

	public Player(int[][] state, int x)
	{
		this.state = state;
		this.side = x;
	}

	public ArrayList<int[]> allLegalMoves(int[][] grid)
	{
		ArrayList<int[]> moves = new ArrayList<int[]>();
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (grid[i][j] == 0)
				{
					int[] move = new int[] {i, j};
					moves.add(move);
				}	
			}
		}
		return moves;
	}

	public abstract int[] getMove();
}