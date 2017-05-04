//This class provides the template for the bag class. 
//It contains setters and getters necessary to obtain card values.
public class Card
{
	private String value;
	private String suit;
	
	//Empty constructor
	public Card()
	{
		value = "";
		suit = "";
	}
	
	//Constructor with parameters
	public Card(String val, String suit)
	{
		this.value = val;
		this.suit = suit;
	}
	
	public String getValue()
	{
		return value;
	}
	
	public String getSuit()
	{
		return suit;
	}
	
	public void setValue(String newValue)
	{
		value = newValue;
	}
	
	public void setSuit(String newSuit)
	{
		suit = newSuit;
	}
	
	public boolean isEqual(Card item)
	{
		if(item.getSuit().equals(item.getSuit()) && item.getValue().equals(item.getValue()))
			return true;
		else
			return false;
	}
	
	//For formatting purposes
	public String getBoth()
	{
		return value + suit;
	}
}
