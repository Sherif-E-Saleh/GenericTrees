package BasicImplementations;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<anyType> implements GeneralBinaryTree<anyType> {

	private BinaryNode root;
	private int treeSize;
	private int depth;
	private int sizePerDepth;
	private int countSizePerDepth;
	private ArrayList<Object> TraversalContainer;

	public BinaryTree() {
		root = null;
		treeSize = 0;
		depth = 0;
		sizePerDepth = 0;
		countSizePerDepth = 0;
		TraversalContainer = new ArrayList<Object>();
	}

	@Override
	public void insert(anyType input, BinaryNode root) {
		// TODO Auto-generated method stub
		BinaryNode newNode = new BinaryNode(input);
		if (this.isEmpty()) {
			this.root = newNode;
			this.depth = 1;
			this.countSizePerDepth = 1;
			this.sizePerDepth = 1;
		}

		else {
			int tempSizePerDepth = this.sizePerDepth;
			int tempCount = this.countSizePerDepth;
			insertDeep(newNode, this.getRoot(), tempCount, tempSizePerDepth);
		}
		treeSize++;
		checkDepth();
	}

	private void insertDeep(BinaryNode addedNode, BinaryNode tRoot, int tCount,
			int tSizePerDepth) {
		if (tSizePerDepth == 0) {
			return;
		}
		if (!hasNodes(tRoot)) {
			tRoot.setLeft(addedNode);
		} else {
			if (tRoot.getRight() == null)
				tRoot.setRight(addedNode);
			else {
				if (((double) tCount / (double) tSizePerDepth) < 0.5) {
					insertDeep(addedNode, tRoot.getLeft(), tCount,
							tSizePerDepth / 2);
				} else if ((tCount % (tSizePerDepth)) <= tSizePerDepth
						/ (this.depth))
					insertDeep(addedNode, tRoot.getLeft(),
							(tCount % tSizePerDepth), (tSizePerDepth / 2));
				else
					insertDeep(addedNode, tRoot.getRight(),
							(tCount % tSizePerDepth), (tSizePerDepth / 2));
			}
		}
	}

	private BinaryNode removeLeaf(BinaryNode parent, BinaryNode node) {
		// remove and return the leaf
		if (node.getLeft() == null && node.getRight() == null) {
			if (parent.getLeft() == node)
				parent.setLeft(null);
			else
				parent.setRight(null);
			return node;
		} else if (node.getLeft() != null && node.getRight() == null) {
			parent = node;
			return removeLeaf(node, node.getLeft());
		} else if (node.getLeft() == null && node.getRight() != null) {
			parent = node;
			return removeLeaf(node, node.getRight());
		} else {
			parent = node;
			return removeLeaf(node, node.getLeft());
		}
	}

	private void deleteActually(anyType item, BinaryNode parent, BinaryNode node) {
		// handle the pointers
		// iterate to find the node
		if (!node.getValue().equals(item)) {
			parent = node;
			if (node.getLeft() != null)
				deleteActually(item, node, node.getLeft());
			if (node.getRight() != null)
				deleteActually(item, node, node.getRight());
		}

		else if (node.getValue().equals(item)) {
			// its a root
			// Two Subtrees Linked
			if (node.getLeft() != null && node.getRight() != null) {
				BinaryNode temp = new BinaryNode();
				temp.setValue(removeLeaf(parent, node).getValue());
				temp.setLeft(node.getLeft());
				temp.setRight(node.getRight());
				if (parent != null) {
					if (parent.getLeft() == node)
						parent.setLeft(temp);
					else
						parent.setRight(temp);
				} else {
					this.root = temp;
				}
				this.treeSize--;
			}
			// A left node
			else if (node.getLeft() != null && node.getRight() == null) {
				BinaryNode temp = new BinaryNode();
				temp.setValue(removeLeaf(parent, node).getValue());
				temp.setLeft(node.getLeft());
				if (parent != null) {
					if (parent.getLeft() == node)
						parent.setLeft(temp);
					else
						parent.setRight(temp);
				} else {
					this.root = temp;
				}
				this.treeSize--;
			}
			// A right node
			else if (node.getLeft() == null && node.getRight() != null) {
				BinaryNode temp = new BinaryNode();
				temp.setValue(removeLeaf(parent, node).getValue());
				temp.setRight(node.getRight());
				if (parent != null) {
					if (parent.getLeft() == node)
						parent.setLeft(temp);
					else
						parent.setRight(temp);
				} else {
					this.root = temp;
				}
				this.treeSize--;
			}
			// A leaf Node
			else {
				if (parent != null) {
					if (parent.getLeft() == node)
						parent.setLeft(null);
					else
						parent.setRight(null);
				} else {
					this.root = null;
				}
			}
		}
		// iterate to find the node
		if (!node.getValue().equals(item)) {
			parent = node;
			if (node.getLeft() != null)
				deleteActually(item, node, node.getLeft());
			if (node.getRight() != null)
				deleteActually(item, node, node.getRight());
		}
	}

	@Override
	public void delete(anyType item, BinaryNode root) {
		// TODO Auto-generated method stub
		if (this.isEmpty())
			return;
		else {
			BinaryNode parent = null;
			deleteActually(item, parent, root);
		}

	}

	private void traversePreOrder(BinaryNode treeRoot) {
		if (treeRoot != null) {
			TraversalContainer.add(treeRoot.getValue());
			traversePreOrder(treeRoot.getLeft());
			traversePreOrder(treeRoot.getRight());
		}
	}
	public Object [] preOrder (){
		this.traversePreOrder(this.getRoot());
		Object [] toBeReturned = TraversalContainer.toArray();
		TraversalContainer.clear();
		return toBeReturned;
	}

	private void traverseInOrder(BinaryNode treeRoot) {
		if (treeRoot != null) {
			traverseInOrder(treeRoot.getLeft());
			TraversalContainer.add(treeRoot.getValue());
			traverseInOrder(treeRoot.getRight());
		}
	}
	public Object [] inOrder (){
		this.traverseInOrder(this.getRoot());
		Object [] toBeReturned = TraversalContainer.toArray();
		TraversalContainer.clear();
		return toBeReturned;
	}

	private void traversePostOrder(BinaryNode treeRoot) {
		if (treeRoot != null) {
			traversePostOrder(treeRoot.getLeft());
			traversePostOrder(treeRoot.getRight());
			TraversalContainer.add(treeRoot.getValue());
		}
	}
	public Object [] postOrder (){
		this.traversePostOrder(this.getRoot());
		Object [] toBeReturned = TraversalContainer.toArray();
		TraversalContainer.clear();
		return toBeReturned;
	}

	@Override
	public void printInOrder() {
		if (this.isEmpty())
			System.out.println("The Tree is Empty, dude");
		else {
			this.traverseInOrder(this.getRoot());
			Object[] container = TraversalContainer.toArray();
			System.out.print("{");
			for (int i = 0; i < container.length; i++) {
				System.out.print(container[i]);
				if (i + 1 != container.length)
					System.out.print(", ");
			}
			System.out.println("}");
		}
		TraversalContainer.clear();
	}

	@Override
	public void printPreOrder() {
		if (this.isEmpty())
			System.out.println("The Tree is Empty, dude");
		else {
			this.traversePreOrder(this.getRoot());
			Object[] container = TraversalContainer.toArray();
			System.out.print("{");
			for (int i = 0; i < container.length; i++) {
				System.out.print(container[i]);
				if (i + 1 != container.length)
					System.out.print(", ");
			}
			System.out.println("}");
		}
		TraversalContainer.clear();
	}

	@Override
	public void printPostOrder() {
		if (this.isEmpty())
			System.out.println("The Tree is Empty, dude");
		else {
			this.traversePostOrder(this.getRoot());
			Object[] container = TraversalContainer.toArray();
			System.out.print("{");
			for (int i = 0; i < container.length; i++) {
				System.out.print(container[i]);
				if (i + 1 != container.length)
					System.out.print(", ");
			}
			System.out.println("}");
		}
		TraversalContainer.clear();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (this.size() == 0)
			return true;
		else
			return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.treeSize;
	}

	public int depth() {
		if (this.isEmpty())
			return 0;
		else if (this.countSizePerDepth == 0)
			return this.depth - 1;
		else
			return this.depth;
	}

	@Override
	public BinaryNode getRoot() {
		// TODO Auto-generated method stub
		return this.root;
	}

	public Object[] toArray() {
		this.traverseInOrder(this.getRoot());
		Object[] temp = TraversalContainer.toArray();
		TraversalContainer.clear();
		return temp;
	}

	public boolean hasNodes(BinaryNode node) {
		if (node.getLeft() != null || node.getRight() != null)
			return true;
		else
			return false;
	}

	private void checkDepth() {
		if (this.countSizePerDepth < this.sizePerDepth)
			countSizePerDepth++;
		if (this.countSizePerDepth == this.sizePerDepth) {
			this.depth++;
			this.countSizePerDepth = 0;
			this.sizePerDepth = (int) Math.pow((double) 2,
					(double) (this.depth - 1));
		}
	}

	public BinaryNode BreadthFirstSearch(anyType toBeFound) {
		if (this.isEmpty())
			return null;
		else {
			BinaryNode toBeReturned = null;
			Queue<BinaryNode> BFSContainer = new LinkedList<BinaryNode>();
			BFSContainer.add(this.getRoot());
			while (!BFSContainer.isEmpty()) {
				if (BFSContainer.peek().getValue().equals(toBeFound)) {
					toBeReturned = BFSContainer.peek();
					break;
				} else {
					if (hasNodes(BFSContainer.peek())) {
						if (BFSContainer.peek().getLeft() != null)
							BFSContainer.add(BFSContainer.peek().getLeft());
						if (BFSContainer.peek().getRight() != null)
							BFSContainer.add(BFSContainer.peek().getRight());

					}
					BFSContainer.poll();
				}
			}

			return toBeReturned;
		}
	}

	@Override
	public Object[] BreadthFirstTraversal() {
		// TODO Auto-generated method stub
		if (this.isEmpty())
			return null;
		else {
			Queue<BinaryNode> BFSContainer = new LinkedList<BinaryNode>();
			BFSContainer.add(this.getRoot());
			while (!BFSContainer.isEmpty()) {
				if (hasNodes(BFSContainer.peek())) {
					if (BFSContainer.peek().getLeft() != null) {
						BFSContainer.add(BFSContainer.peek().getLeft());
					}
					if (BFSContainer.peek().getRight() != null) {
						BFSContainer.add(BFSContainer.peek().getRight());
					}
				}
				TraversalContainer.add(BFSContainer.poll().getValue());
			}
			Object[] toBeReturned = TraversalContainer.toArray();
			TraversalContainer.clear();
			return toBeReturned;
		}
	}



	@Override
	public boolean contains(anyType key, BinaryNode node2StartWith) {
		// TODO Auto-generated method stub
		boolean found = false;
		if (this.isEmpty())
			return false;
		else {
			if (node2StartWith.getValue().equals((Object) key)){
				found = true;
				return found;
			}
			else {
				if (node2StartWith.getLeft() != null && !found)
					 found = contains(key, node2StartWith.getLeft());
				if (!found && node2StartWith.getRight() != null)
					 found = contains(key, node2StartWith.getRight());
			}

		}
		return found;
	}
	
	public BinaryNode find(Object key, BinaryNode treeRoot) {
		// TODO Auto-generated method stub
		BinaryNode found = null;
		if (this.isEmpty())
			return null;
		else {
			if (treeRoot.getValue().equals((Object) key)){
				found = treeRoot;
				return found;
			}
			else {
				if (treeRoot.getLeft() != null && found == null)
					 found = find(key, treeRoot.getLeft());
				if (found == null && treeRoot.getRight() != null)
					 found = find(key, treeRoot.getRight());
			}

		}
		return found;
	}
	@Override
	public Object[] DepthFirstTraversal() {
		// TODO Auto-generated method stub
		if (this.isEmpty())
			return null;
		else {
			BinaryNode temp = null;
			Stack<BinaryNode> DFSContainer = new Stack<BinaryNode>();
			DFSContainer.push(this.getRoot());
			while (!DFSContainer.isEmpty()) {
				if (hasNodes(DFSContainer.peek())
						&& TraversalContainer.size() != this.size() - 1) {
					if (DFSContainer.peek().getLeft() != null
							&& DFSContainer.peek().getLeft() != temp)
						DFSContainer.push(DFSContainer.peek().getLeft());
					else
						DFSContainer.push(DFSContainer.peek().getRight());
				} else {
					temp = DFSContainer.peek();
					TraversalContainer.add(DFSContainer.pop().getValue());
					if (!(DFSContainer.isEmpty())
							&& DFSContainer.peek().getRight() == temp) {
						temp = DFSContainer.pop();
						TraversalContainer.add(temp.getValue());
					} else if (DFSContainer.isEmpty()) {
						break;
					} else {
						DFSContainer.push(DFSContainer.peek().getRight());
					}
				}
			}
		}
		Object[] toBeReturned = TraversalContainer.toArray();
		TraversalContainer.clear();
		return toBeReturned;
	}

	public BinaryNode depthFirstSearch(anyType key) {
		if (this.isEmpty())
			return null;
		else {
			BinaryNode temp = null;
			Stack<BinaryNode> container = new Stack<BinaryNode>();
			container.push(this.getRoot());
			int i = 0;
			if (container.peek().equals(key))
				temp = container.peek();
			else {
				while (!container.isEmpty()) {
					if (hasNodes(container.peek()) && i != this.size() - 1) {
						if (container.peek().getLeft() != null
								&& container.peek().getLeft() != temp) {
							container.push(container.peek().getLeft());
							if (container.peek().getValue().equals(key)) {
								temp = container.peek();
								break;
							}
						} else {
							container.push(container.peek().getRight());
							if (container.peek().getValue().equals(key)) {
								temp = container.peek();
								break;
							}
						}
					} else {
						temp = container.peek();
						container.pop();
						i++;
						if (!(container.isEmpty())
								&& container.peek().getRight() == temp) {
							temp = container.pop();
							i++;
						} else if (container.isEmpty()) {
							break;
						} else {
							container.push(container.peek().getRight());
							if (container.peek().getValue().equals(key)) {
								temp = container.peek();
								break;
							}
						}
					}
				}
			}
			if (!temp.getValue().equals(key))
				temp = new BinaryNode (null);

			return temp;
		}
	}

}
