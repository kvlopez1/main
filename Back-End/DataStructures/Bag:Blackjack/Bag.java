import java.lang.reflect.Array;
import java.util.*;
import javax.swing.Spring;
import java.lang.*;

//Bag class with TCard as template
public class Bag<TCard>
{
	private TCard[] contents;
	private String suit;
	private int index, size;
	
	public Bag()
	{
		index = 0;
		contents = (TCard[]) new Object[52];
	}
	
	//Constructor with parameters
	public Bag(int size)
	{
		index = 0;
		contents = (TCard[]) new Object[size];
	}

	//Returns length of array.
	public int getSize()
	{
		return contents.length;
	}

	//Since array get smaller as cards are grabbed this method calculates the new size.
	public int getCurrentSize() 
	{
		size = 0;
		for(int i=0; i < contents.length; i++)
		{
			if(contents[i] != null)
			{
				size += 1;
			}
		}
		return size;
	}
	
	//Checks if array is up to capacity.
	public boolean isFull()
	{
		if(contents[contents.length - 1] != null)
			return true;
		else
			return false;
	}
	
	//Checks if array has no contents.
	public boolean isEmpty()
	{
		if(contents.length == 0)
			return true;
		else
			return false;
	}
	
	//The index variable is used to add the contents in an ordered manner.
	//As long as the index is not the same as the length the new object gets added to the next index.
	public boolean add(TCard item)
	{
		if(index != contents.length - 1)
		{
			contents[index] = item;
			index++;
			return true;
		}
		else
			return false;
	}
	
	//Not using this method. Using removeElement instead.
	//Finds the item in the array and sets it to null.
	public boolean remove(TCard item)
	{
		for(int i = 0; i < contents.length; i++)
		{
			if(contents[i].equals(item))
			{
				contents[i] = null;
				return true;
			}
		}
		return false;
	}
	
	//Makes everything null.
	public void clear()
	{
		for(int i = 0; i < contents.length; i++)
		{
			contents[i] = null;
		}
	}
	
	//Checks if the objects in the array is the same as the parameter.
	public boolean contains(TCard item)
	{
		for(int i = 0; i < contents.length; i++)
		{
			if(contents[i].equals(item))
			{
				return true;
			}
		}
		return false;
	}
	
	//Counts how many items are the same.
	public int getFrequencyOf(TCard item)
	{
		int freq = 0;
		for(int i=0; i < contents.length; i++)
		{
			if(item.equals(contents[i]))
				freq += 1;
		}
		return freq;
	}
	
	//Gets a random index and returns that card. The index is then removed from array.
	public TCard grab()
	{
		//getting a card at a random index.
		int randomIndex = new Random().nextInt(contents.length);
		TCard random = (contents[randomIndex]);
		//removing the element from array and shifting everything.
		removeElement(contents, randomIndex);
		return random;
	}
	
	//Removed the element at the specified index, and shifts array.
	public void removeElement(TCard[] contents, int indexToRemove)
	{
		//number of objects to move.
		int numToMove = contents.length - (indexToRemove + 1);
		//Shifting everything one spot.
		System.arraycopy(contents, indexToRemove + 1, contents, indexToRemove, numToMove);	
	}
	
}
