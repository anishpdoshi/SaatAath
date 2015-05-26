import java.util.Scanner;

public class HumanPlayer extends Player
{

	public HumanPlayer(int[][] state, int x)
	{
		super(state, x);
	}

	public int[] getMove()
	{
		Scanner reader = new Scanner(System.in);
		int input;
		int a = 0, b = 0;
		do {
			System.out.print("What move should we make? Input: ");
			input = reader.nextInt();
			a = input / 10;
			b = input % 10;
		} while (state[a][b] != 0);

		return new int[] {a, b};
	}
}