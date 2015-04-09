package CrackingTheCode;

import BasicImplementations.BinaryNode;
import BasicImplementations.BinaryTree;

public class Prob_4_7_firstCommonAncestor {

	/*
	Design an algorithm and write code to find the first common ancestor of two
	nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE:
	This is not necessarily a binary search tree.
	*/

	public static <anyType> BinaryNode stCommonAncestor(
			BinaryTree<anyType> home, anyType key1, anyType key2) {
		if (home.isEmpty())
			return null;
		else if (home.getRoot().getValue().equals(key1)
				|| home.getRoot().getValue().equals(key2)) {
			return null;
		} else {
			BinaryNode temp = home.getRoot();
			BinaryNode toBeReturned = null;
			boolean found = false;
			if (home.contains(key1, temp) && home.contains(key2, temp)) {
				found = true;
				toBeReturned = temp;
				while (found) {
					toBeReturned = temp;
					if (temp.getLeft() != null) {
						temp = temp.getLeft();
						found = home.contains(key1, temp)
								&& home.contains(key2, temp);
					}
					if (toBeReturned.getRight() != null && !found) {
						temp = toBeReturned.getRight();
						found = home.contains(key1, temp)
								&& home.contains(key2, temp);
					}

				}
				return toBeReturned;
			} else
				return null;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinaryTree<Integer> trial = new BinaryTree<Integer>();

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
		trial.insert(11, trial.getRoot());
		trial.insert(12, trial.getRoot());
		trial.insert(13, trial.getRoot());
		
		System.out.println(stCommonAncestor(trial, 12, 7).getValue());
	}

}
