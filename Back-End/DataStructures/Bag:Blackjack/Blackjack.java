
public class Blackjack 
{
	private Bag<Card> deck;
	private Card newCard;
	
	public Blackjack()
	{
		deck = new Bag<Card>(53);
		newCard = new Card();
	}
	
	//Since I wanted to display cards as Value,Suit I decided to add them manually and only using loops for the #values.
	public void generateDeck()
	{
		//Adding Hearts.
		for (int i=2; i < 11; i++)
		{
			Card newCard = new Card();
			newCard.setSuit("h");
			newCard.setValue(Integer.toString(i));
			deck.add(newCard);
		}
		Card jackHearts = new Card("J", "h");
		deck.add(jackHearts);
		Card queenHearts = new Card("Q", "h");
		deck.add(queenHearts);
		Card kingHearts = new Card("K", "h");
		deck.add(kingHearts);
		Card aceHearts = new Card("A", "h");
		deck.add(aceHearts);
		
		//Adding Clubs
		for (int i=2; i < 11; i++)
		{
			Card newCard = new Card();
			newCard.setSuit("c");
			newCard.setValue(Integer.toString(i));
			deck.add(newCard);
		}
		Card jackClubs = new Card("J", "c");
		deck.add(jackClubs);
		Card queenClubs = new Card("Q", "c");
		deck.add(queenClubs);
		Card kingClubs = new Card("K", "c");
		deck.add(kingClubs);
		Card aceClubs = new Card("A", "c");
		deck.add(aceClubs);
		
		//Adding Diamonds
		for(int i=2; i < 11; i++)
		{
			Card newCard = new Card();
			newCard.setSuit("d");
			newCard.setValue(Integer.toString(i));
			deck.add(newCard);
		}
		Card jackDiamonds = new Card("J", "d");
		deck.add(jackDiamonds);
		Card queenDiamonds = new Card("Q", "d");
		deck.add(queenDiamonds);
		Card kingDiamonds = new Card("K", "d");
		deck.add(kingDiamonds);
		Card aceDiamonds = new Card("A", "d");
		deck.add(aceDiamonds);
		
		//Adding Spades
		for(int i=2; i < 11; i++)
		{
			Card newCard = new Card();
			newCard.setSuit("s");
			newCard.setValue(Integer.toString(i));
			deck.add(newCard);
		}
		Card jackSpades = new Card("J", "s");
		deck.add(jackSpades);
		Card queenSpades = new Card("Q", "s");
		deck.add(queenSpades);
		Card kingSpades = new Card("K", "s");
		deck.add(kingSpades);
		Card aceSpades = new Card("A", "s");
		deck.add(aceSpades);
	}
	
	//Grabs a card until that card is not null.
	public Card deal()
	{
		Card deal1 = deck.grab();
		while(deal1 == null)
			deal1 = deck.grab();
		
		return deal1;
	}
	
	//Calculates total for round one. 
	public int calculateTotal(String valueOne, String valueTwo)
	{
		int sum = 0;
		
		//If value of card one is a face value then add 10.
		if(valueOne == "J" || valueOne == "Q" || valueOne == "K")
			sum += 10;
		//if the value is not an ace add the number value.
		else if(valueOne != "A")
			sum += Integer.parseInt(valueOne);
		//If the value is an ace and the sum is greater than 10 add 1, otherwise it'll burst.
		else if(valueOne == "A" && sum > 10)
			sum += 1;
		else
			//if the sum so far is less than 10 then count the ace as high
			sum += 11;
		
		
		//Same logic as the value for card one.
		if(valueTwo == "J" || valueTwo == "Q" || valueTwo == "K")
			sum += 10;
		else if(valueTwo != "A")
			sum += Integer.parseInt(valueTwo);
		else if(valueTwo == "A" && sum > 10)
			sum += 1;
		else
			sum += 11;
		

		return sum;
	}
	
}
