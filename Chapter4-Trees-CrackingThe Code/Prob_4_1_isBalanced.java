package CrackingTheCode;

import java.util.ArrayList;
import java.util.Stack;

import BasicImplementations.BinaryNode;
import BasicImplementations.BinaryTree;

public class Prob_4_1_isBalanced{

	/*
	Implement a function to check if a binary tree is balanced. For the purposes of
	this question, a balanced tree is defined to be a tree such that the heights of the
	two subtrees of any node never differ by more than one
	*/

	public static boolean checkForBalancing(BinaryTree<Object> home) {
		if (home.isEmpty())
			return true;
		else {
			boolean isBalanced = true;
			Object[] temp = home.preOrder();
			for (int i = 0; i < temp.length; i++) {
				int leftLength = -1;
				int rightLength = -1;
				BinaryNode current = home.find(temp[i], home.getRoot());
				// initialize the left trip
				if (current.getLeft() != null) {
					leftLength++;
					leftLength = +getLength(current.getLeft());
				}
				// initialize the right trip
				if (current.getRight() != null) {
					rightLength++;
					rightLength = +getLength(current.getRight());
				}
				// finding the left length of the current node

				if (Math.abs(leftLength - rightLength) > 1) {
					isBalanced = false;
					break;
				}

				else
					isBalanced = true;
			}
			return isBalanced;
		}
	}

	public static int getLength(BinaryNode treeNode) {
		if (treeNode.getLeft() == null && treeNode.getRight() == null)
			return 0;
		else {
			Stack<BinaryNode> container = new Stack<BinaryNode>();
			container.push(treeNode);
			ArrayList<BinaryNode> wasPeekBefore = new ArrayList<BinaryNode>();
			BinaryNode tempPoped = null;
			int length = 0;
			int tempLength = 0;
			while (!container.isEmpty()) {

				if ((container.peek().getLeft() != null || container.peek()
						.getRight() != null)
						&& !wasPeekBefore.contains(container.peek())) {
					if (container.peek().getLeft() != null
							&& container.peek().getLeft() != tempPoped)
						container.push(container.peek().getLeft());
					else
						container.push(container.peek().getRight());
					if (length < tempLength)
						length = tempLength;
					tempLength = 0;
				} else {
					if (container.isEmpty())
						break;
					tempLength++;
					tempPoped = container.pop();
					if (!container.isEmpty()) {
						wasPeekBefore.add(container.peek());
						if (container.peek().getRight() == tempPoped) {
							tempPoped = container.pop();
							tempLength++;
						} else {
							if (container.peek().getRight() != null) {
								container.push(container.peek().getRight());
								if (length < tempLength)
									length = tempLength;
								tempLength = 0;
							}
						}
					}
				}
			}
			if (length < tempLength)
				length = tempLength;
			return length;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinaryTree<Object> trial = new BinaryTree<Object>();
		trial.insert(1, trial.getRoot());
		trial.insert(2, trial.getRoot());
		trial.insert(3, trial.getRoot());
		trial.insert(4, trial.getRoot());
		trial.insert(5, trial.getRoot());
		trial.insert(6, trial.getRoot());
		trial.insert(7, trial.getRoot());
		trial.insert(8, trial.getRoot());
		trial.insert(9, trial.getRoot());
		trial.insert(10, trial.getRoot());

		trial.delete(9, trial.getRoot());
		trial.delete(8, trial.getRoot());
		trial.delete(4, trial.getRoot());

		trial.printPreOrder();
		System.out.println(checkForBalancing(trial));
	}

}