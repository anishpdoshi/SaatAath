public class Game
{

	//0 means nothing, 1 means O, 2 means X
	int[][] state;
	Player a;
	Player b;

	public Game()
	{
		state = new int[3][3];
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				state[i][j] = 0;
			}
		}

		//a goes first. b goes second. 
		a = new MachinePlayer(state, 2); //x
		b = new MachinePlayer(state, 1); //o
	}

	public static int win(int[][] state)
	{
		//diaganols
		if (state[0][0] != 0 && state[0][0] == state[1][1] && state[1][1] == state[2][2]) {
			return state[0][0];
		} else if (state[0][2] != 0 && state[0][2] == state[1][1] && state[1][1] == state[2][0]) {
			return state[0][2];

		//rows
		} else if (state[0][0] != 0 && state[0][0] == state[0][1] && state[0][1] == state[0][2]) {
			return state[0][0];
		} else if (state[1][0] != 0 && state[1][0] == state[1][1] && state[1][1] == state[1][2]) {
			return state[1][0];
		} else if (state[2][0] != 0 && state[2][0] == state[2][1] && state[2][1] == state[2][2]) {
			return state[2][0];

		// columns
		} else if (state[0][0] != 0 && state[0][0] == state[1][0] && state[1][0] == state[2][0]) {
			return state[0][0];
		} else if (state[0][1] != 0 && state[0][1] == state[1][1] && state[1][1] == state[2][1]) {
			return state[0][1];
		} else if (state[0][2] != 0 && state[0][2] == state[1][2] && state[1][2] == state[2][2]) {
			return state[0][2];
		}

		boolean draw = true;
		for (short i = 0; i < 3; i++)
		{
			for (short j = 0; j < 3; j++)
			{
				if (state[i][j] == 0)
				{
					draw = false;
				}
			}
		}
		if (draw)
		{
			return -1;
		}

		return 0;
	}


	public static int[][] copy2D(int[][] arr)
	{
		int[][] copy = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++)
		{
			copy[i] = arr[i].clone();
		}
		return copy;
	}

	public int play()
	{
		Player curr = a;
		while (Game.win(this.state) == 0)
		{
			//System.out.println(this + "\n");
			int[] move = curr.getMove();
			state[move[0]][move[1]] = curr.side;
			//System.out.println("last move:  " + move[0] + ", " + move[1]);
			curr = (curr == a) ? b : a;
		}
		//System.out.println(" \nGAME OVER ");
		int status = Game.win(this.state);
		// if (status == -1)
		// {
		// 	//System.out.println("DRAW!");
		// }
		// else
		// {
		// 	//System.out.println("Winner: " + (status == 2 ? "x" : "b"));
		// }
		//System.out.println(this);
		return status;

	}

	public static void main(String [] args)
	{
		Game g = new Game();
		for (int i = 0; i < 100; i++)
		{
			int status = g.play();
			System.out.println(status);
		}	
	}

	public static char val(int[][] grid, int a, int b)
	{
		int item = grid[a][b];
		if (item == 0)
		{
			return ' ';
		}
		else if (item == 1)
		{
			return 'o';
		}
		else 
		{
			return 'x';
		}
	}

	public static String gridView(int[][] grid)
	{
		String str = "";
		str += "   |   |   \n";
		str += " " + val(grid, 0, 0) + " | " + val(grid, 0, 1) + " | " + val(grid, 0, 2) + " \n";
		str += "___|___|___\n";

		str += "   |   |   \n";
		str += " " + val(grid, 1, 0) + " | " + val(grid, 1, 1) + " | " + val(grid, 1, 2) + " \n";
		str += "___|___|___\n";

		str += "   |   |   \n";
		str += " " + val(grid, 2, 0) + " | " + val(grid, 2, 1) + " | " + val(grid, 2, 2) + " \n";
		str += "   |   |   \n";
		return str;
	}

	public String toString()
	{
		return Game.gridView(state);	
	}
}