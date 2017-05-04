
public class BSTMain 
{
	public static void main(String[] args)
	{
		BST<Integer> tree = new BST<Integer>();
		tree.insert(50);
		tree.insert(17);
		tree.insert(12);
		tree.insert(23);
		tree.insert(19);
		tree.insert(72);
		tree.insert(54);
		tree.insert(76);
		tree.insert(67);
		tree.insert(9);
		System.out.println(tree.find(3));
		System.out.println(tree.find(10));
		System.out.println(tree.find(15));
		System.out.println(tree.toString());
		tree.delete(9);
		tree.insert(8);
		tree.insert(7);
		tree.delete(50); //Deleting root. Gets replaced with 54
		System.out.println(tree.toString());
	}
}