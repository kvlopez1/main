import java.util.*;
import java.lang.*;

public class BST<T extends Comparable<T>>
{
	private class Node<T>
	{
		public T data;
		public Node<T> left;
		public Node<T> right;
		
		/**
		 * constructor for Node class
		 */
		public Node(T data)
		{
			this.data = data;
			left = null;
			right = null;
		}
		
		/**
		 * empty constructor for Node class
		 */
		public Node(){}
	}
	
	private Node<T> root;
	
	/**
	 * Constructor for BST. Root is initialized to null.
	 */
	public BST()
	{
		root = null;
	}
	
	/**
	 * Method takes @param item and inserts to BST according to value. 
	 * First, it checks if root is null and places item on root.
	 * Otherwise, keeps moving current according to item value. if it's < 0 goes left, otherwise right.
	 */
	public void insert(T item)
	{
		Node<T> newNode = new Node<T>(item);
		boolean hasNext = true;
		
		//if root is null then item becomes root
		if(root == null)
		{
			root = newNode;
			return;
		}
		else //need to find where it goes.
		{
			Node<T> current = root;
			Node<T> parent = null;
			
			while(hasNext)
			{
				parent = current;
				if(item.compareTo(current.data) < 0)
				{
					//item is less than parent. move current to left
					current = current.left;
					if(current == null) //Then I've found the last node
					{
						parent.left = newNode;
						hasNext = false;
						return;
					}
					else
						hasNext = true;
				}
				else
				{
					//item is greater than parent. move current to right,
					current = current.right;
					if(current == null) //Then I've found the last node
					{
						parent.right = newNode;
						hasNext = false;
						return;
					}
					else
						hasNext = true;
				}
			}
		}
	}
	
	/**
	 * Method takes @param item and first finds that node. 
	 * Then, handles deletion according to the number of children the node has.
	 * @param item
	 * @return
	 */
	public boolean delete(T item)
	{
		Node<T> parent = root;
		Node<T> current = root;
		boolean isLeft = false;
		
		//Traverse the tree until you find the node with T when handles the deletion according to number of children on that node.
		while(current.data != item)
		{
			parent = current;
			//item is less than current. Goes left
			if(item.compareTo(current.data) < 0)
			{
				isLeft = true;
				current = current.left;
			}
			else //goes right
			{
				isLeft = false;
				current = current.right;
			}
			
			if(current == null)
				return false;
		}
		
		//No children
		if(current.left == null && current.right == null)
		{
			if(current == root)
			{
				root = null;
			}
			
			if(isLeft == true)
				parent.left = null;
			else
				parent.right = null;
		}
		//1 children. Left
		else if(current.right == null)
		{
			if(current == root)
			{
				root = current.left;
			}
			else if(isLeft)
				parent.left = current.left;
			else
				parent.right = current.left;
		}
		//1 children. Right
		else if(current.left == null)
		{
			if(current == root)
			{
				root = current.right;
			}
			else if(isLeft)
				parent.left = current.right;
			else
				parent.right = current.right;
		}
		//2 children.
		else if(current.left != null && current.right != null)
		{
			Node<T> next = getNodeToReplace(current);
			System.out.println(next.data);
			
			if(current == root)
			{
				root = next;
			}
			else if(isLeft)
				parent.left = next;
			else
				parent.right = next;
			
			next.left = current.left;
		}
		return true;
	}
	
	/**
	 * Method is used in delete method and finds the node that's going to replace the deleted.
	 * @param nodeToDelete... is the current node found on delete.
	 * @return
	 */
	public Node<T> getNodeToReplace(Node<T> nodeToDelete)
	{
		Node<T> next = null;
		Node<T> parent = root;
		Node<T> current = nodeToDelete.right;
		
		while(current != null)
		{
			parent = next;
			next = current;
			current = current.left;
		}
		
		if(next != nodeToDelete.right)
		{
			parent.left = next.right;
			next.right = nodeToDelete.right;
		}
		
		return next;
	}
	
	/**
	 * Method takes item and traverses BST checking if node is equal to item. 
	 * If item is found returns true, otherwise false.
	 * @param item
	 * @return
	 */
	public boolean find(T item)
	{
		Node<T> current = root;
		while(current != null)
		{
			if(current.data == item)
				return true;
			//item less than current. Goes Left
			else if(item.compareTo(current.data) < 0)
				current = current.left;
			else
				current = current.right;
		}
		return false;
	}
	
	/**
	 * ToString method to call from main method.
	 */
	public String toString()
	{
		StringBuilder string = new StringBuilder();
		return toString(string, this.root).toString();
	}

	/**
	 * ToString method to call recursively and builds a string using Root, Left, Right format.
	 * @param string
	 * @param root
	 * @return
	 */
	private StringBuilder toString(StringBuilder string, Node<T> root)
	{
		string.append("[");
		if (root != null) 
		{
	        string.append("Root:" + root.data);
	        toString(string.append(" , Left:"), root.left);
	        toString(string.append(" , Right:"), root.right);
	    }
		string.append("]");
		return string;
	}
}
