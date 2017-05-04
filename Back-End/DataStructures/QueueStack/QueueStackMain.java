import java.util.Arrays;
import java.util.Scanner;

public class QueueStackMain 
{
	public static void main(String[] args)
	{
		StackStructure<String> newStack = new StackStructure<String>();
		QueueStructure<String> newQueue = new QueueStructure<String>();
		Scanner scan = new Scanner(System.in);
		String postfix = "Postfix: ";
		
		System.out.println("Enter Math Equation: ");
		String mathEquation = scan.nextLine();
		String[] splitEquation = mathEquation.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)|(?<=[-+*/])|(?=[-+*/])");
		System.out.println(Arrays.toString(splitEquation));

		for(int i = 0; i < splitEquation.length; i++)
		{
			newQueue.enqueue(splitEquation[i]);
		}
		
		System.out.println(newQueue.toString());
		while(!newQueue.isEmpty())
		{
			String key = newQueue.dequeue();
			if(isOpenParentheses(key))
			{
				newStack.push(key);
			}
			else if(isOperator(key))
			{
				if(newStack.isEmpty())
				{
					newStack.push(key);
				}
				else
				{
					while(!newStack.isEmpty())
					{
						if(isGreaterOrEqualPrecedence(key, newStack.peek()))
							postfix += newStack.pop();
					}
					newStack.push(key);
				}
			}
			else if(isClosingParentheses(key))
			{
				while(!isOpenParentheses(key))
				{
					postfix += newStack.pop();
				}
				newStack.pop();
			}
		}
		
		System.out.println(postfix);
	}
	
	public static boolean isOperator(String key)
	{
		if(key.equals("+") || key.equals("-") || key.equals("*") || key.equals("/"))
			return true;
		else
			return false;
	}
	
	public static boolean isOpenParentheses(String key)
	{
		if(key.equals("("))
			return true;
		else
			return false;
	}
	
	public static boolean isClosingParentheses(String key)
	{
		if(key.equals(")"))
			return true;
		else
			return false;
	}
	
	public static boolean isGreaterOrEqualPrecedence(String key, String topOfStack)
	{
		switch(key)
		{
			case "+":
				if(topOfStack.equals("-") || topOfStack.equals("+"))
					return true;
				else
					return false;
			case "_":
				if(topOfStack.equals("-") || topOfStack.equals("+"))
					return true;
				else
					return false;
			case "*":
				if(topOfStack.equals("+") || topOfStack.equals("-") || topOfStack.equals("/") || topOfStack.equals("*"))
					return true;
				else
					return false;
			case "/":
				if(topOfStack.equals("+") || topOfStack.equals("-") || topOfStack.equals("/") || topOfStack.equals("*"))
					return true;
				else
					return false;
			default:
				return false;
		}
	}
}
