package BasicImplementations;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 */

/**
 * @author Sherif E. Saleh For the love of my life, my wife Lamiaa
 */
@SuppressWarnings("rawtypes")
public class BinarySearchTree<anyType> extends BinaryNode implements
		BSTree<anyType> {

	private BinaryNode root;
	private int length;
	private ArrayList <Object> TraversalContainer;

	@SuppressWarnings({ "unchecked", "unused" })
	public BinarySearchTree() {
		// TODO Auto-generated constructor stub
		this.root = null;
		this.length = 0;
		this.TraversalContainer = new <Object> ArrayList();
	}

	@Override
	public void insert(anyType node, BinaryNode treeRoot) {
		// TODO Auto-generated method stub
		if (this.isEmpty()) {
			this.root = new BinaryNode(node);
		} else {
			if (!isGreater(node, treeRoot.getValue())) {
				if (treeRoot.getLeft() == null) {
					treeRoot.setLeftByValue(node);
				} else {
					insert(node, treeRoot.getLeft());
				}
			} else {
				if (treeRoot.getRight() == null) {
					treeRoot.setRightByValue(node);
				} else {
					insert(node, treeRoot.getRight());
				}
			}
		}
		this.length++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insertAll(Collection<anyType> list, BinaryNode root) {
		// TODO Auto-generated method stub
		Object[] temp = list.toArray();
		for (int i = 0; i < temp.length; i++) {
			this.insert((anyType) temp[i], this.getRoot());
		}

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.root = null;
		this.length = 0;
		System.gc();
	}

	@Override
	public void delete(anyType element, BinaryNode treeRoot) {
		// TODO Auto-generated method stub
		if (this.isEmpty())
			return;
		else {
			// Case 1: deleting the root
			if ((this.getRoot().getValue()).equals(element)) {
				// case (1.1) there is only the root
				if (this.length() == 1) {
					this.clear();
				}
				// Case (1.2) there is no right subtree
				else if (this.getRoot().getRight() == null) {
					this.root.setValue(removeMax(treeRoot, treeRoot.getLeft())
							.getValue());
				}
				// Case (1.3) there is a right Subtree
				else {
					this.root.setValue(removeMin(treeRoot, treeRoot.getRight())
							.getValue());
				}
				this.length--;
			} else {
				BinaryNode parent = null;
				BinaryNode currentNode = this.getRoot();
				while (currentNode != null) {
					if (currentNode.getValue().equals(element)) {
						// Only Left subtree linked
						if (currentNode.getRight() == null
								&& currentNode.getLeft() != null)
							parent.setLeft(currentNode.getLeft());
						// Only Right Subtree Linked
						else if (currentNode.getRight() != null
								&& currentNode.getLeft() == null)
							parent.setRight(currentNode.getRight());
						// Two Subtrees Linked
						else if (currentNode.getRight() != null
								&& currentNode.getLeft() != null) {
							currentNode.setValue(removeMin(currentNode,
									currentNode.getRight()).getValue());
						}
						// Leaf Node
						else {
							if(parent.getLeft().getValue().equals(currentNode.getValue()))
								parent.setLeft(null);
							if(parent.getRight().getValue().equals(currentNode.getValue()))
								parent.setRight(null);
						}
						this.length--;
						break;
					} else if (!isGreater(element, currentNode.getValue())) {
						parent = currentNode;
						currentNode = currentNode.getLeft();
					} else if (isGreater(element, currentNode.getValue())) {
						parent = currentNode;
						currentNode = currentNode.getRight();
					}

				}
			}
		}
	}

	@Override
	public BinaryNode deleteMin(BinaryNode node) {
		// TODO Auto-generated method stub
		BinaryNode parent = null;
		this.length--;
		return removeMin(parent, node);
	}

	@Override
	public BinaryNode deleteMax(BinaryNode node) {
		// TODO Auto-generated method stub
		BinaryNode parent = null;
		this.length--;
		return removeMax(parent, node);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteAll(Collection<anyType> list) {
		// TODO Auto-generated method stub
		if (this.isEmpty())
			return;
		else {
			Object[] temp = list.toArray();
			for (int i = 0; i < temp.length; i++) {
				this.delete((anyType) temp[i], this.getRoot());
			}
		}
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public void retainAll(Collection <anyType> list) {
		// TODO Auto-generated method stub
		if (this.isEmpty())
			return;
		else {
			Object [] temp1 = this.toArray();
			for (int i = 0; i < temp1.length; i++) {
				if(this.isEmpty())
					break;
				else if (!list.contains(temp1[i]))
					this.delete((anyType) temp1[i], this.getRoot());
			}
		}
	}

	@Override
	public BinaryNode find(Object element) {
		// TODO Auto-generated method stub
		if (this.isEmpty())
			return null;
		else {
			BinaryNode temp = this.getRoot();
			while (temp != null) {
				if (temp.getValue() == element)
					break;
				else if (!isGreater(element, temp.getValue())) {
					temp = temp.getLeft();
				} else if (isGreater(element, temp.getValue())) {
					temp = temp.getRight();
				}
			}
			return temp;
		}
	}

	@Override
	public BinaryNode findMin(BinaryNode rootNode) {
		// TODO Auto-generated method stub
		if (this.isEmpty())
			return null;
		else {
			BinaryNode temp = rootNode;
			while (temp != null) {
				if (temp.getLeft() == null)
					break;
				else
					temp = temp.getLeft();
			}
			return temp;
		}
	}

	@Override
	public BinaryNode findMax(BinaryNode rootNode) {
		// TODO Auto-generated method stub
		if (this.isEmpty())
			return null;
		else {
			BinaryNode temp = rootNode;
			while (temp != null) {
				if (temp.getLeft() == null)
					break;
				else
					temp = temp.getRight();
			}
			return temp;
		}
	}

	@Override
	public boolean contains(Object element) {
		// TODO Auto-generated method stub
		if (this.find(element) == null)
			return false;
		else
			return true;
	}

	@Override
	public boolean containsAll(Collection<anyType> list) {
		// TODO Auto-generated method stub
		Object[] temp = list.toArray();
		boolean result = true;
		for (int i = 0; i < temp.length; i++) {
			if (!this.contains(temp[i])) {
				result = false;
				break;
			}
		}
		return result;
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return this.length;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (this.length == 0)
			return true;
		else
			return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BinarySearchTree <anyType> clone() {
		// TODO Auto-generated method stub+
		this.traversePreOrder(this.getRoot());
		BinarySearchTree <anyType> copy = new BinarySearchTree();
		if(this.isEmpty())
			return copy;
		else{
			Object [] temp = TraversalContainer.toArray();
			for (int i = 0; i < temp.length; i++) {
				copy.insert((anyType)temp[i], copy.getRoot());
			}
			TraversalContainer.clear();
			return copy;
		}
	}

	@Override
	public Object [] toArray() {
		// TODO Auto-generated method stub
		this.traverseInOrder(this.getRoot());
		Object [] temp = TraversalContainer.toArray();
		TraversalContainer.clear();
		return temp;
	}

	
	@Override
	public BinaryNode getRoot() {
		// TODO Auto-generated method stub
		return this.root;
	}

	@Override
	public void balance() {
		// TODO Auto-generated method stub

	}

	// private section
	private BinaryNode removeMin(BinaryNode parent, BinaryNode node) {
		if (this.isEmpty()) {
			node = null;
			return node;
		} else if (this.length() == 1) {
			this.clear();
			return node;
		} else {
			if (node.getLeft() == null) {
				// root case
				if (parent == null) {
					node = node.getRight();
					if (node.getLeft() == null) {
						this.root.setRight(node.getRight());
						BinaryNode temp = new BinaryNode();
						temp.setValue(getRoot().getValue());
						this.root.setValue(node.getValue());
						node.setRight(null);
						return temp;
					} else {
						BinaryNode oldVal = new BinaryNode(this.getRoot().getValue());
						this.root.setValue(removeMin(node, node.getLeft())
								.getValue());
						node = oldVal;
					}
				} else if (node.getRight() != null) {
					parent.setLeft(node.getRight());
					node.setRight(null);
					return node;
				} else if (node.getRight() == null) {
					parent.setLeft(null);
					return node;
				}
			} else {
				parent = node;
				return removeMin(parent, node.getLeft());
			}
		}
		return node;

	}

	private boolean isGreater(Object input1, Object input2) {
		boolean result = false;
		try {
			if (Integer.parseInt(input1.toString()) < Integer.parseInt(input2
					.toString()))
				result = false;

			else
				result = true;
		} catch (NumberFormatException e) {
			boolean equality = false;
			int minLength = Math.min((input1.toString()).length(),
					(input2.toString()).length());
			for (int i = 0; i < minLength; i++) {
				if ((input1.toString()).charAt(i) > (input2.toString())
						.charAt(i)) {
					result = true;
					equality = false;
				} else if ((input1.toString()).charAt(i) < (input2.toString())
						.charAt(i)) {
					result = false;
					equality = false;
				} else if ((input1.toString()).charAt(i) == (input2.toString())
						.charAt(i)) {
					equality = true;
				}
			}
			if (equality) {
				if ((input1.toString()).length() >= (input2.toString())
						.length())
					result = true;
			}
		}
		return result;
	}

	private BinaryNode removeMax(BinaryNode parent, BinaryNode node) {
		if (this.isEmpty()) {
			node = null;
			return node;
		} else if (this.length() == 1) {
			this.clear();
			return node;
		} else {
			if (node.getRight() == null) {
				// root case
				if (parent == null) {
					node = node.getLeft();
					if (node.getRight() == null) {
						this.root.setLeft(node.getLeft());
						BinaryNode temp = new BinaryNode();
						temp.setValue(getRoot().getValue());
						this.root.setValue(node.getValue());
						node.setLeft(null);
						return temp;
					} else {
						BinaryNode oldVal = new BinaryNode(this.getRoot().getValue());
						this.root.setValue(removeMax(node, node.getRight())
								.getValue());
						node = oldVal;
					}
				} else if (node.getLeft() != null) {
					parent.setRight(node.getLeft());
					node.setLeft(null);
					return node;
				} else if (node.getLeft() == null) {
					parent.setRight(null);
					return node;
				}
			} else {
				parent = node;
				return removeMax(parent, node.getRight());
			}
		}
		return node;
	}
		
	
	public void traverseInOrder(BinaryNode treeRoot) {
		if (treeRoot != null){
			traverseInOrder(treeRoot.getLeft());
			TraversalContainer.add(treeRoot.getValue());
			traverseInOrder(treeRoot.getRight());
		}
	}

	private void traversePreOrder(BinaryNode treeRoot) {
		if(treeRoot != null){
			TraversalContainer.add(treeRoot.getValue());
			traversePreOrder(treeRoot.getLeft());
			traversePreOrder(treeRoot.getRight());
		}
	}

	private void traversePostOrder(BinaryNode treeRoot) {
		if(treeRoot != null){
			traversePostOrder(treeRoot.getLeft());
			traversePostOrder(treeRoot.getRight());
			TraversalContainer.add(treeRoot.getValue());
		}
	}

	@Override
	public void printInOrderTree() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			System.out.println("The Tree is Empty, dude");
		else{
			this.traverseInOrder(this.getRoot());
			Object [] container = TraversalContainer.toArray();
			System.out.print("{");
			for (int i = 0; i < container.length; i++) {
				System.out.print(container[i]);
				if(i+1 != container.length)
					System.out.print(", ");
			}
			System.out.println("}");
		}
		TraversalContainer.clear();
	}

	@Override
	public void printPreOrderTree() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			System.out.println("The Tree is Empty, dude");
		else{
			this.traversePreOrder(this.getRoot());
			Object [] container = TraversalContainer.toArray();
			System.out.print("{");
			for (int i = 0; i < container.length; i++) {
				System.out.print(container[i]);
				if(i+1 != container.length)
					System.out.print(", ");
			}
			System.out.println("}");
		}
		TraversalContainer.clear();
	}

	@Override
	public void printPostOrderTree() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
			System.out.println("The Tree is Empty, dude");
		else{
			this.traversePostOrder(this.getRoot());
			Object [] container = TraversalContainer.toArray();
			System.out.print("{");
			for (int i = 0; i < container.length; i++) {
				System.out.print(container[i]);
				if(i+1 != container.length)
					System.out.print(", ");
			}
			System.out.println("}");
		}
		TraversalContainer.clear();
	}

}
