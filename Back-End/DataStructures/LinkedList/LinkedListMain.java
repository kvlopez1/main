import java.util.Arrays;
import java.util.Scanner;

public class LinkedListMain 
{
	public static void main(String[] args)
	{
		System.out.println("Enter number of people:");
		Scanner scan = new Scanner(System.in);
		int numberOfPeople = scan.nextInt();
		System.out.println("Enter skip number:");
		int skipNum = scan.nextInt();
		String eliminationOrder = "Elimination Order: ";
		
		/**
		 * Adds items to array according to the user's input.
		 */
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(int i = 0; i < numberOfPeople; i++)
		{
			list.addAtIndex(i+1, i);
		}
		
		list.deleteAtEnd();
		eliminationOrder += eliminateNext(skipNum, list) + "\t";
		while(list.getCurrent() + skipNum - 1 < list.getEnd())
		{
			eliminationOrder += eliminateNext(skipNum, list) + "\t";
		}

		System.out.println(eliminationOrder);
		System.out.println(list.toString());
		System.out.println(list.getCurrent());
	}
	
	/**
	 * gets next eliminated number according to the skipNumber and the list passed in.
	 * @param skipNum
	 * @param list
	 * @return
	 */
	public static int eliminateNext(int skipNum, LinkedList<Integer> list)
	{
		int eliminatedNum = 0;

		for(int i = 0; i < skipNum; i++)
		{
			list.advanceCurrent();
		}
			
		list.deleteCurrent();
		eliminatedNum = list.getCurrent();
		
		return eliminatedNum;
	}
}
