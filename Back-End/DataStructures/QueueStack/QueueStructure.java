
public class QueueStructure<T> 
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
	 * constructor for Queue class.
	 */
	public QueueStructure()
	{
		head = null;
		current = head;
	}
	
	/**
	 * adds node to the end of the list.
	 * checks if it's empty and adds to the front
	 * else moves current to the end and adds the data passed in.
	 * @param item
	 */
	public void enqueue(T item)
	{
		current = head;
		
		if(head == null)
		{
			head = new Node<T>(item, null);
		}
		else
		{
			while(current.next != null)
			{
				current = current.next;
			}
			
			current.next = new Node<T>(item, null);
		}
	}
	
	/**
	 * makes head point to the second node and returns node.
	 * @return
	 */
	public T dequeue()
	{
		current = head;
		T result = (T) new Object();
		
		if(head != null)
		{
			result = head.data;
			head = current.next;
		}
		
		return result;
	}
	
	/**
	 * returns head of list
	 * @return
	 */
	public T front()
	{
		return head.data;
	}
	
	/**
	 * puts queue on a string
	 */
	public String toString()
	{
		String result = "Queue: ";
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
