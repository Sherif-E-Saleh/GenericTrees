package BasicImplementations;

import java.util.ArrayList;

public interface GeneralBinaryTree<anyType> {

	BinaryNode root = null;
	int treeSize = 0;
	ArrayList<Object> TraversalContainer = new ArrayList<Object>();

	void insert(anyType input, BinaryNode root);

	void delete(anyType item, BinaryNode root);

	BinaryNode find(anyType item, BinaryNode root);

	// Traversals
	void printInOrder();

	void printPreOrder();

	void printPostOrder();
	
	public Object [] postOrder ();
	public Object [] preOrder ();
	public Object [] inOrder ();
	
	Object[] BreadthFirstTraversal();

	Object [] DepthFirstTraversal();

	// Searching
	BinaryNode BreadthFirstSearch(anyType toBeFound);

	BinaryNode depthFirstSearch(anyType toBefound);

	// Properties
	boolean isEmpty();

	int depth();

	int size();
	
	boolean hasNodes(BinaryNode node);

	BinaryNode getRoot();
	
	boolean contains(anyType key, BinaryNode node2Startwith);

	Object[] toArray(); // return inOrder Array of Nodes

}
