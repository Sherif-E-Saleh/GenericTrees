package CrackingTheCode;

import BasicImplementations.BinaryTree;

public class Prob_4_5_isBST{
	
	/*
	Implement a function to check if a binary tree is a binary search tree.
	*/
	public static boolean isBinST (BinaryTree <Integer> input){
		Object [] temp = input.toArray();
		boolean correct = true;
		for (int i = 0; i < temp.length-1; i++) {
			if((int)temp[i+1]<(int)temp[i]){
				correct = false;
				break;
			}
		}
		return correct;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinaryTree <Integer> trial = new BinaryTree<Integer>();
		
		trial.insert(4, trial.getRoot());
		trial.insert(2, trial.getRoot());
		trial.insert(5, trial.getRoot());						
		trial.insert(1, trial.getRoot());
		trial.insert(3, trial.getRoot());
		
		System.out.println(isBinST(trial));
	}

}
