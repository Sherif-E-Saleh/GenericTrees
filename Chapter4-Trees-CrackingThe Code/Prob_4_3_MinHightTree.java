package CrackingTheCode;

import BasicImplementations.BinarySearchTree;

public class Prob_4_3_MinHightTree{
	
	/*
	Given a sorted (increasing order) array with unique integer elements, write an
	algorithm to create a binary search tree with minimal height
	*/

	public static BinarySearchTree<Integer> minHeightInsert(int[] input) {
		int mid = input.length / 2;
		BinarySearchTree<Integer> output = new BinarySearchTree<Integer>();
		output.insert(input[mid], output.getRoot());
		for (int i = 1; i <= mid; i++) {
			output.insert(input[mid - i], output.getRoot());
			if (mid + i < input.length)
				output.insert(input[mid + i], output.getRoot());
		}
		if (input.length % 2 == 0)
			output.insert(input.length - 1, output.getRoot());
		output.printPreOrderTree();
		return output;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		BinarySearchTree<Integer> out = new BinarySearchTree<Integer>();

		out = minHeightInsert(array);
		out.printInOrderTree();
	}

}
