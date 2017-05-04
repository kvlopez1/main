
public class StackStructure<T> 
{
	private class Node<T>
	{
		public T data;
		public Node<T> next;
		
		/**
		 * constructor for Node class
		 */
		public Node(T data, Node<T> next)
		{
			this.data = data;
			this.next = next;
		}
		
		/**
		 * empty constructor for Node class
		 */
		public Node(){}
	}
	
	private Node<T> head;
	private Node<T> current;
	
	/**
	 * constructor for Stack Class;
	 */
	public StackStructure()
	{
		head = null;
		current = head;
	}
	
	/**
	 * adding passed data to stack. 
	 * first check if the list is null to insert on the head;
	 * else find the last item and add after.
	 * @param item
	 */
	public void push(T item)
	{
		//current = head; 
		
		if(head == null)
		{
			head = new Node<T>(item, null);
		}
		else
		{
			current = head;
			while(current.next != null)
			{
				current = current.next;
			}
			
			current.next = new Node<T>(item, null);
		}
	}
	
	/**
	 * gets last item on the stack.
	 * first checks if there's only one item on the stack and deletes that.
	 * else move current and temp until current is at the end and make temp point to the last item.
	 * @return
	 */
	public T pop()
	{
		T result = (T) new Object();
		Node<T> temp = new Node<T>();
		temp = head;
		current = head.next;
		
		if(current == null)
		{
			result = temp.data;
			head = null;
		}
		else
		{
			while(current.next != null)
			{
				temp = current;
				current = current.next;
			}
			
			result = current.data;
			temp.next = null;
		}

		return result;
	}
	
	/**
	 * returns top item on the stack.
	 * if the stack has one item then return that 
	 * else find the last item and return it's value.
	 * @return
	 */
	public T peek()
	{
		T result = (T) new Object();
		current = head;
		
		if(current.next == null)
		{
			result = current.data;
		}
		else
		{
			while(current.next != null)
			{
				current = current.next;
			}
			
			result = current.data;
		}
		
		return result;
	}
	
	/**
	 * putting stack in a String
	 */
	public String toString()
	{
		String result = "Stack: ";
		current = head;
		
		if(head.next == null)
		{
			result += head.data + " ";
		}
		else
		{
			while(current.next != null)
			{
				result += current.data + " ";
				current = current.next;
			}
			result += current.data + " ";
		}
		return result;
	}
	
	/**
	 * checks if head is null and returns true
	 * @return
	 */
	public boolean isEmpty()
	{
		boolean result = false;
		if(head == null)
		{
			result = true;
		}
		
		return result;
	}
}
