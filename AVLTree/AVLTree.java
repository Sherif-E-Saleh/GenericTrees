package BasicImplementations;

import java.util.ArrayList;
import java.util.Collection;

public class AVLTree<anyType> extends AVLNode implements
		AVLTreeInterface<anyType> {

	private AVLNode root = null;
	private int size = 0;
	//private AVLNode imbalancedNode = null;
	private ArrayList<Object> TraversalContainer = new ArrayList<Object>();

	@Override
	public void insert(anyType element) {
		// TODO Auto-generated method stub
		if (this.isEmpty())
			this.root = new AVLNode((Object) element);
		else
			insertActually(element, this.getRoot());
		if (!this.isEmpty()){
			this.heightTracking(this.getRoot());
		}
		//imbalancedNode = null;
		this.size++;
	}

	
	private void insertActually(anyType element, AVLNode treeRoot) {
		// TODO Auto-generated method stub
		if (!isGreater(element, treeRoot.getValue())) {
			if (treeRoot.getLeft() == null) {
				treeRoot.setLeftByValue((Object) element);
			} else {
				insertActually(element, treeRoot.getLeft());
			}
		} else {
			if (treeRoot.getRight() == null) {
				treeRoot.setRightByValue((Object) element);
			} else {
				insertActually(element, treeRoot.getRight());
			}
		}
	}

	@Override
	public AVLNode delete(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void singlyRotate(AVLNode imbalNode) {
		// TODO Auto-generated method stub
		// check if the rotation is left or right rotation
		if (imbalNode.getLeftHeight() > imbalNode.getRightHeight()) {
			// left to right single rotation
			AVLNode temp1 = new AVLNode(imbalNode.getValue());
			if (imbalNode.getRight() != null) {
				temp1.setRight(imbalNode.getRight());
			}
			AVLNode temp2 = null;
			if (imbalNode.getLeft() != null)
				temp2 = imbalNode.getLeft();
			imbalNode.setValue(temp2.getValue());
			// K2 has a left subtree
			if (temp2.getLeft() != null) {
				imbalNode.setLeft(temp2.getLeft());
			}
			// K2 has a right subtree
			if (temp2.getRight() != null) {
				temp1.setLeft(temp2.getRight());
				if (temp2.getLeft() == null) {
					imbalNode.setLeft(null);
				}
			}
			imbalNode.setRight(temp1);

		}

		else {// right to left single rotation
			AVLNode temp1 = new AVLNode(imbalNode.getValue());
			if (imbalNode.getLeft() != null) {
				temp1.setLeft(imbalNode.getLeft());
			}
			AVLNode temp2 = null;
			if (imbalNode.getRight() != null)
				temp2 = imbalNode.getRight();
			imbalNode.setValue(temp2.getValue());
			// K2 has a Right subtree
			if (temp2.getRight() != null) {
				imbalNode.setRight(temp2.getRight());
			}
			// K2 has a left subtree
			if (temp2.getLeft() != null) {
				temp1.setRight(temp2.getLeft());
				if (temp2.getRight() == null) {
					imbalNode.setRight(null);
				}
			}
			imbalNode.setLeft(temp1);
		}
		this.heightTracking(imbalNode);
	}

	@Override
	public void doubleRotate(AVLNode imbalNode) {
		// TODO Auto-generated method stub
		// Right Left Double Rotation
		if(imbalNode.getRightHeight() > imbalNode.getLeftHeight()){
			AVLNode temp3 = imbalNode.getRight();			//K3
			AVLNode temp2 = imbalNode.getRight().getLeft();	//K2
			
			AVLNode replacer = new AVLNode (imbalNode.getValue());
			
			
			if(imbalNode.getLeft()!=null)
				replacer.setLeft(imbalNode.getLeft());
			if(temp2.getLeft()!=null)
				replacer.setRight(temp2.getLeft());
			if(temp2.getRight()!=null)
				temp3.setLeft(temp2.getRight());
			else
				temp3.setLeft(null);
			imbalNode.setValue(temp2.getValue());
			imbalNode.setLeft(replacer);
			this.heightTracking(imbalNode);
		}
		// Left to Right Double Rotation
		else{
			
				AVLNode temp1 = imbalNode.getLeft();			//K1
				AVLNode temp2 = imbalNode.getLeft().getRight();	//K2
				
				AVLNode replacer = new AVLNode (imbalNode.getValue()); //K3
				
				
				if(imbalNode.getRight()!=null)
					replacer.setRight(imbalNode.getRight());
				if(temp2.getRight()!=null)
					replacer.setLeft(temp2.getRight());
				if(temp2.getLeft()!=null)
					temp1.setRight(temp2.getLeft());
				else
					temp1.setRight(null);
				imbalNode.setValue(temp2.getValue());
				imbalNode.setRight(replacer);
				this.heightTracking(imbalNode);
		}
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
		return this.size;
	}

	@Override
	public AVLNode getRoot() {
		// TODO Auto-generated method stub
		return this.root;
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

	public void traverseInOrder(AVLNode treeRoot) {
		if (treeRoot != null) {
			traverseInOrder(treeRoot.getLeft());
			TraversalContainer.add(treeRoot.getValue());
			traverseInOrder(treeRoot.getRight());
		}
	}

	private void traversePreOrder(AVLNode treeRoot) {
		if (treeRoot != null) {
			TraversalContainer.add(treeRoot.getValue());
			traversePreOrder(treeRoot.getLeft());
			traversePreOrder(treeRoot.getRight());
		}
	}

	private void traversePostOrder(AVLNode treeRoot) {
		if (treeRoot != null) {
			traversePostOrder(treeRoot.getLeft());
			traversePostOrder(treeRoot.getRight());
			TraversalContainer.add(treeRoot.getValue());
		}
	}

	@Override
	public void printInOrderTree() {
		// TODO Auto-generated method stub
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
	public void printPreOrderTree() {
		// TODO Auto-generated method stub
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
	public void printPostOrderTree() {
		// TODO Auto-generated method stub
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

	private void heightTracking(AVLNode treeRoot) {
		if (treeRoot != null) {
			heightTracking(treeRoot.getLeft());
			heightTracking(treeRoot.getRight());
			this.HeightTrackingActually(treeRoot);
		}
	}
	
	private void HeightTrackingActually(AVLNode node) {
		if (node.getLeft() != null)
			node.setLeftHeight(node.getLeft().getLeftHeight() > node.getLeft()
					.getRightHeight() ? node.getLeft().getLeftHeight() + 1
					: node.getLeft().getRightHeight() + 1);
		else
			node.setLeftHeight(-1);
		if (node.getRight() != null)
			node.setRightHeight(node.getRight().getLeftHeight() > node.getRight()
					.getRightHeight() ? node.getRight().getLeftHeight() + 1
					: node.getRight().getRightHeight() + 1);
		else
			node.setRightHeight(-1);
		
		if(Math.abs(node.getLeftHeight()-node.getRightHeight())>1){
			//Left
			if(node.getLeftHeight() > node.getRightHeight()){
				//Left
				if(node.getLeft().getLeftHeight() > node.getLeft().getRightHeight())
					this.singlyRotate(node);
				//Right
				else
					this.doubleRotate(node);
			}
			//Right
			else{
				//Right
				if(node.getRight().getRightHeight() > node.getRight().getLeftHeight())
					this.singlyRotate(node);
				//Left
				else
					this.doubleRotate(node);
			}
	
		}
	}
	
	public void clear(){
		if (this.isEmpty())
			return;
		else{
			this.size = 0;
			this.root = null;
			this.setLeft(null);
			this.setRight(null);
			
		}
	}
	
	public void delete(anyType element, AVLNode treeRoot) {
		// TODO Auto-generated method stub
		if (this.isEmpty())
			return;
		else {
			// Case 1: deleting the root
			if ((this.getRoot().getValue()).equals(element)) {
				// case (1.1) there is only the root
				if (this.size() == 1) {
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
				this.size--;
			} else {
				AVLNode parent = null;
				AVLNode currentNode = this.getRoot();
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
							else
								parent.setRight(null);
						}
						this.size--;
						if(size > 0)
							this.heightTracking(this.getRoot());
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
	public AVLNode deleteMin(AVLNode node) {
		// TODO Auto-generated method stub
		AVLNode parent = null;
		this.size--;
		return removeMin(parent, node);
	}

	@Override
	public AVLNode deleteMax(AVLNode node) {
		// TODO Auto-generated method stub
		AVLNode parent = null;
		this.size--;
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
	private AVLNode removeMin(AVLNode parent, AVLNode node) {
		if (this.isEmpty()) {
			node = null;
			return node;
		} else if (this.size() == 1) {
			this.clear();
			return node;
		} else {
			if (node.getLeft() == null) {
				// root case
				if (parent == null) {
					node = node.getRight();
					if (node.getLeft() == null) {
						this.root.setRight(node.getRight());
						AVLNode temp = new AVLNode();
						temp.setValue(getRoot().getValue());
						this.root.setValue(node.getValue());
						node.setRight(null);
						return temp;
					} else {
						AVLNode oldVal = new AVLNode(this.getRoot().getValue());
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
	
	private AVLNode removeMax(AVLNode parent, AVLNode node) {
		if (this.isEmpty()) {
			node = null;
			return node;
		} else if (this.size() == 1) {
			this.clear();
			return node;
		} else {
			if (node.getRight() == null) {
				// root case
				if (parent == null) {
					node = node.getLeft();
					if (node.getRight() == null) {
						this.root.setLeft(node.getLeft());
						AVLNode temp = new AVLNode();
						temp.setValue(getRoot().getValue());
						this.root.setValue(node.getValue());
						node.setLeft(null);
						return temp;
					} else {
						AVLNode oldVal = new AVLNode(this.getRoot().getValue());
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

	
	

}
