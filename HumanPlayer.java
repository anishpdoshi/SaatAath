import java.util.Scanner;
import java.util.ArrayList;

public class HumanPlayer extends Player
{
	public HumanPlayer(Card[] tops, Card[] bottoms, Card[] privates, Card[] enemyTops, Card[] enemyBottoms, Card[] enemyPrivates)
	{
		super(tops, bottoms, privates, enemyTops, enemyBottoms, enemyPrivates);
	}

	//MOVE: <code><index>
	//code: T for top, B for bottom, P for private
	//index: 0 start
	//Postcondition: move is valid
	public String getMove(Card placedCard)
	{
		boolean isValid = true;
		String move = "";
		Scanner reader = new Scanner(System.in);
		ArrayList legalMoves = getAllLegalMoves(placedCard)[0];
		do {
			System.out.print("What move should we do? Input:  ");
			move = reader.nextLine();
			// String moveLoc = moveLine.substring(0, 1);
			// int index = Character.getNumericValue(moveLine.charAt(1));
			// Object[] move = [moveLoc, index];
		} while (!legalMoves.contains(move));	
		return move;
	}

	public int getSurCall()
	{
		Scanner reader = new Scanner(System.in);
		
		boolean valid = true;

		do {
			System.out.print("What sur should we call? Input: ");
			String surString = reader.nextLine().substring(0, 1);
			if (surString.equalsIgnoreCase("s")) {
				return Card.SPADES;
			} else if (surString.equalsIgnoreCase("c")) {
				return Card.CLUBS;
			} else if (surString.equalsIgnoreCase("d")) {
				return Card.DIAMONDS;
			} else if (surString.equalsIgnoreCase("h")) {
				return Card.HEARTS;
			} else {
				System.out.println("Invalid sur!");
				valid = false;
			}
		} while (!valid);
		return -2;
	}
}