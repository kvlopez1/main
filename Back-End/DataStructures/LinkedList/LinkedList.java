import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<T> {
	
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
	private Node<T> previous;
	
	public LinkedList()
	{
		head = new Node<T>();
		previous = head;
		current = head.next;
	}
	
	/**
	 * adds item to front of list.
	 * @param item
	 */
	public void addToFront(T item)
	{
		Node<T> newNode = new Node<T>(item, head);
		
		//Change the first reference to new node;
		head = newNode;
	}
	
	/**
	 * adds item to end of list. First it checks if the list is empty and adds to the front, 
	 * else it goes until the end and adds new item.
	 * @param item
	 */
	public void addToEnd(T item)
	{
		if(head == null)
		{
			addToFront(item);
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
	 * Adds item to next to current.
	 * @param item
	 */
	public void addAtCurrent(T item)
	{
		current = head;
		while(current != null)
		{
			current = current.next;
		}
		
		if(current != null)
			current.next = new Node<T>(item, current.next);
	}
	
	/**
	 * Adds item at indicated index.
	 * @param item
	 * @param index
	 */
	public void addAtIndex(T item, int index)
	{
		if(index >= 0 && index <= getSize())
		{
			ListIterator iterator = this.listIterator();
			current = head;
			
			if(index == 0)
			{
				addToFront(item);
			}
			else
			{
				for(int i = 1; i < index; i++)
				{
					current = current.next;
				}
				
				Node<T> newNode = new Node<T>(item, current.next);
				current.next = newNode;
				current = newNode;
			}
		}
	}
	
	/**
	 * Adds item before current node.
	 * @param item
	 */
	public void addBeforeCurrent(T item)
	{
		if(getSize() == 1)
			addToFront(item);
		
		Node<T> previous = null;
		current = head;
		
		while(current.next != null)
		{
			previous = current; 
			advanceCurrent();
		}
		
		if(current != null)
			previous.next = new Node<T>(item, current);
	}

	/**
	 * delete first item in the list.
	 */
	public void deleteAtFront()
	{
		if(head != null)
			head = head.next;
	}
	
	/**
	 * deletes last item in the list.
	 */
	public void deleteAtEnd()
	{
		LinkedListIterator iterator = new LinkedListIterator();
		T last = null;
		for(int i = 0; i < getSize() - 1; i++)
		{
			iterator.next();
		}
		last = iterator.next();
		iterator.remove();
	}
	
	/**
	 * deletes current node.
	 */
	public void deleteCurrent()
	{
		if(getSize() == 1)
			deleteAtFront();

		previous = current;
		current = current.next;
		
		previous.next = current.next;
	}
	
	/**
	 * resets list.
	 */
	public void clear()
	{
		head = null;
	}
	
	/**
	 * sets current to current.next in oder to advance it.
	 */
	public void advanceCurrent()
	{
		if(current == null)
			current = head;
		
		current = current.next;
	}
	
	/**
	 * checks if list contains item passed in.
	 * @param item
	 * @return
	 */
	public boolean contains(T item)
	{
		current = head;
		while(current.next != null)
		{
			current = current.next;
			if(current.data == item)
				return true;
		}
		
		return false;
	}
	
	/**
	 * finds node with item passed in and returns it.
	 * @param item
	 * @return
	 */
	public Node<T> find(T item)
	{
		Node<T> nodeToReturn = new Node<T>();
		
		current = head;
		while(current.next != null)
		{
			advanceCurrent();
			if(current.data == item)
			{
				nodeToReturn.data = item;
				nodeToReturn.next = current.next;
			}
		}
		return nodeToReturn;
	}
	
	/** 
	 * gets data from the first item in list.
	 * @return
	 */
	public T getFront()
	{
		if(head != null)
			return head.data;
		else
			return null;
	}
	
	/**
	 * gets data from the last item in the list.
	 * @return
	 */
	public T getEnd()
	{
		LinkedListIterator iterator = new LinkedListIterator();
		T end = null;
		for(int i = 0; i < getSize()-1; i++)
		{
			iterator.next();
		}
		
		end = iterator.next();
		return end;
	}
	
	/**
	 * return data of current node.
	 * @return
	 */
	public T getCurrent()
	{
		return current.data;
	}
	
	
	/**
	 * gets data from node at passed in index.
	 * @param index
	 * @return
	 */
	public T get(int index)
	{
		current = head;
		for(int i = 1; i < index; i++)
		{
			current = current.next;
		}
		
		return current.data;
	}
	
	/**
	 * returns size of list. 
	 * @return
	 */
	public int getSize()
	{
		int size = 0;
		
		ListIterator iterator = this.listIterator();
		while(iterator.hasNext())
		{
			iterator.next();
			size++;
		}
		return size;
	}
	
	/**
	 * puts every item in the list into a string.
	 */
	public String toString()
	{
		ListIterator iterator = this.listIterator();
		String result = "List: ";
		while(iterator.hasNext())
		{
			result += iterator.next() + " ";
		}
		
		result += "\n";
		
		return result;
	}
	
	/**
	 * puts items on the list in an array
	 * @return
	 */
	public T[] toArray()
	{
		T[] array = (T[]) new Object[getSize()];
		ListIterator iterator = this.listIterator();
		
		for(int i = 0; i < getSize() - 1; i ++)
		{
			array[i] = (T) iterator.next();
		}
		
		return array;
	}
	
	/**
	 * creates new linkedListIterator()
	 * @return
	 */
	public ListIterator<T> listIterator()
	{
		return new LinkedListIterator();
	}
	
	/**
	 * private class used in toString, toArray methods.
	 * @author KL1
	 *
	 */
	private class LinkedListIterator implements ListIterator<T>
	{
		private Node<T> previous;
		private Node<T> current;
		
		public LinkedListIterator()
		{
			previous = null;
			current = null;
		}
		
		public T next()
		{
			if(!hasNext())
				throw new NoSuchElementException();
			
			previous = current;
			
			if(current == null)
				current = head;
			else
				current = current.next;
			
			return current.data;
		}
		
		public boolean hasNext()
		{
			if(current == null)
				return head != null;
			else
				return current.next != null;
		}
		
		public void remove()
		{
			if(previous == current)
				throw new NoSuchElementException();
			
			if(previous == head)
			{
				deleteAtFront();
			}
			else
			{
				previous.next = current.next;
			}
			current = previous;
		}

		public boolean hasPrevious() {
			return false;
		}

		public void set(T item) 
		{
			if(current == null)
				throw new NoSuchElementException();
			
			current.data = item;
		}

		public void add(T e) 
		{
			if(current == null)
			{
				addToFront(e);
				current = head;
			}
			else
			{
				Node<T> newNode = new Node<T>(e, current.next);
				current.next = newNode;
				current = newNode;
			}
		}

		public T previous() 
		{
			return previous.data;
		}

		public int nextIndex() {
			return 0;
		}

		public int previousIndex() {
			return 0;
		}
	}
}
