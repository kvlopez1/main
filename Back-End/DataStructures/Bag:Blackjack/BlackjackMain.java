import java.util.Random;
import java.util.Scanner;

public class BlackjackMain 
{	
	public static void main(String[] args)
	{
		Blackjack blackjack = new Blackjack();
		Card playerCardOne = new Card();
		Card playerCardTwo = new Card();
		Card playerCardThree = new Card();
		Card playerCardFour = new Card();
		
		//Generates the deck to begin with.
		blackjack.generateDeck();
		
		//Deals two cards and shows the user along with the total so far.
		playerCardOne = blackjack.deal();
		playerCardTwo = blackjack.deal();
		System.out.println("Your Cards: " + playerCardOne.getBoth() + "\t" + playerCardTwo.getBoth());
		int total = blackjack.calculateTotal(playerCardOne.getValue(), playerCardTwo.getValue());
		System.out.println("Total: " + total);
		System.out.println("Do you want a hit? (Y/N)");
		
		Scanner scan = new Scanner(System.in);
		String decision = scan.nextLine();
		
		//If the player wants to continue deal another card and repeat the process.
		if(decision.equalsIgnoreCase("Y"))
		{
			playerCardThree = blackjack.deal();
			System.out.println("New Card: " + playerCardThree.getBoth());
			
			if(playerCardThree.getValue() == "J" || playerCardThree.getValue() == "Q" || playerCardThree.getValue() == "K")
				total += 10;
			else if(playerCardThree.getValue() != "A")
				total += Integer.parseInt(playerCardThree.getValue());
			else if(playerCardThree.getValue() == "A" && total > 10)
				total += 1;
			else
				total += 11;
			
			System.out.println("New Total: " + total);
			if(total > 21)
				System.out.println("You Lose");
			else if (total == 21)
				System.out.println("You Win");
			else
				System.out.println("Do you want a hit? (Y/N)");
			String newDecision = scan.nextLine();
			
			if(newDecision.equalsIgnoreCase("Y"))
			{
				playerCardFour = blackjack.deal();
				System.out.println("New Card: " + playerCardFour.getBoth());
				
				if(playerCardFour.getValue() == "J" || playerCardFour.getValue() == "Q" || playerCardFour.getValue() == "K")
					total += 10;
				else if(playerCardFour.getValue() != "A")
					total += Integer.parseInt(playerCardFour.getValue());
				else if(playerCardFour.getValue() == "A" && total > 10)
					total += 1;
				else
					total += 11;
				
				System.out.println("New Total: " + total);
				if(total > 21)
					System.out.println("You Lose");
				else if (total == 21)
					System.out.println("You Win");
				else
					System.out.println("Do you want a hit? (Y/N)");
				
				if(scan.nextLine().equalsIgnoreCase("Y"))
				{
					playerCardFour = blackjack.deal();
					System.out.println("New Card: " + playerCardFour.getBoth());
					
					if(playerCardFour.getValue() == "J" || playerCardFour.getValue() == "Q" || playerCardFour.getValue() == "K")
						total += 10;
					else if(playerCardFour.getValue() != "A")
						total += Integer.parseInt(playerCardFour.getValue());
					else if(playerCardFour.getValue() == "A" && total > 10)
						total += 1;
					else
						total += 11;
					
					System.out.println("New Total: " + total);
					if(total > 21)
						System.out.println("You Lose");
					else if (total == 21)
						System.out.println("You Win");
				}
				else if(scan.nextLine().equalsIgnoreCase("N"))
				{
					if(total < 21)
						System.out.println("You Lose");
					else if(total == 21)
						System.out.println("You Win");
				}
			}
			else if(newDecision.equalsIgnoreCase("N"))
			{
				if(total < 21)
					System.out.println("You Lose");
				else if (total == 21)
					System.out.println("You Win");
			}
		}
		//If the player wants to say check the total and display the result.
		else if (decision.equalsIgnoreCase("N"))
		{
			if(total < 21)
				System.out.println("You Lose");
			else if(total == 21)
				System.out.println("You Win");
		}
	}
}
